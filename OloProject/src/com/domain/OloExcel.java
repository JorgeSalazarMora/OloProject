package com.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class OloExcel {
	HojaDespacho hoja = new HojaDespacho();
	static String TAG = "ExelLog";
	 private static final int REQUEST_PATH = 1;
	 
	    static String validadoPor;
	    static String fichaEmpleado;
	    static String pais;
	    static String fecha;
	    
	    
	    
	    
	public OloExcel(HojaDespacho hoja){
		setValidadoPor(hoja.getValidadoPor());
		setFichaEmpleado(hoja.getFichaEmpleado());
		setPais(hoja.getPais());
		
		
		
		
	}
	
	public String getValidadoPor() {
		return validadoPor;
	}
	public void setValidadoPor(String validadoPor) {
		this.validadoPor = validadoPor;
	}
	public String getFichaEmpleado() {
		return fichaEmpleado;
	}
	public void setFichaEmpleado(String fichaEmpleado) {
		this.fichaEmpleado = fichaEmpleado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	

	public static boolean saveExcelFile(Context context, String fileName){
		
		
		//check if  available and not read only
		if(!isExternalStorgeAvailable() || isExternalStorageReadOnly() ){
			
			Log.e(TAG, "Storage not avaible or read only");
			return false;
			
		}
		
		 
		
		boolean success = false;
		
		//New Workbook
		Workbook wb = new HSSFWorkbook();
		
		Cell c = null;
		
		CellStyle cs =  wb.createCellStyle();
		cs.setFillForegroundColor(HSSFColor.LIME.index);
		cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		//New Sheet
		Sheet sheet1 = null;
		sheet1 = wb.createSheet("OLO");
		
		// Generate column headings
		Row row = sheet1.createRow(0);
		Row row2 = sheet1.createRow(1);
		
		
		c= row.createCell(0);
		c.setCellValue("Olo!!!");
		c.setCellStyle(cs);
		
	
		
		c = row.createCell(1);
	    c.setCellValue("Validado Por");
	    c.setCellStyle(cs);
	    
		c= row2.createCell(1);
		c.setCellValue(validadoPor);
		

	    c = row.createCell(2);
	    c.setCellValue("Ficha Empleado");
	    c.setCellStyle(cs);
	    
	    c= row2.createCell(2);
		c.setCellValue(fichaEmpleado);
	    
	    c = row.createCell(3);
	    c.setCellValue("Paiz");
	    c.setCellStyle(cs);
	    
	    c= row2.createCell(3);
		c.setCellValue(pais);
	    
	    /*c = row.createCell(4);
	    c.setCellValue("Fecha");
	    c.setCellStyle(cs);
	    
	    c= row2.createCell(4);
		c.setCellValue(fecha);*/
	    
	    sheet1.setColumnWidth(0,(15*500));
	    sheet1.setColumnWidth(1,(15*500));
	    sheet1.setColumnWidth(2,(15*500));
	    sheet1.setColumnWidth(3,(15*500));
	    sheet1.setColumnWidth(4,(15*500));
		
		//Create a path where we will place our list of object on external storage
	    
	    File file = new File(context.getExternalFilesDir(null), fileName);
	    
	    FileOutputStream os = null;
	    
	    try{
	    	
	    	os= new FileOutputStream(file);
	    	wb.write(os);
	    	Log.w("FileUtils", "Writing file" + file); 
	        success = true; 
	    } catch (IOException e) { 
	        Log.w("FileUtils", "Error writing " + file, e); 
	    } catch (Exception e) { 
	        Log.w("FileUtils", "Failed to save file", e); 
	    } finally { 
	        try { 
	            if (null != os) 
	            	os.close();
	          
	        } catch (Exception ex) { 
	      } 
	    } 
	    return success; 
	    
	}

	public static boolean isExternalStorageReadOnly(){
		
		String extStorageState = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)){
			return true;
		}
		return false;
	}

	public static boolean isExternalStorgeAvailable(){
		String extStorageState = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(extStorageState)){
			return true;
		}
		return false;
		
	}

	public static void readExcelFile(Context context, String filename) { 
		 
	    if (!isExternalStorgeAvailable() || isExternalStorageReadOnly()) 
	    { 
	        Log.e(TAG, "Storage not available or read only"); 
	        return; 
	    } 

	    try{
	        // Creating Input Stream 
	        File file = new File(context.getExternalFilesDir(null), filename); 
	        FileInputStream myInput = new FileInputStream(file);

	        // Create a POIFSFileSystem object 
	        POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

	        // Create a workbook using the File System 
	        HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

	        // Get the first sheet from workbook 
	        HSSFSheet mySheet = myWorkBook.getSheetAt(0);

	        /** We now need something to iterate through the cells.**/
	        Iterator<Row> rowIter = mySheet.rowIterator();

	        while(rowIter.hasNext()){
	            HSSFRow myRow = (HSSFRow) rowIter.next();
	            Iterator cellIter = myRow.cellIterator();
	            while(cellIter.hasNext()){
	                HSSFCell myCell = (HSSFCell) cellIter.next();
	                Log.d(TAG, "Cell Value: " +  myCell.toString());
	                Toast.makeText(context, "cell Value: " + myCell.toString(), Toast.LENGTH_SHORT).show();
	            }
	        }
	    }catch (Exception e){e.printStackTrace(); }

	    return;
	} 



}
