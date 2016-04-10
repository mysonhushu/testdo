package com.iamaction.tools.testdo;

import java.util.ArrayList;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        List<String> result = new ArrayList<String>();
        result.add("huxing");
        result.add("tieto");
        String[] ab = result.toArray(new String[0]);
        for(int i=0;i<ab.length;i++)
        {
            System.out.println(ab[i]);
        }
    }
}
