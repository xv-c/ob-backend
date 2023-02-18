package bowling.marsellie.onboarding.controller;

import bowling.marsellie.onboarding.Endpoints;
import bowling.marsellie.onboarding.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.FILE)
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/{id}")
    public byte[] getFile(@PathVariable("id") Long id) {
        return fileService.getFile(id);
    }

}
