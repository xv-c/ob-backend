package bowling.marsellie.onboarding.entity.test;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Question questionId;

    // QuestionType.TEXT
    private String text;

    // QuestionType.SLIDER
    private Integer slider;

    // QuestionType.ONE
    @ManyToOne
    private QuestionAnswer single;

    // QuestionType.MULTI
    @ManyToMany
    private List<QuestionAnswer> multiple;
}
