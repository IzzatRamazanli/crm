package az.izzat.crm.services;

import az.izzat.crm.dto.resp.BillDataResponse;

public interface IvrBillDataService {
    BillDataResponse getBillData(String id);
}
