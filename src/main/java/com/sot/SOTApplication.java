package com.sot;

import com.sot.thing.stack.StackResource;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import org.apache.log4j.Logger;
import org.skife.jdbi.v2.DBI;

import com.sot.thing.*;

public class SOTApplication extends Application<SOTConfiguration> {


    static final Logger logger = Logger.getLogger(SOTApplication.class);

    public static void main(String[] args) throws Exception {
        new SOTApplication().run(args);
    }

    @Override
    public String getName() {
        return "sot";
    }

    @Override
    public void initialize(Bootstrap<SOTConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/sot", "index.html"));
//        bootstrap.addBundle(new AssetsBundle("/assets/favicon.ico","/favicon.ico"));
    }

    @Override
    public void run(SOTConfiguration configuration,
                    Environment environment) {
        try {
            final DBIFactory factory = new DBIFactory();
            final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
            final ThingDAO dao = jdbi.onDemand(ThingDAO.class);
            environment.jersey().register(new ThingResource(dao));
            environment.jersey().register(new FormResource(dao));
            environment.jersey().register(new StackResource(dao));
        } catch (Exception e) {
            logger.error("Error with JDBI Resource",e);
        }
    }

}