package az.izzat.crm.mapper;

import az.izzat.crm.dto.RestaurantsDto;
import az.izzat.crm.model.domain.Restaurants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantsMapper {
    RestaurantsMapper INSTANCE = Mappers.getMapper(RestaurantsMapper.class);

    @Mapping(source = "contractNumber", target = "contractNumber")
    RestaurantsDto restaurantsToDto(Restaurants restaurants);
}
