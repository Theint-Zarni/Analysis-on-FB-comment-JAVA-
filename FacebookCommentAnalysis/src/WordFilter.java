

import java.io.FileInputStream;
import java.sql.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class WordFilter {
	@SuppressWarnings("unused")
	public static void swearWords() {
		Array[] dataList = readExcel();
	}

	@SuppressWarnings("null")
	public static boolean filter(String in) {
		boolean isBad = false;
		String datalist[] = null;
		for (String swearWord : datalist) {
			Pattern pat = Pattern.compile(swearWord, Pattern.CASE_INSENSITIVE);
			Matcher mat = pat.matcher(in);
			while (mat.find()) {
				char[] haha = new char[mat.end() - mat.start()];
				for (int i = 0; i < mat.end() - mat.start(); i++) {
					haha[i] = '*';
				}
				isBad = true;
			}
		}
		return isBad;
	}

	public static Array[] readExcel() {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("D:\\Htet Aung\\ha\\dataList.xlsx"));
			@SuppressWarnings("resource")
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;
			
			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			int cols = 0; // No of columns
			int tmp = 0;
			
			for (int i = 0; i < 10 || i < rows; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if (tmp > cols)
						cols = tmp;
				}
			}


			for (int r = 0; r < rows; r++) {
				row = sheet.getRow(r);
				if (row != null) {
					for (int c = 0; c < cols; c++) {
						cell = row.getCell((short) c);
						if (cell != null) {
							System.out.println("Error");
						}
					}
				}
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return null;
	}

}