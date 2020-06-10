package com.dant.Test;

import com.dant.entity.DataBase;
import junit.framework.*;
import com.dant.entity.Column;
import com.dant.entity.Table;

import java.util.ArrayList;

public class Table_Test extends TestCase{
    public void testTable() throws Exception {

        Table table1 = new Table("Taxi1");
        Column col1 = new Column("VendorID","String");
        Column col2 = new Column("tpep_pickup_datetime", "DateTime");
        ArrayList<Column> cols = new ArrayList<>();
        cols.add(col1);
        cols.add(col2);
        table1.setColumns(cols);

        //expect the table name to be the given and the table's columns to be created and usable 
        assertEquals("Can't get the table name","Taxi1",table1.getName());
        assertEquals("Can't get the columns",2,table1.getColumns().size());
        assertEquals("Can't get the chosen column name","VendorID",table1.getColumns().get(0).getName());
    }

}
