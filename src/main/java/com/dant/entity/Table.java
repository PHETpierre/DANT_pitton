package com.dant.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable{
    private String name;
    private ArrayList<Column> columns;
    private ArrayList<Index> indexes;//stores the created index

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        return name.equals(table.name);
    }

    @Override
    public String toString(){
        return String.format(name + ": " + columns);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }
    /**
     * method get qui retourne les valeurs = une liste de ligne de la table
     */
    public ArrayList<Object[]> get(String name) {
        ArrayList<Object[]> rows = new ArrayList<>();

        return rows;
    }


}
