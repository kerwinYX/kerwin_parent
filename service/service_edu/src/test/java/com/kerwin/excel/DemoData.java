package com.kerwin.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DemoData {

    /**
     * 设置excel的表头名
     * */
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名" , index = 1)
    private String sname;
}
