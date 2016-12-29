package control;


import da.*;
import domain.ScheduleDomain;
import java.util.ArrayList;
public class PaymentControl {

    private PaymentDa pDA;

    public PaymentControl() {
        pDA = new PaymentDa();
    }
    
     public String getLatestID(){
        return pDA.getLID();
    }
   
   
    public void closeDB(){
        pDA.shutDown();
    }
}