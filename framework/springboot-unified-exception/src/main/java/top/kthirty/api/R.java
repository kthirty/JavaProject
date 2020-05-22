package top.kthirty.api;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code; // 响应码
    private boolean success; // 是否成功
    private T data; // 承载数据
    private String message; // 响应码描述

    /**
     * 构造函数
     * @param resultCode 响应码
     * @param data 承载数据
     * @param message 响应码描述
     */
    private R(IResultCode resultCode,T data,String message){
        this.code = resultCode.getCode();
        this.data = data;
        this.message = message==null?resultCode.getMessage():message;
        this.success = this.code == ResultCode.SUCCESS.code;
    }
    private R(IResultCode resultCode,T data){
        this(resultCode,data,null);
    }
    private R(IResultCode resultCode){
        this(resultCode,null,null);
    }
    private R(IResultCode resultCode,String message){
        this(resultCode,null,message);
    }

    // 成功响应方法，成功方法不支持自定义响应码
    public static <T> R<T> success(){ return new R<T>(ResultCode.SUCCESS); }
    public static <T> R<T> success(T data){ return new R<>(ResultCode.SUCCESS,data); }
    public static <T> R<T> success(T data,String message){ return new R<>(ResultCode.SUCCESS,data,message); }

    // 失败响应方法
    public static <T> R<T> fail(){ return new R<>(ResultCode.FAILURE); }
    public static <T> R<T> fail(String message){ return new R<>(ResultCode.FAILURE,message); }
    public static <T> R<T> fail(IResultCode resultCode){ return new R<>(resultCode); }
    public static <T> R<T> fail(IResultCode resultCode,String message){ return new R<>(resultCode,message); }

    // 特殊需求使用方法
    public static <T> R<T> instance(ResultCode resultCode,T data,String message){ return new R<>(resultCode,data,message); }

    // 处理对象方法
    public R message(String message){
        this.message = message;
        return this;
    }
    public R success(boolean success){
        this.success = success;
        return this;
    }
    public R data(T data){
        this.data = data;
        return this;
    }


}
