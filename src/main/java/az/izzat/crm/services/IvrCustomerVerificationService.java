package az.izzat.crm.services;

import az.izzat.crm.dto.resp.OtpResponse;
import az.izzat.crm.dto.resp.VerificationResponse;

public interface IvrCustomerVerificationService {
    VerificationResponse verifyCustomerViaContNumber(String contractNumber);

    VerificationResponse verifyCustomerViaPhoneNumber(String otpCode);

    OtpResponse sendOtp(String phoneNumber);
}
