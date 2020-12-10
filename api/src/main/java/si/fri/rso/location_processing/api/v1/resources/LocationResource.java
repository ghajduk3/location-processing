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
    private LocationServiceBean serviceDBBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocations(){
        List<LocationDto> locations = serviceDBBean.findAll();
        return Response.status(200).entity(locations).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{locationId}")
    public Response getLocations(@PathParam("locationId") Integer locationId){
        LocationDto location = serviceDBBean.findById(locationId);
        return Response.status(200).entity(location).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{locationId}")
    public Response deleteLocation(@PathParam("locationId") Integer locationId){
        boolean deleted = serviceDBBean.deleteById(locationId);
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
