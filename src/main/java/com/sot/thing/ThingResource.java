package com.sot.thing;

/**
 * Created by kevin on 11/1/2014.
 */

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Path("/thing")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ThingResource {
    private final AtomicLong counter;
    private final ThingDAO dao;
    private final int PAGE_SIZE = 25;

    final static Logger logger = LoggerFactory.getLogger(ThingResource.class);

    public ThingResource(ThingDAO dao) {
        this.counter = new AtomicLong();
        this.dao = dao;
    }

    /*
        QUERY METHODS
     */


    @GET
    @Timed
    @Path("/searchByName")
    public List<Thing> searchByName(@QueryParam("n") String name, @QueryParam("s") int start, @QueryParam("r") int rows, @QueryParam("nsfw") int nsfw) {
        if (start <0) { start = 0; }
        if (rows <0) { rows = PAGE_SIZE; }
        if ((name != null) && (!name.contains("%"))) {
            name = "%"+name+"%";
        }

        // to be inclusive, our nsfw search needs to be < our nsfw +1
        nsfw = nsfw <= 0 ? 1 : 2;
        return dao.findThingsByName(name, start, rows, nsfw);
    }

    @GET
    @Timed
    @Path("/list")
    public List<Thing> list(@QueryParam("jtStartIndex") int start, @QueryParam("jtPageSize") int rows,@QueryParam("jtSorting") String sorting, @QueryParam("nsfw") int nsfw) {
        String sortParam = "creation_time";
        String direction = "DESC";
        if (sorting != null && (sorting.contains(" "))) {
            String[] chunks = sorting.split(" ");
            sortParam = chunks[0];
            direction = chunks[1];
        }
        // to be inclusive, our nsfw search needs to be < our nsfw +1
        nsfw = nsfw <= 0 ? 1 : 2;

        return dao.findThings(start,rows,nsfw,sortParam,direction);
    }

    @GET
    @Timed
    @Path("/id/{id}")
    public Thing readById(@PathParam("id") String id) {
        counter.incrementAndGet();
        return dao.findThingById(Integer.valueOf(id));
    }

    @POST
    @Timed
    public Response add(@Valid Thing thing) {
        final int id = dao.insert(thing);
        thing.setId(id);
        // error handling!

        return Response.created(UriBuilder.fromResource(ThingResource.class)
                .build(thing.getId(),id)).build();
    }

    @PUT
    @Timed
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/id/{id}")
    public Thing update(@PathParam("id") int id,@Valid Thing thing) {
        thing.setId(id);
        final int response = dao.update(thing);
        // error handling!!

        return thing;
    }

    @DELETE
    @Timed
    @Path("/id/{id}")
    public Response delete(@PathParam("id") int id) {
        final int response = dao.delete(id);
        // error handling!!
        return Response.ok().build();
    }
}