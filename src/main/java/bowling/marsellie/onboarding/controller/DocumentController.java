package bowling.marsellie.onboarding.controller;

import bowling.marsellie.onboarding.Endpoints;
import bowling.marsellie.onboarding.dto.DocumentDTO;
import bowling.marsellie.onboarding.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Endpoints.DOCUMENT)
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('HR', 'DEPARTMENT_HEAD')")
    public ResponseEntity<Void> addDocumentToDepartment() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<DocumentDTO>> getAllDocuments(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(documentService.getAllDocuments(user));
    }
}
