package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DocumentShortDTO {
    private Long id;
    private String name;

    public DocumentShortDTO(Document document) {
        this.id = document.getId();
        this.name = document.getName();
    }
}
