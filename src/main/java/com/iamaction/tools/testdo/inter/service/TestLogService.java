/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iamaction.tools.testdo.inter.service;

/**
 * write the test running logs
 * @author huuuuxin
 */
public interface TestLogService {
    public void writeLog(String logPath, String caseName, StringBuffer caseLogs);
}
