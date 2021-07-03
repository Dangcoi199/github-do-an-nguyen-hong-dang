package com.nguyenhongdang.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.nguyenhongdang.dto.ChiTietHDNDTO;

@Component
public class ReadFileExcelUtil {
    public static final int COLUMN_INDEX_PRODUCTNAME = 0;
    public static final int COLUMN_INDEX_QUANTITY = 1;
    public static final int COLUMN_INDEX_PRICE = 2;
	public  List<ChiTietHDNDTO> readExcel(String fileBase64 , String fileName) throws IOException{
		List<ChiTietHDNDTO> chiTietHDNs = new ArrayList<>();
		byte[] bytes = Base64.getDecoder().decode(fileBase64.getBytes());
		String dirFile = "D:\\shopdongho\\chitiethdn";
		File fileDir = new File(dirFile);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		File serveFile= new File(dirFile+File.separator+fileName);
		try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serveFile))) {
			stream.write(bytes);
		} catch (IOException e) {
			 e.printStackTrace();
		}
		
		// Get file
        InputStream inputStream = new FileInputStream(new File(dirFile+File.separator+fileName));
 
        // Get workbook
        Workbook workbook = getWorkbook(inputStream, dirFile+File.separator+fileName);
 
        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);
 
        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
 
            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();
 
            // Read cells and set value for book object
            ChiTietHDNDTO chiTietHDN = new ChiTietHDNDTO();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                case COLUMN_INDEX_PRODUCTNAME:
                	chiTietHDN.setProductName((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_QUANTITY:
                	chiTietHDN.setSoLuong(new BigDecimal((double) cellValue).intValue());
                    break;
                case COLUMN_INDEX_PRICE:
                	chiTietHDN.setDonGia(new BigDecimal((double) cellValue).longValue());
                    break;
                default:
                    break;
                }
 
            }
            chiTietHDNs.add(chiTietHDN);
        }
 
        workbook.close();
        inputStream.close();
        
 
		return chiTietHDNs;
		
	}
	// Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
	// Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }
 
        return cellValue;
    }
}
