package bowling.marsellie.onboarding.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResultCreateDTO {
    private Long testId;

    private List<ResultAnswerDTO> answers;

    @Data
    @Builder
    public static class ResultAnswerDTO {
        private Long questionId;

        // QuestionType.TEXT
        private String text;

        // QuestionType.SLIDER
        private Integer slider;

        // QuestionType.ONE
        private Long single;

        // QuestionType.MULTI
        private List<Long> multiple;
    }
}
