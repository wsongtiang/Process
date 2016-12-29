package ui;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import domain.*;
import control.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class createSchedule extends JFrame{ 
    private String host = "jdbc:derby://localhost:1527/BusDB";
        private String user = "nbuser";
        private String password = "nbuser";
        private String scheduletable = "Schedule";
//        private String autable = "Authentication";
        private Connection conn;
        private PreparedStatement stmt;

       private ScheduleControl schedulecontrol;
       
        
       private ButtonGroup btg = new ButtonGroup();
       private JRadioButton jrbscheduleid = new JRadioButton("ScheduleID :", true);

//       private ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
//       private String scheduleid = "";
//       private DefaultComboBoxModel dcbomFoodList= new DefaultComboBoxModel();
        private JComboBox jcbscheduleid = new JComboBox();

       private JLabel jlbscheduleid = new JLabel("ScheduleID :");
       private JLabel jlbdeparturedate = new JLabel("Departure Date :");
       private JLabel jlbdeparturetime = new JLabel("Departure Time :");
       private JLabel jlbarrivaldate = new JLabel("Arrival Date :");
       private JLabel jlbarrivaltime = new JLabel("Arrival Time :");
       private JLabel jlbbusid = new JLabel("Bus ID:");
       private JLabel jlbstaffid = new JLabel("Staff ID:");
       private JLabel jlbrouteid = new JLabel("Route ID:");
       private JLabel jlbstatus = new JLabel("Status(Valid):");
       private JLabel jlbtotalseat = new JLabel("Total Seat:");

       
//       private JTextField jtfsearchschedule = new JTextField(10);
       private JTextField jtfscheduleid = new JTextField();
       private JTextField jtfdeparturedate = new JTextField("Eg. 2016-03-18",10);
       private JTextField jtfdeparturetime = new JTextField("Eg. 8:00 PM",10);
       private JTextField jtfarrivaldate = new JTextField("Eg. 2016-03-18",10);
       private JTextField jtfarrivaltime = new JTextField("Eg. 8:00 PM",10);
       private JTextField jtfstatus = new JTextField();
//       private JTextField jtfbusid = new JTextField();
//       private JTextField jtfstaffid = new JTextField();
//       private JTextField jtfrouteid = new JTextField();
       private JTextField jtftotalseat = new JTextField();
       
       private JComboBox jcbbusid = new JComboBox();
       private JComboBox jcbstaffid = new JComboBox();
       private JComboBox jcbrouteid = new JComboBox();

       
       private JButton clear = new JButton("CLEAR");
       private JButton add = new JButton("ADD");
       private JButton update = new JButton("UPDATE");
       private JButton inactive = new JButton("IN-ACTIVE");
//       private JButton back = new JButton("BACK");
       private JButton back = new JButton("BACK");
       private JButton s = new JButton("SEARCH");
       
     private ImageIcon sch = new ImageIcon(getClass().getResource("schedule.png"));
    private JLabel logo = new JLabel(sch,SwingConstants.CENTER);
    private JPanel logoPanel = new JPanel(new FlowLayout());
    
    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> id2 = new ArrayList<String>();
    ArrayList<String> id3 = new ArrayList<String>();
    ArrayList<String> id4 = new ArrayList<String>();
    private String scheduleID;
 
    
    public createSchedule() throws ParseException{
        
        schedulecontrol = new ScheduleControl();
  
        
         JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("Schedule");
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
        
        FlowLayout fl = new FlowLayout();
        fl.setHgap(20);
        JPanel  search = new JPanel(fl);
        search.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
        5, 5,Color.pink), "Search by", TitledBorder.LEFT, TitledBorder.TOP));
        
        createConnection();
        
        Try();
        Try2();
        Try3();
        Try4();
        
        btg.add(jrbscheduleid);
        search.add(jrbscheduleid);
//        search.add(jtfsearchschedule);
        search.add(jcbscheduleid);
//        search.add(jcbrouteid);

        search.add(s);
        try {
       Image img = ImageIO.read(getClass().getResource("searchSmall.png"));
       s.setIcon(new ImageIcon(img));
       } catch (IOException ex) {
       }
       
        
        JPanel  schedule = new JPanel();
        schedule.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
        5, 5,Color.pink), "Schedule Details", TitledBorder.LEFT, TitledBorder.TOP));
        
        

        JPanel pane1 = new JPanel();
        JPanel details = new JPanel(new GridLayout(13,2));
        JLabel jlbsch = new JLabel("Schedule");
        details.add(jlbsch);
        
       Font font = jlbsch.getFont();
       Map attributes = font.getAttributes();
       attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
       jlbsch.setFont(font.deriveFont(attributes));
       
        details.add(new JLabel(""));
        details.add(jlbscheduleid);
        details.add(jtfscheduleid);
        jtfscheduleid.setEditable(false);
        String stafflatestID = schedulecontrol.getLatestID();
        String nextstaffID = "";
        int staffSqnNo = 0;
        String nextStaffSqnNo = "";
       
        staffSqnNo = Integer.parseInt(stafflatestID.substring(3));
        staffSqnNo+=1;
        nextStaffSqnNo = String.valueOf(staffSqnNo);
        int nextStaffSqnLength = 0;
        nextStaffSqnLength = nextStaffSqnNo.length();
        while(nextStaffSqnLength < 3){
            nextStaffSqnNo = "0" + nextStaffSqnNo;
            nextStaffSqnLength = nextStaffSqnNo.length();
        }
        
        nextstaffID = "SCD" + nextStaffSqnNo;
        
        scheduleID = nextstaffID;
        jtfscheduleid.setText(scheduleID);
        
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbdeparturedate);
        details.add(jtfdeparturedate);
         jtfdeparturedate.setForeground(Color.GRAY);
       jtfdeparturedate.setHorizontalAlignment(SwingConstants.LEFT);
        jtfdeparturedate.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jtfdeparturedate.setText("");
            }
            
        }); 
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbdeparturetime);
        details.add(jtfdeparturetime);
        jtfdeparturetime.setForeground(Color.GRAY);
       jtfdeparturetime.setHorizontalAlignment(SwingConstants.LEFT);
        jtfdeparturetime.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jtfdeparturetime.setText("");
            }
            
        }); 
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbarrivaldate);
        details.add(jtfarrivaldate);
        jtfarrivaldate.setForeground(Color.GRAY);
       jtfarrivaldate.setHorizontalAlignment(SwingConstants.LEFT);
        jtfarrivaldate.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jtfarrivaldate.setText("");
            }
            
        }); 
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbarrivaltime);
        details.add(jtfarrivaltime);
         jtfarrivaltime.setForeground(Color.GRAY);
       jtfarrivaltime.setHorizontalAlignment(SwingConstants.LEFT);
        jtfarrivaltime.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jtfarrivaltime.setText("");
            }
            
        });
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbstatus);
        details.add(jtfstatus);
        
        
        JPanel pane2 = new JPanel();
        JPanel details2 = new JPanel(new GridLayout(9,2));
        JLabel jlbroute = new JLabel("");
        details2.add(jlbroute);
        
       Font font1 = jlbroute.getFont();
       Map attributes1 = font1.getAttributes();
       attributes1.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
       jlbroute.setFont(font1.deriveFont(attributes1));
       
        details2.add(new JLabel(""));
        details2.add(jlbbusid);
//        details2.add(jtfbusid);
        details2.add(jcbbusid);
        details2.add(new JLabel(""));
        details2.add(new JLabel(""));
        details2.add(jlbstaffid);
//        details2.add(jtfstaffid);
         details2.add(jcbstaffid);
        details2.add(new JLabel(""));
        details2.add(new JLabel(""));
        details2.add(jlbrouteid);
        details2.add(jcbrouteid);
        details2.add(new JLabel(""));
        details2.add(new JLabel(""));
        details2.add(jlbtotalseat);
        details2.add(jtftotalseat);
        details2.add(new JLabel(""));
        details2.add(new JLabel(""));
        
        
        JPanel pane3 = new JPanel (new GridLayout(1,2));
        pane3.add(pane1);
        pane3.add(pane2);

        
        JPanel pane4 =new JPanel();
        JPanel details4 = new JPanel();
        details4.add(clear);
        
        JPanel jpsouth = new JPanel(new GridLayout(1,7));
         jpsouth.add(add);
        jpsouth.add(update);
        jpsouth.add(inactive);
        jpsouth.add(new JLabel(""));
        jpsouth.add(new JLabel(""));
         jpsouth.add(new JLabel(""));
        jpsouth.add(back);
//        jpsouth.add(home);
      
        schedule.add(pane3,BorderLayout.CENTER);
        pane1.add(details);
        pane2.add(details2);
        
        schedule.add(pane4,BorderLayout.SOUTH);
        pane4.add(details4);
          
         jptop.add(search,BorderLayout.NORTH);
         jptop.add(schedule,BorderLayout.CENTER);
         jptop.add(jpsouth,BorderLayout.SOUTH);
        basic.add(jptop);
        
         createConnection();
        
        setTitle("Schedule");
        setSize(700,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        add.addActionListener(new AddListener());
        s.addActionListener(new RetrieveListener());
        update.addActionListener(new UpdateListener());
        inactive.addActionListener(new DeleteListener());
        clear.addActionListener(new ClearListener());
        back.addActionListener(new backListener());
    }
    
        public void Try(){
       
        ResultSet rs = selectAll();
        try{
            while(rs.next()){
                
                String input = rs.getString("scheduleid");
                
                if(input.charAt(0) == 'S'){
                    id.add(rs.getString("scheduleid"));
                }

            }
        }catch(SQLException ex){
            
        }

        
        jcbscheduleid.setModel(new DefaultComboBoxModel(id.toArray()));
 
    }
       
       public ResultSet selectAll() {

        String queryStr = " SELECT * FROM SCHEDULE";
        
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(queryStr);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
       
       public void Try2(){
       
        ResultSet rs = selectAll2();
        try{
            while(rs.next()){
                
                String input = rs.getString("busid");
                
                if(input.charAt(0) == 'B'){
                    id2.add(rs.getString("busid"));
                }

            }
        }catch(SQLException ex){
            
        }

        
        jcbbusid.setModel(new DefaultComboBoxModel(id2.toArray()));
 
    }
       
       public ResultSet selectAll2() {

        String queryStr = " SELECT * FROM BUS";
        
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(queryStr);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
       
       public void Try3(){
       
        ResultSet rs = selectAll3();
        try{
            while(rs.next()){
                
                String input = rs.getString("staffid");
                
                if(input.charAt(0) == 'D'){
                    id3.add(rs.getString("staffid"));
                }

            }
        }catch(SQLException ex){
            
        }

        
        jcbstaffid.setModel(new DefaultComboBoxModel(id3.toArray()));
 
    }
       
       public ResultSet selectAll3() {

        String queryStr = " SELECT * FROM STAFF";
        
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(queryStr);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
       
       public void Try4(){
       
        ResultSet rs = selectAll4();
        try{
            while(rs.next()){
                
                String input = rs.getString("routeid");
                
                if(input.charAt(0) == 'R'){
                    id4.add(rs.getString("routeid"));
                }

            }
        }catch(SQLException ex){
            
        }

        
        jcbrouteid.setModel(new DefaultComboBoxModel(id4.toArray()));
 
    }
       
       public ResultSet selectAll4() {

        String queryStr = " SELECT * FROM ROUTE";
        
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(queryStr);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
    
    
    public ResultSet selectRecord(String scheduleid){
            String queryStr = "SELECT * FROM "+scheduletable+" WHERE scheduleid = ? ";
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
              
        String stafflatestID = schedulecontrol.getLatestID();
        String nextstaffID = "";
        int staffSqnNo = 0;
        String nextStaffSqnNo = "";
       
        staffSqnNo = Integer.parseInt(stafflatestID.substring(3));
        staffSqnNo+=1;
        nextStaffSqnNo = String.valueOf(staffSqnNo);
        int nextStaffSqnLength = 0;
        nextStaffSqnLength = nextStaffSqnNo.length();
        while(nextStaffSqnLength < 3){
            nextStaffSqnNo = "0" + nextStaffSqnNo;
            nextStaffSqnLength = nextStaffSqnNo.length();
        }
        
        nextstaffID = "SCD" + nextStaffSqnNo;
        
        scheduleID = nextstaffID;
        jtfscheduleid.setText(scheduleID);
               jtfdeparturedate.setText("Eg. 2016-03-18");
               jtfdeparturetime.setText("Eg. 8:00 PM");
               jtfarrivaldate.setText("Eg. 2016-03-18");
               jtfarrivaltime.setText("Eg. 8:00 PM");
               jtfstatus.setText("");
               jcbbusid.setSelectedItem("");
               jcbstaffid.setSelectedItem("");
               jcbrouteid.setSelectedItem("");
               jtftotalseat.setText("");
               
           }
      
       private class AddListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                
                try{
                   
                    if(jtfdeparturedate.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }
                    else if (!jtfdeparturedate.getText().matches("[0-9-]+")) {
                       JOptionPane.showMessageDialog(null, "Departure date must be in date format.", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                      else if(jtfdeparturetime.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }
                    else if (!jtfdeparturetime.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9] [APap][mM]$")) {
                       JOptionPane.showMessageDialog(null, "Departure time must be in time format", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                   else if(jtfarrivaldate.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }
                     else if (!jtfarrivaldate.getText().matches("[0-9-]+")) {
                       JOptionPane.showMessageDialog(null, "Arrival date must be in date format.", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                     else if(jtfarrivaltime.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }
                    else if (!jtfarrivaltime.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9] [APap][mM]$")) {
                       JOptionPane.showMessageDialog(null, "Arrival time must be in time format", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                    else if(jtfstatus.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }
                    else if (!jtfstatus.getText().matches("[a-zA-Z ]+")) {
                       JOptionPane.showMessageDialog(null, "Status must be in alphabet", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                    else if(jtftotalseat.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }
                    else if (!jtftotalseat.getText().matches("[0-9]+")) {
                       JOptionPane.showMessageDialog(null, "Total seat must be in numeric", "Format error", JOptionPane.ERROR_MESSAGE);
                 }

                    else{
                String scheduleid = jtfscheduleid.getText();
                String departuredate = jtfdeparturedate.getText();
                String arrivaldate = jtfarrivaldate.getText();
                int totalseat = Integer.parseInt(jtftotalseat.getText());
                
                ResultSet rs = selectRecord(scheduleid);
                 
                     if(rs.next()){
                         JOptionPane.showMessageDialog(null,"Staff id already exists","Please enter agaian",JOptionPane.ERROR_MESSAGE);
                     }
                     else{
//                         SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
//                         java.util.Date date = sdf1.parse(date1);
//                         java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
                         
                         String staffStr ="INSERT INTO "+scheduletable+" VALUES(?,?,?,?,?,?,?,?,?,?) ";
                         String staffid =(String)jcbstaffid.getSelectedItem();
                         String busid =(String)jcbbusid.getSelectedItem();
                         String routeid =(String)jcbrouteid.getSelectedItem();
                         stmt=conn.prepareStatement(staffStr);
                         stmt.setString(1,scheduleid);
                         
                         SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                         java.util.Date date = sdf1.parse(departuredate);
                         java.sql.Date ddate = new java.sql.Date(date.getTime()); 
                         stmt.setDate(2, ddate);
                         
                         stmt.setString(3,jtfdeparturetime.getText());
                         
                         SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                         java.util.Date datee = sdf2.parse(arrivaldate);
                         java.sql.Date adate = new java.sql.Date(datee.getTime()); 
                         stmt.setDate(4, adate);
                         
                         stmt.setString(5,jtfarrivaltime.getText());
                         
                         stmt.setString(6,busid);
                         stmt.setString(7,staffid);
                         stmt.setString(8,routeid);
                         stmt.setString(9,jtfstatus.getText());
                         stmt.setInt(10, totalseat);
        
                         stmt.executeUpdate();
                         
                         

                         JOptionPane.showMessageDialog(null,"Recorded has been created");

                         clearText();
                     }
                    }
                }
                    
                 catch(SQLException ex){
                         JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                     } catch (ParseException ex) {
                    Logger.getLogger(createSchedule.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
    }
       
       private class RetrieveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
             
            try{
               String scheduleid = (String)jcbscheduleid.getSelectedItem();
               ResultSet rs = selectRecord(scheduleid);
               
                if(rs.next()){
                    if(rs.getString("status").compareTo("Valid") == 0){
                    jtfscheduleid.setText(rs.getString("scheduleid"));
                    jtfdeparturedate.setText(rs.getString("departuredate"));
                    jtfdeparturetime.setText(rs.getString("departuretime"));
                    jtfarrivaldate.setText(rs.getString("arrivaldate"));
                    jtfarrivaltime.setText(rs.getString("arrivaltime"));
                    jtfstatus.setText(rs.getString("status"));
                    jcbbusid.setSelectedItem(rs.getString("busid"));
                    jcbstaffid.setSelectedItem(rs.getString("staffid"));
                    jcbrouteid.setSelectedItem(rs.getString("routeid"));
                    jtftotalseat.setText(rs.getString("totalseat"));
                    
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
       
        private class UpdateListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String scheduleid = jtfscheduleid.getText();
                ResultSet rs = selectRecord(scheduleid);
              
                if(rs.next()){
                    
                    String departuredate = jtfdeparturedate.getText();
                    String departuretime = jtfdeparturetime.getText();
                    String arrivaldate = jtfarrivaldate.getText();
                    String arrivaltime = jtfarrivaltime.getText();
                    String status = jtfstatus.getText();
                    String busid=(String) jcbbusid.getSelectedItem();
                    String staffid = (String)jcbstaffid.getSelectedItem();
                    String routeid =(String) jcbrouteid.getSelectedItem();
                    int totalseat = Integer.parseInt(jtftotalseat.getText());
                   

                    String updateStr ="UPDATE " + scheduletable +" SET departuredate=?, departuretime=?, arrivaldate=?, arrivaltime=?, status=?, busid=?, staffid=?, routeid=?, totalseat=? "+" WHERE scheduleid=? ";
                    stmt=conn.prepareStatement(updateStr);
                    
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = sdf1.parse(departuredate);
                    java.sql.Date ddate = new java.sql.Date(date.getTime()); 
                    stmt.setDate(1, ddate);
                    
                    stmt.setString(2, departuretime);
                    
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date datee = sdf2.parse(arrivaldate);
                    java.sql.Date adate = new java.sql.Date(datee.getTime()); 
                    stmt.setDate(3, adate);
                    
                    stmt.setString(4, arrivaltime);
                    stmt.setString(5, status);
                    stmt.setString(6, busid);
                    stmt.setString(7, staffid);
                    stmt.setString(8, routeid);
                    stmt.setInt(9,totalseat);
                    stmt.setString(10,scheduleid);
                    
 
                    stmt.executeUpdate();
                    
                    
                    JOptionPane.showMessageDialog(null,"Record has been updated");
                    clearText();
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"This code does not exists","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                Logger.getLogger(createSchedule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
        
        private class DeleteListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String scheduleid =(String) jcbscheduleid.getSelectedItem();
                ResultSet rs = selectRecord(scheduleid);
                
                if(rs.next()){
                    jtfscheduleid.setText(rs.getString("scheduleid"));
                    jtfdeparturedate.setText(rs.getString("departuredate"));
                    jtfdeparturetime.setText(rs.getString("departuretime"));
                    jtfarrivaldate.setText(rs.getString("arrivaldate"));
                    jtfarrivaltime.setText(rs.getString("arrivaltime"));
                    jtfstatus.setText(rs.getString("status"));
                    jcbbusid.setSelectedItem(rs.getString("busid"));
                    jcbstaffid.setSelectedItem(rs.getString("staffid"));
                    jcbrouteid.setSelectedItem(rs.getString("routeid"));
                    jtftotalseat.setText(rs.getString("totalseat"));
                    
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure?");
                    if(option == JOptionPane.YES_OPTION){
                        
                        
                        String deleteStr = "DELETE FROM " + scheduletable + " WHERE scheduleid = ? ";
                        stmt = conn.prepareStatement(deleteStr);
                        stmt.setString(1, scheduleid);
                        stmt.executeUpdate();
                        
                        JOptionPane.showMessageDialog(null, "Record deleted");
                        
                        clearText();
                        
                    }
                    }
                   else{
                        JOptionPane.showMessageDialog(null, "Record does not exits", "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
           catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           }
        }
    }
           
            private class ClearListener implements ActionListener{
                public void actionPerformed(ActionEvent e){
                    clearText();
                }
            }
            
            private class backListener implements ActionListener{
            public void actionPerformed(ActionEvent e){

                AdminLogin admin = new AdminLogin();
                closeFrame();
            }
        }
            
//    private void initComboBox(){
//        
//        scheduleList = control.getScheduleList(scheduleid);
//        
//        for(int i=0; i<scheduleList.size(); i++){
//            dcbomFoodList.addElement(scheduleList.get(i).getScheduleid());
//        }
//    }
    
     private void createConnection(){
        try{
            conn = DriverManager.getConnection(host,user,password);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
     
       public void closeFrame(){
      this.dispose();
  }
    
    public static void main(String [] args) throws ParseException{
   createSchedule frame = new createSchedule();
}

}