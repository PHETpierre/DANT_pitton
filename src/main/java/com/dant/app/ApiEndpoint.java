package com.dant.app;

import com.dant.entity.Account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by pitton on 2017-02-20.
 */
@Path("/api/")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class ApiEndpoint {
    @GET
    @Path("/insert")
    public String insert(){
        return "hello";
    }
}
