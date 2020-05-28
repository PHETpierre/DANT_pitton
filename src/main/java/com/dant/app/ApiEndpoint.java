package com.dant.app;

import javax.swing.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApiEndpoint {

    /**
     * create table from csv file
     * @return
     */
    @GET //post
    @Path("/table")
    @Produces("csv/text")
    public Boolean createTable(){
        Boolean created = false;

        return created;
    }

    /**
     *
     */
}
