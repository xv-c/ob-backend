package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.AppFile;
import bowling.marsellie.onboarding.entity.Document;
import bowling.marsellie.onboarding.entity.test.Test;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
@Getter
public class DocumentDTO {
    private Long id;
    private Long fileId;
    private Long testId;
    private String name;
    private String description;

    public DocumentDTO(Document document) {
        this.id = document.getId();
        this.fileId = Optional
                .ofNullable(document.getFile())
                .map(AppFile::getId)
                .orElse(null);
        this.testId = Optional
                .ofNullable(document.getTest())
                .map(Test::getId)
                .orElse(null);
        this.name = document.getName();
        this.description = document.getDescription();
    }
}
