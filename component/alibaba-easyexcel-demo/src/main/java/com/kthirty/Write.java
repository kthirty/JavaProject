package com.kthirty;

import com.alibaba.excel.EasyExcel;
import com.kthirty.entity.User;

import java.util.*;

/**
 *
 * <br/>
 * @author KThirty
 * @since 2020/5/15 14:43
 */
public class Write {
    /**
     * 获取测试数据
     * <br/>
     * @return java.util.List<com.kthirty.entity.User>
     * @author KThirty
     * @since 2020/5/15 15:11
     */
    public List<User> getData(){
        List<User> list = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            list.add(new User((long)i,"王"+i,i+"@gmail.com"));
        }
        return list;
    }

    /**
     * 简单的写方法，也是常用的
     * <br/>
     * @return void
     * @author KThirty
     * @since 2020/5/15 15:12
     */
    public void simpleWrite(String fileName){
        EasyExcel.write(fileName, User.class).sheet("用户导出数据").doWrite(this.getData());
    }

    /**
     * 忽略某个字段
     * <br/>
     * @param fileName 生成文件路径
     * @param excludeNames 忽略的字段名
     * @return void
     * @author KThirty
     * @since 2020/5/15 15:21
     */
    public void excludeWrite(String fileName,String... excludeNames){
        Set<String> excludeColumnFiledNames = new HashSet<String>(Arrays.asList(excludeNames));
        EasyExcel.write(fileName, User.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("用户导出数据-忽略"+String.join(",",excludeColumnFiledNames)+"字段").doWrite(this.getData());
    }

    /**
     * 指定导出字段
     * <br/>
     * @param fileName 文件名
     * @param includeNames 包含的字段
     * @return void
     * @author KThirty
     * @since 2020/5/15 15:25
     */
    public void includeWrite(String fileName,String... includeNames){
        Set<String> includeColumnFiledNames = new HashSet<String>(Arrays.asList(includeNames));
        EasyExcel.write(fileName, User.class).includeColumnFiledNames(includeColumnFiledNames).sheet("用户导出数据-仅导出"+String.join(",",includeColumnFiledNames)+"字段").doWrite(this.getData());
    }


    public static void main(String[] args) {
        // 需要先创建这个目录
        String path = "D://EasyExcel/";
        String suffix = ".xlsx";
        // 测试简单写
        new Write().simpleWrite(path+"simpleWrite"+suffix);
        // 测试忽略字段
        new Write().excludeWrite(path+"excludeWrite"+suffix,"id");
        // 测试指定字段
        new Write().includeWrite(path+"includeWrite"+suffix,"name");
    }


}
