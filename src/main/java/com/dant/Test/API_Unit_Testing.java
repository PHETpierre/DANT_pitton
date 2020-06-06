package com.dant.Test;

import com.dant.app.ApiEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import static org.junit.Assert.*;
import org.junit.Test;
import javax.ws.rs.core.Application;

public class API_Unit_Testing extends JerseyTest{

    @Override
    protected Application configure() {
        return new ResourceConfig(ApiEndpoint.class);
    }

    @Test
    public void createTableTest(){

    }

}
