package domain;

public class OrderDetailDo{

    
    private String scheduleid;
    private String orderid;
    private int quantity;
    private String ticketno;

    
    public OrderDetailDo(){
     
    }
        
    public OrderDetailDo( String scheduleid,String orderid, int quantity,String ticketno) {
        
        this.scheduleid=scheduleid;
        this.orderid=orderid;
        this.quantity = quantity;
        this.ticketno=ticketno;
    }

    
    
    public String getScheduleid()
    {
        return scheduleid;
    }
    
    public String getOrderid(){
    return orderid;
}
    
    public int getQuantity() {
        return quantity;
    }
    
    public String getTicketno(){
        return ticketno;
    }

  
    
    public void setScheduleid(String scheduleid){
        this.scheduleid =scheduleid;
    }
    
    public void setRouteid(String routeid){
        this.orderid=routeid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setTicketno(String ticketno){
        this.ticketno = ticketno;
    }

    @Override
    public String toString() {
        return "OrderDetailDo{"  +"scheduleid "+scheduleid+",orderid"+orderid + ", quantity=" + quantity +",ticketno"+ticketno+ '}';
    }
    
}