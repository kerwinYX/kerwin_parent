package com.kerwin.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<DemoData> {

    //一行一行读取内容
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        System.out.println("表数据"+data);
    }

    //读取结束之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    //读取表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }
}
