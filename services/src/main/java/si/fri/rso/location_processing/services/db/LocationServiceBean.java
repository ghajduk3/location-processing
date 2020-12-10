package si.fri.rso.location_processing.services.db;

import si.fri.location_processing.models.dtos.LocationDto;
import si.fri.location_processing.models.entities.LocationEntity;
import si.fri.location_processing.models.transformers.LocationConverter;
import si.fri.rso.location_processing.services.LocationProcessingServiceBean;
import si.fri.rso.location_processing.services.config.AppProperties;
import si.fri.rso.location_processing.services.dao.LocationDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

    public List<LocationDto> findAll(){
        List<LocationEntity> entities = locationDAO.findAll();
        processBean.processLocation();
        return locationConverter.transformToDTO(entities);

    }

    public LocationDto findById(Integer id){
        return locationConverter.transformToDTO(locationDAO.findById(id));
    }

    public Boolean deleteById(Integer id){
        Boolean resp = locationDAO.deleteById(id);
        return resp;
    }


}
