package bowling.marsellie.onboarding.controller;

import bowling.marsellie.onboarding.Endpoints;
import bowling.marsellie.onboarding.dto.AppUserDTO;
import bowling.marsellie.onboarding.dto.AppUserRegistrationDTO;
import bowling.marsellie.onboarding.dto.RoleDTO;
import bowling.marsellie.onboarding.entity.AppUser;
import bowling.marsellie.onboarding.entity.Department;
import bowling.marsellie.onboarding.entity.Role;
import bowling.marsellie.onboarding.repo.DepartmentRepo;
import bowling.marsellie.onboarding.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(Endpoints.USER)
@RequiredArgsConstructor
public class UserController {
    private final UserRepo userRepo;
    private final DepartmentRepo departmentRepo;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public AppUserDTO profile(@AuthenticationPrincipal User user) {
        return AppUserDTO.of(userRepo
                .findByUsernameIgnoreCase(user.getUsername())
                .orElseThrow()
        );
    }

    @GetMapping("/roles")
    public List<RoleDTO> roles() {
        return Arrays.stream(Role.values())
                .map(RoleDTO::of)
                .toList();
    }

    @PostMapping
    public AppUserDTO register(@RequestBody AppUserRegistrationDTO appUserRegistrationDTO) {
        userRepo.findByUsernameIgnoreCase(appUserRegistrationDTO.getUsername())
                .ifPresent(val -> {
                    throw new RuntimeException();
                });

        Department department = departmentRepo
                .findById(appUserRegistrationDTO.getDepartmentId())
                .orElseThrow();
        String encodedPassword = passwordEncoder.encode(appUserRegistrationDTO.getPassword());

        return AppUserDTO.of(userRepo.save(AppUser.builder()
                .username(appUserRegistrationDTO.getUsername())
                .password(encodedPassword)
                .name(appUserRegistrationDTO.getName())
                .lastName(appUserRegistrationDTO.getLastName())
                .department(department)
                .roles(appUserRegistrationDTO.getRoles())
                .build()
        ));
    }
}
