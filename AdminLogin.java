package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdminLogin extends JFrame{
    private JLabel jlbmenu = new JLabel("PROFILE");
    
    private JButton jbtstaff = new JButton("PROFILE MAINTENANCE");
    private JButton jbtbus = new JButton("BUS");
    private JButton jbtschedule = new JButton("SCHEDULE");
    private JButton jbtroute = new JButton("ROUTE");
    private JButton jbtreport = new JButton("REPORT");
    private JButton jbtback = new JButton("BACK");
    
    private ImageIcon admin = new ImageIcon(getClass().getResource("adminlog.gif"));
    private JLabel logo = new JLabel(admin,SwingConstants.CENTER); 
    private JPanel logoPanel = new JPanel(new FlowLayout());
    
    public AdminLogin(){
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
        JPanel jpcenter = new JPanel(new GridLayout(11,1));
        jpcenter.add(jbtstaff);
        jpcenter.add(new JLabel(""));
        jpcenter.add(jbtbus);
        jpcenter.add(new JLabel(""));
        jpcenter.add(jbtschedule);
        jpcenter.add(new JLabel(""));
        jpcenter.add(jbtroute);
        jpcenter.add(new JLabel(""));
        jpcenter.add(jbtreport);
        jpcenter.add(new JLabel(""));
        jpcenter.add(jbtback);
        panel.add(jpcenter,BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(new Insets(20, 20 ,20, 20)));
        add(panel,BorderLayout.CENTER);
        
        jbtstaff.addActionListener(new StaffButtonListener());
        jbtbus.addActionListener(new BusButtonListener());
        jbtschedule.addActionListener(new ScheduleButtonListener());
        jbtroute.addActionListener(new RouteButtonListener());
        jbtback.addActionListener(new BackListener());
        jbtreport.addActionListener(new ReportListener());
        addWindowListener(new WindowListener());
        
         setTitle("Welcome to Administration Login");
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
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
    
    private class StaffButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
         

                new ProfileMaintenance();
                closeFrame();
            }
        }
    
    
    private class BusButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){

                Bus bus = new Bus();
                closeFrame();
            }
        }
    
    private class ScheduleButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){

                try {
                    new createSchedule();
                    
                    closeFrame();
                } catch (ParseException ex) {
                    Logger.getLogger(StaffLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    private class RouteButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){

                createRoute route = new createRoute();
                closeFrame();
            }
        }
    
        private class BackListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                AdminLogin adminlogin = new AdminLogin();
                closeFrame();
            }
        }
        
        private class ReportListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                Report report = new Report();
               
            }
        }

    
    
    public void closeFrame(){
      this.dispose();
  }
         
    
    public static void main(String [] args){
    AdminLogin frame = new AdminLogin();
}
}