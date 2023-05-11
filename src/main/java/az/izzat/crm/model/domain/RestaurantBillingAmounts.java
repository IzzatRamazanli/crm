package az.izzat.crm.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurants_billing_amount")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantBillingAmounts {
    @Id
    @GeneratedValue
    private String id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contractNumber", referencedColumnName = "contractNumber")
    private Restaurants restaurants;
    private Double amount;
}
