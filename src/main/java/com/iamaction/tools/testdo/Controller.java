package com.iamaction.tools.testdo;

import com.iamaction.tools.testdo.inter.service.ConfigService;
import com.iamaction.tools.testdo.inter.service.TestCaseService;
import com.iamaction.tools.testdo.inter.service.TestLogService;
import com.iamaction.tools.testdo.inter.bean.TestInfo;

/**
 *
 * @author huuuuxin
 */
public class Controller {
    private TestInfo itsTestInfo;
    private ConfigService itsConfig;
    private TestCaseService itsTestCase;
    private TestLogService itsTestLog;
    
    public void control(){
        init();
    }
    
    public void startUp(){
        
    }
    
    public void init(){
        initConfig();
        initCase();
        initCmd();
    }
    
    private void initConfig(){
        itsTestInfo = itsConfig.readConfig();
    }
    
    private void initCase(){
        itsTestInfo.testCaseNames =  itsTestCase.readTestCase(itsTestInfo.CASE_PATH, itsTestInfo.CASE_FILE_NAME);
    }
    
    private void initCmd(){
        
    }
    

}
