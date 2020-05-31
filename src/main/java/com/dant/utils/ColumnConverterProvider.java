package com.dant.utils;

import com.dant.entity.Column;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class ColumnConverterProvider implements ParamConverterProvider{

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if(rawType.equals(Column.class)){
            return (ParamConverter<T>) new ColumnConverterProvider();//new ColumnConverter()
        }
        return null;
    }
}