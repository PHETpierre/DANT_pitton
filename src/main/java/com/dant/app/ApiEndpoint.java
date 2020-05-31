package com.dant.app;

import com.dant.entity.Column;
import com.dant.entity.DataBase;
import com.dant.entity.Table;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.jboss.resteasy.annotations.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApiEndpoint {

    /**
     * table creation with given name and columns in parameters
     * created by lra on 2020-05-31
     * @return
     */
    @POST
    @Path("/table")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTable(String body) {
        JsonObject json = new JsonParser().parse(body).getAsJsonObject();//retrieve the body and parse it
        JsonArray jsonArray = json.getAsJsonArray("columns");//retrieve the columns into jsonArray
        ArrayList<Column> columns;
        Type arrayListType = new TypeToken<List<Column>>(){}.getType();

        //table name
        Table table = new Table(json.get("name").getAsString()); //retrieve the name in the body

        //retrieve the columns
        Gson gson=new Gson();
        columns = gson.fromJson(jsonArray,arrayListType);//convert the jsonArray to ArrayList<Column>
        table.setColumns(columns);
        DataBase.getInstance().addTable(table);

        return Response.status(200).build();//OK, post method successful
    }

}
