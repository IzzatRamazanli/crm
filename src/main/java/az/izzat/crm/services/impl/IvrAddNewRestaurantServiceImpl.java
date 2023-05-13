package az.izzat.crm.services.impl;

import az.izzat.crm.dto.req.NewRestaurantRequest;
import az.izzat.crm.dto.resp.NewRestaurantResponse;
import az.izzat.crm.exception.RecordNotFoundException;
import az.izzat.crm.model.CustomerValidationLog;
import az.izzat.crm.model.domain.RestaurantBillingAmounts;
import az.izzat.crm.model.domain.Restaurants;
import az.izzat.crm.repository.BillAmountRepository;
import az.izzat.crm.repository.RestaurantsRepository;
import az.izzat.crm.repository.ValidationLogRepository;
import az.izzat.crm.services.IvrAddNewRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class IvrAddNewRestaurantServiceImpl implements IvrAddNewRestaurantService {
    private final ValidationLogRepository validationLogRepository;
    private final RestaurantsRepository restaurantsRepository;
    private final BillAmountRepository billAmountRepository;
    private static final Random random = new Random();

    @Override
    public NewRestaurantResponse addNewRestaurant(NewRestaurantRequest newRestaurantRequest) {
        String logId = newRestaurantRequest.getLogId();
        Optional<CustomerValidationLog> logData = validationLogRepository.findById(logId);
        if (logData.isPresent()) {
            int contractNumber = random.nextInt(9, 999999999);
            Restaurants res = Restaurants.builder()
                    .name(newRestaurantRequest.getName())
                    .owner(newRestaurantRequest.getOwner())
                    .contractNumber(String.valueOf(contractNumber))
                    .location(newRestaurantRequest.getLocation())
                    .build();
            double billData = random.nextDouble(4, 9999);
            RestaurantBillingAmounts bres = RestaurantBillingAmounts.builder().
                    restaurants(res)
                    .amount(billData)
                    .build();
            billAmountRepository.save(bres);
            restaurantsRepository.save(res);
            return NewRestaurantResponse.builder()
                    .contractNumber(String.valueOf(contractNumber))
                    .billData(billData)
                    .build();

        }
        throw new RecordNotFoundException("Log not found");
    }
}
