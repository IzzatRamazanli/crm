package az.izzat.crm.model.domain;

import az.izzat.crm.enums.BillingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "restaurants")
@DynamicInsert
@DynamicUpdate
public class Restaurants implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String owner;
    private String name;
    @Column(name = "CONTRACT_NUMBER", unique = true)
    private String contractNumber;
    private String location;
    private Double billAmount;
    @Enumerated(value = EnumType.STRING)
    private BillingStatus billingStatus;

}
