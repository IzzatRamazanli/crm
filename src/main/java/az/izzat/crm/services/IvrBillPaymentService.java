package az.izzat.crm.services;

import az.izzat.crm.dto.req.BillPaymentRequest;
import az.izzat.crm.dto.resp.RestResponse;

public interface IvrBillPaymentService {
    String billPayment(BillPaymentRequest request);
}
