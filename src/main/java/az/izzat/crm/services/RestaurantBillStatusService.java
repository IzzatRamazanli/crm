package az.izzat.crm.services;

import az.izzat.crm.dto.resp.BillStatusResponse;

public interface RestaurantBillStatusService {
    BillStatusResponse freezeBillStatus(String contractNumber);

    BillStatusResponse unfreezeBillStatus(String contractNumber);
}
