package pharmacy;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author AHMAD
 */
public class Stock {
    
  static final ArrayList<Medicine> edicinelnsidestock = new ArrayList<Medicine>();
 static final  ArrayList<Medicine> expired = new ArrayList<Medicine>();

  
    public static Medicine[] getExpired() {
        Medicine [] m=new Medicine[expired.size()];
        for(int i=0;i<expired.size();i++){
        m[i]=expired.get(i);
        }
        return m;
    }
     public static Medicine[] getAllMedicineInStock() {
        Medicine [] m=new Medicine[edicinelnsidestock.size()];
        for(int i=0;i<edicinelnsidestock.size();i++){
        m[i]=edicinelnsidestock.get(i);
        }
        return m;
    }
 
 
   public static void AddMedicines(Medicine[] M) {
   for(int i=0;i<M.length;i++){
  edicinelnsidestock.add(M[i]);
   }
  
   WriteExcel();
   }
   public static void DeleteMedicine(Medicine M){
   for(int i=0;i<edicinelnsidestock.size();i++){
   if(M.equals(edicinelnsidestock.get(i))){edicinelnsidestock.remove(i);break;}
   }
   WriteExcel();
   }
   public static void DeleteMedicineS(Medicine[] M){
   for(int i=0;i<M.length;i++){
       DeleteMedicine(M[i]);
   }
   WriteExcel();
   }
   
   public static boolean CheackMedicine(int Number){
   for(int i=0;i<edicinelnsidestock.size();i++){
   if(Number==(edicinelnsidestock.get(i).NumberOfMedicine)){return true;}
   }
    return false;
   }
    public static Medicine GetMedicine(int Number){
    for(int i=0;i<edicinelnsidestock.size();i++){
   if(Number==(edicinelnsidestock.get(i)).NumberOfMedicine){return edicinelnsidestock.get(i);}
   }
    return null;
  }
    public static int GetMedicine(String Name){
      int count = -1;
    for(int i=0;i<edicinelnsidestock.size();i++){
   if(Name.equals(edicinelnsidestock.get(i).getNameOfMedicine())){count++;}
   }
    return count;
  }
    
      public static void WriteExcel() {
      try {
          XSSFWorkbook workbook = new XSSFWorkbook();
          XSSFSheet sheet = workbook.createSheet("Stock");
          
          int rows = edicinelnsidestock.size();
          int colum=5;
          
          for(int i=0;i<rows;i++){
              XSSFRow row = sheet.createRow(i);
              XSSFCell cell=row.createCell(0);
              int NumberOfMedicine = edicinelnsidestock.get(i).getNumberOfMedicine();
              cell.setCellValue(NumberOfMedicine);
              XSSFCell cell1=row.createCell(1);
              String NameOfMedicine = edicinelnsidestock.get(i).getNameOfMedicine() ;
              cell1.setCellValue(NameOfMedicine);
              XSSFCell cell2=row.createCell(2);
              double Coast = edicinelnsidestock.get(i).getCoast();
              cell2.setCellValue(Coast);
              XSSFCell cell3=row.createCell(3);
              LocalDate Production_date = edicinelnsidestock.get(i).getProduction_date();
              cell3.setCellValue(Production_date.toString());
              XSSFCell cell4=row.createCell(4);
              LocalDate Expiry_date= edicinelnsidestock.get(i).getExpiry_date();
              cell4.setCellValue(Expiry_date.toString());
           
          }
          String filePath=".\\datafiles\\Stock.xlsx";
          
          FileOutputStream outstream =new FileOutputStream(filePath);
          
          workbook.write(outstream);
          outstream.close();
         // System.out.println("pharmacy.Controler.WriteExcel()");
      } catch (IOException ex) {
          Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    
    }
   
      public static void ReadExcel(){
           
      try {
         
        String excelFilebath = ".\\datafiles\\Stock.xlsx";
        FileInputStream inputstream = new FileInputStream(excelFilebath);
        XSSFWorkbook workbook=new XSSFWorkbook(inputstream);
         XSSFSheet sheet = workbook.getSheet("Stock");
          
          int rows = sheet.getLastRowNum();
         for(int i=0;i<rows;i++){
          XSSFRow row=sheet.getRow(i);
          XSSFCell cell0=row.getCell(0);
          XSSFCell cell1=row.getCell(1);
          XSSFCell cell2=row.getCell(2);
          XSSFCell cell3=row.getCell(3);
          XSSFCell cell4=row.getCell(4);
          
         int NumberOfMedicine = (int)Math.round(cell0.getNumericCellValue());
         String NameOfMedicine = cell1.getStringCellValue();
        double Coast = cell2.getNumericCellValue();
    
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         LocalDate Production_date=LocalDate.parse(cell3.getStringCellValue().toString(),formatter);  
         LocalDate Expiry_date = LocalDate.parse(cell4.getStringCellValue(),formatter); 
         Medicine M =new Medicine(NumberOfMedicine, NameOfMedicine, Coast, Production_date, Expiry_date);
              edicinelnsidestock.add(M);
             
          }
          inputstream.close();
         
          
      } catch (FileNotFoundException ex) {
          Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
      } 
      } 
      
      public static void expiredDate(){
      for(int i=0 ;i<edicinelnsidestock.size();i++){
          LocalDate a= LocalDate.now();
      if(edicinelnsidestock.get(i).Expiry_date.isBefore(a) ){
      expired.add(edicinelnsidestock.get(i));
          //System.out.println("Medicine Name "+edicinelnsidestock.get(i).NameOfMedicine );
      }
      
      }
      
      }
   
    
}
