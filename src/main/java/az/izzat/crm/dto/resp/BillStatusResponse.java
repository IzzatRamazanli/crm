package az.izzat.crm.dto.resp;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import az.izzat.crm.enums.BillingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillStatusResponse {
    private String restaurantName;
    private String description;
    @Enumerated(EnumType.STRING)
    private BillingStatus billStatus;
}
