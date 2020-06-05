package com.dant.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable{
    private String name;
    private ArrayList<Column> columns;//stores the created columns
    private ArrayList<Index> indexes = new ArrayList<>();//stores the created indexes

    public Table(){
    }

    public Table(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    //public void setIndexes(ArrayList<Index> indexes) { this.indexes = indexes; }

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

    @Override
    public String toString(){
        return String.format(name + ": " + columns + ", INDEXES ---- " + indexes);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }


}
