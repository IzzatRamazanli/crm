package az.izzat.crm.services.impl;

import az.izzat.crm.dto.req.BillPaymentRequest;
import az.izzat.crm.enums.BillingStatus;
import az.izzat.crm.enums.OperationName;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.exception.BillingNegativeException;
import az.izzat.crm.exception.RecordNotFoundException;
import az.izzat.crm.model.CustomerValidationLog;
import az.izzat.crm.model.OperationsLog;
import az.izzat.crm.model.domain.Restaurants;
import az.izzat.crm.repository.OperationsRepository;
import az.izzat.crm.repository.RestaurantsRepository;
import az.izzat.crm.repository.ValidationLogRepository;
import az.izzat.crm.services.IvrBillPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IvrBillPaymentServiceImpl implements IvrBillPaymentService {
    private final ValidationLogRepository validationLogRepository;
    private final OperationsRepository operationsRepository;
    private final RestaurantsRepository restaurantsRepository;

    @Override
    public String billPayment(BillPaymentRequest request) {
        String id = request.getId();
        CustomerValidationLog logData = validationLogRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Log not found")
        );

        Restaurants restaurant = restaurantsRepository.findById(logData.getRestaurant())
                .orElseThrow(() -> new RecordNotFoundException("Restaurant not found"));
        if (!restaurant.getBillingStatus().equals(BillingStatus.FREEZE)) {
            restaurant.setBillAmount(request.getAmount());
            restaurant.setBillingStatus(BillingStatus.POSITIVE);
            restaurantsRepository.save(restaurant);
            OperationsLog log = OperationsLog.builder()
                    .operationName(OperationName.BILL_PAYMENT)
                    .operationStatus(OperationStatus.SUCCESS)
                    .build();
            operationsRepository.save(log);
            return id;
        }
        throw new BillingNegativeException("Bill status is frozen, unfreeze first");
    }
}
