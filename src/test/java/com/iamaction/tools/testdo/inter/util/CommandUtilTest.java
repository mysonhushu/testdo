/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iamaction.tools.testdo.inter.util;

import com.iamaction.tools.testdo.inter.bean.PayloadType;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author huuuuxin
 */
public class CommandUtilTest {

    /**
     * Test of createCommnad method, of class CommandUtil.
     */
    @Test
    public void testCreateCommnad() {
        System.out.println("createCommnad");
        String testCaseFileUrl = "F:\\git-workspace\\testdo\\src\\main\\resources\\caseMethod.txt";
        CommandUtil instance = new CommandUtil();
        instance.setAppIp("10.175.163.26");
        instance.setMdIp("10.175.162.169");
        instance.setTestClass("TestCasesMapSRIForSM_APP");
        instance.setNewLogsPath("~/test-logs");
        
        ArrayList<PayloadType> plList = new ArrayList<PayloadType>();
        PayloadType pl_1 = new PayloadType();
        pl_1.setNumber(1);
        pl_1.setLogFolderFullPath("/var/lib/occas/domains/domain1/servers/traffic-1/logs");
        pl_1.setRouterInterface("bond0");
        plList.add(pl_1);
        
        PayloadType pl_2 = new PayloadType();
        pl_2.setNumber(2);
        pl_2.setLogFolderFullPath("/var/lib/occas/domains/domain1/servers/traffic-2/logs");
        pl_2.setRouterInterface("bond0");
        plList.add(pl_2);
        
        instance.setPayloadTypeList(plList);
            
        instance.createCommnad(testCaseFileUrl);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
