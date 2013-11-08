package org.demo.plugin.exchange.standard;

import java.util.List;

import org.demo.plugin.exchange.exception.StandardException;

/**
 * @title IOAData.java
 * @package com.seeyon.v3x.plugin.exchange.standard
 * @description 标准化接口
 * @author LuZhiYuan   
 * @update 2012-9-24 下午10:19:13
 * @version V1.0  
 * Copyright (c)2012 chantsoft-版权所有
 */
public interface IOAData {
	/**
     * 从xml片段中装载数据
     * @param xmlSegment xml片段
     * @throws OAInterfaceException OAInterfaceException
     */
    public void loadFromXMLSegment(XMLSegments xmlSegment) throws StandardException;

    /**
     * 将实体数据存储数据到xml片段
     * @param displayName 定义IOAData的显示名
     * @return xml片段
     * @throws OAInterfaceException OAInterfaceException
     */
    public XMLSegments saveToXMLSegment(String displayName) throws StandardException;

    /**
     * 返回扩展属性列表
     * @return 属性列表
     */
    public List<IAttribute> getExtendedAttributeList();
}
