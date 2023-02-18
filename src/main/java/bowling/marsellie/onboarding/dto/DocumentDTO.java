package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DocumentDTO {
    private Long fileId;
    private String name;
    private String description;

    public DocumentDTO(Document document) {
        this.fileId = document.getFile().getId();
        this.name = document.getName();
        this.description = document.getDescription();
    }
}
