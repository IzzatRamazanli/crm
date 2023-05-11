package az.izzat.crm.repository;

import az.izzat.crm.model.CustomerValidationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationLogRepository extends JpaRepository<CustomerValidationLog, String> {
}
