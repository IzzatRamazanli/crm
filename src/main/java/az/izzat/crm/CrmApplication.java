package az.izzat.crm;

import az.izzat.crm.repository.BillAmountRepository;
import az.izzat.crm.repository.CustomerRepository;
import az.izzat.crm.repository.RestaurantsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class CrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RestaurantsRepository restaurantsRepository,
                                        CustomerRepository customerRepository,
                                        BillAmountRepository billAmountRepository
    ) {
        return args -> {
//            Faker faker = new Faker(Locale.US);
//            Random random = new Random();
//            Customer customer1 = Customer.builder()
//                    .phoneNumber("994515643316")
//                    .fullName("Izzat Ramazanli")
//                    .build();
//            Customer customer2 = Customer.builder()
//                    .fullName(faker.name().name())
//                    .phoneNumber(faker.phoneNumber().cellPhone())
//                    .build();
//            customerRepository.saveAll(List.of(customer1, customer2));
//
//            Restaurants r1 = Restaurants.builder()
//                    .name(faker.commerce().productName())
//                    .owner(faker.name().name())
//                    .contractNumber("123456788")
//                    .billingStatus(BillingStatus.PAYMENT_NEEDED)
//                    .build();
//            RestaurantBillingAmounts br1 = RestaurantBillingAmounts.builder()
//                    .amount(random.nextDouble(4, 9999))
//                    .restaurants(r1)
//                    .build();
//            log.info("Contract number: {}", r1.getContractNumber());
//            billAmountRepository.save(br1);
//            restaurantsRepository.save(r1);


        };
    }

}
