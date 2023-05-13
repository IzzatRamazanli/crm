package az.izzat.crm.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewRestaurantRequest {
    private String logId;
    private String name;
    private String owner;
    private String location;
}
