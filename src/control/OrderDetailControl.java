package control;

import domain.*;
import da.*;
import java.util.ArrayList;

public class OrderDetailControl{

    OrderDetailDa order;
   
    
public ArrayList<OrderDetailDo> getOrderID(String orderid){
    return order.getOrderid(orderid);
}
    
    

    
    public void shutDown(){
        order.shutDown();
    }
}