package com.dant.app;

import com.dant.entity.Column;
import com.dant.entity.DataBase;
import com.dant.entity.Index;
import com.dant.entity.Table;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import javafx.scene.control.TableColumn;
import org.apache.commons.io.TaggedIOException;
import org.jboss.resteasy.annotations.Query;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import static com.dant.entity.Index.insertDatafct;


@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApiEndpoint {
    /**
     * table creation with given name and columns in parameters
     * CREATE TABLE NAME (columnName : type)
     * @return a Response code
     * @param body whole body
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

        //setting up the newly created table
        table.setColumns(columns);
        System.out.println("size: "+table.getColumns().size());
        DataBase.getInstance().addTable(table);//store table in the data base

        return Response.status(200).build();//OK, post method successful
    }

    //test select a table by name and return the whole table
    /**
     *get a table by its name test (without data)
     * @return Table
     * @param name from @QueryParam
     */
    @GET
    @Path("/select")
    public Table getTable(@QueryParam("name") String name){
        Table table = DataBase.getInstance().getTable(name);
        System.out.println(table);
        return table;
    }

    /**
     * create an index on a table's column (1..n)
     * CREATE INDEX index_nom ON table (colonne1,..,colonneN)
     * @return a Response code
     * @param body whole body
     */
    @POST
    @Path("/index")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crateIndex(String body){
        JsonObject json = new JsonParser().parse(body).getAsJsonObject(); //parsing the whole body retrieved
        //retrieve the created table from the DataBase
        String tableName = json.get("table_name").getAsString();
        Table table = DataBase.getInstance().getTable(tableName);

        //extract the givenPositions from the body and put them into ArrayList<Integer>
        ArrayList<Integer> givenPositions = new ArrayList<>();
        JsonArray jsonArray = json.get("givenPositions").getAsJsonArray();
        for(int i = 0; i < jsonArray.size(); i++){
            givenPositions.add(jsonArray.get(i).getAsInt());
        }

        //create the index
        String indexName = json.get("name").getAsString();
        Index index = new Index(indexName);

        //add the retrieved columns and the givenPositions to the newly created index
        index.addColumns(table,givenPositions);

        //add the new index to the table
        table.getIndexes().add(index);

        //verify if the index is added in the table
        System.out.println("TABLE ---- index in the table: " + table.getIndexes());
        System.out.println("THE FINAL TABLE: ---- " + table);

        return Response.status(200).build();//OK Status for post method
    }
    /**
     * phetsinorath pierre
     * insert data in table
     * @return a Response code
     * @param body whole body
     */
    @POST
    @Path("/saveCsv")
    @Consumes({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
    public Response insertData(@QueryParam("tableName") String tableName,
                               @QueryParam("givenPosition") ArrayList<String> givenPosition,
            String body) {
        //retrieve the given name in the param
        String nameTable = tableName;
        System.out.println("The retrieved table:" + nameTable);

        //retrieve the given position
        ArrayList<String> givenPosstr = givenPosition;
        ArrayList<Integer> givenPosint = new ArrayList<>();
        for (String s: givenPosstr) {
            givenPosint.add(Integer.parseInt(s));
        }
        System.out.println("The given positions: " + givenPosint.toString());

        Table table = DataBase.getInstance().getTable(nameTable);
        //System.out.println(body);
        Scanner scanner = new Scanner(body);
        String nextLine;
        File myObj = new File("src/main/tmp/test.txt");
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("src/main/tmp/test.txt");
            while (scanner.hasNextLine() && !((nextLine = scanner.nextLine()).equals(""))) {
                myWriter.write(nextLine+"\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //isert Data into the index
        for (Index i: table.getIndexes()) {
            insertDatafct(i,myObj,givenPosint);
        }

        return Response.status(200).build();//OK Status for post method
    }

    /**
     * phetsinorath pierre
     * créer un fichier
     */
    public void createFile(String path){
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred. During file création");
            e.printStackTrace();
        }
    }

    /**
     * phetsinorath pierre
     * écrit dans un fichier
     */
    public void writeFile(String inputFile, String path){
        Scanner scanner = new Scanner(inputFile);
        String nextLine;
        try {
            FileWriter myWriter = new FileWriter(path);
            while (scanner.hasNextLine() && !((nextLine = scanner.nextLine()).equals(""))) {
                myWriter.write(nextLine+"\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
