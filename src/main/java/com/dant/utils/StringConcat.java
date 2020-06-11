package com.dant.utils;

import java.util.ArrayList;

public class StringConcat {
    public static String concatString(ArrayList<Integer> positions, ArrayList<String> line){
        String s ="";
        for (int i:positions) {
                s += line.get(i);
        }
        return s;
    }
}
