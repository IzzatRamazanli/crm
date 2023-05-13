package az.izzat.crm.repository;

import az.izzat.crm.model.domain.RestaurantBillingAmounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillAmountRepository extends JpaRepository<RestaurantBillingAmounts, Long> {

    @Query(value = "select * from restaurants_billing_amount r where r.contract_number=:contractNumber",
    nativeQuery = true)
    Optional<RestaurantBillingAmounts> findRestaurantBillingAmounts(String contractNumber);
}
