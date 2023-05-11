package az.izzat.crm.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import az.izzat.crm.enums.BillingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDataResponse {
    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus;
    private Double billAmount;
}
