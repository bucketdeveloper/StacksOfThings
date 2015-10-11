package com.sot.thing.stack;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sot.thing.Thing;

import java.math.BigDecimal;

/**
 * Created by kevin on 9/25/2015.
 */
public class Fill {

    private Thing thing;
    private Thing random;
    private String matchQty;
    private String thingQty;
    private double thingQtyNumeric;
    private double matchQtyNumeric;
    private String verb;
    private int nsfw;

    public Fill(Thing thing, Thing random, String matchQty, double matchQtyNumeric, String thingQty, double thingQtyNumeric, String verb, int nsfw) {
        this.thing = thing;
        this.random = random;
        this.matchQty = matchQty;
        this.matchQtyNumeric = matchQtyNumeric;
        this.thingQty = thingQty;
        this.thingQtyNumeric = thingQtyNumeric;
        this.verb = verb;
        this.nsfw = nsfw;
    }

    @JsonProperty
    public Thing getThing() {
        return thing;
    }

    @JsonProperty
    public void setThing(Thing thing) {
        this.thing = thing;
    }

    @JsonProperty
    public String getMatchQty() {
        return matchQty;
    }

    @JsonProperty
    public void setMatchQty(String matchQty) {
        this.matchQty = matchQty;
    }

    @JsonProperty
    public String getThingQty() {
        return thingQty;
    }

    @JsonProperty
    public void setThingQty(String thingQty) {
        this.thingQty = thingQty;
    }

    @JsonProperty
    public String getVerb() {
        return verb;
    }

    @JsonProperty
    public void setVerb(String verb) {
        this.verb = verb;
    }

    @JsonProperty
    public Thing getRandom() {
        return random;
    }

    @JsonProperty
    public void setRandom(Thing random) {
        this.random = random;
    }

    @JsonProperty
    public int getNsfw() {
        return nsfw;
    }

    @JsonProperty
    public void setNsfw(int nsfw) {
        this.nsfw = nsfw;
    }

    @JsonProperty
    public double getMatchQtyNumeric() { return matchQtyNumeric; }

    @JsonProperty
    public void setMatchQtyNumeric(double matchQtyNumeric) { this.matchQtyNumeric = matchQtyNumeric; }

    @JsonProperty
    public double getThingQtyNumeric() { return thingQtyNumeric; }

    @JsonProperty
    public void setThingQtyNumeric(double thingQtyNumeric) { this.thingQtyNumeric = thingQtyNumeric; }
}
