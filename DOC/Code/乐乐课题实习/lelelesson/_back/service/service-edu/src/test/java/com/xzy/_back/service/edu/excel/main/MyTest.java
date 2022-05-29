package com.xzy._back.service.edu.excel.main;

import com.alibaba.excel.EasyExcel;
import com.xzy._back.service.edu.excel.listener.ExcelListener;
import com.xzy._back.service.edu.excel.model.DemoData;
import java.util.ArrayList;
import java.util.List;

public class MyTest {
    public static void main(String[] args) {
        // writeToExcel();
        readFromExcel();
    }
    // 实现 excel 写操作
    public static void writeToExcel(){
        // 1、设置写入excel数据的文件夹路径和名称
        String filePath = "D:\\student.xls";
        /*
         * 2、调用 EasyExcel 中的方法实现写操作，流对象会自动关闭
         *  参数 1：文件路径名称
         *  参数 2：实体类的 class 类型
         */
        EasyExcel.write(filePath, DemoData.class).sheet("学生信息列表").doWrite(getData());
    }
    // 实现 excel 写操作
    public static void readFromExcel(){
        // 1、设置写入excel数据的文件夹路径和名称
        String filePath = "D:\\student.xls";
        EasyExcel.read(filePath, DemoData.class, new ExcelListener()).sheet().doRead();
    }
    // 方法：返回 List 集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<DemoData>();
        for(int i=100; i<=130; i++){
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("Tony" + i);
            list.add(demoData);
        }
        return list;
    }
}