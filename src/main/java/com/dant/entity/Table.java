package com.dant.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable{
    private String name;
    private ArrayList<Column> columns;
    private ArrayList<Index> indexes;

    public Table(){
    }

    public Table(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public ArrayList<Index> getIndexes() {
        return indexes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        return name.equals(table.name);
    }

    /**
     * method get qui retourne les valeurs une liste de ligne
     */
    public ArrayList<Object[]> get(String name) {
        ArrayList<Object[]> rows = new ArrayList<>();
        if(true){

        }
        return rows;
    }


}
