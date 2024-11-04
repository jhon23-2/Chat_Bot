package com.procecingData.procecingDataSpting.service.serviceImpl;

import com.procecingData.procecingDataSpting.service.serviceInterface.ReadXlsFileService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class readFileServiceImpl implements ReadXlsFileService {
    @Override
    public List<Map<String, String>> readFile(MultipartFile multipartFile) {

        List<Map<String, String>> rows;
        try {
            rows = new ArrayList<>();

            Workbook workbook;
            if (multipartFile.getOriginalFilename().endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(multipartFile.getInputStream());
            } else if (multipartFile.getOriginalFilename().endsWith(".xls")) {
                workbook = new HSSFWorkbook(multipartFile.getInputStream());
            } else {
                return null;
            }

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(getCellValueAsString(cell));
            }


            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Map<String, String> rowData = new HashMap<>();
                    for (int j = 0; j < headers.size(); j++) {
                        Cell cell = row.getCell(j);
                        String value = cell != null ? getCellValueAsString(cell) : "";
                        rowData.put(headers.get(j), value);
                    }
                    rows.add(rowData);
                }
            }

            workbook.close();

        } catch (IOException e) {
            return null;
        }

        return rows;
    }


    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
