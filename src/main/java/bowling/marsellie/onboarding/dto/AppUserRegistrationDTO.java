package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class AppUserRegistrationDTO {
    private String username;

    private String password;

    private String name;

    private String lastName;

    private String email;

    private Long departmentId;

    private Role role;
}
