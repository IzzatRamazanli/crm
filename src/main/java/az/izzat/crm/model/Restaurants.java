package az.izzat.crm.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import az.izzat.crm.enums.BillingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "restaurants")
public class Restaurants {

    @Id
    @GeneratedValue
    private String id;
    private String owner;
    private String contractNumber;
    private String location;
    private String number;
    private Double billAmount;
    @Enumerated(value = EnumType.STRING)
    private BillingStatus billingStatus;
}
