package bowling.marsellie.onboarding.controller;

import bowling.marsellie.onboarding.Endpoints;
import bowling.marsellie.onboarding.dto.AppUserDTO;
import bowling.marsellie.onboarding.dto.AppUserRegistrationDTO;
import bowling.marsellie.onboarding.dto.RoleDTO;
import bowling.marsellie.onboarding.entity.Role;
import bowling.marsellie.onboarding.repo.DepartmentRepo;
import bowling.marsellie.onboarding.repo.UserRepo;
import bowling.marsellie.onboarding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(Endpoints.USER)
@RequiredArgsConstructor
public class UserController {

    private final UserRepo userRepo;
    private final UserService userService;

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
    public ResponseEntity<AppUserDTO> register(@RequestBody AppUserRegistrationDTO appUserRegistrationDTO) {
        return ResponseEntity.ok(userService.register(appUserRegistrationDTO));
    }
}
