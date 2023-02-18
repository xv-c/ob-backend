package bowling.marsellie.onboarding.repo;

import bowling.marsellie.onboarding.entity.AppUser;
import bowling.marsellie.onboarding.entity.Department;
import bowling.marsellie.onboarding.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsernameIgnoreCase(String username);
    Optional<AppUser> findByDepartmentAndRole(Department department, Role role);
}
