package az.izzat.crm.services.impl;

import az.izzat.crm.dto.BillDataResponse;
import az.izzat.crm.services.BillDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillDataServiceImpl implements BillDataService {
    @Override
    public BillDataResponse getBillData(String id) {
        return null;
    }
}
