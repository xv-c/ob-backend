package bowling.marsellie.onboarding.controller;

import bowling.marsellie.onboarding.dto.AppUserCDTO;
import bowling.marsellie.onboarding.entity.AppUser;
import bowling.marsellie.onboarding.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

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

    @PostMapping
    public AppUser register(@RequestBody AppUserCDTO appUserCDTO) {
        return userRepo.save(AppUser.builder()
                .username(appUserCDTO.getUsername())
                .password(passwordEncoder.encode(appUserCDTO.getPassword()))
                .roles(Collections.singleton(AppUser.Role.USER))
                .build()
        );
    }
}
