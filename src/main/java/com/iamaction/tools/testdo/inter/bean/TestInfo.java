package com.iamaction.tools.testdo.inter.bean;

import java.util.List;

/**
 * 
 * @author huuuuxin
 */
public class TestInfo {
    public String OS_TYPE;
    public String HOST_IP;
    public boolean INTERACTIVE;
    public boolean ACTIVE;
    public LogType LOG_TYPE;
    public String PROJECT_NAME;
    public String RUN_PATH;
    public String CASE_PATH;
    public String CASE_FILE_NAME;
    public String LOG_PATH;
    public String COMMAND_VALUE;
    public boolean FULL_CMD;
    public String HTTP_PORT;
    public String APP_IP;
    public String MD_IP;
    public String TEST_CLASS;
    public List<Case> testCaseNames; 
    public KeyValue<String,Case> listCaseName;
    
}