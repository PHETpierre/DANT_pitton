package com.dant.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.dant.utils.StringConcat.concatString;

public class Index {

    public String name;
    public Column column;// the chosen column
    public HashMap<Column,Integer> columns = new HashMap<>(); // for the case 1..n columns K: Column V: its position in the table
    public HashMap<String, ArrayList<Integer>> rows = new HashMap<>();// rows to be stored, K: Column actual value V: position of the row containing the value in the file
    public HashMap<String,HashMap<String, ArrayList<Integer>>> indexRows = new HashMap<>();//K: index name, V: rows
    public Index(String name){ this.name = name; }

    /**
     * add 1..n columns to the index
     * @param table the already created and stored table in the database
     * @param givenPositions the position(s) of the column to be indexed
     * */
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

    /**
     * insert the data into the index rows
     * @param index the created index in the table
     * @param file the retrieved file from the api that contains data
     * @param givenPositions positions of the columns to be filled in the table
     * */
    public void insertData(Index index, File file,ArrayList<Integer> givenPositions) {
        ArrayList<String> lines;//store the lines and deleted after its use
        BufferedReader reader;
        ArrayList<Integer> posInFile = new ArrayList<>();//position of the line where the needed values are
        String value;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            int count = -1; //counter of line, -1 because the first line is 0
            //read each line (may takes eternity)
            while (line != null) {
                line = reader.readLine();
                lines = (ArrayList<String>) Arrays.asList(line.split("\\s*,\\s*"));//store the retrieve line in an arrayList of strings split by coma
                //loop through the line and get the value by the given positions
                value = concatString(givenPositions,lines);
                count +=1;
                posInFile.add(count);
                if(rows.containsKey(value)){
                    rows.get(value).add(count);
                }
                else{
                    rows.put(value,posInFile);
                }
                lines.clear();//clear the array list so that it can be used for the next line
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        indexRows.put(index.name,rows);
        System.out.println("Inserted rows in the index: " + indexRows.toString());
    }

    @Override
    public String toString(){
        return String.format("NAME: "+name + " --- " +" COLUMNS: "+ columns);
    }



}
