package si.fri.rso.location_processing.services.dtos.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResult {

    public List<GeocodeObject> results;
    public String status;

    public GeocodeResult(){}

    public List<GeocodeObject> getResults() {
        return results;
    }

    public void setResults(List<GeocodeObject> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
