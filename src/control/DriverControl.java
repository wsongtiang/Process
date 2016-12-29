package control;

import da.*;
import ui.*;
import domain.*;
import control.*;

public class DriverControl{
    
    private DriverDa staffDA;
    
    public DriverControl(){
        staffDA = new DriverDa();
    }
    
    public StaffDomain selectRecord(String staffid){
        return staffDA.getRecord(staffid);
    }
    
//    public void addRecord(StaffDomain staff){
//        staffDA.addRecord(staff);
//    }
//    
//    public void updateRecord(StaffDomain staff){
//        staffDA.updateRecord(staff);
//    }
//    
//    public void deleteRecord(String staffid){
//        staffDA.deleteRecord(staffid);
//    }
    
    public String getLatestID(){
        return staffDA.getLatestID();
    }
    
    public String getLatestUserID(){
        return staffDA.getLatestUsID();
    }
    
    public void closeDB(){
        staffDA.shutDown();
    }
}