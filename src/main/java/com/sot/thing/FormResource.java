package com.sot.thing;

/**
 * Created by kevin on 11/1/2014.
 *
 * This resource handles all of the JTable and form-relted requests.
 *
 * That way the Thing API is clean.
 */

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Path("/form")
@Produces(MediaType.APPLICATION_JSON)
public class FormResource {
    private final AtomicLong counter;
    private final ThingDAO dao;
    private final int PAGE_SIZE = 25;

    final static Logger logger = LoggerFactory.getLogger(FormResource.class);

    public FormResource(ThingDAO dao) {
        this.counter = new AtomicLong();
        this.dao = dao;
    }

    /*
        QUERY METHODS
     */


    @GET
    @Timed
    @Path("/searchByName")
    public Response searchByName(@QueryParam("n") String name, @QueryParam("s") int start, @QueryParam("r") int rows, @QueryParam("nsfw") int nsfw) {
        if (start <0) { start = 0; }
        if (rows <0) { rows = PAGE_SIZE; }
        if ((name != null) && (!name.contains("%"))) {
            name = "%"+name+"%";
        }
        // to be inclusive, our nsfw search needs to be < our nsfw +1
        nsfw = nsfw <= 0 ? 2 : 2;

        List<Thing> things = dao.findThingsByName(name,start,rows,nsfw);
        Map responseMap = new HashMap();
        responseMap.put("Result","OK");
        responseMap.put("Records",things);
        return Response.status(200).entity(responseMap).build();
    }

    @POST
    @Timed
    @Path("/list")
    public Response list(@QueryParam("jtStartIndex") int start, @QueryParam("jtPageSize") int rows,@QueryParam("jtSorting") String sorting,@QueryParam("nsfw") int nsfw) {
        String sortParam = "creation_time";
        String direction = "DESC";
        if (sorting != null && (sorting.contains(" "))) {
            String[] chunks = sorting.split(" ");
            sortParam = chunks[0];
            direction = chunks[1];
        }
        // to be inclusive, our nsfw search needs to be < our nsfw +1
        nsfw = nsfw <= 0 ? 2: 2;

        List<Thing> things = dao.findThings(start,rows,nsfw,sortParam,direction);
        long recordCount = dao.getRowCount();
        Map responseMap = new HashMap();
        responseMap.put("Result","OK");
        responseMap.put("Records",things);
        responseMap.put("TotalRecordCount",recordCount);
        return Response.status(200).entity(responseMap).build();
    }

    @POST
    @Timed
    @Consumes("application/x-www-form-urlencoded")
    @Path("/delete")
    public Response create(
            @FormParam("id") int id)
    {
        dao.delete(id);
        Map responseMap = new HashMap();
        responseMap.put("Result","OK");
        return Response.status(200).entity(responseMap).build();
    }

    @POST
    @Timed
    @Consumes("application/x-www-form-urlencoded")
    @Path("/create")
    public Response create(
            @FormParam("name") String name,
            @FormParam("pluralName") String pluralName,
            @FormParam("height") double height,
            @FormParam("width") double width,
            @FormParam("length") double length,
            @FormParam("weight") double weight,
            @FormParam("volume") double volume,
            @FormParam("originalPrice") double originalPrice,
            @FormParam("currentPrice") double currentPrice,
            @FormParam("year") int year,
            @FormParam("description") String description,
            @FormParam("nsfw") int nsfw,
            @FormParam("imageUrl") String imageUrl,
            @FormParam("radius") double radius
            ) {

        Thing newThing = new Thing(-1, height, length, width, weight, volume, originalPrice, currentPrice, year, name, pluralName, description, nsfw, imageUrl, null, radius,0);
        final int id = dao.insert(newThing);
        newThing.setId(id);
        // error handling!!
        Map responseMap = new HashMap();
        responseMap.put("Result","OK");
        responseMap.put("Record",newThing);
        return Response.status(200).entity(responseMap).build();
    }

    @POST
    @Timed
    @Consumes("application/x-www-form-urlencoded")
    @Path("/update")
    public Response create(
            @FormParam("id") int id,
            @FormParam("name") String name,
            @FormParam("pluralName") String pluralName,
            @FormParam("height") double height,
            @FormParam("width") double width,
            @FormParam("length") double length,
            @FormParam("weight") double weight,
            @FormParam("volume") double volume,
            @FormParam("originalPrice") double originalPrice,
            @FormParam("currentPrice") double currentPrice,
            @FormParam("year") int year,
            @FormParam("description") String description,
            @FormParam("nsfw") int nsfw,
            @FormParam("imageUrl") String imageUrl,
            @FormParam("radius") double radius
    ) {

        Thing newThing = new Thing(id, height, length, width, weight, volume, originalPrice, currentPrice, year, name, pluralName, description, nsfw, imageUrl, null, radius,0);

        final int update = dao.update(newThing);

        // error handling!!
        Map responseMap = new HashMap();
        responseMap.put("Result","OK");
        responseMap.put("Record",newThing);
        return Response.status(200).entity(responseMap).build();
    }
}