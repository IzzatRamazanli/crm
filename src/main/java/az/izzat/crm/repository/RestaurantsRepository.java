package az.izzat.crm.repository;

import java.util.Optional;

import az.izzat.crm.model.domain.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, String> {
    Optional<Restaurants> findRestaurantsByContractNumberEndingWith(String contNumber);
}
