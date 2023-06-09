package az.izzat.crm.services.impl;

import az.izzat.crm.dto.RestaurantsDto;
import az.izzat.crm.dto.resp.BillAmountInfoResponse;
import az.izzat.crm.enums.OperationName;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.enums.ValidationStatus;
import az.izzat.crm.exception.RecordNotFoundException;
import az.izzat.crm.mapper.RestaurantsMapper;
import az.izzat.crm.model.CustomerValidationLog;
import az.izzat.crm.model.OperationsLog;
import az.izzat.crm.model.domain.RestaurantBillingAmounts;
import az.izzat.crm.model.domain.Restaurants;
import az.izzat.crm.repository.BillAmountRepository;
import az.izzat.crm.repository.OperationsRepository;
import az.izzat.crm.repository.RestaurantsRepository;
import az.izzat.crm.repository.ValidationLogRepository;
import az.izzat.crm.services.BillAmountInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillAmountInformationServiceImpl implements BillAmountInformationService {

    private final BillAmountRepository billAmountRepository;
    private final OperationsRepository operationsRepository;
    private final RestaurantsRepository restaurantsRepository;
    private final ValidationLogRepository validationLogRepository;

    @Override
    public BillAmountInfoResponse getInfoAboutBillAmount(String contractNumber) {
        Restaurants restaurant = restaurantsRepository.findRestaurantsByContractNumberEndsWith(contractNumber)
                .orElseThrow(() -> new RecordNotFoundException("Restaurant not found"));
        RestaurantsDto restaurantsDto = RestaurantsMapper.INSTANCE.restaurantsToDto(restaurant);
        RestaurantBillingAmounts billData =
                billAmountRepository.findRestaurantBillingAmounts(restaurantsDto.getContractNumber())
                        .orElseThrow(() -> new RecordNotFoundException("Bill data not found"));
        OperationsLog log = OperationsLog.builder()
                .operationName(OperationName.BILL_DATA)
                .operationStatus(OperationStatus.SUCCESS)
                .insertDate(LocalDateTime.now())
                .build();
        operationsRepository.save(log);
        CustomerValidationLog vldLog = CustomerValidationLog.builder()
                .contractNumber(contractNumber)
                .restaurant(restaurant.getId())
                .operationStatus(OperationStatus.SUCCESS)
                .validationStatus(ValidationStatus.VALIDATION_SUCCESS)
                .build();
        CustomerValidationLog saved = validationLogRepository.save(vldLog);

        return BillAmountInfoResponse.builder()
                .logId(saved.getId())
                .contractNumber(billData.getRestaurants().getContractNumber())
                .billAmount(Math.floor(billData.getAmount()))
                .build();
    }
}
