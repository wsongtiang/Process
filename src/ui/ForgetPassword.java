package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ForgetPassword extends JFrame{
    private String host = "jdbc:derby://localhost:1527/BusDB";
        private String user = "nbuser";
        private String password = "nbuser";
        private String routetable = "Authentication";
        private Connection conn;
        private PreparedStatement stmt;
    
    private JLabel jlbuserid = new JLabel("UserID :");
    private JLabel jlbprivacyques = new JLabel("Privacy Question :");
    private JLabel jlbprivacyans = new JLabel("Privacy Answer :");
    
    private JTextField jtfuserid = new JTextField(20);
    private JTextField jtfprivacyques = new JTextField();
    private JTextField jtfprivacyans = new JTextField(20);
    
    private JButton jbtconfirm = new JButton("CONFIRM");
    private JButton jbtback = new JButton("BACK");
    private JButton search = new JButton("SEARCH");
    
    private ImageIcon forget = new ImageIcon(getClass().getResource("forgotpass.png"));
    private JLabel logo = new JLabel(forget,SwingConstants.CENTER); 
    private JPanel logoPanel = new JPanel(new FlowLayout());
    
    public ForgetPassword(){
        setLayout(new BorderLayout());
        
        JPanel panelNorth = new JPanel();
        JPanel jpnorth = new JPanel();
        logoPanel.add(logo);
        jpnorth.add(logoPanel);
        panelNorth.add(jpnorth);
        add(panelNorth,BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        JPanel jpcenter = new JPanel(new GridLayout(4,3));
        jpcenter.add(jlbuserid);
        jpcenter.add(jtfuserid);
        jpcenter.add(new JLabel(""));
        jpcenter.add(search);
        
        search.setOpaque(false);
        search.setContentAreaFilled(false);
        search.setBorderPainted(false);
        
        jpcenter.add(jlbprivacyques);
        jpcenter.add(jtfprivacyques);
        jtfprivacyques.setEditable(false);
        jpcenter.add(jlbprivacyans);
        jpcenter.add(jtfprivacyans);
        panel.add(jpcenter,BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(new Insets(20, 20 ,20, 20)));
        add(panel,BorderLayout.CENTER);
        
        JPanel panelconfirm = new JPanel(new BorderLayout());
        JPanel jpSouth = new JPanel();
        jpSouth.add(jbtback);
        jpSouth.add(jbtconfirm);
        
        panelconfirm.add(jpSouth,BorderLayout.EAST);
        add(panelconfirm,BorderLayout.SOUTH);
        
        search.addActionListener(new SearchListener());
        jbtconfirm.addActionListener(new ConfirmListener());
        jbtback.addActionListener(new BackListener());
        
        createConnection();
        
        setTitle("Forget Password");
        setSize(600,325);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
       public ResultSet selectRecord(String userid){
            String queryStr = "SELECT * FROM "+routetable+" WHERE userid = ? ";
            ResultSet rs = null;

            try{
                stmt=conn.prepareStatement(queryStr);
                stmt.setString(1,userid);

                rs = stmt.executeQuery();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            
            return rs;
        }
       
       public ResultSet selectRecord2(String userid){
            String queryStr = "SELECT * FROM AUTHENTICATION a , STAFF s WHERE a.staffid = s.staffid AND userid = ? ";
            
            ResultSet rs = null;

            try{
                stmt=conn.prepareStatement(queryStr);
                stmt.setString(1,userid);

                rs = stmt.executeQuery();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            
            return rs;
        }
    
      private class SearchListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
             
            try{
                String routeid = jtfuserid.getText();
               ResultSet rs = selectRecord(routeid);
               
                if(rs.next()){
                    jtfprivacyques.setText(rs.getString("privacyques"));
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
      
        private class ConfirmListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
        
             
            try{
                String id = jtfuserid.getText();
               ResultSet rs = selectRecord2(id);
               
               
                if(rs.next()){
                    
                    String password = rs.getString("icno");
                    
                    String ans = jtfprivacyans.getText();
                    String dbans = rs.getString("privacyans");
                   // jtfprivacyques.setText(rs.getString("privacyans"));
                    if(ans.compareTo(dbans) == 0){
                       
                        String updateStr ="UPDATE authentication SET password=? "+" WHERE privacyans=? ";
                        stmt=conn.prepareStatement(updateStr);
                        stmt.setString(1, password);
                        stmt.setString(2,ans);
                        stmt.executeUpdate();
                        
                        JOptionPane.showMessageDialog(null, "Answer matched!");
                        closeFrame();
                        new LogIn();
                        
                    } 
                    else if(ans.compareTo(dbans) != 0){
                        JOptionPane.showMessageDialog(null, "Answer not match, please enter again!");
                    }
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
        
        private class BackListener implements ActionListener{
            public void actionPerformed(ActionEvent e){

                new LogIn();
                closeFrame();
            }
        }
      
         public void closeFrame(){
        this.dispose();
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
    
    public static void main(String [] args){
    ForgetPassword frame = new ForgetPassword();
}
}