package bowling.marsellie.onboarding.repo;

import bowling.marsellie.onboarding.entity.AppUser;
import bowling.marsellie.onboarding.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgressRepo extends JpaRepository<Progress, Long> {
    Optional<Progress> findByUser(AppUser user);
}
