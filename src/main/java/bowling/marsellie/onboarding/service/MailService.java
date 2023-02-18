package bowling.marsellie.onboarding.service;

import bowling.marsellie.onboarding.dto.AppUserRegistrationDTO;
import bowling.marsellie.onboarding.entity.AppUser;
import bowling.marsellie.onboarding.entity.Department;
import bowling.marsellie.onboarding.entity.Role;
import bowling.marsellie.onboarding.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    public static final String SUBJECT = "Зарегестрирован новый сотрудник";
    public static final String EMAIL_FROM = "noreply@rosmolodej.ru";

    private final JavaMailSender mailSender;
    private final UserRepo userRepo;

    public void sendEmailToBoss(AppUserRegistrationDTO appUserRegistrationDTO, Department department) {
        AppUser boss = userRepo.findByDepartmentAndRole(department, Role.DEPARTMENT_HEAD).orElseThrow();
        sendEmail(boss.getEmail(), SUBJECT, createMailTextObject(appUserRegistrationDTO.getUsername()));
    }

    private void sendEmail(String emailToSend, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EMAIL_FROM);
        message.setTo(emailToSend);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    private String createMailTextObject(String username) {
        return String.format("Пользователь %s был зарегестрирован в системе.", username);
    }
}
