package com.sot.thing;


/**
 * Created by kevin on 11/5/2014.
 */
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ThingMapper implements ResultSetMapper<Thing>
{
    public Thing map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException
    {
        Thing t = new Thing (
                resultSet.getInt("id"),
                resultSet.getDouble("height"),
                resultSet.getDouble("length"),
                resultSet.getDouble("width"),
                resultSet.getDouble("weight"),
                resultSet.getDouble("volume"),
                resultSet.getDouble("originalPrice"),
                resultSet.getDouble("currentPrice"),
                resultSet.getInt("year"),
                resultSet.getString("name"),
                resultSet.getString("pluralName"),
                resultSet.getString("description"),
                resultSet.getInt("nsfw"),
                resultSet.getString("imageUrl"),
                resultSet.getDate("creation_time"),
                resultSet.getDouble("radius"),
                resultSet.getDouble("diff")
                );
        // have to abstract this away better
//        t.setDiff(resultSet.getDouble("diff"));
        return t;
    }
}