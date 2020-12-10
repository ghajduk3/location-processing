package si.fri.rso.location_processing.services.clients;

import si.fri.rso.location_processing.services.config.AppProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

@ApplicationScoped
public class AmazonMapsClient {

    @Inject
    private AppProperties config;


}
