package az.izzat.crm.model.domain;

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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "restaurants")
@DynamicInsert
@DynamicUpdate
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
