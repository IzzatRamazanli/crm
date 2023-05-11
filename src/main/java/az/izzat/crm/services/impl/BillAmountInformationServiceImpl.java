package az.izzat.crm.services.impl;

import java.time.LocalDateTime;

import az.izzat.crm.dto.resp.BillAmountInfoResponse;
import az.izzat.crm.enums.OperationName;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.exception.RecordNotFoundException;
import az.izzat.crm.model.OperationsLog;
import az.izzat.crm.model.domain.RestaurantBillingAmounts;
import az.izzat.crm.model.domain.Restaurants;
import az.izzat.crm.repository.BillAmountRepository;
import az.izzat.crm.repository.OperationsRepository;
import az.izzat.crm.repository.RestaurantsRepository;
import az.izzat.crm.services.BillAmountInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillAmountInformationServiceImpl implements BillAmountInformationService {

    private final BillAmountRepository billAmountRepository;
    private final OperationsRepository operationsRepository;
    private final RestaurantsRepository restaurantsRepository;

    @Override
    public BillAmountInfoResponse getInfoAboutBillAmount(String contractNumber) {
        Restaurants restaurant = restaurantsRepository.findRestaurantsByContractNumberEndingWith(contractNumber)
                .orElseThrow(() -> new RecordNotFoundException("Restaurant not found"));
        RestaurantBillingAmounts billData =
                billAmountRepository.findRestaurantBillingAmountsByRestaurants(restaurant)
                        .orElseThrow(() -> new RecordNotFoundException("Bill data not found"));
        OperationsLog log = OperationsLog.builder()
                .operationName(OperationName.BILL_DATA)
                .operationStatus(OperationStatus.SUCCESS)
                .insertDate(LocalDateTime.now())
                .build();
        operationsRepository.save(log);

        return BillAmountInfoResponse.builder()
                .contractNumber(billData.getRestaurants().getContractNumber())
                .billAmount(billData.getAmount())
                .build();
    }
}
