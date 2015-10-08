package com.sot.thing.stack;

/**
 * Created by kevin on 11/1/2014.
 *
 * This resource handles all of the Stacking / Filling logic
 */

import com.codahale.metrics.annotation.Timed;
import com.sot.thing.Thing;
import com.sot.thing.ThingDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Path("/stack")
@Produces(MediaType.APPLICATION_JSON)
public class StackResource {
    private final AtomicLong counter;
    private final ThingDAO dao;

    final static Logger logger = LoggerFactory.getLogger(StackResource.class);

    public StackResource(ThingDAO dao) {
        this.counter = new AtomicLong();
        this.dao = dao;
    }

    /*
        QUERY METHODS
     */

    private Thing getThing(String verb,int thingId,List<Integer> existingThingIds,int nsfw) {
        // find the thing they are looking for (if "t" param is present), or a random one
        Thing thing = null;
        if (thingId > 0) {
            thing = dao.findThingById(thingId);
        } else {
            switch (verb) {
                case "ATA":
                    // As tall as
                    thing = dao.findRandomThingHeight(existingThingIds, nsfw);
                    break;
                case "AWA":
                    // As wide as
                    thing = dao.findRandomThingWidth(existingThingIds, nsfw);
                    break;
                case "ALA":
                    // As long as
                    thing = dao.findRandomThingLength(existingThingIds, nsfw);
                    break;
                case "AHA":
                    // As heavy as
                    thing = dao.findRandomThingWeight(existingThingIds, nsfw);
                    break;
                case "AVA":
                    // As voluminous as
                    thing = dao.findRandomThingVolume(existingThingIds, nsfw);
                    break;
                case "ACA":
                    // As costly as
                    thing = dao.findRandomThingCost(existingThingIds, nsfw);
                    break;
                case "ARA":
                    // As radius as?
                    thing = dao.findRandomThingRadius(existingThingIds, nsfw);
                    break;
                default:
                    break;

            }

        }
        return thing;
    }


    @GET
    @Timed
    @Path("/fill")
    public Fill fillThing(@QueryParam("x") int thingId, @QueryParam("v") String verb, @QueryParam("q") int thingQty, @QueryParam("nsfw") int nsfw, @QueryParam("m") int matchId, @QueryParam("h") final List<Integer> existingIds) {
        logger.info("Query: thingId:"+thingId+",v:"+verb+", q:"+thingQty+",n"+nsfw+",m:"+matchId+",h:"+existingIds.size());
        // find the thing
        if (thingQty < 1) {
            thingQty = 1;
        }

        existingIds.add(thingId);
        Thing thing = getThing(verb,thingId,existingIds,nsfw);
        int tryLimit = 20;

        // todo - fix this shit. there should be no need for retries
        if (thing == null) {
            for (int i = 0; (i < tryLimit && (thing == null)); i++) {
                thing = getThing(verb,thingId,existingIds,nsfw);
            }
        }

        existingIds.add(thing.getId());
        Thing match = getThing(verb,matchId,existingIds,nsfw);

        if (match == null) {
            for (int i = 0; (i < tryLimit && (match == null)); i++) {
                match = getThing(verb,matchId,existingIds,nsfw);
            }
        }

        FillQty matchQty = null;

        switch(verb) {
            case "ATA":
                // As tall as
                matchQty = TheQuantificator.getFillQty((thing.getHeight()*thingQty), match.getHeight());
                break;
            case "AWA":
                // As wide as
                matchQty = TheQuantificator.getFillQty((thing.getWidth()*thingQty),match.getWidth());
                break;
            case "ALA":
                // As long as
                matchQty = TheQuantificator.getFillQty((thing.getLength()*thingQty),match.getLength());
                break;
            case "AHA":
                // As heavy as
                matchQty = TheQuantificator.getFillQty((thing.getWeight()*thingQty),match.getWeight());
                break;
            case "AVA":
                // As voluminous as
                matchQty = TheQuantificator.getFillQty((thing.getVolume()*thingQty),match.getVolume());
                break;
            case "ACA":
                // As costly as
                matchQty = TheQuantificator.getFillQty((thing.getCurrentPrice()*thingQty),match.getCurrentPrice());
                break;
            case "ARA":
                // As radius as?
                matchQty = TheQuantificator.getFillQty((thing.getRadius()*thingQty),match.getRadius());
                break;
            default:
                break;
        }

        DecimalFormat formatter = new DecimalFormat("#,###");
        Fill fill = new Fill(thing,match,matchQty.getQtyString(), matchQty.getQtyNumeric().doubleValue(),formatter.format(thingQty),verb,nsfw);
        FillRecord record = new FillRecord(fill);
        dao.insertFillRecord(record);
        return fill;
    }


    private static double generateBoundary(boolean up, double value) {
        double offset = value * 0.25;
        if (up) {
            return value + offset;
        } else {
            return value - offset;
        }
    }

}