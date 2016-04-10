package com.iamaction.tools.testdo.inter.util;

/**
 *
 * @author huuuuxin
 */
public class PIDUtil {
    /**
     * execute the command and return the Process ID.
     * @param executePath
     * @param cmd
     * @return 
     */
    public int executeOnLinux(String executePath, String cmd){
        int pid = -999;
        return pid;
    }
    
    public boolean killOnLinux(int pid){
        boolean isSuccess = false;
        return isSuccess;
    }
    
    /**
     * execute the command and return the Process ID.
     * @param executePath
     * @param cmd
     * @return 
     */
    public int executeOnWindows(String executePath, String cmd){
        int pid = -999;
        return pid;
    }
    
    public boolean killOnWindows(int pid){
        boolean isSuccess = false;
        return isSuccess;
    }
}
