package si.fri.rso.location_processing.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("app-properties")
public class AppProperties {



    @ConfigValue(value = "geocode-key")
    private String geocodeKey;

    public String getGeocodeKey() {
        return geocodeKey;
    }

    public void setGeocodeKey(String geocodeKey) {
        this.geocodeKey = geocodeKey;
    }

}