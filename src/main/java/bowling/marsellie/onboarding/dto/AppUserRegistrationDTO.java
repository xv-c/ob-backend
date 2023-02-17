package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.AppUser;
import lombok.Data;

import java.util.Set;

@Data
public class AppUserRegistrationDTO {
    private String username;
    private String password;
    private Set<AppUser.Role> roles;
}
