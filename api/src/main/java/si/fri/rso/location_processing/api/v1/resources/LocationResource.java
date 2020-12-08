package si.fri.rso.location_processing.api.v1.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;


import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import si.fri.location_processing.models.dtos.LocationDto;
import si.fri.rso.location_processing.services.db.LocationServiceBean;

@Path("/location")
@RequestScoped
public class LocationResource {

    @Inject
    private LocationServiceBean serviceBean;
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getMejnikInfo(){
//
//        return Response
//                .status(200)
//
//                .entity("{\n" +
//                        "    \"clani\": [\"gh8590\"],\n" +
//                        "    \"opis_projekta\": \"Project implements application for collective countryside garbage reporting and cleaning.\",\n" +
//                        "    \"mikrostoritve\": [\"http://20.71.72.191:8080/v1/location/\", \"http://20.73.228.57:8080/v1/event/\",\"http://20.73.239.23:8080/v1/image/\"],\n" +
//                        "    \"github\": [\"https://github.com/ghajduk3/location-processing\", \"https://github.com/ghajduk3/mejnik-event-catalog\",\"https://github.com/ghajduk3/mejnik-image-upload\"],\n" +
//                        "    \"travis\": [\"https://travis-ci.org/github/ghajduk3/location-processing\", \"https://travis-ci.org/github/ghajduk3/mejnik-event-catalog\",\"https://travis-ci.org/github/ghajduk3/mejnik-image-upload\"],\n" +
//                        "    \"dockerhub\": [\"https://hub.docker.com/r/ghajduk3/location-processing/\", \"https://hub.docker.com/r/ghajduk3/mejnik-event-catalog/\",\"https://hub.docker.com/r/ghajduk3/mejnik-image-upload/\"]\n" +
//                        "}")
//                .build();
//    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocations(){
        List<LocationDto> locations = serviceBean.findAll();
        return Response.status(200).entity(locations).build();
    }
}
