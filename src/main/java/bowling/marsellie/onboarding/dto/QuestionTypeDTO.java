package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.test.Question;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionTypeDTO {
    private String code;

    private String name;

    public static QuestionTypeDTO of(Question.QuestionType questionType) {
        if (questionType == null) {
            return null;
        }

        return QuestionTypeDTO.builder()
                .code(questionType.toString())
                .name(questionType.getName())
                .build();
    }
}