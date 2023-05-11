package az.izzat.crm.services;

import az.izzat.crm.dto.VerificationResponse;

public interface IvrCustomerVerification {
    VerificationResponse verifyCustomer(String contractNumber);
}
