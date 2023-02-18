package bowling.marsellie.onboarding.repo;

import bowling.marsellie.onboarding.entity.test.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<Result, Long> {
}
