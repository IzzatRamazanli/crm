package az.izzat.crm.repository;

import az.izzat.crm.model.OperationsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationsRepository extends JpaRepository<OperationsLog, String> {
}
