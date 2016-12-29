package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ProfileMaintenance extends JFrame{
    private JLabel jlbmenu = new JLabel("ADMIN LOGIN");
    
    private JButton jbtmanager = new JButton("Manager");
    private JButton jbtstaff = new JButton("Staff");
    private JButton jbtdriver = new JButton("Driver");
    private JButton jbtback = new JButton("Back");
    
    private ImageIcon admin = new ImageIcon(getClass().getResource("pm.gif"));
    private JLabel logo = new JLabel(admin,SwingConstants.CENTER); 
    private JPanel logoPanel = new JPanel(new FlowLayout());
    
    public ProfileMaintenance(){
        setLayout(new BorderLayout());
//        JPanel jpnorth = new JPanel();
//        jpnorth.add(jlbmenu);
//        jlbmenu.setFont(new Font("Serif", Font.BOLD, 24));
//        jlbmenu.setForeground(Color.GRAY);
//        add(jpnorth,BorderLayout.NORTH);
        JPanel panelNorth = new JPanel();
        JPanel jpnorth = new JPanel();
        logoPanel.add(logo);
        jpnorth.add(logoPanel);
        panelNorth.add(jpnorth);
        add(panelNorth,BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        JPanel jpcenter = new JPanel(new GridLayout(7,1));
        jpcenter.add(jbtmanager);
        jpcenter.add(new JLabel(""));
        jpcenter.add(jbtstaff);
        jpcenter.add(new JLabel(""));
        jpcenter.add(jbtdriver);
        jpcenter.add(new JLabel(""));
        jpcenter.add(jbtback);
//         jpcenter.add(new JLabel(""));
//        jpcenter.add(jbtreport);
        panel.add(jpcenter,BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(new Insets(20, 20 ,20, 20)));
        add(panel,BorderLayout.CENTER);
        
        jbtmanager.addActionListener(new ManagerListener());
        jbtstaff.addActionListener(new StaffListener());
        jbtdriver.addActionListener(new DriverListener());
        jbtback.addActionListener(new BackListener());
        
         setTitle("Welcome to Administration Login");
        setSize(600,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    private class ManagerListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                createManager manager= new createManager();
                closeFrame();
            }
        }
    
    
    private class StaffListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                createStaff staff = new createStaff();
                closeFrame();
            }
        }
    
    private class DriverListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                createDriver driver = new createDriver();
                closeFrame();
            }
        }
    
    private class BackListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                AdminLogin adminlogin = new AdminLogin();
                closeFrame();
            }
        }

    
    public void closeFrame(){
      this.dispose();
  }
         
    
    public static void main(String [] args){
    ProfileMaintenance frame = new ProfileMaintenance();
}
}