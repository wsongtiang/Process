package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import da.*;
import domain.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.*;
import control.*;
import java.text.SimpleDateFormat;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ViewOrder extends JFrame{
    
    private String host = "jdbc:derby://localhost:1527/BusDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private Connection conn;
    private PreparedStatement stmt;

    private OrderDetailDa tablemodel;
    private JTable table=new JTable((TableModel)tablemodel);
    private OrderDetailDo schedo;
    
    private JPanel basic;
    private JPanel centerPanel;
  //  private JTable table;
    private JScrollPane scrollPane;
    
    private ImageIcon sch = new ImageIcon(getClass().getResource("schedule.png"));
    private JLabel logo = new JLabel(sch,SwingConstants.CENTER);
    private JPanel logoPanel = new JPanel(new FlowLayout());
//    
//    private JButton back = new JButton("BACK");
    private JButton delete = new JButton("DELETE");
    private JButton search = new JButton("SEARCH");
//    private JButton viewall = new JButton("REFRESH");
    private JButton payment=new JButton("Payment");
   
    private JLabel jlbtotalprice = new JLabel("Total Price");
    
    private JTextField jtfview = new JTextField(20);
    private JTextField jtftotalprice = new JTextField(20);
    private JTextField jtfdate= new JTextField(20);
//    ArrayList<String> id = new ArrayList<String>();
    
     private ScheduleControl sControl;
     ArrayList <String>priceList =new ArrayList<String>();
     ArrayList <String>qtyList =new ArrayList<String>();
     
     private String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
    
    public ViewOrder(String orderid){

         sControl = new ScheduleControl();
        
        setConnection();
         JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("View Bus Schedule and Route");
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
        
//        createConnection();
        
//        Try();
        
        JPanel top = new JPanel();
        top.add(jtfview);
        jtfview.setEditable(false);
        jtfview.setText(orderid);
            jtfview.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jtfview.setText("");
            }
            
        }); 

        top.add(search);
        top.add(new JLabel(""));
        top.add(new JLabel(""));
        top.add(jtfdate);
        jtfdate.setText(date);
        jtfdate.setEditable(false);
//        top.add(viewall);

        

        JPanel center = new JPanel();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout( new BorderLayout() );
        getContentPane().add( centerPanel );
        tablemodel = new OrderDetailDa(orderid);
        table= new JTable((TableModel)tablemodel);

        scrollPane = new JScrollPane( table );
        centerPanel.add(scrollPane);

        
        JPanel bottompanel = new JPanel(new GridLayout(2,6));
//        JPanel bottom = new JPanel(new GridLayout(1,2));
//        bottompanel.add(bottom,SwingConstants.RIGHT);
        bottompanel.add(new JLabel(""));
        bottompanel.add(new JLabel(""));
        bottompanel.add(new JLabel(""));
        bottompanel.add(new JLabel(""));
        bottompanel.add(jlbtotalprice);
        bottompanel.add(jtftotalprice);
        jtftotalprice.setText(calculateTotalPrice()+"");

        bottompanel.add(new JLabel(""));
        bottompanel.add(new JLabel(""));
        bottompanel.add(new JLabel(""));
        bottompanel.add(new JLabel(""));
        bottompanel.add(new JLabel(""));
//        bottompanel.add(delete);
        bottompanel.add(payment);
        
        
//        bottompanel.add(back);

         setContentPane(basic);
          pack();
        basic.setVisible(true);

         basic.add(top,BorderLayout.NORTH);
         basic.add(centerPanel,BorderLayout.CENTER);
         basic.add(bottompanel,BorderLayout.SOUTH);

        basic.add(jptop);
        
        addWindowListener(new WindowListener());
        payment.addActionListener(new PaymentListener());
        
//            table.addMouseListener(new JTableListener()); 
//        search.addActionListener(new SearchListener());
//        back.addActionListener(new backListener());
//        viewall.addActionListener(new ViewAllListener());
           createConnection();
        
        setTitle("View Your Order");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,500);
        setVisible(true);
        
//        table.addMouseListener(new JTableListener()); 
//        search.addActionListener(new SearchListener());
//        back.addActionListener(new backListener());
//        viewall.addActionListener(new ViewAllListener());

        
    }
    
    public ResultSet selectRecord(String orderid){
            String queryStr = "SELECT * FROM ORDERTABLE WHERE orderid = ? ";
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
    
     private class AddListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                
             
                String orderid = jtfview.getText();
                ResultSet rs = selectRecord(orderid);
                try{
                     if(rs.next()){
                         JOptionPane.showMessageDialog(null,"Order id already exists","Please enter agaian",JOptionPane.ERROR_MESSAGE);
                     }
                     else{
                         String staffStr ="INSERT INTO ORDERTABLE VALUES(?,?,?) ";
                         stmt=conn.prepareStatement(staffStr);
                         stmt.setString(1,orderid);
                         stmt.setString(2,jtftotalprice.getText());
                         stmt.setString(3,jtfdate.getText());
                      
                        
        
                         stmt.executeUpdate();
                         
                  

                         JOptionPane.showMessageDialog(null,"Recorded has been created");
                         String id = jtfview.getText();
                         String price = jtftotalprice.getText();
                         
                         closeFrame();
                         Payment p = new Payment(id,price);

                     }
                    }
                
                 catch(SQLException ex){
                         JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
    
    private double calculateTotalPrice(){
        int totalRow =tablemodel.getRowCount();
        for(int i=0 ; i<totalRow;i++){
            priceList.add(tablemodel.getValueAt(i, 2).toString());
            qtyList.add(tablemodel.getValueAt(i, 4).toString());
        }
        int totalPrice=0;
        for(int j=0;j<priceList.size();j++){
            double subtotal = Double.parseDouble(priceList.get(j))* Double.parseDouble(qtyList.get(j));
            totalPrice+=subtotal;
        }
      return totalPrice;
    }
         
    
      private class PaymentListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                
              String id = jtfview.getText();
              String price = jtftotalprice.getText();
              Payment p = new Payment(id,price);
              
            }
        }
    
//           private class backListener implements ActionListener{
//            public void actionPerformed(ActionEvent e){
//           int confirm = JOptionPane.showConfirmDialog(null, "Confirm Log Out?", "Confirm?", JOptionPane.WARNING_MESSAGE);
//           if(confirm == JOptionPane.YES_OPTION){
//                
//               closeFrame();
//               new BookingTicket();
//            }
//            }
//        }
    

    
    private void setConnection(){
   String host = "jdbc:derby://localhost:1527/BusDB ";  
     String user = "nbuser";
     String password = "nbuser";
     
     try{
         conn= DriverManager.getConnection(host,user,password);
     }
     catch(Exception ex){
         System.err.println("DB ERROR: "+ ex.toString());
     }
    }
    

    
//         private class SearchListener implements ActionListener{
//             public void actionPerformed(ActionEvent e){
//                 
//                
//            if(jtfview.getText().isEmpty())
//            {
//                JOptionPane.showMessageDialog(null, "Please enter soups item's information to search.","WARNING",JOptionPane.WARNING_MESSAGE);
//            }
//            else
//            {
//             ScheduleDomain s = sControl.selectRecord(jtfview.getText());
//            if (s != null) {
//            tablemodel.retrieveRecordsByRouteid(jtfview.getText());
//            }
//            else
//            {
//            String text = jtfview.getText();
//            }
//            }
//             }
//         }
//          private class ViewAllListener implements ActionListener{
//        public void actionPerformed(ActionEvent e)
//        {
//        refresh();
//        }
//    }
//
//    
//
////       private class WindowListener extends WindowAdapter{
////        public void windowClosing(WindowEvent e) {
////        
////           int confirm = JOptionPane.showConfirmDialog(null, "Confirm return to the Main Menu?", "Confirm?", JOptionPane.WARNING_MESSAGE);
////           if(confirm == JOptionPane.YES_OPTION){
////                closeFrame();
////                new Menu();
////           }
////       }
////    }
//       
////         private class ViewAllListener implements ActionListener{
////        public void actionPerformed(ActionEvent e)
////        {
////        refresh();
//////        }
////    }
//       
//         public void refresh()
//     {
//       
//       centerPanel.removeAll();
        
//        JPanel center = new JPanel();
//        JPanel centerPanel = new JPanel();
//        centerPanel.setLayout( new BorderLayout() );
//        getContentPane().add( centerPanel );
//        tablemodel = new ScheduleDa();
//        table= new JTable((TableModel)tablemodel);
//        
//        scrollPane = new JScrollPane( table );
//        centerPanel.add(scrollPane);
        
//        tablemodel = new ScheduleDa();
//        table = new JTable(tablemodel);//add tablemodel inside
//        table.setOpaque(true);
//        table.setBackground(new Color(255,248,220));
//        
//        JScrollPane scrollPane=new JScrollPane(table);
//        centerPanel.add(scrollPane);
//        scrollPane.setOpaque(true);
//        scrollPane.getViewport().setBackground(new Color(255,248,220));
  
//        centerPanel.setBackground(new Color(255,248,220));
//        centerPanel.revalidate();
//        centerPanel.repaint();
//        basic.add(centerPanel,BorderLayout.CENTER);
//     }
//         
//       private class RetrieveListener implements ActionListener{
//        public void actionPerformed(ActionEvent e){
//             
//            try{
//                String Str ="SELECT * FROM" + tableName ;
//                stmt= conn.prepareStatement(Str);
//                rs=stmt.executeQuery(Str);
//            }
//            catch(Exception ex){
//                JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
//            }
//        } 
//    }
    
//     public ResultSet selectRecord(String orderid) {
//        String queryStr = "SELECT * FROM OrderDetail WHERE ORDERID= ?";
//        ResultSet rs = null;
//        try {
//            stmt = conn.prepareStatement(queryStr);
//            stmt.setString(1, orderid);
//            rs = stmt.executeQuery();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
//        System.out.println("GOT RETURN!"); 
//        return rs;
//    }
//       
//       private class JTableListener implements MouseListener{
//        public void mouseClicked(MouseEvent e){
//            if(e.getClickCount() == 2){
//                JTable target = (JTable) e.getSource();
//                int row = target.getSelectedRow();
//                
//                String value =(String)tablemodel.getValueAt(row, 0);
//                 try {
//                
//                ResultSet rs = selectRecord(value);
//                if (rs.next()) {
//                    String scheduleid=rs.getString("scheduleid");
//                    String orderid=rs.getString("orderid");
//                    int quantity=rs.getInt("quantity");
//                    String ticketno=rs.getString("ticketno");
//                  
//                    
//                    schedo = new OrderDetailDo(scheduleid,orderid,quantity,ticketno);
//                  
//                 
//                } else {
//                    JOptionPane.showMessageDialog(null, "No such programme code.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
//                }
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//            }
//                closeFrame();
//                //JOptionPane.showMessageDialog(null, value);  
//            }
//           
//        }
//        public void mouseExited(MouseEvent e) { } 
//        public void mouseEntered(MouseEvent e) { }
//        public void mouseReleased(MouseEvent e){ }
//        public void mousePressed(MouseEvent e) { }
//    }
       
       private void createConnection(){
        try{
            conn = DriverManager.getConnection(host,user,password);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
     
    
        public void closeFrame() {
                this.dispose();
        }
    
    public static void main(String[] args){
        //new ViewOrder();
}

}