package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.KeyEvent;
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
import control.*;

public class Bus extends JFrame{
      private String host = "jdbc:derby://localhost:1527/BusDB";
       private String user = "nbuser";
       private String password = "nbuser";
       private String bus = "bus";
       private Connection conn;
       private PreparedStatement stmt;

    private BusControl buscontrol;
       
       private ButtonGroup btg = new ButtonGroup();
       private JRadioButton jrbbusid = new JRadioButton("Bus ID :", true);
       private JRadioButton jrbbusno =new JRadioButton("Bus Number :");
//       private JComboBox jcbbusid = new JComboBox(new Object[]{"BUS001","BUS002","BUS003"});
//       private JComboBox jcbbusno = new JComboBox(new Object[]{"PKM003","BDH123","PJA5277"});
        private JComboBox jcbbusid = new JComboBox();
       
       private JLabel jlbbusid = new JLabel("Bus ID :");
       private JLabel jlbbusname = new JLabel("Bus Name :");
       private JLabel jlbbustype = new JLabel("Bus Type :");
       private JLabel jlbmodel = new JLabel("Model :");
       private JLabel jlbmadein = new JLabel("Made In :");
       private JLabel jlbcolor = new JLabel("Color :");
       private JLabel jlbbusno = new JLabel("Bus Number :");
       private JLabel jlbyearofpartnership = new JLabel("Year of Partnership :");
       
       private JTextField jtfbusid = new JTextField(10);
       private JTextField jtfbusname = new JTextField(10);
       private JTextField jtfbustype = new JTextField(10);
       private JTextField jtfmodel = new JTextField(10);
       private JTextField jtfmadein = new JTextField(10);
       private JTextField jtfcolor = new JTextField(10);
       private JTextField jtfbusno = new JTextField(10);
       private JTextField jtfyearofpartnership = new JTextField(10);
    
       private JButton clear = new JButton("CLEAR");
       private JButton add = new JButton("ADD");
       private JButton update = new JButton("UPDATE");
       private JButton delete = new JButton("DELETE");
       private JButton back = new JButton("BACK");
//       private JButton home = new JButton("BACK");
       private JButton s = new JButton("SEARCH");
       
        private ImageIcon card = new ImageIcon(getClass().getResource("bus.png"));
    private JLabel logo = new JLabel(card,SwingConstants.CENTER);
    private JPanel logoPanel = new JPanel(new FlowLayout());
       
    ArrayList<String> id = new ArrayList<String>();
    
    private String busID;
    
       public Bus(){
           buscontrol = new BusControl();
           
         JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("BUS");
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
        
        btg.add(jrbbusid);
        btg.add(jrbbusno);
        search.add(jrbbusid);
        search.add(jcbbusid);
        
         search.add(new JLabel(""));
        search.add(new JLabel(""));
        search.add(new JLabel(""));
        search.add(new JLabel(""));
        search.add(s);
        try {
       Image img = ImageIO.read(getClass().getResource("searchSmall.png"));
       s.setIcon(new ImageIcon(img));
       } catch (IOException ex) {
       }

        JPanel details = new JPanel(new GridLayout(9,4));
        details.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
      5, 5, Color.pink), "Bus Details", TitledBorder.LEFT, TitledBorder.TOP));
        
        details.add(jlbbusid);
        details.add(jtfbusid);
        jtfbusid.setEditable(false);
        String stafflatestID = buscontrol.getLatestID();
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
        
        nextstaffID = "BUS" + nextStaffSqnNo;
        
        busID = nextstaffID;
        jtfbusid.setText(busID);
        
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbbusname);
        details.add(jtfbusname);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbbustype);
        details.add(jtfbustype);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbmodel);
        details.add(jtfmodel);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbmadein);
        details.add(jtfmadein);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbcolor); 
        details.add(jtfcolor);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbbusno);
        details.add(jtfbusno);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
        details.add(jlbyearofpartnership);
        details.add(jtfyearofpartnership);
        details.add(new JLabel(""));
        details.add(new JLabel(""));
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
        
        
        back.addActionListener(new backListener());
         add.addActionListener(new AddListener());
        s.addActionListener(new RetrieveListener());
        update.addActionListener(new UpdateListener());
        delete.addActionListener(new DeleteListener());
        clear.addActionListener(new ClearListener());


         createConnection();
//        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        basic.add(bottom);

        setTitle("Bus");
        setSize(700,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
}
       
         public void Try(){
       
        ResultSet rs = selectAll();
        try{
            while(rs.next()){
                
                String input = rs.getString("busid");
                
                if(input.charAt(0) == 'B'){
                    id.add(rs.getString("busid"));
                }

            }
        }catch(SQLException ex){
            
        }

        
        jcbbusid.setModel(new DefaultComboBoxModel(id.toArray()));
 
    }
       
       public ResultSet selectAll() {

        String queryStr = " SELECT * FROM BUS  ";
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(queryStr);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
       
//       public void clearText(){
//               jtfbusid.setText("");
//               jtfbusname.setText("");
//               jtfbustype.setText("");
//               jtfmodel.setText("");
//               jtfmadein.setText("");
//               jtfcolor.setText("");
//               jtfbusno.setText(""); 
//               jtfyearofpartnership.setText("");
//           }
//       
       
       private class backListener implements ActionListener{
            public void actionPerformed(ActionEvent e){

                AdminLogin admin = new AdminLogin();
                closeFrame();
            }
        }
       
       private class ClearListener implements ActionListener{
            public void actionPerformed(ActionEvent e){

                clearText();
            }
        }
       
       
       public ResultSet selectRecord(String busid){
            String queryStr = "SELECT * FROM "+bus+" WHERE busid = ? ";
            ResultSet rs = null;

            try{
                stmt=conn.prepareStatement(queryStr);
                stmt.setString(1,busid);

                rs = stmt.executeQuery();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            
            
            return rs;
        }

       
        public void clearText(){
               jtfbusid.setText("");
               jtfbusname.setText("");
               jtfbustype.setText("");
               jtfyearofpartnership.setText("");
               jtfmodel.setText("");
               jtfmadein.setText("");
               jtfcolor.setText("");
               jtfbusno.setText("");
           }
        
        private class AddListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                String busid = jtfbusid.getText();
                ResultSet rs = selectRecord(busid);
                int yearofpartnership = Integer.parseInt(jtfyearofpartnership.getText());
                 try{
                     if(rs.next()){
                         JOptionPane.showMessageDialog(null,"Bus id already exists","Please enter agaian",JOptionPane.ERROR_MESSAGE);
                     }
                     else{
                         String busStr ="INSERT INTO "+bus+" VALUES(?,?,?,?,?,?,?,?) ";
        
                         stmt=conn.prepareStatement(busStr);
                         stmt.setString(1,busid);
                         stmt.setString(2,jtfbusname.getText());
                         stmt.setString(3,jtfbustype.getText());
                         stmt.setInt(4,yearofpartnership);
                         stmt.setString(5,jtfmodel.getText());
                         stmt.setString(6,jtfmadein.getText());
                         stmt.setString(7,jtfcolor.getText());
                         stmt.setString(8,jtfbusno.getText());

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
               String busid =(String) jcbbusid.getSelectedItem();
               ResultSet rs = selectRecord(busid);

                if(rs.next()){
                    jtfbusname.setText(rs.getString("busname"));
                    jtfbustype.setText(rs.getString("bustype"));
                    jtfyearofpartnership.setText(rs.getString("yearofpartnership"));
                    jtfmodel.setText(rs.getString("model"));
                    jtfmadein.setText(rs.getString("madein"));
                    jtfcolor.setText(rs.getString("color"));
                    jtfbusno.setText(rs.getString("busno"));

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
                String busid = jtfbusid.getText();
                ResultSet rs = selectRecord(busid);
                if(rs.next()){
                    String busname = jtfbusname.getText();
                    String bustype = jtfbustype.getText();
                    int yearofpartnership = Integer.parseInt(jtfyearofpartnership.getText());
                    String model = jtfmodel.getText();
                    String madein = jtfmadein.getText();
                    String color= jtfcolor.getText();
                    String busno = jtfbusno.getText();

                    String updateStr ="UPDATE " + bus +" SET busname=?, bustype=?, yearofpartnership=?, model=?, madein=?, color=?, busno=? "+" WHERE busid=? ";
                    stmt=conn.prepareStatement(updateStr);
                    stmt.setString(1, busname);
                    stmt.setString(2, bustype);
                    stmt.setInt(3, yearofpartnership);
                    stmt.setString(4, model);
                    stmt.setString(5, madein);
                    stmt.setString(6, color);
                    stmt.setString(7, busno);
                    stmt.setString(8, busid);

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
                String busid = jtfbusid.getText();
                ResultSet rs = selectRecord(busid);
                
                if(rs.next()){
                    jtfbusname.setText(rs.getString("busname"));
                    jtfbustype.setText(rs.getString("bustype"));
                    jtfyearofpartnership.setText(rs.getString("yearofpartnership"));
                    jtfmodel.setText(rs.getString("model"));
                    jtfmadein.setText(rs.getString("madein"));
                    jtfcolor.setText(rs.getString("color"));
                    jtfbusno.setText(rs.getString("busno"));
                
                    
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure?");
                    if(option == JOptionPane.YES_OPTION){
                        String deleteStr = "DELETE FROM " + bus + " WHERE busid = ? ";
                        stmt = conn.prepareStatement(deleteStr);
                        stmt.setString(1, busid);
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
   Bus frame = new Bus();
}
}