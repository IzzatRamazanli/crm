package az.izzat.crm.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "otp")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Otp {
    @Id
    @GeneratedValue
    private String id;
    private String phoneNumber;
    private String otpCode;
}
