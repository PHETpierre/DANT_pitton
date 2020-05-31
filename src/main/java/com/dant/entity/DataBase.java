package com.dant.entity;

import java.util.HashMap;
import java.util.Map;
/**
 * This class is the one who will handle entities with data, it's our database
 */
public class DataBase {

    private Map<String,Table> storedTables = new HashMap<>();//String = table name
    private Map<String, Column> stroredColumns = new HashMap<>();//String = column name

    //singleton and should not be instantiated directly, this is the only instance
    private static DataBase instance = new DataBase();
    public static DataBase getInstance(){
        return instance;
    }

    //to check the table
    public Table getTable(String name){
        return storedTables.get(name);
    }

    //to add new table
    public void addTable(Table table){
        storedTables.put(table.getName(),table);
        System.out.println("Newly added table: " + table);
    }

    //TODO: Create a delete method
}
