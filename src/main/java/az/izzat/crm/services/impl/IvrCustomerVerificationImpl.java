package az.izzat.crm.services.impl;

import java.util.Optional;
import java.util.Random;

import az.izzat.crm.dto.resp.OtpResponse;
import az.izzat.crm.dto.resp.VerificationResponse;
import az.izzat.crm.enums.OperationName;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.enums.ValidationStatus;
import az.izzat.crm.exception.RecordNotFoundException;
import az.izzat.crm.model.CustomerValidationLog;
import az.izzat.crm.model.domain.Otp;
import az.izzat.crm.model.domain.Customer;
import az.izzat.crm.model.domain.Restaurants;
import az.izzat.crm.repository.CustomerRepository;
import az.izzat.crm.repository.OtpRepository;
import az.izzat.crm.repository.RestaurantsRepository;
import az.izzat.crm.repository.ValidationLogRepository;
import az.izzat.crm.services.IvrCustomerVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IvrCustomerVerificationImpl implements IvrCustomerVerificationService {

    private final RestaurantsRepository restaurantsRepository;
    private final ValidationLogRepository validationLogRepository;
    private final CustomerRepository customerRepository;
    private final OtpRepository otpRepository;
    private final Random rnd = new Random();

    @Override
    public VerificationResponse verifyCustomerViaContNumber(String contractNumber) {
        Optional<Restaurants> restaurant =
                restaurantsRepository.findRestaurantsByContractNumberEndingWith(contractNumber);
        if (restaurant.isEmpty()) {
            CustomerValidationLog log = CustomerValidationLog.builder()
                    .contractNumber(contractNumber)
                    .operationName(OperationName.VERIFICATION)
                    .operationStatus(OperationStatus.FAIL)
                    .validationStatus(ValidationStatus.VALIDATION_FAILED)
                    .build();
            validationLogRepository.save(log);
            throw new RecordNotFoundException("Restaurant not found");
        }
        CustomerValidationLog log = CustomerValidationLog.builder()
                .contractNumber(contractNumber)
                .operationName(OperationName.VERIFICATION)
                .operationStatus(OperationStatus.SUCCESS)
                .restaurant(restaurant.get().getId())
                .validationStatus(ValidationStatus.VALIDATION_SUCCESS)
                .build();
        validationLogRepository.save(log);

        return VerificationResponse.builder().id(log.getId()).build();

    }

    @Override
    public VerificationResponse verifyCustomerViaPhoneNumber(String otpCode) {
        Optional<Otp> otp = otpRepository.findByOtpCode(otpCode);
        if (otp.isEmpty()) {
            CustomerValidationLog log = CustomerValidationLog.builder()
                    .operationName(OperationName.OTP_VERIFICATION)
                    .operationStatus(OperationStatus.FAIL)
                    .validationStatus(ValidationStatus.VALIDATION_FAILED)
                    .build();
            validationLogRepository.save(log);
        }
        CustomerValidationLog log = CustomerValidationLog.builder()
                .operationName(OperationName.OTP_VERIFICATION)
                .operationStatus(OperationStatus.SUCCESS)
                .validationStatus(ValidationStatus.VALIDATION_SUCCESS)
                .build();
        validationLogRepository.save(log);

        return VerificationResponse.builder().id(log.getId()).build();
    }

    @Override
    public OtpResponse sendOtp(String phoneNumber) {
        Optional<Customer> customer = customerRepository.findByPhoneNumber(phoneNumber);
        if (customer.isEmpty()) {
            CustomerValidationLog log = CustomerValidationLog.builder()
                    .operationName(OperationName.VERIFICATION)
                    .phoneNumber(phoneNumber)
                    .operationStatus(OperationStatus.FAIL)
                    .validationStatus(ValidationStatus.VALIDATION_FAILED)
                    .build();
            validationLogRepository.save(log);
            throw new RecordNotFoundException("Customer not found");
        }
        int otpCode = rnd.nextInt(6, 999999);
        Otp build = Otp.builder().otpCode(String.valueOf(otpCode)).phoneNumber(phoneNumber).build();
        otpRepository.save(build);
        return OtpResponse.builder().otpCode(String.valueOf(otpCode)).build();
    }
}
