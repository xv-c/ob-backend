package bowling.marsellie.onboarding.service;

import bowling.marsellie.onboarding.dto.DocumentDTO;
import bowling.marsellie.onboarding.dto.DocumentShortDTO;
import bowling.marsellie.onboarding.entity.AppFile;
import bowling.marsellie.onboarding.entity.Department;
import bowling.marsellie.onboarding.entity.Document;
import bowling.marsellie.onboarding.repo.DocumentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final FileService fileService;
    private final DocumentRepo documentRepo;
    private final DepartmentService departmentService;
    private final UserService userService;

    public Document addDocumentToDepartment(byte[] fileBytes, String filename, Long departmentId) {
        AppFile newFile = fileService.createFile(fileBytes, filename);
        Department department = departmentService.findById(departmentId);
        return documentRepo.save(Document.builder()
                .file(newFile)
                .department(department)
                .build());
    }

    public List<DocumentShortDTO> getAllDocuments(User user) {
        return documentRepo.findByDepartment(userService.findByUsername(user).getDepartment())
                .map(DocumentShortDTO::new)
                .stream()
                .toList();
    }

    public DocumentDTO getById(Long id, User user) {
        return documentRepo.findByIdAndDepartment(id, userService.findByUsername(user).getDepartment())
                .map(DocumentDTO::new)
                .orElseThrow();
    }
}
