package pharmacy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author AHMAD
 */
public class Bill {

   

  
 Medicine []Medicines;
   double BillAmount;
   double Discount = 0;
    LocalDate datetime;
    
    public Bill(Medicine[] Medicines, double Discount , LocalDate datetime) {
        this.Medicines = Medicines;
       this.Discount = Discount;
       this.BillAmount=Calculate(Medicines) - Calculate(Medicines)/Discount;
        this.datetime = datetime;
    }
    public Bill(Medicine[] Medicines, LocalDate datetime) {
        this.Medicines = Medicines;
       this.datetime = datetime;
       this.BillAmount=Calculate(Medicines);
    }
      public Medicine[] getMedicines() {
        return Medicines;
    }

    public void setMedicines(Medicine[] Medicines) {
        this.Medicines = Medicines;
    }

    public double getBillAmount() {
        return BillAmount;
    }

    public void setBillAmount(double BillAmount) {
        this.BillAmount = BillAmount;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }
    public double Calculate(Medicine[] Array){
  double sum=0;
     for(int i=0;i<Array.length;i++){
    sum=sum+Array[i].getCoast();
     }
    return sum;
    }
     @Override
    public String toString() {
        String Med="";
        for(int i=0;i<Medicines.length;i++){
        Med = Med+(i+1)+"- "+ Medicines[i].toString()+"\n";                              
        }
        
         return "BillAmount  = "+BillAmount+" \t Discount = "+Discount+"\t \nDateTime = "+datetime+"\n \t Medicines \t \n " +
                 "------------------------------------------------------------------------\n"+Med+
                 "------------------------------------------------------------------------\n \t  THANK YOU "; 
           }
    
    
    
}
