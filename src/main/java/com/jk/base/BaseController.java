package com.jk.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 基础控制器
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2017/7/8 21:39
 */
public abstract class BaseController {
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * 管理基础路径
     */
    @Value("${adminPath}")
    protected String adminPath;
}
