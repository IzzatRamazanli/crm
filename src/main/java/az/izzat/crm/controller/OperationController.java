package az.izzat.crm.controller;

import az.izzat.crm.dto.req.BillPaymentRequest;
import az.izzat.crm.dto.resp.*;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.services.BillAmountInformationService;
import az.izzat.crm.services.IvrBillDataService;
import az.izzat.crm.services.IvrBillPaymentService;
import az.izzat.crm.services.RestaurantBillStatusService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operations")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
        value = "Operations Controller", tags = "Customer Operations")
@RequiredArgsConstructor
public class OperationController {
    private final IvrBillDataService ivrBillDataService;
    private final IvrBillPaymentService ivrBillPaymentService;
    private final RestaurantBillStatusService restaurantBillStatusService;
    private final BillAmountInformationService billAmountInformationService;


    @ApiOperation(value = "Get restaurant billing data", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "No data found", response = ErrorResponse.class)})
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


    @ApiOperation(value = "Payment restaurant bill amount", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "No data found", response = ErrorResponse.class)})
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


    @ApiOperation(value = "Freeze bill status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "No data found", response = ErrorResponse.class)})
    @PostMapping("/freeze")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<BillStatusResponse> freezeBillStatus(
            @RequestParam
            @ApiParam(value = "validation log id",
                    required = true,
                    example = "1234")
            String logId) {
        return RestResponse.<BillStatusResponse>builder()
                .status(OperationStatus.SUCCESS)
                .data(restaurantBillStatusService.freezeBillStatus(logId))
                .build();
    }


    @ApiOperation(value = "Unfreeze bill status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "No data found", response = ErrorResponse.class)})
    @PostMapping("/unfreeze")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<BillStatusResponse> unfreezeBillStatus(
            @RequestParam
            @ApiParam(value = "validation log id",
                    required = true,
                    example = "1234")
            String logId) {
        return RestResponse.<BillStatusResponse>builder()
                .status(OperationStatus.SUCCESS)
                .data(restaurantBillStatusService.unfreezeBillStatus(logId))
                .build();
    }

    @ApiOperation(value = "Learn bill amount", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "No data found", response = ErrorResponse.class)})
    @GetMapping("/bill-amount")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<BillAmountInfoResponse> getBillAmountInfo(
            @RequestParam
            @ApiParam(value = "contract number, must be 4 digits",
                    required = true,
                    example = "1234")
            String contractNumber) {
        return RestResponse.<BillAmountInfoResponse>builder()
                .status(OperationStatus.SUCCESS)
                .data(billAmountInformationService.getInfoAboutBillAmount(contractNumber))
                .build();
    }
}
