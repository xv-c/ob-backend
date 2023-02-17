package bowling.marsellie.onboarding.repo;

import bowling.marsellie.onboarding.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
