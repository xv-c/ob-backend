package bowling.marsellie.onboarding.controller;

import bowling.marsellie.onboarding.dto.AppUserRegistrationDTO;
import bowling.marsellie.onboarding.entity.AppUser;
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
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public User profile(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping("/roles")
    @PreAuthorize("isAuthenticated()")
    public List<AppUser.Role> roles() {
        return Arrays.asList(AppUser.Role.values());
    }

    @PostMapping
    public AppUser register(@RequestBody AppUserRegistrationDTO appUserRegistrationDTO) {
        return userRepo.save(AppUser.builder()
                .username(appUserRegistrationDTO.getUsername())
                .password(passwordEncoder.encode(appUserRegistrationDTO.getPassword()))
                .roles(appUserRegistrationDTO.getRoles())
                .build()
        );
    }
}
