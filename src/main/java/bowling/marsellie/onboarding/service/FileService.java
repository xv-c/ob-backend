package bowling.marsellie.onboarding.service;

import bowling.marsellie.onboarding.entity.AppFile;
import bowling.marsellie.onboarding.repo.FileRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepo fileRepo;

    public AppFile createFile(byte[] file, String filename) {
        String path = getPath(filename);
        storeFile(file, path);
        return fileRepo.save(AppFile.builder()
                .path(path)
                .filename(filename)
                .build());
    }

    @SneakyThrows
    public byte[] getFile(Long id) {
        AppFile file = fileRepo.findById(id).orElseThrow(() -> new RuntimeException("File was not found by id " + id));
        return getClass().getClassLoader().getResourceAsStream(file.getPath()).readAllBytes();
    }

    private String getPath(String filename) {
        return "static/" + filename;
    }

    @SneakyThrows
    private void storeFile(byte[] file, String path) {
        Files.write(Path.of(path), file);
    }
}
