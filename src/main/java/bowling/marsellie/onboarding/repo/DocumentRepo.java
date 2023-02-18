package bowling.marsellie.onboarding.repo;

import bowling.marsellie.onboarding.entity.Department;
import bowling.marsellie.onboarding.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepo extends JpaRepository<Document, Long> {

    List<Document> findByDepartment(Department department);
    Optional<Document> findByIdAndDepartment(Long id, Department department);
}
