package utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReaderManager {

    private static ExcelReaderManager instance;
    private Workbook workbook;
    private final Map<String, List<Map<String, String>>> cache = new HashMap<>();

    private ExcelReaderManager(String excelFilePath) {
        try (FileInputStream fis = new FileInputStream(excelFilePath)) {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file: " + excelFilePath, e);
        }
    }

    public static void initialize(String excelFilePath) {
        if (instance == null) {
            instance = new ExcelReaderManager(excelFilePath);
        }
    }

    public static ExcelReaderManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ExcelReaderManager not initialized. Call initialize() first.");
        }
        return instance;
    }

    public List<Map<String, String>> getSheetData(String sheetName) {
        if (cache.containsKey(sheetName)) {
            return cache.get(sheetName);
        }

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }

        Iterator<Row> rowIterator = sheet.iterator();
        if (!rowIterator.hasNext()) {
            throw new IllegalStateException("Sheet is empty: " + sheetName);
        }

        Row headerRow = rowIterator.next();
        List<String> headers = new ArrayList<>();
        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue().trim());
        }

        List<Map<String, String>> data = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Map<String, String> rowMap = new LinkedHashMap<>();
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String value = formatter.formatCellValue(cell);
                rowMap.put(headers.get(i), value.trim());
            }
            data.add(rowMap);
        }

        cache.put(sheetName, data);
        return data;
    }

    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing workbook: " + e.getMessage());
        }
    }

    public void clearCache() {
        cache.clear();
    }
}
