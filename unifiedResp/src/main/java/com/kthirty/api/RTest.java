package com.kthirty.api;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * 测试R 类
 */
public class RTest {
    public static void main(String[] args) {
        System.out.println("\n——————————————————成功————————————————————————");
        System.out.println("无承载数据成功__"+ JSON.toJSONString(R.success()));
        System.out.println("承载数据成功__"+JSON.toJSONString(R.success(new String[]{"承载数据"})));
        System.out.println("承载数据并修改描述成功__"+JSON.toJSONString(R.success(new String[]{"承载数据"},"修改的描述信息")));

        System.out.println("\n——————————————————失败————————————————————————");
        System.out.println("默认失败__"+JSON.toJSONString(R.fail()));
        System.out.println("修改错误信息失败__"+JSON.toJSONString(R.fail("修改后的错误信息")));
        System.out.println("修改ResultCode(缺失参数)失败__"+JSON.toJSONString(R.fail(ResultCode.PARAM_MISS)));
        System.out.println("修改ResultCode并自定义信息失败__"+JSON.toJSONString(R.fail(ResultCode.PARAM_MISS,"请检查你的请求参数")));

        System.out.println("\n——————————————————特殊————————————————————————");
        System.out.println("自定义__"+JSON.toJSONString(R.instance(ResultCode.SUCCESS,new String[]{"承载数据"},"自定义的返回信息")));

        System.out.println("\n——————————————————创建后修改————————————————————————");
        System.out.println("修改消息__"+R.success().message("创建之后修改的消息"));
        System.out.println("修改数据__"+R.success("11").data("创建之后修改的承载数据"));
        System.out.println("修改成功标识__"+R.success().success(false));
    }
}
