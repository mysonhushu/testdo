package com.iamaction.tools.testdo.inter.service;

import com.iamaction.tools.testdo.inter.bean.Case;
import java.util.List;

/**
 * read the test Case name
 * @author huuuuxin
 */
public interface TestCaseService {
    /**
     * read all the java test case name.
     * @param filePath
     * @return  the test cases.
     */
    public List<Case> readTestCase(String filePath, String fileName);
    
    /**
     * print test case name on console.
     * @param caseNames 
     */
    public void printCaseOption(List<Case> caseNames);
    
//    public static void printList(List<String> testCaseNames){
//         System.out.println("--------------use case list--------------------");
//         for(int i=0; i<testCaseNames.size(); i++)
//         {
//             System.out.println("   "+(i+1)+" : "+testCaseNames.get(i));
//         }
//         System.out.println("-----------------------------------------------");
//         System.out.println("Please select the case you want to run...");
//         
//    }
}
