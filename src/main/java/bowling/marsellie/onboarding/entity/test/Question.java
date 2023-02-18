package bowling.marsellie.onboarding.entity.test;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String hint;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<QuestionAnswer> answers;

    @RequiredArgsConstructor
    @Getter
    public enum QuestionType {
        ONE("Одиночный выбор"),
        MULTI("Множественный выбор"),
        TEXT("Текстовый вопрос"),
        SLIDER("От 1 до 100");

        private final String name;

    }
}
