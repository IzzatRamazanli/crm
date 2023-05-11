package az.izzat.crm.repository;

import java.util.Optional;

import az.izzat.crm.model.domain.RestaurantBillingAmounts;
import az.izzat.crm.model.domain.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillAmountRepository extends JpaRepository<RestaurantBillingAmounts, String> {
    Optional<RestaurantBillingAmounts> findRestaurantBillingAmountsByRestaurants(Restaurants restaurants);
}
