package com.dant.entity;
import java.util.ArrayList;

public class Column {
    private String name;
    private String type;//pour l'instant c'est juste un String
    //private ArrayList<Column> col

    public Column(){
    }

    public Column(String name, String type){
        this.name = name;
        this.type = type;
        //col = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString(){
        return String.format(name);
    }
}
