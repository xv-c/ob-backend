package bowling.marsellie.onboarding.mvc;

import bowling.marsellie.onboarding.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class DocumentUploadController {
    private final DepartmentService departmentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String page(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "uploadDocument";
    }
}
