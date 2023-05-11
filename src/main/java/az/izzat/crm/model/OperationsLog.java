package az.izzat.crm.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import az.izzat.crm.enums.OperationName;
import az.izzat.crm.enums.OperationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "operations_log")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OperationsLog {
    @GeneratedValue
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private OperationName operationName;
    @Enumerated(EnumType.STRING)
    private OperationStatus operationStatus;
    private LocalDateTime insertDate;

}
