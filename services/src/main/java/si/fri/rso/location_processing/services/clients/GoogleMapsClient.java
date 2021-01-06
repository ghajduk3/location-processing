package si.fri.rso.location_processing.services.clients;

import com.google.maps.GeoApiContext;
import si.fri.rso.location_processing.services.config.AppProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import java.util.logging.Logger;

@ApplicationScoped
public class GoogleMapsClient {
    private Logger log = Logger.getLogger(GoogleMapsClient.class.getName());

    @Inject
    private AppProperties config;


    @PostConstruct
    public void init(){
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyAvSGseJIVEoPaF22P8ebFINbKn70IVBxo").build();
        log.info("Initializing google maps client.");

    }




}
