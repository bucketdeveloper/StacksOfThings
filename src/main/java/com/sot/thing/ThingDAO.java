package com.sot.thing;


import com.sot.thing.stack.FillRecord;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.unstable.BindIn;

import java.util.List;

/**
 * Created by kevin on 11/3/2014.
 */
@UseStringTemplate3StatementLocator
@RegisterMapper(ThingMapper.class)
public interface ThingDAO {

    /*
        CRUD methods
     */
    @SqlUpdate("insert into thing (height,length,weight,width,volume,originalPrice,currentPrice,year,name,pluralName,description,nsfw,imageUrl,radius) values (:height,:length,:weight,:width,:volume,:originalPrice,:currentPrice,:year,:name,:pluralName,:description,:nsfw,:imageUrl,:radius)")
    @GetGeneratedKeys
    int insert(@BindBean Thing thing);

    @SqlUpdate("update thing set height=:height, length=:length, weight=:weight, width=:width, volume=:volume, originalPrice=:originalPrice, currentPrice=:currentPrice, year=:year, name=:name,pluralName=:pluralName,description=:description,nsfw=:nsfw,imageUrl=:imageUrl,radius=:radius WHERE id=:id")
    int update(@BindBean Thing thing);

    @SqlUpdate("delete from thing where id = :id")
    int delete(@Bind("id") int id);

    @SqlQuery("select * from thing where id = :id")
    Thing findThingById(@Bind("id") int id);

    @SqlQuery("select SQL_CALC_FOUND_ROWS * from thing where nsfw \\< :nsfw order by <order> <direction> limit :start,:rows")
    List<Thing> findThings(@Bind("start") int start,@Bind("rows") int rows,@Bind("nsfw") int nsfw,@Define("order") String order,@Define("direction") String direction);

    @SqlQuery("select SQL_CALC_FOUND_ROWS * from thing where name like :name and nsfw \\< :nsfw order by creation_time desc LIMIT :start,:rows")
    List<Thing> findThingsByName(@Bind("name") String name, @Bind("start") int start,@Bind("rows") int rows,@Bind("nsfw") int nsfw);

    @SqlQuery("select FOUND_ROWS()")
    long getRowCount();

    /*
        Find a random object to fill
     */
    @SqlQuery("SELECT * FROM thing AS r1 JOIN (SELECT CEIL( () *(SELECT MAX(id) FROM thing)) AS id) AS r2 WHERE r1.id != :id AND r1.nsfw \\<= :nsfw ORDER BY r1.id ASC LIMIT 1")
    Thing findRandomThingNotMe(@Bind("id") int id,@Bind("nsfw") int nsfw);

    @SqlQuery("SELECT * FROM thing AS r1 JOIN (SELECT CEIL(RAND() *(SELECT MAX(id) FROM thing)) AS id) AS r2 WHERE r1.id = r2.id AND r1.id NOT IN (<ids>) AND r1.nsfw \\<= :nsfw AND r1.height>0 ORDER BY r1.id ASC LIMIT 1")
    Thing findRandomThingHeight(@BindIn("ids") List<Integer> id,@Bind("nsfw") int nsfw);

    @SqlQuery("SELECT * FROM thing AS r1 JOIN (SELECT CEIL(RAND() *(SELECT MAX(id) FROM thing)) AS id) AS r2 WHERE r1.id = r2.id AND r1.id NOT IN (<ids>) AND r1.nsfw \\<= :nsfw AND r1.width>0 ORDER BY r1.id ASC LIMIT 1")
    Thing findRandomThingWidth(@BindIn("ids") List<Integer> id,@Bind("nsfw") int nsfw);

    @SqlQuery("SELECT * FROM thing AS r1 JOIN (SELECT CEIL(RAND() *(SELECT MAX(id) FROM thing)) AS id) AS r2 WHERE r1.id = r2.id AND r1.id NOT IN (<ids>) AND r1.nsfw \\<= :nsfw AND r1.weight>0 ORDER BY r1.id ASC LIMIT 1")
    Thing findRandomThingWeight(@BindIn("ids") List<Integer> id,@Bind("nsfw") int nsfw);

    @SqlQuery("SELECT * FROM thing AS r1 JOIN (SELECT CEIL(RAND() *(SELECT MAX(id) FROM thing)) AS id) AS r2 WHERE r1.id = r2.id AND r1.id NOT IN (<ids>) AND r1.nsfw \\<= :nsfw AND r1.length>0 ORDER BY r1.id ASC LIMIT 1")
    Thing findRandomThingLength(@BindIn("ids") List<Integer> id,@Bind("nsfw") int nsfw);

//    @SqlQuery("SELECT * FROM thing WHERE id >= (SELECT FLOOR( MAX(id) * RAND()) FROM thing ) AND id NOT IN (<ids>) AND nsfw \\<= :nsfw AND volume>0 ORDER BY id LIMIT 1")
    @SqlQuery("SELECT * FROM thing AS r1 JOIN (SELECT CEIL(RAND() *(SELECT MAX(id) FROM thing)) AS id) AS r2 WHERE r1.id = r2.id AND r1.id NOT IN (<ids>) AND r1.nsfw \\<= :nsfw AND r1.volume>0 ORDER BY r1.id ASC LIMIT 1")
    Thing findRandomThingVolume(@BindIn("ids") List<Integer> id,@Bind("nsfw") int nsfw);

    @SqlQuery("SELECT * FROM thing AS r1 JOIN (SELECT CEIL(RAND() *(SELECT MAX(id) FROM thing)) AS id) AS r2 WHERE r1.id = r2.id AND r1.id NOT IN (<ids>) AND r1.nsfw \\<= :nsfw AND r1.currentPrice>0 ORDER BY r1.id ASC LIMIT 1")
    Thing findRandomThingCost(@BindIn("ids") List<Integer> id,@Bind("nsfw") int nsfw);

    @SqlQuery("SELECT * FROM thing AS r1 JOIN (SELECT CEIL(RAND() *(SELECT MAX(id) FROM thing)) AS id) AS r2 WHERE r1.id = r2.id AND r1.id NOT IN (<ids>) AND r1.nsfw \\<= :nsfw AND r1.radius>0 ORDER BY r1.id ASC LIMIT 1")
    Thing findRandomThingRadius(@BindIn("ids") List<Integer> id,@Bind("nsfw") int nsfw);
    /*
        Finding the things within double ranges
     */
    @SqlQuery("(SELECT id,name,pluralName,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:height - height) as diff FROM thing WHERE id != :id AND height between :height and :high order by height asc limit 3) UNION (SELECT id,name,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:height - height) as diff FROM thing WHERE id != :id AND height between :low and :height order by height desc limit 3) ORDER BY diff ASC LIMIT 6;")
    List<Thing> findHeightMatch(@Bind("id") int id,@Bind("height") double height,@Bind("low") double low,@Bind("high") double high);

    @SqlQuery("(SELECT id,name,pluralName,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:width - width) as diff FROM thing WHERE id != :id AND width between :width and :high order by width asc limit 3) UNION (SELECT id,name,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:width - width) as diff FROM thing WHERE id != :id AND width between :low and :width order by width desc limit 3) ORDER BY diff ASC LIMIT 6;")
    List<Thing> findWidthMatch(@Bind("id") int id,@Bind("width") double width,@Bind("low") double low,@Bind("high") double high);

    @SqlQuery("(SELECT id,name,pluralName,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:length - length) as diff FROM thing WHERE id != :id AND length between :length and :high order by length asc limit 3) UNION (SELECT id,name,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:length - length) as diff FROM thing WHERE id != :id AND length between :low and :length order by length desc limit 3) ORDER BY diff ASC LIMIT 6;")
    List<Thing> findLengthMatch(@Bind("id") int id,@Bind("length") double length,@Bind("low") double low,@Bind("high") double high);

    @SqlQuery("(SELECT id,name,pluralName,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:weight - weight) as diff FROM thing WHERE id != :id AND weight between :weight and :high order by weight asc limit 3) UNION (SELECT id,name,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:weight - weight) as diff FROM thing WHERE id != :id AND weight between :low and :weight order by weight desc limit 3) ORDER BY diff ASC LIMIT 6;")
    List<Thing> findWeightMatch(@Bind("id") int id,@Bind("weight") double weight,@Bind("low") double low,@Bind("high") double high);

    @SqlQuery("(SELECT id,name,pluralName,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:volume - volume) as diff FROM thing WHERE id != :id AND volume between :volume and :high order by volume asc limit 3) UNION (SELECT id,name,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:volume - volume) as diff FROM thing WHERE id != :id AND volume between :low and :volume order by volume desc limit 3) ORDER BY diff ASC LIMIT 6;")
    List<Thing> findVolumeMatch(@Bind("id") int id,@Bind("volume") double weight,@Bind("low") double low,@Bind("high") double high);

    @SqlQuery("(SELECT id,name,pluralName,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:currentPrice - currentPrice) as diff FROM thing WHERE id != :id AND currentPrice between :currentPrice and :high order by currentPrice asc limit 3) UNION (SELECT id,name,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:currentPrice - currentPrice) as diff FROM thing WHERE id != :id AND currentPrice between :low and :currentPrice order by currentPrice desc limit 3) ORDER BY diff ASC LIMIT 6;")
    List<Thing> findPriceMatch(@Bind("id") int id,@Bind("currentPrice") double currentPrice,@Bind("low") double low,@Bind("high") double high);

    @SqlQuery("(SELECT id,name,pluralName,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:radius - radius) as diff FROM thing WHERE id != :id AND radius between :radius and :high order by radius asc limit 3) UNION (SELECT id,name,description,height,width,length,weight,volume,year,nsfw,originalPrice,currentPrice,creation_time,modification_time,imageUrl,radius,abs(:radius - radius) as diff FROM thing WHERE id != :id AND radius between :low and :radius order by radius desc limit 3) ORDER BY diff ASC LIMIT 6;")
    List<Thing> findRadiusMatch(@Bind("id") int id,@Bind("radius") double radius,@Bind("low") double low,@Bind("high") double high);

    /*
    Insert Stack & Fill record
     */
    @SqlUpdate("insert into fill (thingName,thingId,matchName,matchImageUrl,matchId,matchQty,thingQty,verb,nsfw) values (:thingName,:thingId,:matchName,:matchImageUrl,:matchId,:matchQty,:thingQty,:verb,:nsfw)")
    @GetGeneratedKeys
    int insertFillRecord(@BindBean FillRecord fill);

}
