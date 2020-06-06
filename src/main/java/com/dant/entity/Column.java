package com.dant.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Column implements Serializable {
    private String name;
    private String type;//for now, it's just a String but later we'll figure out how to have a true Type
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

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return String.format(name +" : "+type);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

}
