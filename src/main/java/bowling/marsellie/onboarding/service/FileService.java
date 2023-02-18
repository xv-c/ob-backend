package bowling.marsellie.onboarding.service;

import bowling.marsellie.onboarding.entity.AppFile;
import bowling.marsellie.onboarding.repo.FileRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepo fileRepo;

    @SneakyThrows
    public AppFile createFile(MultipartFile file) {
        String path = getPath();
        storeFile(file.getInputStream().readAllBytes(), path);
        return fileRepo.save(AppFile.builder()
                .path(path)
                .filename(file.getName())
                .build());
    }

    @SneakyThrows
    public byte[] getFile(Long id) {
        AppFile file = fileRepo.findById(id).orElseThrow(() -> new RuntimeException("File was not found by id " + id));
        return Files.readAllBytes(Path.of(file.getPath()));
    }

    private String getPath() {
        return "/project/static/" + UUID.randomUUID();
    }

    @SneakyThrows
    private void storeFile(byte[] file, String path) {
        File f = new File(path);
        f.getParentFile().mkdirs();
        f.createNewFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(f)) {
            fileOutputStream.write(file);
        }
    }
}
