package utilities.excelReader;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public final class ExcelReader {

    private static final String EXCEL_FILE = "excel/TestData.xlsx";

    private ExcelReader() {
        // Prevent instantiation
    }

    // OPTION A: Returns a specific test case using the Test Case ID
    public static Map<String, String> getTestData(String sheetName, String testCaseId) {

        Map<String, String> data = new HashMap<>();

        try (Workbook workbook = getWorkbook()) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException(
                        "Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                throw new RuntimeException(
                        "Header row not found in sheet: " + sheetName);

            }

            int rowCount = sheet.getLastRowNum();

            for (int i = 1; i <= rowCount; i++) {

                Row row = sheet.getRow(i);
                if (row == null) {

                    continue;

                }

                String currentTcId =
                        getCellValue(row.getCell(0));

                if (testCaseId.equalsIgnoreCase(currentTcId)) {

                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {

                        String columnName =
                                getCellValue(headerRow.getCell(j));

                        String value =
                                getCellValue(row.getCell(j));

                        data.put(columnName, value);

                    }

                    break;

                }

            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error reading test case '"
                            + testCaseId
                            + "' from sheet '"
                            + sheetName + "'.",
                    e);

        }

        return data;

    }

    // OPTION B: Returns the entire sheet as Object[][]
    public static Object[][] getSheetData(String sheetName) {

        try (Workbook workbook = getWorkbook()) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException(
                        "Sheet not found: " + sheetName);

            }

            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                throw new RuntimeException(
                        "Header row not found in sheet: " + sheetName);

            }

            int rowCount = sheet.getLastRowNum();
            int colCount = headerRow.getLastCellNum();

            Object[][] data =
                    new Object[rowCount][colCount];

            for (int i = 1; i <= rowCount; i++) {

                Row row = sheet.getRow(i);

                if (row == null) {

                    continue;

                }

                for (int j = 0; j < colCount; j++) {

                    data[i - 1][j] =
                            getCellValue(row.getCell(j));

                }

            }

            return data;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error reading sheet: " + sheetName,
                    e);

        }

    }

    // Returns the entire sheet as Map<String,String>
    public static Object[][] getSheetDataAsMap(String sheetName) {

        try (Workbook workbook = getWorkbook()) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {

                throw new RuntimeException(
                        "Sheet not found: " + sheetName);

            }

            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {

                throw new RuntimeException(
                        "Header row not found in sheet: " + sheetName);

            }

            int rowCount = sheet.getLastRowNum();

            Object[][] data =
                    new Object[rowCount][1];

            for (int i = 1; i <= rowCount; i++) {

                Row row = sheet.getRow(i);

                if (row == null) {

                    continue;

                }

                Map<String, String> rowData =
                        new HashMap<>();

                for (int j = 0; j < headerRow.getLastCellNum(); j++) {

                    rowData.put(
                            getCellValue(headerRow.getCell(j)),
                            getCellValue(row.getCell(j)));

                }

                data[i - 1][0] = rowData;

            }

            return data;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error reading sheet as Map: " + sheetName,
                    e);

        }

    }

    // Returns a single row as Map<String,String> for TestNG DataProvider
    public static Object[][] getTestDataAsMap(String sheetName, String testCaseId) {

        return new Object[][]{
                {getTestData(sheetName, testCaseId)}
        };

    }

    // Returns multiple rows with the same Test Case ID as Map<String, String>
// for TestNG DataProvider
    public static Object[][] getTestDataAsMapMultipleRows(String sheetName, String testCaseId) {

        try (Workbook workbook = getWorkbook()) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {

                throw new RuntimeException("Sheet not found: " + sheetName);

            }

            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {

                throw new RuntimeException("Header row not found in sheet: " + sheetName);

            }

            int rowCount = sheet.getLastRowNum();

            java.util.List<Map<String, String>> matchingRows =
                    new java.util.ArrayList<>();

            for (int i = 1; i <= rowCount; i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                String currentTestCaseId = getCellValue(row.getCell(0));

                if (testCaseId.equalsIgnoreCase(currentTestCaseId)) {

                    Map<String, String> rowData = new HashMap<>();

                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {

                        String columnName = getCellValue(headerRow.getCell(j));

                        String value = getCellValue(row.getCell(j));

                        rowData.put(columnName, value);
                    }

                    matchingRows.add(rowData);
                }
            }

            if (matchingRows.isEmpty()) {

                throw new RuntimeException("No test data found for test case '"
                                + testCaseId
                                + "' in sheet '"
                                + sheetName
                                + "'.");
            }

            Object[][] data = new Object[matchingRows.size()][1];

            for (int i = 0; i < matchingRows.size(); i++) {

                data[i][0] = matchingRows.get(i);
            }

            return data;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error reading multiple rows for test case '"
                            + testCaseId
                            + "' from sheet '"
                            + sheetName
                            + "'.",
                    e);
        }
    }

    // Opens the workbook from resources
    private static Workbook getWorkbook() {

        try {

            InputStream inputStream = ExcelReader.class
                    .getClassLoader()
                    .getResourceAsStream(EXCEL_FILE);

            if (inputStream == null) {

                throw new RuntimeException(
                        "Excel file not found: " + EXCEL_FILE);

            }

            return new XSSFWorkbook(inputStream);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to open Excel file: " + EXCEL_FILE,
                    e);

        }

    }

    // Converts any Excel cell type to String
    private static String getCellValue(
            Cell cell) {

        if (cell == null) {

            return "";

        }

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                return String.valueOf(
                        (long) cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(
                        cell.getBooleanCellValue());

            case FORMULA:
                return cell.getCellFormula();

            case BLANK:
                return "";

            default:
                return "";

        }

    }

}



