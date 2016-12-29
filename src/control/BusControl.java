package control;

import domain.*;
import da.*;
import java.util.ArrayList;

public class BusControl{

    BusDa orderDA;
    
    public BusControl(){
        orderDA = new BusDa();
    }
    
    public String getLatestID(){
        return orderDA.getLatestID();
    }
    
    public void shutDown(){
        orderDA.shutDown();
    }
}