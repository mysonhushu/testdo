package com.iamaction.tools.testdo;
import com.iamaction.tools.testdo.request.RequestController;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

public class App 
{
//    public static void main( String[] args )
//    {
////        System.out.println( "Hello World!" );
//        List<String> result = new ArrayList<String>();
//        result.add("huxing");
//        result.add("tieto");
//        String[] ab = result.toArray(new String[0]);
//        for(int i=0;i<ab.length;i++)
//        {
//            System.out.println(ab[i]);
//        }
//    }
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RequestController.class, args);
    }
}
