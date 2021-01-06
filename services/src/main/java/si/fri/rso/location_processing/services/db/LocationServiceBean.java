package si.fri.rso.location_processing.services.db;

import si.fri.location_processing.models.dtos.LocationDto;
import si.fri.location_processing.models.entities.LocationEntity;
import si.fri.location_processing.models.transformers.LocationConverter;
import si.fri.rso.location_processing.services.LocationProcessingServiceBean;
import si.fri.rso.location_processing.services.config.AppProperties;
import si.fri.rso.location_processing.services.dao.LocationDAO;
import si.fri.rso.location_processing.services.exceptions.InternalServerException;
import si.fri.rso.location_processing.services.exceptions.InvalidParameterException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RequestScoped
public class LocationServiceBean {

    @Inject
    private LocationDAO locationDAO;

    @Inject
    private LocationConverter locationConverter;

    @Inject
    private AppProperties config;

    @Inject
    private LocationProcessingServiceBean processBean;

    public List<LocationDto> findAll() throws UnsupportedEncodingException {
        List<LocationEntity> entities = locationDAO.findAll();
        return locationConverter.transformToDTO(entities);

    }

    public LocationDto findById(Integer id) throws EntityNotFoundException,InternalServerException {
        return locationConverter.transformToDTO(locationDAO.findById(id));
    }

    public Boolean deleteById(Integer id) throws InternalServerException,EntityNotFoundException, InvalidParameterException {
        Boolean resp = locationDAO.deleteById(id);
        return resp;
    }

    public LocationDto createLocation(LocationDto locationDto){
       return locationConverter.transformToDTO(locationDAO.createNew(locationConverter.transformToEntity(locationDto)));
    }


}
