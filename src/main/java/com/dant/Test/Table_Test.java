package com.dant.Test;

import com.dant.entity.Column;
import com.dant.entity.Table;

import java.util.ArrayList;

public class Table_Test {
    public static void main(String[] args) {

        Table table1 = new Table("Taxi1");
        Column col1 = new Column("VendorID","String");
        Column col2 = new Column("tpep_pickup_datetime", "DateTime");
        ArrayList<Column> cols = new ArrayList<Column>();
        cols.add(col1);
        cols.add(col2);
        table1.setColumns(cols);

        System.out.println(table1);

    }
}
