package control;

import da.ScheduleDa;
import domain.ScheduleDomain;
import java.util.ArrayList;
public class ScheduleControl {

    private ScheduleDa sDA;

    public ScheduleControl() {
        sDA = new ScheduleDa();
    }
    public ScheduleDomain retrieveDetails(String routeid) {
        return sDA.getRecord(routeid);
    }

     public ScheduleDomain selectRecord(String routeid) {
        return sDA.getRecord(routeid);
    }
  
    public ArrayList<ScheduleDomain> getArray(String passValue) {
         return sDA.getArray(passValue);
     
     }
    
     public String getLatestID(){
        return sDA.getLatestID();
    }
   
   
    public void closeDB(){
        sDA.shutDown();
    }
}