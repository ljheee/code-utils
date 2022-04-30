package com.ljheee.util.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuNameExportVO {

    @ExcelProperty(value = "id", index = 0)
    private Long id;


    @ExcelProperty(value = "originName", index = 1)
    private String originName;

    @ExcelProperty(value = "newName", index = 2)
    private String newName;
}