package az.izzat.crm.services;

import az.izzat.crm.dto.req.NewRestaurantRequest;
import az.izzat.crm.dto.resp.NewRestaurantResponse;

public interface IvrAddNewRestaurantService {
    NewRestaurantResponse addNewRestaurant(NewRestaurantRequest newRestaurantRequest);
}
