package bowling.marsellie.onboarding.service;

import bowling.marsellie.onboarding.dto.AppUserDTO;
import bowling.marsellie.onboarding.dto.AppUserRegistrationDTO;
import bowling.marsellie.onboarding.entity.AppUser;
import bowling.marsellie.onboarding.entity.Department;
import bowling.marsellie.onboarding.repo.DepartmentRepo;
import bowling.marsellie.onboarding.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final DepartmentRepo departmentRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsernameIgnoreCase(username)
                .map(user -> new User(user.getUsername(), user.getPassword(), Collections.singleton(user.getRole())))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found [%s]".formatted(username)));
    }

    @Transactional
    public AppUserDTO register(AppUserRegistrationDTO appUserRegistrationDTO) {
        Department department = departmentRepo.findById(appUserRegistrationDTO.getDepartmentId()).orElseThrow();
        AppUserDTO newUser = createUser(appUserRegistrationDTO, department);
        mailService.sendEmailToBoss(appUserRegistrationDTO, department);
        return newUser;
    }

    private AppUserDTO createUser(AppUserRegistrationDTO appUserRegistrationDTO, Department department) {
        userRepo.findByUsernameIgnoreCase(appUserRegistrationDTO.getUsername())
                .ifPresent(val -> {
                    throw new RuntimeException();
                });
        String encodedPassword = passwordEncoder.encode(appUserRegistrationDTO.getPassword());
        return AppUserDTO.of(userRepo.save(AppUser.builder()
                .username(appUserRegistrationDTO.getUsername())
                .password(encodedPassword)
                .name(appUserRegistrationDTO.getName())
                .lastName(appUserRegistrationDTO.getLastName())
                .department(department)
                .role(appUserRegistrationDTO.getRole())
                .build()));
    }
}
