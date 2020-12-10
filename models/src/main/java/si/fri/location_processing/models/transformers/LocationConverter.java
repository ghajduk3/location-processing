package si.fri.location_processing.models.transformers;

import si.fri.location_processing.models.dtos.LocationDto;
import si.fri.location_processing.models.entities.LocationEntity;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class LocationConverter extends GenericConverter<LocationEntity, LocationDto>{

    @Override
    public LocationDto transformToDTO(LocationEntity entity){
        return new LocationDto(entity.getId(), entity.getAddress(), entity.getCountry(),entity.getCity(), entity.getStreet(), entity.getLat(), entity.getLon());
    }

    @Override
    public List<LocationDto> transformToDTO(List<LocationEntity> entities){
        List<LocationDto> dtos = new ArrayList<>();

        for(LocationEntity entity : entities){
            dtos.add(transformToDTO(entity));
        }
        return dtos;
    }

    @Override
    public LocationEntity transformToEntity(LocationDto dto){
        LocationEntity entity = new LocationEntity();

        entity.setAddress(dto.getAddress());
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setLat(dto.getLat());
        entity.setLon(dto.getLon());

        return entity;
    }

    @Override
    public List<LocationEntity> transformToEntity(List<LocationDto> dtos){
        List<LocationEntity> entities = new ArrayList<>();
        for(LocationDto dto: dtos){
            entities.add(transformToEntity(dto));
        }
        return entities;
    }


}
