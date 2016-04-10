package com.iamaction.tools.testdo.inter.util;

import com.iamaction.tools.testdo.exception.PayloadNumNotSupportException;
import com.iamaction.tools.testdo.inter.bean.PayloadType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huuuuxin
 */
public class CommandUtil {

    private String appIp;
    private String mdIp;
    private String testClass;
    private String newLogsPath;

    public CommandUtil(String appIp, String mdIp, String testClass, String newLogsPath, ArrayList<PayloadType> payloadTypeList) {
        this.appIp = appIp;
        this.mdIp = mdIp;
        this.testClass = testClass;
        this.newLogsPath = newLogsPath;
        this.payloadTypeList = payloadTypeList;
    }

    public String getNewLogsPath() {
        return newLogsPath;
    }

    public void setNewLogsPath(String newLogsPath) {
        this.newLogsPath = newLogsPath;
    }
    private ArrayList<PayloadType> payloadTypeList;

    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append( "CommandUtil{" + "appIp=" + appIp + ", mdIp=" + mdIp + ", testClass=" + testClass +"\r\n" );
        sb.append("payloadTypeList=");
        for(PayloadType pl:payloadTypeList)
        {
            sb.append(pl.toString());
        }
        return sb.toString();
    }
    

    public ArrayList<PayloadType> getPayloadTypeList() {
        return payloadTypeList;
    }

    public void setPayloadTypeList(ArrayList<PayloadType> payloadTypeList) {
        this.payloadTypeList = payloadTypeList;
    }
    
    public CommandUtil(){
        
    }
    
    public String getAppIp() {
        return appIp;
    }

    public void setAppIp(String appIp) {
        this.appIp = appIp;
    }

    public String getMdIp() {
        return mdIp;
    }

    public void setMdIp(String mdIp) {
        this.mdIp = mdIp;
    }

    public String getTestClass() {
        return testClass;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }

    /**
     * Create a maven execute command to test unit test case.
     * @param appIp the ECE payload 1 or payload 2 IP address.
     * @param mdIp  the TG IP address.
     * @param testClass the class name
     * @param testCase  the case name 
     * @return  a maven command string
     */
    private String createMavenCommand(String appIp, String mdIp,
            String testClass, String testCase){
        StringBuffer mvnCmd = new StringBuffer();
        mvnCmd.append(" mvn clean install -Poccas,test -Djava.security.policy=src/test/resources/client.policy -DHTTP_PORT=38080");
        mvnCmd.append(" -DAPP_IP=").append(appIp);
        mvnCmd.append(" -DMD_IP=").append(mdIp);
        mvnCmd.append(" -Dtest=").append(testClass).append("#").append(testCase);
        mvnCmd.append(" | tee ").append(testCase).append(".log");
        return mvnCmd.toString();
    }
    
    /**
     * Create a execute command to capture traffic log on payload 1 or payload 2
     * @param fileFullPath the ECE payload traffic log file's full path
     * @param testCase the running test case name
     * @param logFoldFullPath the new create log file name
     * @return a new command to capture the traffic log and input in a new file named by testCase
     */
    private String createTrafficLogCommand(int payloadNumber,String testCase, String logFolderFullPath, String newLogsPath)
            throws PayloadNumNotSupportException{
        StringBuffer trafficLogCmd = new StringBuffer();
        if(payloadNumber == 1){
             trafficLogCmd.append("  tail -f ").append(logFolderFullPath)
                     .append("/traffic-1.log");
             trafficLogCmd.append("  | tee ")
                     .append(newLogsPath).append("/").append(testCase).append(".traffic");
        }else if(payloadNumber == 2){
             trafficLogCmd.append("  tail -f ").append(logFolderFullPath)
                     .append("/traffic-2.log");
             trafficLogCmd.append("  | tee ")
                     .append(newLogsPath).append("/").append(testCase).append(".traffic");
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
            String logFolderFullPath, String testCase, String newLogsPath) 
                    throws PayloadNumNotSupportException{
        StringBuffer tcpDumpCmd = new StringBuffer();
        if(payloadNumber == 1){
             tcpDumpCmd.append(" tcpdump -i ").append(routerInterfaceName)
                     .append(" -w ").append(newLogsPath).append("/")
                     .append(testCase).append(".cap");
        }else if(payloadNumber == 2){
             tcpDumpCmd.append(" tcpdump -i ").append(routerInterfaceName)
                     .append(" -w ").append(newLogsPath).append("/")
                     .append(testCase).append(".cap");
        }else {
            throw 
                 new PayloadNumNotSupportException("PayLoad number is not support! payload number ="+payloadNumber);
        }
        return tcpDumpCmd.toString();
    }
    
    /**
     * create command
     * @param testCaseFileUrl 
     */
    public void createCommnad(String testCaseFileUrl){
        List<String> commandList = new ArrayList<String>();
        List<String> expResult = null;
        List<String> result = FileUtil.readFile2List(testCaseFileUrl);
        int i=1;
        for(String testCase:result)
        {
            //add maven command
            commandList.add(" num"+(i)+" mvn comand="+createMavenCommand(appIp,mdIp,testClass,testCase));
            
            //add tcpdump command and traffic command by payload
            for(PayloadType pl:payloadTypeList)
            {
                try {
                    commandList.add(" num"+(i)+" payload-"+pl.getNumber()
                        +" tcpdump command = "
                        +createTCPDumpCommand(pl.getNumber(),pl.getRouterInterface(),pl.getLogFolderFullPath(),testCase, newLogsPath));
                    commandList.add(" num"+(i)+" payload-"+pl.getNumber()
                        +" traffic command = "
                        +createTrafficLogCommand(pl.getNumber(), testCase,pl.getLogFolderFullPath(),newLogsPath));
                    commandList.add("\r\n");
                } catch (PayloadNumNotSupportException ex) {
                    Logger.getLogger(CommandUtil.class.getName()).log(Level.SEVERE, null, ex);
                    StringUtil.print(ex.toString());
                    System.exit(-1);
                }
            }
            i++;
           commandList.add("---------------------------------------------------------------------------");
        }
        for(String str: commandList)
        {
            System.out.println(str);
        }
    }

}
