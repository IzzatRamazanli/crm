package az.izzat.crm.services.impl;

import java.time.LocalDateTime;

import az.izzat.crm.dto.resp.BillDataResponse;
import az.izzat.crm.enums.OperationName;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.exception.RecordNotFoundException;
import az.izzat.crm.model.CustomerValidationLog;
import az.izzat.crm.model.OperationsLog;
import az.izzat.crm.model.domain.Restaurants;
import az.izzat.crm.repository.OperationsRepository;
import az.izzat.crm.repository.RestaurantsRepository;
import az.izzat.crm.repository.ValidationLogRepository;
import az.izzat.crm.services.IvrBillDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IvrBillDataServiceImpl implements IvrBillDataService {

    private final ValidationLogRepository validationLogRepository;
    private final RestaurantsRepository restaurantsRepository;
    private final OperationsRepository operationsRepository;

    @Override
    public BillDataResponse getBillData(String id) {
        CustomerValidationLog logData = validationLogRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Log data not found"));
        Restaurants restaurant = restaurantsRepository.findById(logData.getRestaurant())
                .orElseThrow(() -> new RecordNotFoundException("Restaurant not found"));

        OperationsLog log = OperationsLog.builder()
                .operationName(OperationName.BILL_DATA)
                .operationStatus(OperationStatus.SUCCESS)
                .insertDate(LocalDateTime.now())
                .build();
        operationsRepository.save(log);

        return BillDataResponse.builder()
                .id(id)
                .billAmount(restaurant.getBillAmount())
                .billingStatus(restaurant.getBillingStatus())
                .build();
    }
}
