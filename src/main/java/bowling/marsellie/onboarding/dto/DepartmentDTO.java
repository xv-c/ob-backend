package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.Department;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class DepartmentDTO {
    private Long id;

    private String name;

    private Long headId;

    public static DepartmentDTO of(Department department) {
        if (department == null) {
            return null;
        }

        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .headId(Optional
                        .ofNullable(department.getHeadId())
                        .map(Department::getId)
                        .orElse(null))
                .build();
    }
}
