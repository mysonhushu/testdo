/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iamaction.tools.testdo.test.inter.util;

import com.iamaction.tools.testdo.inter.bean.TestInfo;
import com.iamaction.tools.testdo.inter.util.XMLUtil;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.w3c.dom.Document;

/**
 *
 * @author huuuuxin
 */
public class XMLUtilTest extends TestCase {
    
    public XMLUtilTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of readXML method, of class XMLUtil.
     */
    public void testReadXML() {
        System.out.println("readXML");
        String pathName = "";
        Document expResult = null;
        Document result = XMLUtil.readXML(pathName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readTestInfo method, of class XMLUtil.
     */
    public void testReadTestInfo() {
        System.out.println("readTestInfo");
        String pathName = "F:\\git-workspace\\testdo\\src\\main\\resources\\testdo-config.xml";
        List<TestInfo> expResult = new ArrayList<TestInfo>();
        List<TestInfo> result = XMLUtil.readTestInfo(pathName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
