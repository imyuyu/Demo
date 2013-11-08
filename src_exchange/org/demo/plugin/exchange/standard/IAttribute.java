package org.demo.plugin.exchange.standard;

/**
 * @title IAttribute.java
 * @package com.seeyon.v3x.plugin.exchange.standard
 * @description 属性接口
 * @author LuZhiYuan   
 * @update 2012-9-24 下午10:20:33
 * @version V1.0  
 * Copyright (c)2012 chantsoft-版权所有
 */

public interface IAttribute {

    /**
     * 返回属性名。
     * @return 属性名称
     */
    public String getName();

    /**
     * 返回属性值
     * @return 属性值
     */
    public Object getValue();
}
