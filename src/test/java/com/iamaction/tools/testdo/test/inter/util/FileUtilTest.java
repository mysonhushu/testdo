/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iamaction.tools.testdo.test.inter.util;

import com.iamaction.tools.test.exception.PayloadNumNotSupportException;
import com.iamaction.tools.testdo.inter.util.FileUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author huuuuxin
 */
public class FileUtilTest {
    
    public FileUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of read method, of class FileUtil.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        String fileName = "";
        StringBuilder expResult = null;
        StringBuilder result = FileUtil.read(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readFile2List method, of class FileUtil.
     */
    @Test
    public void testReadFile2List() {
       //System.out.println("readFile2List");
        String fileName = "F:\\git-workspace\\testdo\\src\\main\\resources\\caseMethod.txt";
        List<String> expResult = null;
        List<String> result = FileUtil.readFile2List(fileName);
        int i=1;
        for(String a: result)
        {
            System.out.println("num"+i+":  "+i+"    "+a);
            System.out.println("num"+i+":  "+i+"   mvn command =");
            System.out.println("num"+i+":  "+i+" mvn clean install -Poccas,test -Djava.security.policy=src/test/resources/client.policy -DHTTP_PORT=38080 -DAPP_IP=10.175.163.43 -DMD_IP=10.175.162.169 -Dtest=TestCasesMapSRIForSM_APP#"+a+"| tee "+a+".log");
            System.out.println("num"+i+"    PL-1  traffic comannd =");
            System.out.println("num"+i+"    PL_1  num:  "+i+"    tail -f /var/lib/occas/domains/domain1/servers/traffic-1/logs/traffic-1.log| tee  /var/lib/occas/domains/domain1/servers/traffic-1/logs/hx-cap/pl_1_"+a+".traffic");
            System.out.println("num"+i+"    PL-1 tcpdump comannd =");
            System.out.println("num"+i+":  "+i+"     tcpdump -i bond1 -w /var/lib/occas/domains/domain1/servers/traffic-1/logs/hx-cap/pl_1_"+a+".cap");
//            System.out.println("---------------");
//            System.out.println("PL-2  traffic tcpdump comannd =");
//            System.out.println("num:  "+i+"    tail -f /var/lib/occas/domains/domain1/servers/traffic-2/logs/traffic-2.log| tee  /var/lib/occas/domains/domain1/servers/traffic-2/logs/hx-cap/hx-cap/pl_2_"+a+".traffic");
//            System.out.println("PL-2 tcpdump comannd =");
//            System.out.println("num:  "+i+"    tcpdump -i eth0 -w /var/lib/occas/domains/domain1/servers/traffic-2/logs/hx-cap/pl_2_"+a+".cap");
            System.out.println("----------------------------------------------");
            ++i;
//            temp.caseTrafficCommand = "tail -f /var/lib/occas/domains/domain1/servers/traffic-2/logs/traffic-2.log>"+testInfo.TEST_CLASS+".traffic";
//            temp.caseTcpdumpCommandSC1 = "tcpdump -i bond1.2101 -w /root/sc2_"+testInfo.TEST_CLASS+".cap";
//            temp.caseTcpdumpCommandSC2 = "tcpdump -i bond1.2200 -w /root/sc-1_"+testInfo.TEST_CLASS+".cap";
        }
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    /**
     * Create a maven execute command to test unit test case.
     * @param appIp the ECE payload 1 or payload 2 IP address.
     * @param mdIp  the TG IP address.
     * @param testClass the class name
     * @param testCase  the case name 
     * @return  a maven command string
     */
    private String createMavenCommand(String appIp, String mdIp, String testClass, String testCase){
        StringBuffer mvnCmd = new StringBuffer();
        mvnCmd.append(" mvn clean install -Poccas,test -Djava.security.policy=src/test/resources/client.policy -DHTTP_PORT=38080");
        mvnCmd.append(" -DAPP_IP=").append(appIp);
        mvnCmd.append(" -DMD_IP=").append(mdIp);
        mvnCmd.append(" -Dtest=").append(testClass).append("#").append(testCase);
        mvnCmd.append(" | tree ").append(testCase).append(".log");
        return mvnCmd.toString();
    }
    
    /**
     * Create a execute command to capture traffic log on payload 1 or payload 2
     * @param fileFullPath the ECE payload traffic log file's full path
     * @param testCase the running test case name
     * @param logFoldFullPath the new create log file name
     * @return a new command to capture the traffic log and input in a new file named by testCase
     */
    private String createTrafficLogCommand(int payloadNumber,String testCase, String logFolderFullPath)
            throws PayloadNumNotSupportException{
        StringBuffer trafficLogCmd = new StringBuffer();
        if(payloadNumber == 1){
             trafficLogCmd.append("  tail -f ").append(" /var/lib/occas/domains/domain1/servers/traffic-1/logs/traffic-1.log");
             trafficLogCmd.append("  | tee ").append(logFolderFullPath).append(testCase).append(".traffic");
        }else if(payloadNumber == 2){
             trafficLogCmd.append("  tail -f ").append(" /var/lib/occas/domains/domain1/servers/traffic-2/logs/traffic-2.log");
             trafficLogCmd.append("  | tee ").append(logFolderFullPath).append(testCase).append(".traffic");
        }else {
            throw new PayloadNumNotSupportException("PayLoad number is not support! payload number ="+payloadNumber);
        }
       
        return trafficLogCmd.toString();
    }

    /**
     * Create a TCP dump command to capture logs.
     * @param payloadNumber the number(1 or 2) of payload which the TCP command execute.
     * @param routerInterfaceName 
     * @param logFolderFullPath
     * @param testCase
     * @return
     * @throws PayloadNumNotSupportException 
     */
    private String createTCPDumpCommand(int payloadNumber, String routerInterfaceName,
            String logFolderFullPath, String testCase) throws PayloadNumNotSupportException{
        StringBuffer tcpDumpCmd = new StringBuffer();
        if(payloadNumber == 1){
             tcpDumpCmd.append(" tcpdump -i ").append(routerInterfaceName).append(" -w ").append(logFolderFullPath).append(testCase).append(".cap");
        }else if(payloadNumber == 2){
             tcpDumpCmd.append(" tcpdump -i ").append(routerInterfaceName).append(" -w ").append(logFolderFullPath).append(testCase).append(".cap");
        }else {
            throw new PayloadNumNotSupportException("PayLoad number is not support! payload number ="+payloadNumber);
        }
        return tcpDumpCmd.toString();
    }
    
    /**
     * Test of readStream method, of class FileUtil.
     */
    @Test
    public void testReadStream() {
        System.out.println("readStream");
        String fileName = "";
        StringBuilder expResult = null;
        StringBuilder result = FileUtil.readStream(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFile method, of class FileUtil.
     */
    @Test
    public void testCreateFile() {
        System.out.println("createFile");
        String fileUrl = "";
        boolean expResult = false;
        boolean result = FileUtil.createFile(fileUrl);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isExite method, of class FileUtil.
     */
    @Test
    public void testIsExite() {
        System.out.println("isExite");
        String fileUrl = "";
        boolean expResult = false;
        boolean result = FileUtil.isExite(fileUrl);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileWriter method, of class FileUtil.
     */
    @Test
    public void testGetFileWriter() throws Exception {
        System.out.println("getFileWriter");
        String fileName = "";
        String charsetName = "";
        boolean isAppend = false;
        FileUtil instance = new FileUtil();
        instance.getFileWriter(fileName, charsetName, isAppend);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writer method, of class FileUtil.
     */
    @Test
    public void testWriter() throws Exception {
        System.out.println("writer");
        String fileContent = "";
        FileUtil instance = new FileUtil();
        instance.writer(fileContent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateFileContent method, of class FileUtil.
     */
    @Test
    public void testUpdateFileContent() throws Exception {
        System.out.println("updateFileContent");
        String fileName = "";
        String fileContent = "";
        FileUtil.updateFileContent(fileName, fileContent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeFileWriter method, of class FileUtil.
     */
    @Test
    public void testCloseFileWriter() throws Exception {
        System.out.println("closeFileWriter");
        FileUtil instance = new FileUtil();
        instance.closeFileWriter();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class FileUtil.
     */
    @Test
    public void testClose_BufferedReader_FileReader() {
        System.out.println("close");
        BufferedReader reader = null;
        FileReader filer = null;
        FileUtil.close(reader, filer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class FileUtil.
     */
    @Test
    public void testClose_FileInputStream() {
        System.out.println("close");
        FileInputStream filer = null;
        FileUtil.close(filer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
