/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import form.*;
import java.io.IOException;



/**
 *
 * @author AHMAD
 */
public class Pharmacy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
    
    Stock.ReadExcel();
    Transaction.ReadExcelGeneral();
    ONE a=new ONE();
    a.setVisible(true);
    Stock.expiredDate();
    MyTimerTask.startTask();
    
    
  
    }
    
}

