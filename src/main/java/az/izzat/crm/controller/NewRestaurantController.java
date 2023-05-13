package az.izzat.crm.controller;

import az.izzat.crm.dto.req.NewRestaurantRequest;
import az.izzat.crm.dto.resp.ErrorResponse;
import az.izzat.crm.dto.resp.NewRestaurantResponse;
import az.izzat.crm.dto.resp.RestResponse;
import az.izzat.crm.enums.OperationStatus;
import az.izzat.crm.services.IvrAddNewRestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/new")
@RequiredArgsConstructor
@Api(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
        value = "New Data Controller", tags = "New Data Controller")
public class NewRestaurantController {
    private final IvrAddNewRestaurantService newRestaurantService;

    @PostMapping("/restaurant")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add new restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "No data found", response = ErrorResponse.class)})
    public RestResponse<NewRestaurantResponse> addNewRestaurant(
            @RequestBody NewRestaurantRequest newRestaurantRequest
    ) {
        return RestResponse.<NewRestaurantResponse>builder()
                .data(newRestaurantService.addNewRestaurant(newRestaurantRequest))
                .status(OperationStatus.SUCCESS)
                .build();
    }

}
