package az.izzat.crm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BillAmountInfoResponse {
    private String contractNumber;
    private Double billAmount;
}
