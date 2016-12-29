package ui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.sql.*;
import control.*;
import java.util.ArrayList;

public class Payment extends JFrame{
    private String host = "jdbc:derby://localhost:1527/BusDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String ordertable = "ORDERTABLE";
    private String payment = "PAYMENT";
    private Connection conn;
    private PreparedStatement stmt;
    
     private PaymentControl paymentcontrol;
    
    private JLabel jlborderid = new JLabel("OrderID :");
    private JLabel jlborderdate = new JLabel("Order Date :");
    private JLabel jlbpaymentid = new JLabel("PaymentID :");
    private JLabel jlbprice = new JLabel("Price :");
    private JLabel jlbpaymentmethod = new JLabel("Payment Method :");
    
    private JTextField jtforderid = new JTextField();
    private JTextField jtforderdate= new JTextField();
    private JTextField jtfpaymentid = new JTextField();
    private JTextField jtfprice = new JTextField();
    private JComboBox jcbpaymentmethod = new JComboBox(new Object[]{"Cash","Credit Card"});
    
    private JButton submit = new JButton("SUBMIT");
    private JButton cancel = new JButton("CANCEL");

     
    private String paymentID;
    private String idid="";
    
     private ImageIcon card = new ImageIcon(getClass().getResource("card.png"));
    private JLabel logo = new JLabel(card,SwingConstants.CENTER);
    private JPanel logoPanel = new JPanel(new FlowLayout());
    
    public Payment(String id, String price){
        
        paymentcontrol = new PaymentControl();
        
        JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("Payment");
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
       
        JPanel  payment = new JPanel(new GridLayout(5,2));
        payment.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
        5, 5,Color.pink), "Payment", TitledBorder.LEFT, TitledBorder.TOP));
        
          createConnection();
          update();
          
          
        
        payment.add(jlborderid);
        payment.add(jtforderid);
        jtforderid.setEditable(false);
        jtforderid.setText(id);
        payment.add(new JLabel(""));
        payment.add(new JLabel(""));
        payment.add(jlbprice);
        payment.add(jtfprice);
        jtfprice.setText(price);
        payment.add(new JLabel(""));
        payment.add(new JLabel(""));
        payment.add(jlbpaymentid);
        payment.add(jtfpaymentid);
         jtfpaymentid.setEditable(false);

         
        payment.add(new JLabel(""));
        payment.add(new JLabel(""));
        payment.add(jlbpaymentmethod);
        payment.add(jcbpaymentmethod);
        payment.add(new JLabel(""));
        payment.add(new JLabel(""));
        payment.add(new JLabel(""));
        payment.add(new JLabel(""));
        payment.add(new JLabel(""));
        payment.add(submit);

        
       JPanel south = new JPanel(new GridLayout(1,4));
        south.add(new JLabel(""));
        south.add(new JLabel(""));
        south.add(new JLabel(""));

        
        
         jptop.add(payment,BorderLayout.NORTH);

         jptop.add(south,BorderLayout.SOUTH);
         basic.add(jptop);
        
       
         
         submit.addActionListener(new AddListener());
          addWindowListener(new WindowListener());

         
         idid = id;
        setTitle("Payment");
        setSize(700,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void update(){
           ResultSet rs = selectAll();
           String lastadminid = "";
           
           try{
               
               while(rs.next()){
               String adminid = rs.getString("paymentid");
               lastadminid = adminid;
               //JOptionPane.showMessageDialog(null, String.format("%s", lastadminid));
           }

           }catch(SQLException ex){
               
           }
           
           int num = Integer.parseInt(lastadminid.charAt(5)+"");
           //JOptionPane.showMessageDialog(null, String.format("%d", num));
           num++;
           String newadminid = lastadminid.substring(0,5) + num;
           
           jtfpaymentid.setText(newadminid);
       }
    
     public ResultSet selectAll() {

        String queryStr = " SELECT * FROM PAYMENT  ";
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(queryStr);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
    
    public void clearText(){
        jtforderid.setText("");
        jtfpaymentid.setText("");
        jtfprice.setText("");
        jtforderid.setText("");
    }
    
    public ResultSet selectRecord(String paymentid){
            String queryStr = "SELECT * FROM "+payment+" WHERE paymentid = ? ";
            ResultSet rs = null;

            try{
                stmt=conn.prepareStatement(queryStr);
                stmt.setString(1,paymentid);

                rs = stmt.executeQuery();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            
            
            return rs;
        }
    
private class WindowListener extends WindowAdapter{
        public void windowClosing(WindowEvent e) {
        
           int confirm = JOptionPane.showConfirmDialog(null, "Confirm to exit?", "Confirm?", JOptionPane.WARNING_MESSAGE);
           if(confirm == JOptionPane.YES_OPTION){
                closeFrame();
              
           }
       }
    }
    
    private class AddListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                String paymentid = jtfpaymentid.getText();
                ResultSet rs = selectRecord(paymentid);
                 try{
                     if(rs.next()){
                         JOptionPane.showMessageDialog(null,"Payment id have already exist","Please enter agaian",JOptionPane.ERROR_MESSAGE);
                     }
                     else{
                         String paymentStr ="INSERT INTO "+payment+" VALUES(?,?,?) ";
                         String paymentmethod = (String) jcbpaymentmethod.getSelectedItem();

                         stmt=conn.prepareStatement(paymentStr);
                         stmt.setString(1,paymentid);
                         stmt.setString(2,paymentmethod);
                         stmt.setString(3, jtforderid.getText());
        
                         stmt.executeUpdate();


                         JOptionPane.showMessageDialog(null,"Recorded has been created");

                         clearText();
                         closeFrame();
                         PrintTicket p =new PrintTicket(idid);
                         
                     }
               
                    }
                 catch(SQLException ex){
                         JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                     }

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
        
        public void closeFrame(){
      this.dispose();
  }
     public static void main(String [] args){
}
}