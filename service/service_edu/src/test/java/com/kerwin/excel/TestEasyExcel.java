package com.kerwin.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {

    public static void main(String[] args) {
        //实现excel写操作
        //设置写入文件夹地址和excel文件名称
        String filename = "D:\\table2.xlsx";
        //调用
        /*
         params1: 需要写入到的文件名
         params2: 实体类的class
        */
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i+1);
            data.setSname("kerwin"+i);
            list.add(data);
        }
        //写操作
        //EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(list);
        //读操作
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
}
