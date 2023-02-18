package bowling.marsellie.onboarding.repo;

import bowling.marsellie.onboarding.entity.AppFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<AppFile, Long> {
}
