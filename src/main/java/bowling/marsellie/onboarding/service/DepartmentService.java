package bowling.marsellie.onboarding.service;

import bowling.marsellie.onboarding.dto.DepartmentDTO;
import bowling.marsellie.onboarding.entity.Department;
import bowling.marsellie.onboarding.repo.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepo departmentRepo;

    public Department findById(Long departmentId) {
        return departmentRepo.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department was not found by id " + departmentId));
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepo.findAll(Pageable.unpaged())
                .map(DepartmentDTO::of)
                .toList();
    }
}
