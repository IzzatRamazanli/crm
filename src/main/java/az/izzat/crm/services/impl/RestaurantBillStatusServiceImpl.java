package az.izzat.crm.services.impl;

import az.izzat.crm.dto.resp.BillStatusResponse;
import az.izzat.crm.enums.BillingStatus;
import az.izzat.crm.enums.OperationName;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.exception.RecordNotFoundException;
import az.izzat.crm.model.CustomerValidationLog;
import az.izzat.crm.model.OperationsLog;
import az.izzat.crm.model.domain.Restaurants;
import az.izzat.crm.repository.OperationsRepository;
import az.izzat.crm.repository.RestaurantsRepository;
import az.izzat.crm.repository.ValidationLogRepository;
import az.izzat.crm.services.RestaurantBillStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantBillStatusServiceImpl implements RestaurantBillStatusService {
    private final OperationsRepository operationsRepository;
    private final RestaurantsRepository restaurantsRepository;
    private final ValidationLogRepository validationLogRepository;

    @Override
    public BillStatusResponse freezeBillStatus(String logId) {
        CustomerValidationLog logData = validationLogRepository.findById(logId)
                .orElseThrow(() -> new RecordNotFoundException("Log not found"));

        Restaurants rest = getRestaurants(logData.getContractNumber());
        if (rest.getBillingStatus().equals(BillingStatus.FROZEN)) {
            return BillStatusResponse.builder()
                    .restaurantName(rest.getName())
                    .description("Restaurant already frozen")
                    .build();
        }
        rest.setBillingStatus(BillingStatus.FROZEN);
        restaurantsRepository.save(rest);
        OperationsLog log = OperationsLog.builder()
                .operationStatus(OperationStatus.SUCCESS)
                .operationName(OperationName.BILL_DATA)
                .insertDate(LocalDateTime.now())
                .build();
        operationsRepository.save(log);
        return BillStatusResponse.builder()
                .billStatus(BillingStatus.FROZEN)
                .restaurantName(rest.getName())
                .build();
    }

    @Override
    public BillStatusResponse unfreezeBillStatus(String logId) {
        CustomerValidationLog logData = validationLogRepository.findById(logId)
                .orElseThrow(() -> new RecordNotFoundException("Log not found"));

        Restaurants rest = getRestaurants(logData.getContractNumber());
        if (!rest.getBillingStatus().equals(BillingStatus.FROZEN)) {
            return BillStatusResponse.builder()
                    .restaurantName(rest.getName())
                    .description("Restaurant not frozen")
                    .build();
        }
        rest.setBillingStatus(BillingStatus.PAYMENT_NEEDED);
        restaurantsRepository.save(rest);
        OperationsLog log = OperationsLog.builder()
                .operationStatus(OperationStatus.SUCCESS)
                .operationName(OperationName.BILL_DATA)
                .insertDate(LocalDateTime.now())
                .build();
        operationsRepository.save(log);
        return BillStatusResponse.builder()
                .billStatus(BillingStatus.PAYMENT_NEEDED)
                .restaurantName(rest.getName())
                .build();
    }

    private Restaurants getRestaurants(String contractNumber) {
        return restaurantsRepository
                .findRestaurantsByContractNumberEndsWith(contractNumber)
                .orElseThrow(() -> new RecordNotFoundException("Restaurant not found"));
    }
}
