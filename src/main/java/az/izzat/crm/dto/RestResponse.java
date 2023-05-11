package az.izzat.crm.dto;

import az.izzat.crm.enums.OperationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestResponse<T> {
    private OperationStatus status;
    private T data;
}
