package si.fri.rso.location_processing.api.v1;
import com.kumuluz.ee.discovery.annotations.RegisterService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

@RegisterService(value = "location-processing", ttl = 20, pingInterval = 15, version = "1.0.0", singleton = false)
@ApplicationPath("/v1")
@OpenAPIDefinition(
        info = @Info(title = "Rest API", version = "v1", description = "Location processing rest api endpoints"),
        servers = @Server(url ="http://localhost:8083/v1")
)
public class LocationApplication extends Application{

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("jersey.config.server.provider.classnames",
                "org.glassfish.jersey.media.multipart.MultiPartFeature");
        return props;
    }

}