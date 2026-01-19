package com.pcbuild.util;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class ExcelUtil {

    public static Workbook getWorkbook(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            Workbook wb = new XSSFWorkbook();
            wb.createSheet("Sheet1");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                wb.write(fos);
            }
            return wb;
        }
        return new XSSFWorkbook(new FileInputStream(file));
    }
}
