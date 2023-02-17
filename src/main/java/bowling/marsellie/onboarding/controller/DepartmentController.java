package bowling.marsellie.onboarding.controller;

import bowling.marsellie.onboarding.Endpoints;
import bowling.marsellie.onboarding.dto.DepartmentDTO;
import bowling.marsellie.onboarding.repo.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Endpoints.DEPARTMENT)
@PreAuthorize("hasRole('HR')")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentRepo departmentRepo;

    @GetMapping
    public List<DepartmentDTO> list() {
        return departmentRepo.findAll(Pageable.unpaged())
                .map(DepartmentDTO::of)
                .toList();
    }
}
