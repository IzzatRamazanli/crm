package az.izzat.crm.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "restaurants_billing_amount")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantBillingAmounts {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contractNumber", referencedColumnName = "CONTRACT_NUMBER")
    private Restaurants restaurants;
    private Double amount;
}
