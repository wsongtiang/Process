package control;

import da.*;
import ui.*;
import domain.*;
import control.*;

public class RouteControl{
    
    private RouteDa routeDA;
    
    public RouteControl(){
        routeDA = new RouteDa();
    }
 
    public String getLatestID(){
        return routeDA.getLatestID();
    }
   
    public RouteDo selectRecord(String orderid) {
        return routeDA.getRecord(orderid);
    }
    
    public void closeDB(){
        routeDA.shutDown();
    }
}