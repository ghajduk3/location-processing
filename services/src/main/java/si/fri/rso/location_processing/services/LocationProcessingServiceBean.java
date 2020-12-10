package si.fri.rso.location_processing.services;

import si.fri.location_processing.models.dtos.LocationDto;
import si.fri.location_processing.models.transformers.LocationConverter;
import si.fri.rso.location_processing.services.config.AppProperties;
import si.fri.rso.location_processing.services.dao.LocationDAO;
import si.fri.rso.location_processing.services.db.LocationServiceBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@RequestScoped
public class LocationProcessingServiceBean {
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


    @PostConstruct
    private void init(){
        httpClient = ClientBuilder.newClient();
        baseUrl = "https://atlas.microsoft.com/search/address/json?subscription-key=xgmq28JmflPPIIVrfxsn3hoFroYjKgVuZq4TbjKWZUk&api-version=1.0&language=en-US&query=RistaLekicaI29";
    }

    public void processLocation(){

        String message = httpClient
                    .target(baseUrl)
                    .request()
                    .get(String.class);
        System.out.println("Messss  " + message);

    }

}
