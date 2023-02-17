package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {
    private String code;

    private String name;

    public static RoleDTO of(Role role) {
        return RoleDTO.builder()
                .code(role.toString())
                .name(role.getName())
                .build();
    }
}
