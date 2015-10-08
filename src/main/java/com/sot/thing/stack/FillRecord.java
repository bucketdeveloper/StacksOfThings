package com.sot.thing.stack;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sot.thing.Thing;

import java.math.BigDecimal;

/**
 * Created by kevin on 9/25/2015.
 */
public class FillRecord {

    private String thingName;
    private int thingId;
    private String matchName;
    private String matchImageUrl;
    private int matchId;
    private String matchQty;
    private String verb;
    private int nsfw;
    private String thingQty;

    public FillRecord(Fill f) {
        this.thingName = f.getThing().getName();
        this.thingId = f.getThing().getId();
        this.matchName = f.getRandom().getName();
        this.matchImageUrl = f.getRandom().getImageUrl();
        this.matchId = f.getRandom().getId();
        this.matchQty = f.getMatchQty();
        this.verb = f.getVerb();
        this.nsfw = f.getNsfw();
    }

    @JsonProperty
    public String getMatchName() {
        return matchName;
    }

    @JsonProperty
    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    @JsonProperty
    public String getMatchImageUrl() {
        return matchImageUrl;
    }

    @JsonProperty
    public void setMatchImageUrl(String matchImageUrl) {
        this.matchImageUrl = matchImageUrl;
    }

    @JsonProperty
    public int getMatchId() {
        return matchId;
    }

    @JsonProperty
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @JsonProperty
    public String getmatchQty() {
        return matchQty;
    }

    @JsonProperty
    public void setmatchQty(String matchQty) {
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
    public String getThingName() {
        return thingName;
    }

    @JsonProperty
    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    @JsonProperty
    public int getThingId() {
        return thingId;
    }

    @JsonProperty
    public void setThingId(int thingId) {
        this.thingId = thingId;
    }

    @JsonProperty
    public int getNsfw() {
        return nsfw;
    }

    @JsonProperty
    public void setNsfw(int nsfw) {
        this.nsfw = nsfw;
    }
}
