package si.fri.rso.location_processing.services.dao;


import si.fri.location_processing.models.entities.LocationEntity;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class LocationDAO extends GenericDAO<LocationEntity,Integer>{

    LocationDAO(){
        super(LocationEntity.class);
    }

    @Override
    public List<LocationEntity> findAll(){
        return em.createNamedQuery("Location.findAll",LocationEntity.class).getResultList();
    }

    @Override
    public LocationEntity update(LocationEntity location, Integer id){
        LocationEntity entity = findById(id);

        if(entity == null){
            return null;
        }

        try {
            beginTx();
            location.setId(entity.getId());
            location = em.merge(location);
            commitTx();
        }catch (Exception e) {
            rollbackTx();
        }
        return location;
    }



}
