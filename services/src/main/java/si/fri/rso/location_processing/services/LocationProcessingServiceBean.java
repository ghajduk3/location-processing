package si.fri.rso.location_processing.services;

import si.fri.location_processing.models.dtos.LocationDto;
import si.fri.location_processing.models.transformers.LocationConverter;
import si.fri.rso.location_processing.services.config.AppProperties;
import si.fri.rso.location_processing.services.dao.LocationDAO;
import si.fri.rso.location_processing.services.db.LocationServiceBean;
import si.fri.rso.location_processing.services.dtos.geocoding.GeocodeLocation;
import si.fri.rso.location_processing.services.dtos.geocoding.GeocodeObject;
import si.fri.rso.location_processing.services.dtos.geocoding.GeocodeResult;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@RequestScoped
public class LocationProcessingServiceBean {
    private Logger log = Logger.getLogger(LocationProcessingServiceBean.class.getName());

    @Inject
    private LocationDAO locationDAO;

    @Inject
    private LocationConverter locationConverter;

    @Inject
    private LocationServiceBean serviceDBbean;
    @Inject
    private AppProperties config;

    private Client httpClient;

    private String baseUrl;

    private String encodedAddress;


    @PostConstruct
    private void init(){
        httpClient = ClientBuilder.newClient();
        baseUrl = "https://maps.googleapis.com/maps/api/geocode/json?";
    }



    public Integer processLocation(String address) {
        log.info("Geocoding specific address");
        GeocodeResult result;
        try {
            System.out.println(config.getGeocodeKey());
            result = httpClient
                    .target(baseUrl)
                    .queryParam("address", address)
                    .queryParam("key", config.getGeocodeKey())
                    .request(MediaType.APPLICATION_JSON)
                    .get(GeocodeResult.class);
        }catch(WebApplicationException | ProcessingException e){
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return preprocess(result.getResults().get(0),address);
    }

    private Integer preprocess(GeocodeObject geoData,String address){
        GeocodeLocation geoLocation = geoData.getGeometry().getGeocodeLocation();
        String[] addressParts = address.split(",");
//        LocationDto location = new LocationDto(address,addressParts[2],addressParts[1],addressParts[0],Float.parseFloat(geoLocation.getLatitude()),Float.parseFloat(geoLocation.getLongitude()));
        LocationDto location = serviceDBbean.createLocation(new LocationDto(address,addressParts[2],addressParts[1],addressParts[0],Float.parseFloat(geoLocation.getLatitude()),Float.parseFloat(geoLocation.getLongitude())));
        return location.getId();

    }


}
