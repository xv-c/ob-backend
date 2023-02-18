package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.test.Question;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TestCreateDTO {
    private List<QuestionCreateDTO> questions;

    @Data
    @Builder
    public static class QuestionCreateDTO {
        private String title;

        private String hint;

        private Question.QuestionType questionType;

        private List<String> answers;
    }
}
