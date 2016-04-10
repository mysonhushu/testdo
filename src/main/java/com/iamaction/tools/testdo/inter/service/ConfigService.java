package com.iamaction.tools.testdo.inter.service;

import com.iamaction.tools.testdo.inter.bean.TestInfo;

/**
 *
 * @author huuuuxin
 */
public interface ConfigService {
    /**
     * read configuration file.
     * @return TestInfo
     */
    public TestInfo readConfig();
}
