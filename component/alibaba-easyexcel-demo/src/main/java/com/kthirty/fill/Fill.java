package com.kthirty.fill;

import com.alibaba.excel.EasyExcel;
import com.kthirty.Write;

/**
 * 填充：给定模板，填充出新的Excel文件
 * <br/>
 * @author KThirty
 * @since 2020/5/15 17:00
 */
public class Fill {
    public static void main(String[] args) {
        // 需要先创建这个目录
        String path = "D://EasyExcel/";
        String suffix = ".xlsx";
        new Fill().simpleFill(path+"fill_template"+suffix,path+"fill_template_after"+suffix);
    }


    /**
     * 根据模板填充Excel
     * <br/>
     * @param templateFileName 模板文件名称
     * @param fileName 生成文件名
     * @return void
     * @author KThirty
     * @since 2020/5/15 17:01
     */
    public void simpleFill(String templateFileName,String fileName){
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(new Write().getData());
    }
}
