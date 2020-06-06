package com.dant.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Index {

    public String name;
    public Column column;// the chosen column
    public HashMap<Column,Integer> columns = new HashMap<>(); // for the case 1..n columns K: Column V: its position in the table
    public HashMap<Integer, ArrayList<Object[]>> indexRows = new HashMap<>(); //K: chosen column hashcode , V: list of the values in the column
    public int positionColumnIndex; //the position stands for the column's position in the table

    public Index(String name){ this.name = name; }

    //add 1..n columns to the index
    public void addColumns(Table table, ArrayList<Integer> givenPositions){
        for (int i : givenPositions) {
            System.out.println("i: " +i );
            System.out.println("the column: "+table.getColumns().get(i));
            try {
                columns.put(table.getColumns().get(i), i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Newly created index: " + name + ": " +columns.toString() + " on the table: " + table);
    }

    @Override
    public String toString(){
        return String.format("NAME: "+name + " --- " +" COLUMNS: "+ columns);
    }

    ///todo: Add the select method and the insert method bc the data are going to be inserted in the index rows
}
