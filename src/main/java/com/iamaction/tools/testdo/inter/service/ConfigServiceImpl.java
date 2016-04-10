/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iamaction.tools.testdo.inter.service;

import com.iamaction.tools.testdo.inter.bean.Case;
import com.iamaction.tools.testdo.inter.bean.TestInfo;
import com.iamaction.tools.testdo.inter.util.StringUtil;
import com.iamaction.tools.testdo.inter.util.XMLUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huuuuxin
 */
public class ConfigServiceImpl implements  ConfigService {
    
    /**
     * read configuration file.
     * @return TestInfo
     */
    public TestInfo readConfig() {
        TestInfo testInfo = null;
        String path = "F:\\git-workspace\\testdo\\src\\main\\resources\\testdo-config.xml";
        List<TestInfo> tiList = XMLUtil.readTestInfo(path);
        System.out.println("tiList.size()="+tiList.size());
        List<TestInfo> activeList = new ArrayList<TestInfo>();
        for(TestInfo a: tiList)
        {
            System.out.print(a.CASE_FILE_NAME);
            if(a.ACTIVE)
            {
                activeList.add(a);
            }
        }
        if(activeList.size()==0 || activeList.size()>1)
        {
            System.out.println("activeList.size()="+activeList.size());
            StringUtil.print("the project active number is none or greater than 1!");
            System.exit(-1);
        } else {
            testInfo = activeList.get(0);
        }
        for(int i=0;i<testInfo.testCaseNames.size();i++)
        {
            Case temp = testInfo.testCaseNames.get(i);
            temp.caseNum = i;
            temp.caseTGCommand = testInfo.COMMAND_VALUE+ testInfo.TEST_CLASS+"#"
                    +temp.caseName+">"+temp.caseName+".log";
            temp.caseTGRunTimes = 0;
            temp.caseTrafficCommand = "tail -f /var/lib/occas/domains/domain1/servers/traffic-2/logs/traffic-2.log>"+testInfo.TEST_CLASS+".traffic";
            temp.caseTcpdumpCommandSC1 = "tcpdump -i bond1.2101 -w /root/sc2_"+testInfo.TEST_CLASS+".cap";
            temp.caseTcpdumpCommandSC2 = "tcpdump -i bond1.2200 -w /root/sc-1_"+testInfo.TEST_CLASS+".cap";
            System.out.println("caseNum ="+i);
            System.out.println("caseTGCommand =");
            System.out.println(temp.caseTGCommand);
            System.out.println("caseTrafficCommand =");
            System.out.println(temp.caseTrafficCommand);
            System.out.println("caseTcpdumpCommandSC1 =");
            System.out.println(temp.caseTcpdumpCommandSC1);
            System.out.println("caseTcpdumpCommandSC2 =");
            System.out.println(temp.caseTcpdumpCommandSC2);
             System.out.println("------------------------------------------------------------------");
        }
        return testInfo;
    }
    
}
