package ui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import control.*;
import java.util.ArrayList;
import domain.*;
import java.text.SimpleDateFormat;
import java.util.Date;




public class BookingTicket extends JFrame{
   private String host = "jdbc:derby://localhost:1527/BusDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String orderTable = "ORDERTABLE";
    private String orderDetail = "ORDERDETAIL";
    private String scheduleTable ="SCHEDULE";
    private String routeTable = "ROUTE";
    private Connection conn;
    private PreparedStatement stmt;
    
    private ScheduleDomain schedo;
    private OrderControl orderControl;
    
    private ImageIcon sch = new ImageIcon(getClass().getResource("schedule.png"));
    private JLabel logo = new JLabel(sch,SwingConstants.CENTER);
    private JPanel logoPanel = new JPanel(new FlowLayout());
    
      private JLabel jlborderid = new JLabel("Order ID :");
       private JLabel jlbscheduleid= new JLabel("Schedule ID:");
       private JLabel jlbfrom = new JLabel("From :");
       private JLabel jlbto = new JLabel("To :");
       private JLabel jlbdeparturedate = new JLabel("Departure Date :");
       private JLabel jlbdeparturetime = new JLabel("Departure Time :");
       private JLabel jlbarrivaldate = new JLabel("Arrival Date :");
       private JLabel jlbarrivaltime = new JLabel("Arrival Time :");
       private JLabel jlbdistance = new JLabel("Distance (km):");
       private JLabel jlbprice = new JLabel("Price :                                 RM");
       private JLabel jlbquantity = new JLabel("Quantity :");
       private JLabel jlbtotalseat = new JLabel("Total Seat :");
       private JLabel jlbticketno = new JLabel("Ticket NO :");
       private JLabel jlborderdate = new JLabel("Order Date:");
 
       
       
//       private JTextField jtforderid = new JTextField(10);
       private javax.swing.JTextField jtforderid = new javax.swing.JTextField();
//       
//       OrderControl control;
//    
//    private ArrayList<ScheduleDomain> schedule = new ArrayList<ScheduleDomain>();
//    private String scheduleid = "";
//    private DefaultComboBoxModel dcbomschedule= new DefaultComboBoxModel();
//    private JComboBox jcboschedule = new JComboBox(dcbomschedule);
    
//     private JTablePassValueDemo targetFrame;
//    private SelectFoodByTypeFrame thisFrame;
//    
//     public BookingTicket(String id, JTablePassValueDemo targetFrame){
//        if(!id.equals("")){
//            this.id = id;
//            this.targetFrame = targetFrame;       
//            thisFrame = this;
//            initComponents();
//            displayFrame(this);
//        }
//    }  
//    
       private JTextField jtfscheduleid = new JTextField(10);
       private JTextField jtffrom = new JTextField(10);
       private JTextField jtfto = new JTextField(10);
       private JTextField jtfdeparturedate = new JTextField(10);
       private JTextField jtfdeparturetime = new JTextField(10);
       private JTextField jtfarrivaldate = new JTextField(10);
       private JTextField jtfarrivaltime = new JTextField(10);
       private JTextField jtfdistance = new JTextField(10);
       private JTextField jtfprice = new JTextField(10);
       private JTextField jtfquantity = new JTextField(10);
       private JTextField jtftotalseat = new JTextField(10);
       private JTextField jtfticketno = new JTextField(10);
       private JTextField jtforderdate = new JTextField(10);
    
    private JButton view = new JButton("View Schedule");
    private JButton add = new JButton("ADD");
       private JButton delete = new JButton("DELETE");
       private JButton back = new JButton("BACK");
//       private JButton home = new JButton("HOME");
       private JButton search = new JButton("View Route");
        private JButton order = new JButton("View Your Order");
       
    private JPanel centerPanel;
    private JTable table;
    private JScrollPane scrollPane;
    
    private String orderID;
    private String ticketNo;
    
    private String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    
       public BookingTicket(ScheduleDomain schedo){
           
           orderControl = new OrderControl();
           
               JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("Booking Ticket");
        hint.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        topPanel.add(hint);
        
        logoPanel.add(logo);
               
         JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);
         topPanel.add(separator, BorderLayout.SOUTH);

         topPanel.add(logoPanel, BorderLayout.NORTH);
        basic.add(topPanel);
        
        JPanel jptop = new JPanel(new BorderLayout());
        jptop.setBorder(BorderFactory.createEmptyBorder(15,25,15,25));
        
        JPanel overall = new JPanel();
        JPanel topview = new JPanel();
        topview.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
        5, 5,Color.pink), "View", TitledBorder.LEFT, TitledBorder.TOP));
         topview.add(view);
         
//        JPanel centerdetails = new JPanel();
        JPanel details = new JPanel(new GridLayout(9,4));
        details.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
        5, 5, Color.pink), "Booking Ticket Details", TitledBorder.LEFT, TitledBorder.TOP));
        
        setConnection();
       this.schedo = schedo;
        String scheduleid=schedo.getScheduleid();
        Date departuredate=schedo.getDeparturedate();
        String departuretime=schedo.getDeparturetime();
        Date arrivaldate=schedo.getArrivaldate();
        String arrivaltime = schedo.getArrivaltime();
        
        jtfscheduleid.setText(scheduleid);
        jtfdeparturedate.setText(departuredate + "");
        jtfdeparturetime.setText(departuretime);
        jtfarrivaldate.setText(arrivaldate+"");
        jtfarrivaltime.setText(arrivaltime);
        
        
         details.add(jlborderdate);
        details.add(jtforderdate);
        jtforderdate.setText(date);
        jtforderdate.setEditable(false);
        details.add(jlbticketno);
        details.add(jtfticketno);
        jtfticketno.setEditable(false);
        //autogenerate ticketno
        String latestTicketID = orderControl.getLatestTicketID();
        String nextTicketID = "";
        int TicketSqnNo = 0;
        String nextTicketSqnNo = "";
       
        TicketSqnNo = Integer.parseInt(latestTicketID.substring(3));
        TicketSqnNo+=1;
        nextTicketSqnNo = String.valueOf(TicketSqnNo);
        int nextTicketSqnLength = 0;
        nextTicketSqnLength = nextTicketSqnNo.length();
        while(nextTicketSqnLength < 3){
            nextTicketSqnNo = "0" + nextTicketSqnNo;
            nextTicketSqnLength = nextTicketSqnNo.length();
        }
        
        nextTicketID = "TIC" + nextTicketSqnNo;
        
        ticketNo = nextTicketID;
        jtfticketno.setText(ticketNo);

        details.add(jlborderid);
        details.add(jtforderid);
        jtforderid.setEditable(false);
        //autogenerate id
        String latestOrderID = orderControl.getLatestOrderID();
        String nextOrderID = "";
        int OrderSqnNo = 0;
        String nextOrderSqnNo = "";
        
        OrderSqnNo = Integer.parseInt(latestOrderID.substring(3));
        OrderSqnNo+=1;
        nextOrderSqnNo = String.valueOf(OrderSqnNo);
        int nextOrderSqnLength = 0;
        nextOrderSqnLength = nextOrderSqnNo.length();
        while(nextOrderSqnLength < 3){
            nextOrderSqnNo = "0" + nextOrderSqnNo;
            nextOrderSqnLength = nextOrderSqnNo.length();
        }
        
        nextOrderID = "ORD" + nextOrderSqnNo;
        
        orderID = nextOrderID;
        jtforderid.setText(orderID);

        details.add(jlbscheduleid);
        details.add(jtfscheduleid);
        jtfscheduleid.setEditable(false);
        details.add(jlbdeparturedate);
        details.add(jtfdeparturedate);
        jtfdeparturedate.setEditable(false);
        details.add(jlbarrivaldate);
        details.add(jtfarrivaldate);
        jtfarrivaldate.setEditable(false);
        details.add(jlbdeparturetime);
        details.add(jtfdeparturetime);
        jtfdeparturetime.setEditable(false);
        details.add(jlbarrivaltime);
        details.add(jtfarrivaltime);
        jtfarrivaltime.setEditable(false);
         details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(search);
        details.add(jlbfrom);
        details.add(jtffrom);
        jtffrom.setEditable(false);
        details.add(jlbto);
        details.add(jtfto);
        jtfto.setEditable(false);
        details.add(jlbdistance);
        details.add(jtfdistance);
        jtfdistance.setEditable(false);
        details.add(jlbprice); 
        details.add(jtfprice); 
        jtfprice.setEditable(false);
        details.add(jlbquantity);
        details.add(jtfquantity);
        details.add(jlbtotalseat);
         details.add(jtftotalseat);
         jtftotalseat.setEditable(false);
         details.add(new JLabel(""));
         details.add(new JLabel(""));
         details.add(new JLabel(""));
         details.add(add);
//         centerdetails.add(details,BorderLayout.CENTER);
       
        
        JPanel bottomtable = new JPanel();
         bottomtable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
        5, 5,Color.pink), "View", TitledBorder.LEFT, TitledBorder.TOP));
//        JPanel bottomcenter = new JPanel();
         JPanel centerPanel = new JPanel();
        centerPanel.setLayout( new BorderLayout() );
  
        getContentPane().add( centerPanel );
        
        String columnNames[] = { "From","To", "Price", "Total ticket sold out", "Total Price(RM)"};
        
        String dataValues[][] ={
			 {"Penang", "Kedah","10","50","500"},
			 {"Penang", "Selangor","60","30","1800" },
			{ "Selangor", "Johorr","40","40","1600"},
       };
        
        table = new JTable( dataValues, columnNames );
        table.setPreferredSize(new Dimension(500,200));
        scrollPane = new JScrollPane( table );
        scrollPane.setPreferredSize(new Dimension(500,200));
//        
//        centerPanel.add( scrollPane);
//        
       
        bottomtable.add(centerPanel,BorderLayout.CENTER);
        bottomtable.add(order);
        bottomtable.add(back);


   
 
          setContentPane(basic);
          pack();
          basic.setVisible(true);
        jptop.add(topview,BorderLayout.NORTH);
//        jptop.add(overall,BorderLayout.CENTER);
        jptop.add(details,BorderLayout.CENTER);
//        overall.add(centerdetails,BorderLayout.CENTER);
        basic.add(jptop,BorderLayout.CENTER);
        basic.add(bottomtable,BorderLayout.SOUTH);
        
        view.addActionListener(new ViewButtonListener());
        add.addActionListener(new CreateListener());
        search.addActionListener(new SearchListener());
        back.addActionListener(new backListener());
        order.addActionListener(new orderListener());
        addWindowListener(new WindowListener());
//        jcboschedule.addItemListener(new ItemListener());
        
        createConnection();
        
       setTitle("Booking Ticket");
        setSize(700,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
       
//        add.addActionListener(new AddButtonListener());
        

}
       public ResultSet selectRecord(String orderid){
       String queryStr = "SELECT * FROM "+orderTable+" WHERE orderid = ? ";
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
       
       public ResultSet selectRecord2(String scheduleid){
       String queryStr = "SELECT r.*, s.* FROM "+" route r, schedule s "+" WHERE r.routeid = s.routeid  "+" AND scheduleid = ? ";
       ResultSet rs = null;
       
       try{
           stmt=conn.prepareStatement(queryStr);
           stmt.setString(1,scheduleid);
           
           rs = stmt.executeQuery();
       }
       catch(SQLException ex){
           JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
       }
       return rs;
   }

       
       public ResultSet selectRecord3(String scheduleid){
       String queryStr = "SELECT od.*, ot.* FROM "+" orderdetail od, ordertable ot "+" WHERE od.orderid = ot.orderid "+" AND scheduleid = ? ";
       ResultSet rs = null;
       
       try{
           stmt=conn.prepareStatement(queryStr);
           stmt.setString(1,scheduleid);
           
           rs = stmt.executeQuery();
       }
       catch(SQLException ex){
           JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
       }
       return rs;
   }
       
       
        public void clearText(){
            
              String latestTicketID = orderControl.getLatestTicketID();
        String nextTicketID = "";
        int TicketSqnNo = 0;
        String nextTicketSqnNo = "";
       
        TicketSqnNo = Integer.parseInt(latestTicketID.substring(3));
        TicketSqnNo+=1;
        nextTicketSqnNo = String.valueOf(TicketSqnNo);
        int nextTicketSqnLength = 0;
        nextTicketSqnLength = nextTicketSqnNo.length();
        while(nextTicketSqnLength < 3){
            nextTicketSqnNo = "0" + nextTicketSqnNo;
            nextTicketSqnLength = nextTicketSqnNo.length();
        }
        
        nextTicketID = "TIC" + nextTicketSqnNo;
        
        ticketNo = nextTicketID;
        jtfticketno.setText(ticketNo);
       
         String latestOrderID = orderControl.getLatestOrderID();
        String nextOrderID = "";
        int OrderSqnNo = 0;
        String nextOrderSqnNo = "";
        
        OrderSqnNo = Integer.parseInt(latestOrderID.substring(3));
        OrderSqnNo+=1;
        nextOrderSqnNo = String.valueOf(OrderSqnNo);
        int nextOrderSqnLength = 0;
        nextOrderSqnLength = nextOrderSqnNo.length();
        while(nextOrderSqnLength < 3){
            nextOrderSqnNo = "0" + nextOrderSqnNo;
            nextOrderSqnLength = nextOrderSqnNo.length();
        }
        
        nextOrderID = "ORD" + nextOrderSqnNo;
        
        orderID = nextOrderID;
        jtforderid.setText(orderID);
        
         jtfscheduleid.setText("");
       jtffrom.setText("");
       jtfto.setText("");
       jtfdeparturedate.setText("");
       jtfdeparturetime.setText("");
       jtfarrivaldate.setText("");
       jtfarrivaltime.setText("");
       jtfdistance.setText("");
       jtfprice.setText("");
       jtfquantity.setText("");
       jtftotalseat.setText("");
   }
        
        public void clearText1(){
            
        
         jtfscheduleid.setText("");
       jtffrom.setText("");
       jtfto.setText("");
       jtfdeparturedate.setText("");
       jtfdeparturetime.setText("");
       jtfarrivaldate.setText("");
       jtfarrivaltime.setText("");
       jtfdistance.setText("");
       jtfprice.setText("");
       jtfquantity.setText("");
       jtftotalseat.setText("");
   }
        
        private class orderListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
              
                String orderid = jtforderid.getText();
                     closeFrame();
               ViewOrder order= new ViewOrder(orderid);
          
            }
        }
      
       private class CreateListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String orderid = jtforderid.getText();
            ResultSet rs = selectRecord(orderid);
             try{
                 if(rs.next()){
                     JOptionPane.showMessageDialog(null,"The order ID already exists","Please enter again-",JOptionPane.ERROR_MESSAGE);
                 }
                 else{
                     String queryStr =" INSERT INTO "+orderTable+" VALUES(?,?,?) ";
                     stmt=conn.prepareStatement(queryStr);
                     stmt.setString(1,orderid);
                     stmt.setString(2,jtforderdate.getText());
                     stmt.setString(3,jtfprice.getText());
                     
                     stmt.executeUpdate();
                     
                     String queryStr2 =" INSERT INTO "+orderDetail+" VALUES(?,?,?,?) ";
                     stmt=conn.prepareStatement(queryStr2);
                     stmt.setString(1,jtfscheduleid.getText());
                     stmt.setString(2,orderid);
                     stmt.setString(3,jtfquantity.getText());
                     stmt.setString(4,jtfticketno.getText());
                     
//                     id=dcbomschedule[selectedIndex];
                     
                     
                     stmt.executeUpdate();
                     
                     int totalseat = Integer.parseInt(jtftotalseat.getText());
                     if(jtfquantity != null){
                         int quantity =Integer.parseInt(jtfquantity.getText()) ;
                         
                         
                         totalseat -=quantity;
                         jtftotalseat.setText(totalseat+"");
                     }
                     
                     String updateStr ="UPDATE SCHEDULE SET totalseat=? "+" WHERE scheduleid = ? ";
                    stmt=conn.prepareStatement(updateStr);
                    stmt.setInt(1, totalseat);
                    stmt.setString(2, jtfscheduleid.getText());
                    stmt.executeUpdate();
                    
                    
                     JOptionPane.showMessageDialog(null,"Recorded has been created");
                     
                     clearText1();
                 }
                }
             catch(SQLException ex){
                     JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                 }
             
        }
    }

       
//       private void initComboBox(){
//        
//        schedule = control.getScheduleID(scheduleid);
//        
//        for(int i=0; i<schedule.size(); i++){
//            dcbomschedule.addElement(schedule.get(i).getScheduleid());
//        }
//    }
//       
//     
     
       private class ViewButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e)
        {
        ViewSchedule view = new ViewSchedule();
//        clearText1();
        closeFrame();
        }
       }
       
        private class SearchListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
             
            try{
               String scheduleid = jtfscheduleid.getText();
               ResultSet rs2 = selectRecord2(scheduleid);
//               ResultSet rs3 =selectRecord3(scheduleid);
               
                if(rs2.next()){

                    jtffrom.setText(rs2.getString("departuredest"));
                    jtfto.setText(rs2.getString("arrivaldest"));
                    jtfdistance.setText(rs2.getString("distance"));
                    jtfprice.setText(rs2.getString("price"));
                    jtftotalseat.setText(rs2.getString("totalseat"));
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Record does not exists", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }
       
//       private class AddButtonListener implements ActionListener{
//           public void actionPerformed(ActionEvent e){
//                 try
//                {
//                    int ticketno=Integer.parseInt(jtfticketno.getText());
//                    String orderid=jtforderid.getText();
//                    String scheduleid = jtfscheduleid.getText();
//                    String jtffrom = jtffrom.getText();
//                    String jtfto = jtfto.getText();
//                    Date departuredate =jtfdeparturedate.getText();
//                    
//                    if(name.isEmpty())
//                    {
//                        JOptionPane.showMessageDialog(null, "Empty string for item's name detected.\nPlease reenter.");
//                    }
//                    else
//                    {
//                    soupsControl.createRecord(jtfCode.getText(),name, price);
//                    closeFrame();
//                    
//                    
//                    }
//                }
//                catch(NumberFormatException ex)
//                {
//                     JOptionPane.showMessageDialog(null, "Invalid data type.");
//                     jtfPrice.setText("");
//                }
//           }
//       }
        
         private class backListener implements ActionListener{
            public void actionPerformed(ActionEvent e){

                StaffLogin staff = new StaffLogin();
                closeFrame();
            }
        }
        
        private void createConnection(){
        try{
            conn = DriverManager.getConnection(host,user,password);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void shutDown(){
        if(conn!= null){
            try{
                conn.close();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private class WindowListener extends WindowAdapter{
        public void windowClosing(WindowEvent e) {
        
           int confirm = JOptionPane.showConfirmDialog(null, "Confirm to exit?", "Confirm?", JOptionPane.WARNING_MESSAGE);
           if(confirm == JOptionPane.YES_OPTION){
                closeFrame();
              
           }
       }
    }
   
       
       public void closeFrame(){
           this.dispose();
       }
       
        private void setConnection(){
         String host = "jdbc:derby://localhost:1527/BusDB";
         String user = "nbuser";
         String password = "nbuser";
         
         try{
             conn = DriverManager.getConnection(host,user,password);
             
         }catch(Exception ex){
             System.err.println("DB ERROR: "+ex.toString());
         }
     }

        public static void main(String [] args){
//         BookingTicket frame = new BookingTicket();
}
}