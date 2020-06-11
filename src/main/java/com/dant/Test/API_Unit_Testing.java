package com.dant.Test;

import com.dant.app.ApiEndpoint;
import com.dant.entity.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.Parameterized;

import javax.ws.rs.core.Application;
import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class API_Unit_Testing extends JerseyTest{

    @Override
    protected Application configure() {
        return new ResourceConfig(ApiEndpoint.class);
    }

    @Test
    @Order(2)
    public void createTableTest() throws Exception {
        Table_Test testTable = new Table_Test();

        testTable.testTable();
    }

    @Test
    @Order(1)
    public void testCreateColumn() throws Exception {
        Column testColumn = new Column("VendorID", "String");

        assertEquals("VendorID", testColumn.getName());
        assertEquals("String", testColumn.getType());
    }

    @Test
    @Order(3)
    public void testCreateIndex() throws Exception {
        String s = "{\"givenPositions\" : [0,1], \"name\": \"vendorid_tpep_pickup_datetime_idx\",\"table_name\": \"Yellow Taxi\"}";
        JsonObject json = (new JsonParser()).parse(s).getAsJsonObject();
        String tableName = json.get("table_name").getAsString();
        Table table = DataBase.getInstance().getTable(tableName);

        ArrayList<Integer> givenPositions = new ArrayList<>();
        JsonArray jsonArray = json.get("givenPositions").getAsJsonArray();
        for(int i = 0; i < jsonArray.size(); i++){
            givenPositions.add(jsonArray.get(i).getAsInt());
        }

        String indexName = json.get("name").getAsString();
        Index index = new Index(indexName);

        index.addColumns(table,givenPositions);

        table.getIndexes().add(index);

        index.toString();

    }

}
