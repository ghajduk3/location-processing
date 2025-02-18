package si.fri.rso.location_processing.services.dtos.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeGeometry {
    @JsonProperty("location")
    GeocodeLocation geocodeLocation;
    public GeocodeGeometry() {
    }
    public GeocodeLocation getGeocodeLocation() {
        return geocodeLocation;
    }
    public void setGeocodeLocation(GeocodeLocation geocodeLocation) {
        this.geocodeLocation = geocodeLocation;
    }
}