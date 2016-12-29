package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import domain.*;
import control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintTicket extends JFrame{
    
     private String host = "jdbc:derby://localhost:1527/BusDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String orderTable = "ORDERTABLE";
    private String orderDetail = "ORDERDETAIL";
    private String scheduleTable ="SCHEDULE";
    private String routeTable = "ROUTE";
    private Connection conn;
    private PreparedStatement stmt;
    
    JTextArea textarea = new JTextArea();
    ArrayList <OrderDetailDo>orderList = new ArrayList<OrderDetailDo>();
    
    private OrderDetailControl od;
    private RouteControl rd;
    
    private String id;
    
    public PrintTicket(String orderid){
        
        JOptionPane.showMessageDialog(null, String.format("%s", orderid));
        
        od = new OrderDetailControl();
        rd = new RouteControl();
        
        JPanel jpTitle = new JPanel(new BorderLayout());
        jpTitle.add(new JLabel("Ticket No:"), BorderLayout.NORTH);
        jpTitle.add(new JTextField());
        jpTitle.add(new JLabel("FOC BUS EXPRESS COMPANY"), BorderLayout.CENTER);
        
//        orderList =od.getOrderID(orderid);
//        int quantity=orderList.size();
//        RouteDo route = null;
//        
//        
//        String str="";
//        for (int i=0;i<quantity;i++){
            
//        ResultSet rs=selectAll(orderid);
//            if(rs.next()){
//            str +="\n*********************************************************************************************\n"
//                    + "============================FAST AND CONVENIENT(FAC)==============================\n"
//                    + "\nOrder ID:    "+ orderid+"\nSchedule ID: "+rs.getString("scheduleid")+"\nTicket No: "+rs.getString("ticketno")+
//                     "\nDeparture Destination: "+rs.getString("departuredest")+"\nArrival Destination: "+rs.getString("arrivaldest")+"\n";
////            JOptionPane.showMessageDialog(null, "HAHAH");
////        }
//            }
        JPanel jpDetail = new JPanel(new GridLayout(10,2));
//
//        jpDetail.add(new JLabel("Order Date:"));
//        jpDetail.add(new JTextField(""));
//        jpDetail.add(new JLabel("From:"));
//        jpDetail.add(new JTextField(""));
//        jpDetail.add(new JLabel("To:"));
//        jpDetail.add(new JTextField(""));
//        jpDetail.add(new JLabel("Departure Date:"));
//        jpDetail.add(new JTextField(""));
//        jpDetail.add(new JLabel("Departure Time:"));
//        jpDetail.add(new JTextField(""));
//        jpDetail.add(new JLabel("Destination Date:"));
//        jpDetail.add(new JTextField(""));
//        jpDetail.add(new JLabel("Destination Time:"));
//        jpDetail.add(new JTextField(""));
//        jpDetail.add(new JLabel("Distance"));
//        jpDetail.add(new JTextField(""));
//        jpDetail.add(new JLabel("Total cost of ticket:"));
//        jpDetail.add(new JTextField(""));
//        jpDetail.add(new JLabel("Pay by:"));
//        jpDetail.add(new JTextField(""));

        
        JButton jbPrint = new JButton("Print Ticket");

        add(jpTitle, BorderLayout.NORTH);
        add(textarea, BorderLayout.CENTER);
        add(jbPrint, BorderLayout.SOUTH);
        
        jbPrint.addActionListener(new printListener());

        setTitle("Ticket");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,300);
        setVisible(true);
}
    
//     public void Display(){
//           String id =orderid;
//           ResultSet rs = selectAll(orderid);
//     
//       }
    
    public ResultSet selectAll(String orderid){
       String queryStr = "SELECT * FROM "+" route r, schedule s, orderdetail od, ordertable ot "+" WHERE r.routeid = s.routeid AND s.scheduleid = od.scheduleid AND od.orderid = ot.orderid AND orderid = ? ";
       ResultSet rs = null;
       
       try{
           stmt=conn.prepareStatement(queryStr);
           stmt.setString(1,orderid);
           
           rs = stmt.executeQuery();
       }
       catch(SQLException ex){
           JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
       }
       return rs;
   }
    
    private class printListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                Boolean complete =textarea.print();
                if(complete){
                    JOptionPane.showMessageDialog(null,"Done Printing");
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Printing");
                }
            }catch(PrinterException ex){
                Logger.getLogger(PrintTicket.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }

public static void main(String[] args){
//    PrintTicket frame = new PrintTicket();
}

}