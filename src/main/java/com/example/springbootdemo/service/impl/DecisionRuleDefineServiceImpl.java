package com.example.springbootdemo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootdemo.dao.DecisionRuleDefineDao;
import com.example.springbootdemo.entity.DecisionRuleDefineEntity;
import com.example.springbootdemo.service.DecisionRuleDefineService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ex_jingjintao
 */
@Service("decisionRuleDefineService")
public class DecisionRuleDefineServiceImpl extends ServiceImpl<DecisionRuleDefineDao, DecisionRuleDefineEntity> implements DecisionRuleDefineService {


    @Override
    public void downloadAllData() {
        List<DecisionRuleDefineEntity> list = list(new QueryWrapper<DecisionRuleDefineEntity>().orderByDesc("rule_define_id"));
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("定义表");

        HSSFRow row = sheet.createRow(0);

        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

//        HSSFCell cell = row.createCell(0);
//        cell.setCellValue("创建人");
//        HSSFCell cell1 = row.createCell(1);
//        cell1.setCellValue("创建时间");
//        HSSFCell cell2 = row.createCell(2);
//        cell2.setCellValue("修改人");
//        HSSFCell cell3 = row.createCell(3);
//        cell3.setCellValue("修改时间");
//        HSSFCell cell4 = row.createCell(4);
//        cell4.setCellValue("失效人");
//        HSSFCell cell5 = row.createCell(5);
//        cell5.setCellValue("失效时间");
//        HSSFCell cell6 = row.createCell(6);
//        cell6.setCellValue("是否有效标识");
//        HSSFCell cell7 = row.createCell(7);
//        cell7.setCellValue("定义ID");
        HSSFCell cell8 = row.createCell(8);
        cell8.setCellValue("定义名称");
        cell8.setCellStyle(cellStyle);
        HSSFCell cell9 = row.createCell(9);
        cell9.setCellValue("规则类型");
        cell9.setCellStyle(cellStyle);
        HSSFCell cell10 = row.createCell(10);
        cell10.setCellValue("运行方式");
        cell10.setCellStyle(cellStyle);
        HSSFCell cell11 = row.createCell(11);
        cell11.setCellValue("事实类型");
        cell11.setCellStyle(cellStyle);
        HSSFCell cell12 = row.createCell(12);
        cell12.setCellValue("结论类型");
        cell12.setCellStyle(cellStyle);
        HSSFCell cell13 = row.createCell(13);
        cell13.setCellValue("定义code");
        cell13.setCellStyle(cellStyle);
        HSSFCell cell14 = row.createCell(14);
        cell14.setCellValue("别名");
        cell14.setCellStyle(cellStyle);

        for (int i = 0; i < list.size(); i++) {
            DecisionRuleDefineEntity ruleDefineEntity = list.get(i);
            HSSFRow sheetRow = sheet.createRow(i + 1);

            HSSFCell cell = sheetRow.createCell(8);
            cell.setCellValue(ruleDefineEntity.getRuleDefineName());
            cell.setCellStyle(cellStyle);

            HSSFCell cell_9 = sheetRow.createCell(9);
            cell_9.setCellValue(ruleDefineEntity.getRuleType());
            cell_9.setCellStyle(cellStyle);

            HSSFCell cell_10 = sheetRow.createCell(10);
            cell_10.setCellValue(ruleDefineEntity.getExecuteType());
            cell_10.setCellStyle(cellStyle);

            HSSFCell cell_11 = sheetRow.createCell(11);
            cell_11.setCellValue(ruleDefineEntity.getFactType());
            cell_11.setCellStyle(cellStyle);

            HSSFCell cell_12 = sheetRow.createCell(12);
            cell_12.setCellValue(ruleDefineEntity.getConclusionType());
            cell_12.setCellStyle(cellStyle);

            HSSFCell cell_13 = sheetRow.createCell(13);
            cell_13.setCellValue(ruleDefineEntity.getBusinessType());
            cell_13.setCellStyle(cellStyle);

            HSSFCell cell_14 = sheetRow.createCell(14);
            cell_14.setCellValue(ruleDefineEntity.getAlias());
            cell_14.setCellStyle(cellStyle);
        }
        sheet.autoSizeColumn(12);
        sheet.autoSizeColumn(8);
        sheet.autoSizeColumn(13);
        sheet.autoSizeColumn(14);

        try {
            //将数据存到xls文件中
            OutputStream outputStream = new FileOutputStream("D:/test.xls");
            workbook.write(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取excel
     */
    private static List<DecisionRuleDefineEntity> readExcel() {
        List<DecisionRuleDefineEntity> list = new ArrayList<>();
        HSSFWorkbook workbook = null;
        try {
            InputStream inputStream = new FileInputStream("D:/test.xls");
            workbook = new HSSFWorkbook(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            HSSFSheet sheet = workbook.getSheetAt(i);
            if (sheet == null)
                continue;
            for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                HSSFRow row = sheet.getRow(j);
                if (row == null)
                    continue;
                DecisionRuleDefineEntity defineEntity = new DecisionRuleDefineEntity();
                HSSFCell cell = row.getCell(8);
                if (cell == null)
                    continue;
                defineEntity.setRuleDefineName(cell.getStringCellValue());
                cell = row.getCell(13);
                if (cell == null)
                    continue;
                defineEntity.setBusinessType(cell.getStringCellValue());
                cell = row.getCell(14);
                if (cell == null)
                    continue;
                defineEntity.setAlias(cell.getStringCellValue());
                list.add(defineEntity);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<DecisionRuleDefineEntity> list = readExcel();
        System.out.println(list.size());
    }
}