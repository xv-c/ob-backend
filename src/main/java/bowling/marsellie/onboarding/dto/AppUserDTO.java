package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.AppUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserDTO {
    private Long id;

    private String username;

    private String name;

    private String lastName;

    private DepartmentDTO department;

    private RoleDTO role;

    public static AppUserDTO of(AppUser appUser) {
        if (appUser == null) {
            return null;
        }

        return AppUserDTO.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .name(appUser.getName())
                .lastName(appUser.getLastName())
                .department(DepartmentDTO.of(appUser.getDepartment()))
                .role(RoleDTO.of(appUser.getRole()))
                .build();
    }
}
