package az.izzat.crm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import az.izzat.crm.dto.resp.ErrorResponse;
import az.izzat.crm.dto.resp.OtpResponse;
import az.izzat.crm.dto.resp.RestResponse;
import az.izzat.crm.dto.resp.VerificationResponse;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.services.IvrCustomerVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
@RequiredArgsConstructor
@Slf4j
@Api(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
        value = "Verification Controller", tags = "Customer Verification")
public class VerificationController {
    private final IvrCustomerVerificationService ivrCustomerVerification;


    @ApiOperation(value = "Customer Verification via Contract Number's last 4 digits",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "No data found", response = ErrorResponse.class)})
    @PostMapping("/contract-number")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<VerificationResponse> verifyCustomerViaContNumber(
            @RequestParam
            @ApiParam(
                    value = "contract number, must be 4 digits",
                    example = "1234567891",
                    required = true)
            String contractNumber) {
        return RestResponse.<VerificationResponse>builder().status(OperationStatus.SUCCESS)
                .data(ivrCustomerVerification.verifyCustomerViaContNumber(contractNumber))
                .build();
    }


    @ApiOperation(value = "Send otp for verification", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "No data found", response = ErrorResponse.class)})
    @PostMapping("/send-otp")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<OtpResponse> sendOtpToPhoneNumber(
            @RequestParam
            @ApiParam(
                    value = "phone number",
                    example = "994515643316",
                    required = true
            )
            String phoneNumber
    ) {
        return RestResponse.<OtpResponse>builder()
                .data(ivrCustomerVerification.sendOtp(phoneNumber))
                .status(OperationStatus.SUCCESS)
                .build();
    }


    @ApiOperation(value = "Otp code verification", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "No data found", response = ErrorResponse.class)})
    @PostMapping("/otp")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<VerificationResponse> verifyOtpCode(@RequestParam
                                                            @ApiParam(value = "otp code", example = "123456",
                                                                    required = true)
                                                            String otpCode) {
        return RestResponse.<VerificationResponse>builder()
                .status(OperationStatus.SUCCESS)
                .data(ivrCustomerVerification.verifyCustomerViaPhoneNumber(otpCode))
                .build();
    }

}
