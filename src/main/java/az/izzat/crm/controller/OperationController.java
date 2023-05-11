package az.izzat.crm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import az.izzat.crm.dto.req.BillPaymentRequest;
import az.izzat.crm.dto.resp.BillDataResponse;
import az.izzat.crm.dto.resp.BillStatusResponse;
import az.izzat.crm.dto.resp.RestResponse;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.services.IvrBillDataService;
import az.izzat.crm.services.IvrBillPaymentService;
import az.izzat.crm.services.RestaurantBillStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operations")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
        value = "Operations Controller", tags = "Customer Operations")
@RequiredArgsConstructor
public class OperationController {
    private final IvrBillDataService ivrBillDataService;
    private final IvrBillPaymentService ivrBillPaymentService;
    private final RestaurantBillStatusService restaurantBillStatusService;

    @GetMapping("/bill-data")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<BillDataResponse> getInformationAboutBill(
            @RequestParam
            @ApiParam(value = "log id",
                    required = true,
                    example = "1234567")
            String id
    ) {
        return RestResponse.<BillDataResponse>builder()
                .status(OperationStatus.SUCCESS)
                .data(ivrBillDataService.getBillData(id))
                .build();
    }

    @PostMapping("/payment")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<String> billPayment(
            @RequestBody
            @ApiParam(required = true)
            BillPaymentRequest billPaymentRequest) {
        return RestResponse.<String>builder()
                .data(ivrBillPaymentService.billPayment(billPaymentRequest))
                .status(OperationStatus.SUCCESS)
                .build();
    }

    @PostMapping("/freeze")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<BillStatusResponse> freezeBillStatus(
            @RequestParam
            @ApiParam(value = "contract number, must be 4 digits",
                    required = true,
                    example = "1234")
            String contractNumber) {
        return RestResponse.<BillStatusResponse>builder()
                .status(OperationStatus.SUCCESS)
                .data(restaurantBillStatusService.freezeBillStatus(contractNumber))
                .build();
    }

    @PostMapping("/unfreeze")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<BillStatusResponse> unfreezeBillStatus(
            @RequestParam
            @ApiParam(value = "contract number, must be 4 digits",
                    required = true,
                    example = "1234")
            String contractNumber) {
        return RestResponse.<BillStatusResponse>builder()
                .status(OperationStatus.SUCCESS)
                .data(restaurantBillStatusService.unfreezeBillStatus(contractNumber))
                .build();
    }
}
