package com.dant.Test;

import com.dant.app.ApiEndpoint;
import com.dant.entity.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    @Order(3)
    public void createTableTest() throws Exception {
        Table_Test testTable = new Table_Test();

        testTable.testTable();
    }

    @Test
    @Order(1)
    public void createAccountTest() throws Exception {
        Account testAccount = new Account("toto@helloworld.com");

        assertEquals("toto@helloworld.com", testAccount.getEmail());
    }

    @Test
    @Order(2)
    public void testCreateColumn() throws Exception {
        Column testColumn = new Column("VendorID", "String");

        assertEquals("VendorID", testColumn.getName());
        assertEquals("String", testColumn.getType());
    }

    @Test
    @Order(4)
    public void testCreateIndex(String body) throws Exception {

        JsonObject json = new JsonParser().parse(body).getAsJsonObject();
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
