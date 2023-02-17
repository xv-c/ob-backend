package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.AppUser;
import bowling.marsellie.onboarding.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class AppUserDTO {
    private Long id;

    private String username;

    private String name;

    private String lastName;

    private DepartmentDTO department;

    private Set<RoleDTO> roles;

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
                .roles(appUser.getRoles().stream()
                        .map(RoleDTO::of)
                        .collect(Collectors.toSet())
                )
                .build();
    }
}
