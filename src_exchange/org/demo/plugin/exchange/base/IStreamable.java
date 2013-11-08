package org.demo.plugin.exchange.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;


/**
 * 流化接口
 * @author Yang.Cheng
 * @date Sep 21, 201210:27:57 AM
 * @Copyright(c) ChantSoft Co.,LTD
 */
public interface IStreamable extends Serializable{
    
    /**
     * 把本对象转化为一个流
     * @param os
     */
    public InputStream expressByStream() throws IOException;
    
    /**
     * 从流中读取本对象
     * @param is
     */
    public void expressByObject(InputStream is)  throws IOException;
    
}
