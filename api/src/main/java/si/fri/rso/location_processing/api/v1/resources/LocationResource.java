package si.fri.rso.location_processing.api.v1.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
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

import com.kumuluz.ee.logs.cdi.Log;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.fri.rso.location_processing.services.exceptions.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import si.fri.location_processing.models.dtos.LocationDto;
import si.fri.rso.location_processing.services.LocationProcessingServiceBean;
import si.fri.rso.location_processing.services.db.LocationServiceBean;
import si.fri.rso.location_processing.services.exceptions.InternalServerException;

@Log
@Path("/location")
@RequestScoped
public class LocationResource {

    @Inject
    private LocationServiceBean serviceDBBean;
    @Inject
    private LocationProcessingServiceBean processBean;


    @Operation(summary = "Get all locations", tags = {"Event"},
            description = "Returns a list of all geo-locations",
            responses = {
                    @ApiResponse(
                            description = "Location data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = LocationDto.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Server error",
                            responseCode = "500"
                    )
            }
    )
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocations(){
        List<LocationDto> locations;
        try {
            locations = serviceDBBean.findAll();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).entity(locations).build();
    }


    @Operation(summary = "Get location by id", tags = {"Event"},
            description = "Returns a geo-location for specific id if exists",
            responses = {
                    @ApiResponse(
                            description = "Geo location data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LocationDto.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Entity not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Server error",
                            responseCode = "500"
                    )
            }
    )
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{locationId}")
    public Response getLocation(
            @Parameter(description = "Location ID", required = true)
            @PathParam("locationId") Integer locationId){
        LocationDto location ;
        try{
            location = serviceDBBean.findById(locationId);
        }catch (EntityNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch (InternalServerException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).entity(location).build();
    }


    @Operation(summary = "Delete geo-location", tags = {"Event"},
            description = "Deletes a geo-location for specific id if exists",
            responses = {
                    @ApiResponse(
                            description = "Geo-location successfully deleted",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Entity not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Server error",
                            responseCode = "500"
                    )
            }
    )
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{locationId}")
    public Response deleteLocation(
            @Parameter(description = "Location ID", required = true)
            @PathParam("locationId") Integer locationId){
        Boolean deleted ;
        try {
            deleted = serviceDBBean.deleteById(locationId);
        }catch (InvalidParameterException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }catch (InternalServerException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Address geocoding", tags = {"Event"},
            description = "Returns geocoded information for specific address",
            responses = {
                    @ApiResponse(
                            description = "Geo-location successfully obtained",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Server error",
                            responseCode = "500"
                    )
            }
    )
    @Path("/process")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response processLocation(
            @Parameter(description = "Address to be geocoded", required = true, example = "Ulica Rista Lekica I-29,Bar,Montenegro")
            @QueryParam("address") String address){

        Integer locationId;
        try{
            locationId = processBean.processLocation(address);
        }catch (InternalServerErrorException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).entity(locationId).build();
    }

}
