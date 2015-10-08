package com.sot.thing;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.sql.Date;

/**
 * Created by kevin on 11/1/2014.
 *
 * De-dumbassed by kevin on 9/12/2015.
 */
public class Thing {

    double height;
    double length;
    double weight;
    double width;
    double volume;
    double radius;

    // general parameters
    double originalPrice;
    double currentPrice;
    int year;
    Date createDate;
    String imageUrl;
    String name;
    String pluralName;

    String description;
    int nsfw;
    int id;
    // re-think this. It leads to a weird inheritance thing
    double diff;

    public Thing() {}

    /**
     * Just a bean, no logic to be found here.
     *
     * @param id
     * @param height
     * @param length
     * @param width
     * @param weight
     * @param volume
     * @param originalPrice
     * @param currentPrice
     * @param year
     * @param name
     * @param description
     * @param nsfw
     * @param imageUrl
     * @param createdDate
     * @param radius
     * @param diff - sorry about this. being lazy.
     *
     */
    public Thing(int id, double height, double length, double width, double weight, double volume, double originalPrice, double currentPrice, int year, String name, String pluralName, String description, int nsfw, String imageUrl, Date createdDate, double radius, double diff ) {
        this.height = height;
        this.length = length;
        this.weight = weight;
        this.width = width;
        this.volume = volume;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
        this.year = year;
        this.name = name;
        this.pluralName = pluralName;
        this.description = description;
        this.nsfw = nsfw;
        this.id = id;
        this.imageUrl = imageUrl;
        this.createDate = createdDate;
        this.radius = radius;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public double getRadius() {
        return radius;
    }

    @JsonProperty
    public double getOriginalPrice() {
        return originalPrice;
    }

    @JsonProperty
    public double getCurrentPrice() {
        return currentPrice;
    }

    @JsonProperty
    public int getYear() {
        return year;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getPluralName() { return pluralName; }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public int getNsfw() {
        return nsfw;
    }

    @JsonProperty
    public double getHeight() {
        return height;
    }

    @JsonProperty
    public double getLength() {
        return length;
    }

    @JsonProperty
    public double getWeight() {
        return weight;
    }

    @JsonProperty
    public double getWidth() {
        return width;
    }

    @JsonProperty
    public double getVolume() {
        return volume;
    }

    @JsonProperty
    public String getImageUrl() { return imageUrl; }

    @JsonProperty
    public Date getCreateDate() { return createDate; }

    @JsonProperty
    public double getDiff() { return diff; }

    @JsonProperty
    public void setDiff(double diff) { this.diff = diff; }


}
