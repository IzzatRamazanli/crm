package az.izzat.crm.services.impl;

import java.util.Optional;

import az.izzat.crm.dto.VerificationResponse;
import az.izzat.crm.enums.BillingStatus;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.enums.ValidationStatus;
import az.izzat.crm.exception.BillingNegativeException;
import az.izzat.crm.exception.RecordNotFoundException;
import az.izzat.crm.model.CustomerValidationLog;
import az.izzat.crm.model.Restaurants;
import az.izzat.crm.repository.RestaurantsRepository;
import az.izzat.crm.repository.ValidationLogRepository;
import az.izzat.crm.services.IvrCustomerVerification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IvrCustomerVerificationImpl implements IvrCustomerVerification {

    private final RestaurantsRepository restaurantsRepository;
    private final ValidationLogRepository validationLogRepository;

    @Override
    public VerificationResponse verifyCustomer(String contractNumber) {
        Optional<Restaurants> restaurant =
                restaurantsRepository.findRestaurantsByContractNumberEndingWith(contractNumber);
        if (restaurant.isEmpty()) {
            CustomerValidationLog log = CustomerValidationLog.builder()
                    .contractNumber(contractNumber)
                    .operationStatus(OperationStatus.FAIL)
                    .validationStatus(ValidationStatus.VALIDATION_FAILED)
                    .build();
            validationLogRepository.save(log);
            throw new RecordNotFoundException("Restaurant not found");
        }
        BillingStatus billingStatus = restaurant.get().getBillingStatus();
        if (billingStatus.equals(BillingStatus.FREEZE)) {
            CustomerValidationLog log = CustomerValidationLog.builder()
                    .contractNumber(contractNumber)
                    .operationStatus(OperationStatus.FAIL)
                    .validationStatus(ValidationStatus.VALIDATION_FAILED)
                    .build();
            validationLogRepository.save(log);
            throw new BillingNegativeException("Billing is frozen, please unfreeze first ");
        }

        CustomerValidationLog log = CustomerValidationLog.builder()
                .contractNumber(contractNumber)
                .operationStatus(OperationStatus.SUCCESS)
                .validationStatus(ValidationStatus.VALIDATION_SUCCESS)
                .build();
        validationLogRepository.save(log);

        return VerificationResponse.builder().id(log.getId()).build();

    }
}
