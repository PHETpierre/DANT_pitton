package com.dant.utils;

import com.dant.entity.Column;

import javax.ws.rs.ext.ParamConverter;

public class ColumnConverter implements ParamConverter<Column> {

    @Override
    public Column fromString(String s){
        Column column  = new Column();
        return column;
    }

    @Override
    public String toString(Column c){
        return c.toString();
    }
}