package bowling.marsellie.onboarding.service;

import bowling.marsellie.onboarding.dto.ResultCreateDTO;
import bowling.marsellie.onboarding.dto.ResultCreateDTO.ResultAnswerDTO;
import bowling.marsellie.onboarding.dto.TestCreateDTO;
import bowling.marsellie.onboarding.dto.TestDTO;
import bowling.marsellie.onboarding.entity.AppUser;
import bowling.marsellie.onboarding.entity.test.*;
import bowling.marsellie.onboarding.repo.ResultRepo;
import bowling.marsellie.onboarding.repo.TestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepo testRepo;
    private final ResultRepo resultRepo;

    public TestDTO findOne(Long id) {
        return TestDTO.of(testRepo.findById(id)
                .orElseThrow()
        );
    }

    public List<TestDTO> findAllTests() {
        return testRepo.findAll(Pageable.unpaged())
                .map(TestDTO::of)
                .toList();
    }

    public TestDTO createTest(TestCreateDTO testCreateDTO) {
        Test createdTest = testRepo.save(toEntity(testCreateDTO));
        return TestDTO.of(createdTest);
    }

    public Result createResult(AppUser appUser, ResultCreateDTO resultCreateDTO) {
        return resultRepo.save(toEntity(appUser, resultCreateDTO));
    }

    private Test toEntity(TestCreateDTO testCreateDTO) {
        return Test.builder()
                .questions(testCreateDTO.getQuestions().stream()
                        .map(question -> Question.builder()
                                .title(question.getTitle())
                                .hint(question.getHint())
                                .questionType(question.getQuestionType())
                                .answers(question.getAnswers().stream()
                                        .map(answer -> QuestionAnswer.builder()
                                                .title(answer)
                                                .build())
                                        .toList())
                                .build())
                        .toList())
                .build();
    }

    private Result toEntity(AppUser user, ResultCreateDTO resultCreateDTO) {
        Test test = testRepo
                .findById(resultCreateDTO.getTestId())
                .orElseThrow();

        return Result.builder()
                .appUser(user)
                .testId(test)
                .answers(resultCreateDTO.getAnswers().stream()
                        .map(resultAnswerDTO -> toEntity(test, resultAnswerDTO))
                        .toList()
                )
                .build();
    }

    private ResultAnswer toEntity(Test test, ResultAnswerDTO resultAnswerDTO) {
        Question questionById = test.getQuestions().stream()
                .filter(question -> question.getId().equals(resultAnswerDTO.getQuestionId()))
                .findFirst()
                .orElseThrow();

        return ResultAnswer.builder()
                .questionId(questionById)
                .text(resultAnswerDTO.getText())
                .slider(resultAnswerDTO.getSlider())
                .single(Optional
                        .ofNullable(resultAnswerDTO.getSingle())
                        .map(singleId -> questionById.getAnswers().stream()
                                .filter(answer -> answer.getId().equals(singleId))
                                .findFirst()
                                .orElseThrow()
                        )
                        .orElse(null))
                .multiple(Optional
                        .ofNullable(resultAnswerDTO.getMultiple())
                        .map(multipleIds -> multipleIds.stream()
                                .map(multipleId -> questionById.getAnswers().stream()
                                        .filter(answer -> answer.getId().equals(multipleId))
                                        .findFirst()
                                        .orElseThrow())
                                .toList()
                        )
                        .orElse(null))
                .build();
    }
}
