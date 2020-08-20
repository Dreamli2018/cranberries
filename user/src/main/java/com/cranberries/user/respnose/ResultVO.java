package com.cranberries.user.respnose;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用响应结果封装对象
 * @param <T>
 */
@Data
public class ResultVO<T> implements Serializable {

    /**
     * 响应状态码
     */
    private String resultCode;

    /**
     * 响应信息
     */
    private String resultMsg;

    /**
     * 响应具体数据
     */
    private T data;

}
