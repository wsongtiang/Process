package control;

import domain.*;
import da.*;
import java.util.ArrayList;

public class OrderControl{

    OrderDa orderDA;
    
    public OrderControl(){
        orderDA = new OrderDa();
    }
    

    public String getLatestOrderID(){
        return orderDA.getLatestOrID();
    }
    
   public String getLatestTicketID(){
        return orderDA.getLatestTicID();
    }
    
    public void shutDown(){
        orderDA.shutDown();
    }
}