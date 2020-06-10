package com.dant.Test;

import com.dant.entity.DataBase;
import com.dant.entity.Index;
import junit.framework.*;
import com.dant.entity.Column;
import com.dant.entity.Table;

import java.util.ArrayList;

public class Entities_Unit_Testing extends TestCase{
    Table table1;
    Column col1, col2;
    Index index1;
    public void testEntities() throws Exception {
        table1 = new Table("Taxi1");
        col1 = new Column("VendorID","String");
        col2 = new Column("tpep_pickup_datetime", "DateTime");
        ArrayList<Column> cols = new ArrayList<>();
        cols.add(col1);
        cols.add(col2);
        table1.setColumns(cols);
        ArrayList<Integer> positions = new ArrayList<>();
        positions.add(0);
        positions.add(1);

        index1 = new Index("vendorid_idx");
        index1.addColumns(table1,positions);
        table1.getIndexes().add(index1);

        int actualIndex = table1.getIndexes().size();


        //expect the table name to be the given
        assertEquals("Can't get the table name","Taxi1",table1.getName());
        //expect the number of columns in the table to correspond to the number of inserted
        assertEquals("Can't get the columns",2,table1.getColumns().size());
        //expect to get the name of the column by its position
        assertEquals("Can't get the chosen column name","VendorID",table1.getColumns().get(0).getName());
        /* expect the created index to be inserted in the table */
        assertEquals("Can't put index in the table",1,actualIndex);
    }


}
