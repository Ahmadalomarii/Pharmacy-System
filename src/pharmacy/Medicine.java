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
public class Medicine {


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medicine other = (Medicine) obj;
        if (this.NumberOfMedicine != other.NumberOfMedicine) {
            return false;
        }
        return true;
    }

  

   
   int NumberOfMedicine;
    String NameOfMedicine;
    double Coast;
    LocalDate Production_date;
    LocalDate Expiry_date;
     
     public Medicine(int NumberOfMedicine, String NameOfMedicine, double Coast, LocalDate Production_date, LocalDate Expiry_date) {
        this.NumberOfMedicine = NumberOfMedicine;
        this.NameOfMedicine = NameOfMedicine;
        this.Coast = Coast;
        this.Production_date = Production_date;
        this.Expiry_date = Expiry_date;
    }
      public int getNumberOfMedicine() {
        return NumberOfMedicine;
    }

    public void setNumberOfMedicine(int NumberOfMedicine) {
        this.NumberOfMedicine = NumberOfMedicine;
    }

    public String getNameOfMedicine() {
        return NameOfMedicine;
    }

    public void setNameOfMedicine(String NameOfMedicine) {
        this.NameOfMedicine = NameOfMedicine;
    }

    public double getCoast() {
        return Coast;
    }

    public void setCoast(double Coast) {
        this.Coast = Coast;
    }

    public LocalDate getProduction_date() {
        return Production_date;
    }

    public void setProduction_date(LocalDate Production_date) {
        this.Production_date = Production_date;
    }

    public LocalDate getExpiry_date() {
        return Expiry_date;
    }

    public void setExpiry_date(LocalDate Expiry_date) {
        this.Expiry_date = Expiry_date;
    }
      @Override
    public String toString() {
       
 return  "( Number-->" + NumberOfMedicine + "<--Name-->" + NameOfMedicine + "<--Coast-->" + Coast + "<-- )";
    }
     
}
