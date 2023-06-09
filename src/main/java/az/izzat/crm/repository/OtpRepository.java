package az.izzat.crm.repository;

import java.util.Optional;

import az.izzat.crm.model.domain.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByOtpCode(String otpCode);
}
