package com.ljheee.util.excel;

import com.alibaba.excel.EasyExcel;
import com.ljheee.util.file.FileOperate;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author lijianhua.
 */
public class ExcelOperate {


    public static void main(String[] args) throws Exception {
        // 获取Excel数据
        File file = FileOperate.openFromResources("/file.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        SkuNameExcelListener excelListener = new SkuNameExcelListener();
        EasyExcel.read(fileInputStream, SkuNameExportVO.class, excelListener).sheet().doRead();
        List<SkuNameExportVO> dataList = excelListener.getParseResult();

        EasyExcel.write(new File("output.xlsx"), SkuNameExportVO.class).sheet("工作表1").doWrite(dataList);
        fileInputStream.close();


    }


}
