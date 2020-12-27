package si.fri.rso.location_processing.services.clients;

import com.google.maps.GeoApiContext;
import si.fri.rso.location_processing.services.config.AppProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

@ApplicationScoped
public class GoogleMapsClient {

    @Inject
    private AppProperties config;


    @PostConstruct
    public void init(){
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyAvSGseJIVEoPaF22P8ebFINbKn70IVBxo").build();


    }




}
