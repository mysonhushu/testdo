package com.iamaction.tools.testdo.inter.bean;

/**
 *
 * @author huuuuxin
 */
public class PayloadType {

    public PayloadType() {
    }

    @Override
    public String toString() {
        return "PayloadType{" + "number=" + number + ", routerInterface=" + routerInterface + ", logFolderFullPath=" + logFolderFullPath + '}';
    }
    private int number;
    private String routerInterface;
    private String logFolderFullPath;

    public PayloadType(int number, String routerInterface, String logFolderFullPath) {
        this.number = number;
        this.routerInterface = routerInterface;
        this.logFolderFullPath = logFolderFullPath;
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRouterInterface() {
        return routerInterface;
    }

    public void setRouterInterface(String routerInterface) {
        this.routerInterface = routerInterface;
    }

    public String getLogFolderFullPath() {
        return logFolderFullPath;
    }

    public void setLogFolderFullPath(String logFolderFullPath) {
        this.logFolderFullPath = logFolderFullPath;
    }
}
