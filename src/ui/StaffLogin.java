package ui;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffLogin extends JFrame{
    
    private JLabel jlbmenu = new JLabel("STAFF LOGIN");
    
    private JButton jbtbookingticket = new JButton("BOOKING TICKET");
    private JButton jbtEditStaff = new JButton("EDIT STAFF PROFILE");
    private JButton jbtback = new JButton("BACK");
    
    private ImageIcon admin = new ImageIcon(getClass().getResource("Stafflogin.png"));
    private JLabel logo = new JLabel(admin,SwingConstants.CENTER); 
    private JPanel logoPanel = new JPanel(new FlowLayout());
   
    
    
    public StaffLogin(){
        setLayout(new BorderLayout());

        JPanel panelNorth = new JPanel();
        JPanel jpnorth = new JPanel();
        logoPanel.add(logo);
        jpnorth.add(logoPanel);
        logoPanel.setOpaque(rootPaneCheckingEnabled);
        panelNorth.add(jpnorth);
       add(panelNorth,BorderLayout.NORTH);
       
        
        JPanel panel = new JPanel();

    
        JPanel jpcenter = new JPanel(new GridLayout(6,1));

        jpcenter.add(new JLabel(""));
        jpcenter.add(jbtbookingticket);
        jpcenter.add(new JLabel(""));
        jpcenter.add(jbtEditStaff);
        jpcenter.add(new JLabel(""));
        jpcenter.add(jbtback);
        panel.add(jpcenter,BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(new Insets(20, 20 ,20, 20)));
        add(panel,BorderLayout.CENTER);
        
         jbtbookingticket.addActionListener(new BookButtonListener());
        jbtEditStaff.addActionListener(new EditButtonListener());
        jbtback.addActionListener(new BackButtonListener());
        addWindowListener(new WindowListener());
        
         setTitle("Welcome to Staff Login");
        setSize(600,525);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

       
     } 
    
        
         private class BookButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                ViewSchedule schedule = new ViewSchedule();
                closeFrame();
            }
        }
         
          private class EditButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                 new EditingProfile();
                closeFrame();
            }
        }
          
          private class BackButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
           int confirm = JOptionPane.showConfirmDialog(null, "Confirm Log Out?", "Confirm?", JOptionPane.WARNING_MESSAGE);
           if(confirm == JOptionPane.YES_OPTION){
                
               closeFrame();
               new LogIn();
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
    
    public static void main(String [] args){
    StaffLogin frame = new StaffLogin();
}
}