package az.izzat.crm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import az.izzat.crm.dto.RestResponse;
import az.izzat.crm.dto.VerificationResponse;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.services.IvrCustomerVerification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final IvrCustomerVerification ivrCustomerVerification;

    @GetMapping("/contract-number")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<VerificationResponse> verifyCustomerViaContNumber(
            @RequestParam
            @ApiParam(
                    value = "contract number, must be 4 digits",
                    example = "1234567891",
                    required = true)
            String contractNumber) {
        return RestResponse.<VerificationResponse>builder().status(OperationStatus.SUCCESS)
                .data(ivrCustomerVerification.verifyCustomer(contractNumber))
                .build();
    }

}
