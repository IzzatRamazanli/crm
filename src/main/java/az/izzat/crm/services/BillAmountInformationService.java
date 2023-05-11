package az.izzat.crm.services;

import az.izzat.crm.dto.resp.BillAmountInfoResponse;

public interface BillAmountInformationService {
    BillAmountInfoResponse getInfoAboutBillAmount(String contractNumber);
}
