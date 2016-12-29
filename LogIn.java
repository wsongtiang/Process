package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.*; 
import domain.*;
import control.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn extends JFrame{
    private String host = "jdbc:derby://localhost:1527/BusDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String autable = "AUTHENTICATION";
    private Connection conn;
    private PreparedStatement stmt;
    
//    private LoginDomain logindo;
//    private LoginControl logcon;
    
    private JLabel jlbuserid = new JLabel("UserID");
    private  JLabel jlbpassword = new JLabel("Password");
    private JTextField jtfuserid = new JTextField (15);
    private JPasswordField jtfpassword = new JPasswordField();
    
    private JButton jbtlogin = new JButton("LOGIN");
//    private JLabel jlbforget = new JLabel("Forget Password?");
    private JButton jbtforget = new JButton("Forget Password?");
    
    private ImageIcon login = new ImageIcon(getClass().getResource("loginSmall.png"));
    private JLabel logo = new JLabel(login,SwingConstants.CENTER); 
    private JPanel logoPanel = new JPanel(new FlowLayout());
    
     
    
    public LogIn(){
//        logcon = new LoginControl();
        
        setLayout(new BorderLayout());
        
        JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("Log In");
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
        
        JPanel pane1 = new JPanel();
        JPanel jpcenter = new JPanel(new GridLayout(4,2));
        jpcenter.add(jlbuserid);
        jpcenter.add(jtfuserid);
        jpcenter.add(new JLabel(""));
        jpcenter.add(new JLabel(""));
        jpcenter.add(jlbpassword);
        jpcenter.add(jtfpassword);
         jpcenter.add(new JLabel(""));
//        jpcenter.add(jlbforget);
//        Font font = jlbforget.getFont();
         jpcenter.add(jbtforget);
        Font font = jbtforget.getFont();
        //button transparent
        jbtforget.setOpaque(false);
        jbtforget.setContentAreaFilled(false);
        jbtforget.setBorderPainted(false);
        
       Map attributes = font.getAttributes();
       attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
       jbtforget.setFont(font.deriveFont(attributes));
//       jlbforget.setFont(font.deriveFont(attributes));
       
       
       JPanel pane2 = new JPanel();
       JPanel jpsouth = new JPanel();
       jpsouth.add(jbtlogin);
         
       
        pane1.add(jpcenter);
        basic.add(jptop,BorderLayout.NORTH);
        jptop.add(pane1,BorderLayout.CENTER);
        pane2.add(jpsouth);
       jptop.add(pane2,BorderLayout.SOUTH);
        
       createConnection();
        jbtlogin.addActionListener(new ButtonListener());
        jbtforget.addActionListener(new ForgetListener());
        
        setTitle("LogIn");
        setSize(600,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
         
        
    }
    
    public ResultSet selectRecord(String userid){
        String queryStr = "SELECT * FROM "+ autable + " WHERE userid = ? ";
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, userid);
            
            rs = stmt.executeQuery();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
    
//    private class ButtonListener implements ActionListener{
//        public void actionPerformed(ActionEvent e){
//            if(e.getSource() == jbtlogin){
//                String userid = jtfuserid.getText();
//                String password = jtfpassword.getText();
//                
//                ResultSet rs 
//            }
//        }
//    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == jbtlogin){
                String enteredUsername = jtfuserid.getText();
                String enteredPassword = jtfpassword.getText();
                
                if(!enteredUsername.equals("") && !enteredPassword.equals("")){
                  
                    ResultSet rs = selectRecord(enteredUsername);
                    
                    try{
                    if(rs.next()){
                        String username = rs.getString("userid");
                        String password = rs.getString("password");
                        String staffid = rs.getString("staffid");
                        
                        if(username.compareTo(enteredUsername) == 0){
                            if(password.compareTo(enteredPassword) == 0){
                                if(staffid.charAt(0)=='M'){
                                    JOptionPane.showMessageDialog(null, "Welcome,Manager!","Login Successful!",JOptionPane.INFORMATION_MESSAGE);
                                    new AdminLogin();
                                    closeFrame();
                                }
                                else if(staffid.charAt(0)=='S'){
                                    JOptionPane.showMessageDialog(null, "Welcome,Staff!","Login Successful!",JOptionPane.INFORMATION_MESSAGE);
                                    new StaffLogin();
                                    closeFrame();
                                }
                            }else{
                               JOptionPane.showMessageDialog(null, "Invalid password.","Login Failed",JOptionPane.ERROR_MESSAGE);
                               jtfuserid.setText("");
                               jtfpassword.setText("");
                               jtfuserid.requestFocusInWindow();}
                        
                        }
                        
                    }else{
                               JOptionPane.showMessageDialog(null, "Invalid username.","Login Failed",JOptionPane.ERROR_MESSAGE);
                               jtfuserid.setText("");
                               jtfpassword.setText("");
                               jtfuserid.requestFocusInWindow();}
                    }catch(SQLException ex){
                        
                    }
                    
//                    if(validity){
//                        
//                     
//                        JOptionPane.showMessageDialog(null,"Congrats login successful!","Welcome",JOptionPane.INFORMATION_MESSAGE);
//                        new StaffLogin();
////                        closeFrame();
////                        new Home();
////                        currentUser = enteredUsername;
////                        Login loginReadStaff = loginControl.selectUser(enteredUsername);
////                        currentStaff = loginReadStaff.getStaffID();
//                        dispose();
//                    }
//                    else{
//                        JOptionPane.showMessageDialog(null, "Invalid username or password.","Login Failed",JOptionPane.ERROR_MESSAGE);
//                        jtfuserid.setText("");
//                        jtfpassword.setText("");
//                        jtfuserid.requestFocusInWindow();
//                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Both username and password fields cannot be blank!","Attention",JOptionPane.WARNING_MESSAGE);
//                    jtfUsername.requestFocusInWindow();
                    
                }
            }
            else{ //exit button
//               logcon.closeDB();
                closeFrame();
               System.exit(0); 
            }
        }
    }
    
    public void closeFrame(){
        this.dispose();
    }
    
    private class ForgetListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new ForgetPassword();
            closeFrame();
       }
    }
    
    private class WindowListener extends WindowAdapter{
        public void windowClosing(WindowEvent e) {
        
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
    
public static void main(String [] args){
    LogIn frame = new LogIn();
}
}
