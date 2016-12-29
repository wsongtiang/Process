package ui;
import control.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

  


public class createRoute extends JFrame{
        private String host = "jdbc:derby://localhost:1527/BusDB";
        private String user = "nbuser";
        private String password = "nbuser";
        private String routetable = "Route";
        private Connection conn;
        private PreparedStatement stmt;
        
        private RouteControl routecontrol;
    
       private ButtonGroup btg = new ButtonGroup();
       private JRadioButton jrbrouteid =new JRadioButton("RouteID :",true);
       private JComboBox jcbrouteid = new JComboBox();
       
       private JLabel jlbrouteid = new JLabel("Route ID :");
       private JLabel jlbdeparturedest = new JLabel("Departure Destination :");
       private JLabel jlbarrivaldest = new JLabel("Arrival Destination :");
       private JLabel jlbdistance = new JLabel("Distance :");
       private JLabel jlbprice = new JLabel("Price :");
       
       private JTextField jtfrouteid = new JTextField(10);
       private JTextField jtfdeparturedest = new JTextField(10);
       private JTextField jtfarrivaldest = new JTextField(10);
       private JTextField jtfdistance = new JTextField(10);
       private JTextField jtfprice = new JTextField(10);
    
    
       private JButton clear = new JButton("CLEAR");
       private JButton add = new JButton("ADD");
       private JButton update = new JButton("UPDATE");
       private JButton delete = new JButton("DELETE");
       private JButton back = new JButton("BACK");
//       private JButton home = new JButton("HOME");
       private JButton s = new JButton("SEARCH");
       
   private ImageIcon staff = new ImageIcon(getClass().getResource("route.png"));
    
    private JLabel logo = new JLabel(staff,SwingConstants.CENTER);
    
    private JPanel logoPanel = new JPanel(new FlowLayout());
    
     ArrayList<String> id = new ArrayList<String>();
     
      private String routeID;
       
       public createRoute(){
           
           routecontrol = new RouteControl();
           
         JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("ROUTE");
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
        
        btg.add(jrbrouteid);
        search.add(jrbrouteid);
        search.add(jcbrouteid);
          search.add(s);
        try {
       Image img = ImageIO.read(getClass().getResource("searchSmall.png"));
       s.setIcon(new ImageIcon(img));
       } catch (IOException ex) {
       }

        FlowLayout fl2 = new FlowLayout();
        fl2.setHgap(20);
        fl2.setVgap(20);
        JPanel setsize = new JPanel(fl2);
        JPanel details = new JPanel(new GridLayout(10,2));
        details.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
      5, 5, Color.pink), "Staff Details", TitledBorder.LEFT, TitledBorder.TOP));
        
        details.add(jlbrouteid);
        details.add(jtfrouteid);
        jtfrouteid.setEditable(false);
        String stafflatestID = routecontrol.getLatestID();
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
        
        nextstaffID = "ROT" + nextStaffSqnNo;
        
        routeID = nextstaffID;
        jtfrouteid.setText(routeID);
        
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbdeparturedest);
        details.add(jtfdeparturedest);
       details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbarrivaldest);
        details.add(jtfarrivaldest);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbdistance);
         details.add(jtfdistance);
         details.add(new JLabel(""));
         details.add(new JLabel(""));
        details.add(jlbprice); 
        details.add(jtfprice); 
//        jtfprice.setForeground(Color.GRAY);
       jtfprice.setHorizontalAlignment(SwingConstants.LEFT);
        jtfprice.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jtfprice.setText("");
            }
            
        }); 
         details.add(new JLabel(""));
        details.add(clear);
        setsize.add(details,BorderLayout.CENTER);

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
        jptop.add(setsize,BorderLayout.CENTER);
        jptop.add(jpsouth,BorderLayout.SOUTH);
        basic.add(jptop);
        
   

//        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        basic.add(bottom);
        add.addActionListener(new AddListener());
        update.addActionListener(new UpdateListener());
        s.addActionListener(new RetrieveListener());
        delete.addActionListener(new DeleteListener());
        back.addActionListener(new backListener());
        clear.addActionListener(new clearListener());
        
        createConnection();

        setTitle("Route");
        setSize(800,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
}
       
            public void Try(){
       
        ResultSet rs = selectAll();
        try{
            while(rs.next()){
                
                String input = rs.getString("routeid");
                
                if(input.charAt(0) == 'R'){
                    id.add(rs.getString("routeid"));
                }

            }
        }catch(SQLException ex){
            
        }

        
        jcbrouteid.setModel(new DefaultComboBoxModel(id.toArray()));
 
    }
       
       public ResultSet selectAll() {

        String queryStr = " SELECT * FROM ROUTE  ";
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(queryStr);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
       
       
        public ResultSet selectRecord(String routeid){
            String queryStr = "SELECT * FROM "+routetable+" WHERE routeid = ? ";
            ResultSet rs = null;

            try{
                stmt=conn.prepareStatement(queryStr);
                stmt.setString(1,routeid);

                rs = stmt.executeQuery();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            
            
            return rs;
        }
        
          public void clearText(){
               String stafflatestID = routecontrol.getLatestID();
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
        
        nextstaffID = "ROT" + nextStaffSqnNo;
        
        routeID = nextstaffID;
        jtfrouteid.setText(routeID);
               jtfdeparturedest.setText("");
               jtfarrivaldest.setText("");
               jtfdistance.setText("");
               jtfprice.setText("");
           }
       
          private class AddListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                
                try{ 
                    if(jtfdeparturedest.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }
                    else if (!jtfdeparturedest.getText().matches("[a-zA-Z ]+")) {
                       JOptionPane.showMessageDialog(null, "Departure destination must be in alphabet.", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                   else if(jtfarrivaldest.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }
                   else if (!jtfarrivaldest.getText().matches("[a-zA-Z ]+")) {
                       JOptionPane.showMessageDialog(null, "Departure destination must be in alphabet.", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                   
                    else if(jtfdistance.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }
                    else if (!jtfdistance.getText().matches("[0-9.]+")) {
                       JOptionPane.showMessageDialog(null, "Distance must be a double.", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                    else if(jtfprice.getText().equals("")){
                         JOptionPane.showMessageDialog(null,"Some fields are blank,please fill all the empty field before save it. ","Empty Field(s)",JOptionPane.WARNING_MESSAGE);
                    }
                    else if (!jtfprice.getText().matches("[0-9.]+")) {
                       JOptionPane.showMessageDialog(null, "Departure destination must be string.", "Format error", JOptionPane.ERROR_MESSAGE);
                 }
                   
                    else{
                String routeid = jtfrouteid.getText();
                ResultSet rs = selectRecord(routeid);
                
                     if(rs.next()){
                         JOptionPane.showMessageDialog(null,"Staff id already exists","Please enter agaian",JOptionPane.ERROR_MESSAGE);
                     }
                     else{
                         String staffStr ="INSERT INTO "+routetable+" VALUES(?,?,?,?,?) ";
                         stmt=conn.prepareStatement(staffStr);
                         stmt.setString(1,routeid);
                         stmt.setString(2,jtfdeparturedest.getText());
                         stmt.setString(3,jtfarrivaldest.getText());
                         stmt.setString(4,jtfdistance.getText());
                         stmt.setString(5,jtfprice.getText());
                        
        
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
          
            private class UpdateListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String routeid = jtfrouteid.getText();
                ResultSet rs = selectRecord(routeid);
                if(rs.next()){
                    String departuredest = jtfdeparturedest.getText();
                    String arrivaldest = jtfarrivaldest.getText();
                    Double distance = Double.parseDouble(jtfdistance.getText());
                    Double price = Double.parseDouble(jtfprice.getText());
                   

                    String updateStr ="UPDATE " + routetable +" SET departuredest=?, arrivaldest=?, distance=?, price=? "+" WHERE routeid=? ";
                    stmt=conn.prepareStatement(updateStr);
                    stmt.setString(1, departuredest);
                    stmt.setString(2, arrivaldest);
                    stmt.setDouble(3, distance);
                    stmt.setDouble(4, price);
                    stmt.setString(5, routeid);
                    
 
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
            
             private class RetrieveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
             
            try{
                String routeid = (String) jcbrouteid.getSelectedItem();
               ResultSet rs = selectRecord(routeid);
               
                if(rs.next()){
                    jtfrouteid.setText(routeid);
                    jtfdeparturedest.setText(rs.getString("departuredest"));
                    jtfarrivaldest.setText(rs.getString("arrivaldest"));
                    jtfdistance.setText(rs.getString("distance"));
                    jtfprice.setText(rs.getString("price"));
                   
               
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
             
             private class clearListener implements ActionListener{
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
        
         private class DeleteListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String routeid = jtfrouteid.getText();
                ResultSet rs = selectRecord(routeid);
                
                if(rs.next()){
                    jtfdeparturedest.setText(rs.getString("departuredest"));
                    jtfarrivaldest.setText(rs.getString("arrivaldest"));
                    jtfdistance.setText(rs.getString("distance"));
                    jtfprice.setText(rs.getString("price"));
                    
                    int option = JOptionPane.showConfirmDialog(null, "ARE you Sure?");
                    if(option == JOptionPane.YES_OPTION){
                        String deleteStr = "DELETE FROM " + routetable + " WHERE routeid = ? ";
                        stmt = conn.prepareStatement(deleteStr);
                        stmt.setString(1, routeid);
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
         
         
        
       
        public static void main(String [] args){
   createRoute frame = new createRoute();
}
}