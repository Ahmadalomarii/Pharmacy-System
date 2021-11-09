package pharmacy;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author AHMAD
 */
public class MyTimerTask extends TimerTask {
    private final static long ONCE_PER_DAY = 1000*60*60*24;
    private final static int TWO_AM = 12;
    private final static int ZERO_MINUTES = 0;
    private final static int fONE_DAY = 0;

    @Override
    public void run() {
        long currennTime = System.currentTimeMillis();
        long stopTime = currennTime + 2000;
          
            Stock.expiredDate();
            System.out.println("Start Job"+stopTime);
            System.out.println("End Job"+System.currentTimeMillis());
       
    }

    private static Date getTomorrowMorning2AM(){
    Calendar tomorrow = new GregorianCalendar();
    tomorrow.add(Calendar.DATE, fONE_DAY);
    Calendar result = new GregorianCalendar(
      tomorrow.get(Calendar.YEAR),
      tomorrow.get(Calendar.MONTH),
      tomorrow.get(Calendar.DATE),
      TWO_AM,
      ZERO_MINUTES
    );
    return result.getTime();
  }
  
    public static void startTask(){
        MyTimerTask task = new MyTimerTask();
        Timer timer = new Timer();  
        timer.schedule(task,getTomorrowMorning2AM(),ONCE_PER_DAY);
   }
    

}