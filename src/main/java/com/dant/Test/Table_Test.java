package com.dant.Test;

import com.dant.entity.DataBase;
import junit.framework.*;
import com.dant.entity.Column;
import com.dant.entity.Table;
import org.joda.time.DateTime;

import java.util.ArrayList;

public class Table_Test extends TestCase{
    public void testTable() throws Exception {

        Table table1 = new Table("Taxi1");
        Column col1 = new Column("VendorID","String");
        Column col2 = new Column("tpep_pickup_datetime", "DateTime");
        ArrayList<Column> cols = new ArrayList<Column>();
        cols.add(col1);
        cols.add(col2);
        table1.setColumns(cols);

        //expect the table name to be the given
        assertEquals("Taxi1", table1.getName());
        assertEquals(cols, table1.getColumns());
    }

}
