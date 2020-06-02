package com.dant.entity;

import com.google.gson.internal.$Gson$Preconditions;

import java.util.ArrayList;
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

    //to get all the stored tables
    public Map<String, Table> getAllTables(){
        return storedTables;
    }

    //to check the table
    public Table getTable(String name){
        return storedTables.get(name);
    }

    //to add new table
    public void addTable(Table table){
        storedTables.put(table.getName(),table);
        System.out.println("Newly added table: " + table);
        System.out.println(DataBase.getInstance().getAllTables().toString());//test to see if the table is really created
    }

    public Map<String, Table> getStoredTables() {
        return storedTables;
    }

    public Map<String, Column> getStroredColumns() {
        return stroredColumns;
    }

    @Override
    public String toString(){
        String s = "The database contains: ";
        for (Table table : storedTables.values()) {
            s = table.getName();
        }
        return s;
    }
}
