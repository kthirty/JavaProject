package com.kthirty;

import com.alibaba.excel.EasyExcel;
import com.kthirty.entity.User;

/**
 * 读操作，导入
 * <br/>
 * @author KThirty
 * @since 2020/5/15 16:32
 */
public class Read {
    public static void main(String[] args) {
        // 需要先创建这个目录
        String path = "D://EasyExcel/";
        String suffix = ".xlsx";
        new Read().simpleRead(path+"simpleWrite"+suffix);
    }


    /**
     * 简单的读
     * <br/>
     * @param fileName Excel文件名
     * @return void
     * @author KThirty
     * @since 2020/5/15 16:36
     */
    public void simpleRead(String fileName){
        EasyExcel.read(fileName, User.class,new UserImportListener()) // 指定实体类
                .sheet() // 指定第几个sheet页
                .doRead(); // read操作
    }
}
