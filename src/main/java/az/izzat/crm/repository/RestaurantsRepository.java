package az.izzat.crm.repository;

import az.izzat.crm.model.domain.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, Long> {
    Optional<Restaurants> findRestaurantsByContractNumberEndsWith(@Param("contractNumber") String contractNumber);
}
