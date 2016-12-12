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

  


public class createDriver extends JFrame{
        private String host = "jdbc:derby://localhost:1527/BusDB";
        private String user = "nbuser";
        private String password = "nbuser";
        private String stafftable = "Staff";
        private String autable = "Authentication";
        private Connection conn;
        private PreparedStatement stmt;
    
        private DriverControl staffcontrol;
    
       private ButtonGroup btg = new ButtonGroup();
//       private JRadioButton jrbuserid = new JRadioButton("UserID :", true);
       private JRadioButton jrbstaffid =new JRadioButton("StaffID :",true);
       private JRadioButton jrbstaffname = new JRadioButton("Staff Name :");
//       private JComboBox jcbuserid = new JComboBox(new Object[]{"user001","user002","user003"});
       private JCheckBox jcbpassword = new JCheckBox("Show Password");
       private JComboBox jcbstaffid = new JComboBox(new Object[]{"S001","S002","S003"});
       private JTextField jtfstaffname = new JTextField(10);
       
       private JLabel jlbstaffid = new JLabel("Staff ID :");
       private JLabel jlbuserid = new JLabel("User ID :");
       private JLabel jlbpassword = new JLabel("Password :");
       private JLabel jlbprivacyques = new JLabel("Privacy Question :");
       private JLabel jlbprivacyans = new JLabel("Privacy Answer :");
       private JLabel jlbname = new JLabel("Name :");
       private JLabel jlbgender = new JLabel("Gender :");
       private JLabel jlbic = new JLabel("IC NO :");
       private JLabel jlbcontact = new JLabel("Contact No:                               +60");
       private JLabel jlbemail = new JLabel("E-mail :");
       private JLabel jlbaddress = new JLabel("Address :");
       private JLabel jlbpostcode = new JLabel("Postcode :");
       private JLabel jlbcity = new JLabel("City :");
       private JLabel jlbstate = new JLabel("State :");
       private JLabel jlbposition = new JLabel("Position :");
       
       private JTextField jtfstaffid = new JTextField(10);
       private JTextField jtfuserid = new JTextField(10);
       private JPasswordField jtfpassword = new JPasswordField(10);
       private JTextField jtfprivacyques = new JTextField(10);
       private JTextField jtfprivacyans = new JTextField(10);
       private JTextField jtfname = new JTextField(10);
//       private JTextField jtfgender = new JTextField(10);
       JComboBox jcbgender = new JComboBox(new String[] {"Male" , "Female"});

        private JTextField jtfic = new JTextField("Eg. 960202075233");
       private JTextField jtfcontact = new JTextField("Eg. 174076399");
       private JTextField jtfemail = new JTextField("Eg. user001@gmail.com");
       private JTextField jtfaddress = new JTextField(40);
         private JTextField jtfpostcode = new JTextField("Eg. 12000");
       private JTextField jtfcity = new JTextField(10);
       private JTextField jtfstate = new JTextField(10);
       private JTextField jtfposition = new JTextField(10);
    
       private JButton clear = new JButton("CLEAR");
       private JButton add = new JButton("ADD");
       private JButton update = new JButton("UPDATE");
       private JButton delete = new JButton("DELETE");
       private JButton back = new JButton("BACK");
//       private JButton home = new JButton("HOME");
       private JButton s = new JButton("SEARCH");
//       private JButton check = new JButton("Check Position");
       
   private ImageIcon staff = new ImageIcon(getClass().getResource("staff.png"));
    
    private JLabel logo = new JLabel(staff,SwingConstants.CENTER);
    
    private JPanel logoPanel = new JPanel(new FlowLayout());
    
    private String staffID;
    private String userID;
  
    
    ArrayList<String> id = new ArrayList<String>();
     JComboBox jcb = new JComboBox();
     
      private String name;
    private String icno;
    private String contactno;
    private String cpassword;
    private String email;
    private String address;
    private String postcode;
    private String city;
    private String state;
       
       public createDriver(){
           staffcontrol = new DriverControl();
           
         JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("DRIVER");
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
        fl.setHgap(10);
        JPanel  search = new JPanel(fl);
        search.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
        5, 5,Color.pink), "Search by", TitledBorder.LEFT, TitledBorder.TOP));
        
        createConnection();
        
        Try();
        
//         btg.add(jrbuserid);
        btg.add(jrbstaffid);
//        btg.add(jrbstaffname);
//        search.add(jrbuserid);
//        search.add(jcbuserid);
        search.add(jrbstaffid);
        search.add(jcb);
//        search.add(jrbstaffname);
//        search.add(jtfstaffname);
          search.add(s);
        try {
       Image img = ImageIO.read(getClass().getResource("searchSmall.png"));
       s.setIcon(new ImageIcon(img));
       } catch (IOException ex) {
       }

        JPanel details = new JPanel(new GridLayout(14,4));
        details.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
      5, 5, Color.pink), "Driver Details", TitledBorder.LEFT, TitledBorder.TOP));
        
        details.add(jlbstaffid);
        details.add(jtfstaffid);
        jtfstaffid.setEditable(false);
        
//          jtfstaffid.setEditable(false);
//        String stafflatestID = staffcontrol.getLatestID();
//        String nextstaffID = "";
//        int staffSqnNo = 0;
//        String nextStaffSqnNo = "";
//       
//        staffSqnNo = Integer.parseInt(stafflatestID.substring(3));
//        staffSqnNo+=1;
//        nextStaffSqnNo = String.valueOf(staffSqnNo);
//        int nextStaffSqnLength = 0;
//        nextStaffSqnLength = nextStaffSqnNo.length();
//        while(nextStaffSqnLength < 3){
//            nextStaffSqnNo = "0" + nextStaffSqnNo;
//            nextStaffSqnLength = nextStaffSqnNo.length();
//        }
//        
//        nextstaffID = "D" + nextStaffSqnNo;
//        
//        staffID = nextstaffID;
        jtfstaffid.setText(staffID);
        
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbuserid);
        details.add(jtfuserid);
        jtfuserid.setEditable(false);
       details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbprivacyques);
        details.add(jtfprivacyques);
        jtfprivacyques.setEditable(false);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbprivacyans);
         details.add(jtfprivacyans);
          jtfprivacyans.setEditable(false);
         details.add(new JLabel(""));
         details.add(new JLabel(""));
        details.add(jlbname); 
        details.add(jtfname); 
        details.add(jlbgender);
        details.add(jcbgender);
        details.add(jlbic);
         details.add(jtfic);
          jtfic.setForeground(Color.GRAY);
       jtfic.setHorizontalAlignment(SwingConstants.LEFT);
        jtfic.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jtfic.setText("");
            }
            
        }); 
        details.add(jlbcontact);
        details.add(jtfcontact);
         jtfcontact.setForeground(Color.GRAY);
       jtfcontact.setHorizontalAlignment(SwingConstants.LEFT);
        jtfcontact.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jtfcontact.setText("");
            }
            
        }); 
          details.add(jlbpassword);
        details.add(jtfpassword);
        jtfpassword.setEditable(false);
//        details.add(jcbpassword);
//          jcbpassword.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                showPasswordListenerActionPerformed(evt);
//            }
//        });
         details.add(new JLabel(""));
         details.add(new JLabel(""));
        details.add(jlbemail);
        details.add(jtfemail);
          jtfemail.setForeground(Color.GRAY);
       jtfemail.setHorizontalAlignment(SwingConstants.LEFT);
        jtfemail.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jtfemail.setText("");
            }
            
        }); 
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbaddress);
        details.add(jtfaddress);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbpostcode);
         details.add(jtfpostcode);
           jtfpostcode.setForeground(Color.GRAY);
       jtfpostcode.setHorizontalAlignment(SwingConstants.LEFT);
        jtfpostcode.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jtfpostcode.setText("");
            }
            
        }); 
         details.add(new JLabel(""));
         details.add(new JLabel(""));
        details.add(jlbcity);
         details.add(jtfcity);
         details.add(new JLabel(""));
         details.add(new JLabel(""));
        details.add(jlbstate);
        details.add(jtfstate);     
        details.add(jlbposition);
        details.add(jtfposition); 
           jtfposition.setEditable(false);
        jtfposition.setText("Driver");
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(clear);

        JPanel jpsouth = new JPanel(new GridLayout(1,7));
        jpsouth.add(add);
        jpsouth.add(update);
        jpsouth.add(delete);
        jpsouth.add(new JLabel(""));
        jpsouth.add(new JLabel(""));
         jpsouth.add(new JLabel(""));
        jpsouth.add(back);
//        jpsouth.add(home);
        
       setContentPane(basic);
       pack();
        basic.setVisible(true);
        jptop.add(search,BorderLayout.NORTH);
        jptop.add(details,BorderLayout.CENTER);
        jptop.add(jpsouth,BorderLayout.SOUTH);
        basic.add(jptop);
        
//        jtfname.setEditable(false);
//        jtfemail.setEditable(false);
//        jtfcontact.setEditable(false);
//        jcbgender.setEditable(false);
//        jtfic.setEditable(false);
//        jtfaddress.setEditable(false);
//        jtfcity.setEditable(false);
//        jtfstate.setEditable(false);
//        jtfpostcode.setEditable(false);
//        
//        jtfuserid.setEditable(false);
//        jtfpassword.setEditable(false);
//        jtfprivacyques.setEditable(false);
//        jtfprivacyans.setEditable(false);       
//        jtfposition.setEditable(false);
//        jtfposition.setText("Driver");
        
        add.addActionListener(new AddListener());
        s.addActionListener(new RetrieveListener());
        update.addActionListener(new UpdateListener());
        delete.addActionListener(new DeleteListener());
        clear.addActionListener(new ClearListener());
        back.addActionListener(new backListener());
//        check.addActionListener(new CheckListener());
        
        createConnection();
        
        update();
        
        setTitle("Driver");
        setSize(800,750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
}
       
          public void update(){
           ResultSet rs = selectAll();
           String lastadminid = "";
           
           try{
               
               while(rs.next()){
               String adminid = rs.getString("staffid");
               if(adminid.charAt(0) == 'D'){
                   lastadminid = adminid;
               }
               
           }

           }catch(SQLException ex){
               
           }
           
           int num = Integer.parseInt(lastadminid.charAt(3)+"");
           num++;
           String newadminid = lastadminid.substring(0,3) + num;
           
           jtfstaffid.setText(newadminid);
       }
       
         public void Try(){
       
        ResultSet rs = selectAll();
        try{
            while(rs.next()){
                
                String input = rs.getString("staffid");
                
                if(input.charAt(0) == 'D'){
                    id.add(rs.getString("staffid"));
                }
                
                
                //id.add(rs.getString("staffid"));
            }
        }catch(SQLException ex){
            
        }

        
        jcb.setModel(new DefaultComboBoxModel(id.toArray()));
        
//        String[] id1 = new String[id.size()];
//        id1 = id.toArray(id1);
        
        
        
//        String staffid1 = (String) JOptionPane.showInputDialog(null, "Choose STAFFID for search, if no click cancel.",
//        "STAFF ID", JOptionPane.QUESTION_MESSAGE, null,id1,id1[0]); 
       

        //jtfcomboid.setText(staffid1);
    }
       
       public ResultSet selectAll() {

        String queryStr = " SELECT * FROM STAFF  ";
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(queryStr);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
       
       
       public ResultSet selectRecord(String staffid){
            String queryStr = "SELECT * FROM "+stafftable+" WHERE staffid = ? ";
            ResultSet rs = null;

            try{
                stmt=conn.prepareStatement(queryStr);
                stmt.setString(1,staffid);

                rs = stmt.executeQuery();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            
            
            return rs;
        }
       
       public ResultSet selectRecord2(String staffid){
            String queryStr = "SELECT * FROM "+autable+" WHERE staffid = ? ";
            ResultSet rs = null;

            try{
                stmt=conn.prepareStatement(queryStr);
                stmt.setString(1,staffid);

                rs = stmt.executeQuery();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            
            
            return rs;
        }
       
       
       
        public void clearText(){
              update();
       
               jtfuserid.setText("");
               jtfname.setText("");
               jtfic.setText("");
               jtfcontact.setText("");
              jcbgender.setSelectedItem("Male");
               jtfemail.setText("");
               jtfaddress.setText("");
               jtfpostcode.setText("");
               jtfcity.setText("");
               jtfstate.setText("");
               jtfposition.setText("Driver");
           }
        
        private class AddListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                
                try{
               
                    if(jtfname.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }else if (!jtfname.getText().matches("[a-zA-Z ]+")) {
                       JOptionPane.showMessageDialog(null, "Name must be string.", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                    else if(jtfic.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }else if (jtfic.getText().length()>12 || jtfic.getText().length()<12) {
                       JOptionPane.showMessageDialog(null, "ICNO only can input 12 digits", "Format error", JOptionPane.ERROR_MESSAGE);
                    }
                   else if(jtfcontact.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }else if (jtfcontact.getText().length()>9 || jtfcontact.getText().length()<9) {
                       JOptionPane.showMessageDialog(null, "Contact only can only input 9/10 digits", "Format error", JOptionPane.ERROR_MESSAGE);
                    }
//                    else if(jtfpassword.getText().equals("")){
//                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
//                    }
                  else if(jtfemail.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    } 
                  else if (!jtfemail.getText(). matches(".+@.+")) {
                       JOptionPane.showMessageDialog(null, "Email must be correct format.", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                   else if(jtfaddress.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }
                  else if(jtfpostcode.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }else if (!jtfpostcode.getText(). matches("[0-9]+")) {
                       JOptionPane.showMessageDialog(null, "Postcode must be input integer.", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                   else if(jtfcity.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }else if (!jtfcity.getText().matches("[a-zA-Z ]+")) {
                       JOptionPane.showMessageDialog(null, "City must be string.", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                  else if(jtfstate.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }else if (!jtfstate.getText().matches("[a-zA-Z ]+")) {
                       JOptionPane.showMessageDialog(null, "State must be string.", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
             
                    else{
                String staffid = jtfstaffid.getText();
                ResultSet rs = selectRecord(staffid);
                 
                     if(rs.next()){
                         JOptionPane.showMessageDialog(null,"Driver id already exists","Please enter agaian",JOptionPane.ERROR_MESSAGE);
                     }
                     else{
                         String staffStr ="INSERT INTO "+stafftable+" VALUES(?,?,?,?,?,?,?,?,?,?,?) ";
                         String gender = (String) jcbgender.getSelectedItem();
                         
                         stmt=conn.prepareStatement(staffStr);
                         stmt.setString(1,staffid);
                         stmt.setString(2,jtfname.getText());
                         stmt.setString(3,jtfemail.getText());
                         stmt.setString(4,jtfcontact.getText());
                         stmt.setString(5,gender);
                         stmt.setString(6,jtfic.getText());
                         stmt.setString(7,jtfaddress.getText());
                         stmt.setString(8,jtfcity.getText());
                         stmt.setString(9,jtfstate.getText());
                         stmt.setString(10,jtfpostcode.getText());
                         stmt.setString(11,jtfposition.getText());
        
                         stmt.executeUpdate();


                         JOptionPane.showMessageDialog(null,"Recorded has been created");
                            
                         clearText();
                     }
                     }
                 }
                     
                 catch(SQLException ex){
                         JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                     }

            }
    }
        
       private class RetrieveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
             
            try{
                String staffid = jcb.getSelectedItem()+"";
               ResultSet rs = selectRecord(staffid);

                if(rs.next()){
                    jtfstaffid.setText(rs.getString("staffid"));
                    jtfname.setText(rs.getString("staffname"));
                    jtfemail.setText(rs.getString("email"));
                    jtfcontact.setText(rs.getString("contactno"));

                    
                    String gender = rs.getString("gender");
                    if(gender.equalsIgnoreCase("Male")){
                        jcbgender.setSelectedItem("Male");
                    }else if(gender.equalsIgnoreCase("Female")){
                        jcbgender.setSelectedItem("Female");
                    }

                    jtfic.setText(rs.getString("icno"));
                    jtfaddress.setText(rs.getString("address"));
                    jtfcity.setText(rs.getString("city"));
                    jtfstate.setText(rs.getString("state"));
                    jtfpostcode.setText(rs.getString("postcode"));
                    jtfposition.setText(rs.getString("position"));
  
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
                String staffid = jtfstaffid.getText();
                ResultSet rs = selectRecord(staffid);
                if(rs.next()){
                    String name = jtfname.getText();
                    String email = jtfemail.getText();
                    String contactno = jtfcontact.getText();
                    String gender = (String)jcbgender.getSelectedItem();
                    String icno = jtfic.getText();
                    String address = jtfaddress.getText();
                    String city= jtfcity.getText();
                    String state = jtfstate.getText();
                    String postcode = jtfpostcode.getText();
                    String position = jtfposition.getText();


                    String updateStr ="UPDATE " + stafftable +" SET staffname=?, email=?, contactno=?, gender=?, icno=?, address=?, city=?, state=?, postcode=?, position=? "+" WHERE staffid=? ";
                    stmt=conn.prepareStatement(updateStr);
                    stmt.setString(1, name);
                    stmt.setString(2, email);
                    stmt.setString(3, contactno);
                    stmt.setString(4, gender);
                    stmt.setString(5, icno);
                    stmt.setString(6, address);
                    stmt.setString(7, city);
                    stmt.setString(8, state);
                    stmt.setString(9, postcode);
                    stmt.setString(10, position);
                    stmt.setString(11, staffid);
 
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
            }
        }
        
    }
       
       private class DeleteListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String staffid = jtfstaffid.getText();
                ResultSet rs = selectRecord(staffid);
                ResultSet rs2 = selectRecord2(staffid);
                
                if(rs.next()){
                    jtfname.setText(rs.getString("staffname"));
                    jtfemail.setText(rs.getString("email"));
                    jtfcontact.setText(rs.getString("contactno"));
                    
                    String gender = rs.getString("gender");
                    if(gender.equalsIgnoreCase("Male")){
                        jcbgender.setSelectedItem("Male");
                    }else if(gender.equalsIgnoreCase("Female")){
                        jcbgender.setSelectedItem("Female");
                    }
                    
                    jtfic.setText(rs.getString("icno"));
                    jtfaddress.setText(rs.getString("address"));
                    jtfcity.setText(rs.getString("city"));
                    jtfstate.setText(rs.getString("state"));
                    jtfpostcode.setText(rs.getString("postcode"));
                    jtfposition.setText(rs.getString("position"));
                    
                    rs2.next();
                    jtfuserid.setText(rs2.getString("userid"));
                    jtfpassword.setText(rs2.getString("password"));
                    jtfprivacyques.setText(rs2.getString("privacyques"));
                    jtfprivacyans.setText(rs2.getString("privacyans"));
               
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure?");
                    if(option == JOptionPane.YES_OPTION){
                            
                            String deleteStr = "DELETE FROM " + stafftable + " WHERE staffid = ? ";
                            stmt = conn.prepareStatement(deleteStr);
                            stmt.setString(1, staffid);
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

                new ProfileMaintenance();
                closeFrame();
            }
        }
       
       private void showPasswordListenerActionPerformed(java.awt.event.ActionEvent evt){
         if(jcbpassword.isSelected()){
            jtfpassword.setEchoChar((char)0);
        }else{
          jtfpassword.setEchoChar('*');
        }
     }
       
//       private class CheckListener implements ActionListener{
//           public void actionPerformed(ActionEvent e){
//               String staffid = jtfstaffid.getText();
//               ResultSet rs = selectRecord(staffid);
//               
//               try{
//                   if(staffid.charAt(0) == 'M'){
//                       JOptionPane.showMessageDialog(null, "Welcone, you are Manager.");
//                       jtfname.setEditable(true);
//                       jtfemail.setEditable(true);
//                       jtfcontact.setEditable(true);
//                       jcbgender.setEditable(true);
//                       jtfic.setEditable(true);
//                       jtfaddress.setEditable(true);
//                       jtfcity.setEditable(true);
//                       jtfstate.setEditable(true);
//                       jtfpostcode.setEditable(true);
//                       jtfposition.setText("Manager");
//                       jtfuserid.setEditable(true);
//                       jtfpassword.setEditable(true);
//                       jtfprivacyques.setEditable(true);
//                       jtfprivacyans.setEditable(true);
//
//                   }
//                   else if(staffid.charAt(0) == 'S'){
//                       JOptionPane.showMessageDialog(null, "Welcome, you are Staff.");
//                       jtfname.setEditable(true);
//                       jtfemail.setEditable(true);
//                       jtfcontact.setEditable(true);
//                       jcbgender.setEditable(true);
//                       jtfic.setEditable(true);
//                       jtfaddress.setEditable(true);
//                       jtfcity.setEditable(true);
//                       jtfstate.setEditable(true);
//                       jtfpostcode.setEditable(true);
//                       jtfposition.setText("Staff");
//                       jtfuserid.setEditable(true);
//                       jtfpassword.setEditable(true);
//                       jtfprivacyques.setEditable(true);
//                       jtfprivacyans.setEditable(true);
//                   }
//                   else if(staffid.charAt(0) == 'D'){
//                       JOptionPane.showMessageDialog(null, "Welcome, you are Driver.");
//                       jtfname.setEditable(true);
//                       jtfemail.setEditable(true);
//                       jtfcontact.setEditable(true);
//                       jcbgender.setEditable(true);
//                       jtfic.setEditable(true);
//                       jtfaddress.setEditable(true);
//                       jtfcity.setEditable(true);
//                       jtfstate.setEditable(true);
//                       jtfpostcode.setEditable(true);
//                       jtfposition.setText("Driver");
//                   }
//               }
//               catch(Exception ex){
//               JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//           }
//           }
//       }
       
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
   createDriver frame = new createDriver();
}
}