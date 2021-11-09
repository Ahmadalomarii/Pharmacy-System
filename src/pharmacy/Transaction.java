package pharmacy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static pharmacy.Stock.edicinelnsidestock;

/**
 *
 * @author AHMAD
 */
public class Transaction {

    int Phone_Number = 0 ;
    Medicine []Medicines;
    LocalDate  datetime;
    Bill BillAmount;
    static final ArrayList<Transaction> GeneralTransaction= new ArrayList<Transaction>();
    static  XSSFWorkbook workbookP = new XSSFWorkbook();
    static final String filePath=".\\datafiles\\PatiantTransaction.xlsx";
    FileOutputStream outstream = null;
    FileInputStream  file=null;
    static XSSFWorkbook work=null;
       
  
 

    public Transaction(Medicine[] Medicines, LocalDate datetime, Bill BillAmount) {
        this.Medicines = Medicines;
        this.datetime = datetime;
        this.BillAmount = BillAmount;
        Stock.DeleteMedicineS(Medicines);
      GeneralTransaction.add(this);
      WriteExcelGeneral();
        
    }

    public Transaction(int Phone_Number, Medicine[] Medicines, LocalDate datetime, Bill BillAmount) {
        this.Phone_Number = Phone_Number;
        this.Medicines = Medicines;
        this.datetime = datetime;
        this.BillAmount = BillAmount;
        Stock.DeleteMedicineS(Medicines);
        WriteExcelPatiant(Phone_Number);
    }
  
    
    
    public int getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(int Phone_Number) {
        this.Phone_Number = Phone_Number;
    }

    public Medicine[] getMedicines() {
        return Medicines;
    }

    public void setMedicines(Medicine[] Medicines) {
        this.Medicines = Medicines;
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    public Bill getBillAmount() {
        return BillAmount;
    }

    public void setBillAmount(Bill BillAmount) {
        this.BillAmount = BillAmount;
    }
    @Override
    public String toString() {
    return "Transaction{" + "Phone_Number=" + Phone_Number + ", Medicines=" + Medicines + ", datetime=" + datetime + ", BillAmount=" + BillAmount + '}';
    }
    public boolean IsPatiant(){
    if(Phone_Number!=0){return true;}
    return false;
    
    }


     public static void WriteExcelGeneral() {
      try {
          XSSFWorkbook workbook = new XSSFWorkbook();
          XSSFSheet sheet = workbook.createSheet("GeneralTransaction");
          
          int rows = GeneralTransaction.size();
          int colum=5;
          
          for(int i=0;i<rows;i++){
              XSSFRow row = sheet.createRow(i);
              XSSFCell cell=row.createCell(0);
              double BillAmount = GeneralTransaction.get(i).getBillAmount().BillAmount;
              cell.setCellValue(BillAmount);
              XSSFCell cell1=row.createCell(1);
              LocalDate LocalDatrT = GeneralTransaction.get(i).getDatetime();
             cell1.setCellValue(LocalDatrT.toString());
              for(int j=0;j<GeneralTransaction.get(i).Medicines.length;j++){
              XSSFCell cell2=row.createCell(j+2);
              int Namber = GeneralTransaction.get(i).Medicines[j].NumberOfMedicine;
              cell2.setCellValue(Namber);
              }
             
           
          }
          String filePath=".\\datafiles\\GeneralTransaction.xlsx";
          
          FileOutputStream outstream =new FileOutputStream(filePath);
          
          workbook.write(outstream);
          outstream.close();
        
      } catch (IOException ex) {
          Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    
    }
   
      public static void ReadExcelGeneral(){
        
           
      try {
        
          String excelFilebath = ".\\datafiles\\GeneralTransaction.xlsx";
         FileInputStream inputstream = new FileInputStream(excelFilebath);
        XSSFWorkbook workbook=new XSSFWorkbook(inputstream);
         
          XSSFSheet sheet = workbook.getSheet("GeneralTransaction");
           int rows = sheet.getLastRowNum();
          
          for(int i=0;i<rows;i++){
          XSSFRow row=sheet.getRow(i);
          XSSFCell cell0=row.getCell(0);
          XSSFCell cell1=row.getCell(1);
          double BillAmount=cell0.getNumericCellValue();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          LocalDate LocalDatej=LocalDate.parse(cell1.getStringCellValue().toString(),formatter);
          Medicine [] Med  =new Medicine[row.getLastCellNum()-2];
          for(int j=2;j<row.getLastCellNum();j++){
          XSSFCell cell2=row.getCell(j);
          int number=(int)Math.round( cell2.getNumericCellValue());
          Medicine a=Stock.GetMedicine(number);
          Med[j-2]=a;
          }
     
         Bill B =new Bill(Med, LocalDatej);
         Transaction T=new Transaction(Med, LocalDatej, B);
          GeneralTransaction.add(T);
             
          }
          inputstream.close();
         
          
      } catch (Exception ex) {
          Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
    
      } }
  
      public  void WriteExcelPatiant(int phoneNumber ) {
      try {
         file =new FileInputStream(filePath);
         work =new XSSFWorkbook(file);
         workbookP=work;
         XSSFSheet sheetR =null;
          XSSFSheet sheet = null;
          if(work.getSheetIndex(""+phoneNumber)!=-1){
           sheetR = work.getSheet(""+phoneNumber); 
              System.out.println("SheetR"+sheetR.getSheetName()+"  "+sheetR.getLastRowNum());
              sheet=sheetR;
          }
           if(workbookP.getSheetIndex(""+phoneNumber)==-1){
           sheet = workbookP.createSheet(""+phoneNumber);
           
          }
          else{
            sheet=workbookP.getSheet(""+phoneNumber);
             
          }
       
         int ad= workbookP.getNumberOfSheets();
        
          int rows = sheet.getLastRowNum();
          int colum=5;
           XSSFRow row = sheet.createRow(rows+1);
              XSSFCell cell=row.createCell(0);
              double BillAmounts =this.BillAmount.BillAmount; 
              cell.setCellValue(BillAmounts);
              XSSFCell cell1=row.createCell(1);
             LocalDate LocalDatrT = this.datetime;
             cell1.setCellValue(LocalDatrT.toString());
              for(int j=0;j<this.Medicines.length;j++){
              XSSFCell cell2=row.createCell(j+2);
              int Namber = this.Medicines[j].NumberOfMedicine;
              cell2.setCellValue(Namber);
             
          }
         outstream = new FileOutputStream(filePath) ;
         
          workbookP.write(outstream);
          outstream.close();
        
      } catch (Exception ex) {
          Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
      } 
        
    
    }
    
}
