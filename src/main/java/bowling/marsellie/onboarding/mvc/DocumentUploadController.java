package bowling.marsellie.onboarding.mvc;

import bowling.marsellie.onboarding.service.DepartmentService;
import bowling.marsellie.onboarding.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class DocumentUploadController {
    private final DepartmentService departmentService;
    private final DocumentService documentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String page(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "uploadDocument";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String handleFileUploaded(@RequestParam("file") MultipartFile file, @RequestParam("department") Long departmentId) {
        documentService.addDocumentToDepartment(file, departmentId);
        return "uploadDocument";
    }
}
