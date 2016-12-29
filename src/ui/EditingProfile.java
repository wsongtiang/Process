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

  


public class EditingProfile extends JFrame{
        private String host = "jdbc:derby://localhost:1527/BusDB";
        private String user = "nbuser";
        private String password = "nbuser";
        private String stafftable = "Staff";
        private String autable = "Authentication";
        private Connection conn;
        private PreparedStatement stmt;
    
        private StaffControl staffcontrol;
//        private LoginControl logincontrol;
    
       private ButtonGroup btg = new ButtonGroup();
//       private JRadioButton jrbuserid = new JRadioButton("UserID :", true);
       private JRadioButton jrbstaffid =new JRadioButton("StaffID :",true);
       private JRadioButton jrbstaffname = new JRadioButton("Staff Name :");
//       private JComboBox jcbuserid = new JComboBox(new Object[]{"user001","user002","user003"});
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
       
       private JTextField jtfcomboid = new JTextField(10);
       private JTextField jtfstaffid = new JTextField(10);
       private JTextField jtfuserid = new JTextField(10);
       private JTextField jtfpassword = new JTextField(10);
       private JTextField jtfprivacyques = new JTextField(10);
       private JTextField jtfprivacyans = new JTextField(10);
       private JTextField jtfname = new JTextField(10);
//       private JTextField jtfgender = new JTextField(10);
       JComboBox jcbgender = new JComboBox(new String[] {"Male" , "Female"});

       private JTextField jtfic = new JTextField(10);
       private JTextField jtfcontact = new JTextField(10);
       private JTextField jtfemail = new JTextField(10);
       private JTextField jtfaddress = new JTextField(40);
       private JTextField jtfpostcode = new JTextField(10);
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
       
   private ImageIcon staff = new ImageIcon(getClass().getResource("staff.png"));
    
    private JLabel logo = new JLabel(staff,SwingConstants.CENTER);
    
    private JPanel logoPanel = new JPanel(new FlowLayout());
    JComboBox jcb = new JComboBox();
    //used for autogenerate id 
    private String staffID;
    private String userID;
    
    //for combobox used
    ArrayList<String> id = new ArrayList<String>();
       
       public EditingProfile(){
           staffcontrol = new StaffControl();
//           logincontrol = new LoginControl();
           
         JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("STAFF");
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
//        search.add(jcbstaffid);
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
      5, 5, Color.pink), "Staff Details", TitledBorder.LEFT, TitledBorder.TOP));
        
        details.add(jlbstaffid);
        details.add(jtfstaffid);
//        jtfstaffid.setEditable(false);
//        //autogenerate staffid
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
//        nextstaffID = "S" + nextStaffSqnNo;
//        
//        staffID = nextstaffID;
//        jtfstaffid.setText(staffID);
//        
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbuserid);
        details.add(jtfuserid);
//        
//        String userlatestID = staffcontrol.getLatestUserID();
//        String nextuserID = "";
//        int userSqnNo = 0;
//        String nextUserSqnNo = "";
//       
//        userSqnNo = Integer.parseInt(userlatestID.substring(4));
//        userSqnNo+=1;
//        nextUserSqnNo = String.valueOf(userSqnNo);
//        int nextUserSqnLength = 0;
//        nextUserSqnLength = nextUserSqnNo.length();
//        while(nextUserSqnLength < 3){
//            nextUserSqnNo = "0" + nextUserSqnNo;
//            nextUserSqnLength = nextUserSqnNo.length();
//        }
//        
//        nextuserID = "user" + nextUserSqnNo;
//        
//        userID = nextuserID;
//        jtfuserid.setText(userID);
        
       details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbprivacyques);
        details.add(jtfprivacyques);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbprivacyans);
         details.add(jtfprivacyans);
         details.add(new JLabel(""));
         details.add(new JLabel(""));
        details.add(jlbname); 
        details.add(jtfname); 
        details.add(jlbgender);
        details.add(jcbgender);
        details.add(jlbic);
         details.add(jtfic);
        details.add(jlbcontact);
        details.add(jtfcontact);
          details.add(jlbpassword);
        details.add(jtfpassword);
         details.add(new JLabel(""));
         details.add(new JLabel(""));
        details.add(jlbemail);
        details.add(jtfemail);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbaddress);
        details.add(jtfaddress);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbpostcode);
         details.add(jtfpostcode);
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
        jtfposition.setText("Staff");
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
        
        add.setEnabled(false);
        delete.setEnabled(false);

//        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        basic.add(bottom);
        
        add.addActionListener(new AddListener());
        s.addActionListener(new RetrieveListener());
        update.addActionListener(new UpdateListener());
        delete.addActionListener(new DeleteListener());
        clear.addActionListener(new ClearListener());
        back.addActionListener(new backListener());
        
        
        setTitle("Staff");
        setSize(800,750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
}
       
       public void Try(){
       
        ResultSet rs = selectAll();
        try{
            while(rs.next()){
                
                String input = rs.getString("staffid");
                
                if(input.charAt(0) == 'S'){
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
//        nextstaffID = "S" + nextStaffSqnNo;
//        
//        staffID = nextstaffID;
//        jtfstaffid.setText(staffID);
//        
//       
//        
//        String userlatestID = staffcontrol.getLatestUserID();
//        String nextuserID = "";
//        int userSqnNo = 0;
//        String nextUserSqnNo = "";
//       
//        userSqnNo = Integer.parseInt(userlatestID.substring(4));
//        userSqnNo+=1;
//        nextUserSqnNo = String.valueOf(userSqnNo);
//        int nextUserSqnLength = 0;
//        nextUserSqnLength = nextUserSqnNo.length();
//        while(nextUserSqnLength < 3){
//            nextUserSqnNo = "0" + nextUserSqnNo;
//            nextUserSqnLength = nextUserSqnNo.length();
//        }
//        
//        nextuserID = "user" + nextUserSqnNo;
        
//        userID = nextuserID;
//        jtfuserid.setText(userID);
               jtfstaffid.setText("");
               jtfuserid.setText("");
               jtfprivacyques.setText("");
               jtfprivacyans.setText("");
               jtfname.setText("");
//               jtfgender.setText("");
               jtfic.setText("");
               jtfcontact.setText("");
               jcbgender.setSelectedItem("Male");
               jtfpassword.setText("");
               jtfemail.setText("");
               jtfaddress.setText("");
               jtfpostcode.setText("");
               jtfcity.setText("");
               jtfstate.setText("");
               jtfposition.setText("Staff");
           }
        
        private class AddListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                String staffid = jtfstaffid.getText();
                ResultSet rs = selectRecord(staffid);
                 try{
                     if(rs.next()){
                         JOptionPane.showMessageDialog(null,"Staff id already exists","Please enter agaian",JOptionPane.ERROR_MESSAGE);
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
                         
                         String auStr ="INSERT INTO "+autable+" VALUES(?,?,?,?,?) ";
                         stmt=conn.prepareStatement(auStr);
                         stmt.setString(1,jtfuserid.getText());
                         stmt.setString(2,jtfpassword.getText());
                         stmt.setString(3,jtfprivacyques.getText());
                         stmt.setString(4,jtfprivacyans.getText());
                         stmt.setString(5,staffid);
                         
                         stmt.executeUpdate();

                         JOptionPane.showMessageDialog(null,"Recorded has been created");

                         clearText();
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
               ResultSet rs2 = selectRecord2(staffid);
                if(rs.next()){
                    jtfstaffid.setText(rs.getString("staffid"));
                    jtfname.setText(rs.getString("staffname"));
                    jtfemail.setText(rs.getString("email"));
                    jtfcontact.setText(rs.getString("contactno"));
//                    jtfgender.setText(rs.getString("gender"));
                    
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
                    String userid = jtfuserid.getText();
                    String password = jtfpassword.getText();
                    String privacyques = jtfprivacyques.getText();
                    String privacyans = jtfprivacyans.getText();

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
                    
                    String updateStr1 ="UPDATE " + autable +" SET userid=?, password=?, privacyques=?, privacyans=? "+" WHERE staffid=? ";
                    stmt=conn.prepareStatement(updateStr1);
                    stmt.setString(1, userid);
                    stmt.setString(2, password);
                    stmt.setString(3, privacyques);
                    stmt.setString(4, privacyans);
                    stmt.setString(5, staffid);
                    
                    stmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null,"Record has been updated");
                    clearText();
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please fill in the bank.","Error",JOptionPane.ERROR_MESSAGE);
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
                }
                    
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure?");
                    if(option == JOptionPane.YES_OPTION){
                        
                        ResultSet rs3 = selectRecord2(staffid);
                        if(rs3.next()){
                            
                         String deleteStr1 = "DELETE FROM " + autable + " WHERE staffid = ? ";
                         stmt = conn.prepareStatement(deleteStr1);
                         stmt.setString(1, staffid);
                         stmt.executeUpdate();
                        
                         ResultSet rs4 = selectRecord(staffid);
                         if(rs4.next()){
                             String deleteStr = "DELETE FROM " + stafftable + " WHERE staffid = ? ";
                             
                            stmt = conn.prepareStatement(deleteStr);
                            stmt.setString(1, staffid);
                            stmt.executeUpdate();
                         }
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

                new StaffLogin();
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
        
        public void closeFrame(){
      this.dispose();
  }
        
        public static void main(String [] args){
   EditingProfile frame = new EditingProfile();
}
}