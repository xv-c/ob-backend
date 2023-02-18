package bowling.marsellie.onboarding.dto;

import bowling.marsellie.onboarding.entity.test.Question;
import bowling.marsellie.onboarding.entity.test.QuestionAnswer;
import bowling.marsellie.onboarding.entity.test.Test;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TestDTO {
    private Long id;

    private List<QuestionDTO> questions;

    public static TestDTO of(Test test) {
        if (test == null) {
            return null;
        }

        return TestDTO.builder()
                .id(test.getId())
                .questions(test.getQuestions().stream()
                        .map(QuestionDTO::of)
                        .toList())
                .build();
    }

    @Data
    @Builder
    public static class QuestionDTO {
        private Long id;

        private String title;

        private String hint;

        private Question.QuestionType questionType;

        private List<QuestionAnswerDTO> answers;

        public static QuestionDTO of(Question question) {
            if (question == null) {
                return null;
            }

            return QuestionDTO.builder()
                    .id(question.getId())
                    .title(question.getTitle())
                    .hint(question.getHint())
                    .questionType(question.getQuestionType())
                    .answers(question.getAnswers().stream()
                            .map(QuestionAnswerDTO::of)
                            .toList())
                    .build();
        }
    }

    @Data
    @Builder
    public static class QuestionAnswerDTO {
        private Long id;

        private Long questionId;

        private String title;

        public static QuestionAnswerDTO of(QuestionAnswer answer) {
            if (answer == null) {
                return null;
            }

            return QuestionAnswerDTO.builder()
                    .id(answer.getId())
                    .title(answer.getTitle())
                    .build();
        }
    }
}
