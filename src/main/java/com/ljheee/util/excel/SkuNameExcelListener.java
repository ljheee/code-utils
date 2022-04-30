package com.ljheee.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SkuNameExcelListener extends AnalysisEventListener<SkuNameExportVO> {
    @Getter
    private final List<SkuNameExportVO> parseResult;


    public SkuNameExcelListener() {
        this.parseResult = new ArrayList<>();
    }

    @Override
    public void invoke(SkuNameExportVO skuNameExportVO, AnalysisContext analysisContext) {
        // 计算新name
        if (skuNameExportVO.getOriginName() != null) {
            skuNameExportVO.setNewName(skuNameExportVO.getOriginName() + " hot");
        }
        parseResult.add(skuNameExportVO);

        System.out.println(skuNameExportVO);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        if (context != null && context.readRowHolder() != null) {
            log.info("SkuNameExcelListener,rowIndex={}", context.readRowHolder().getRowIndex());
        } else {
            log.info("SkuNameExcelListener,exception={}", exception);
        }
    }
}