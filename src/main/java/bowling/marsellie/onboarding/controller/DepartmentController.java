package bowling.marsellie.onboarding.controller;

import bowling.marsellie.onboarding.Endpoints;
import bowling.marsellie.onboarding.dto.DepartmentDTO;
import bowling.marsellie.onboarding.service.DepartmentService;
import lombok.RequiredArgsConstructor;
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
    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDTO> list() {
        return departmentService.getAllDepartments();
    }
}
