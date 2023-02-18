package bowling.marsellie.onboarding.repo;

import bowling.marsellie.onboarding.entity.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<Test, Long> {
}
