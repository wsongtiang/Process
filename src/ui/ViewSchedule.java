package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import da.ScheduleDa;
import domain.ScheduleDomain;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.*;
import control.*;

public class ViewSchedule extends JFrame{
    
    private String host = "jdbc:derby://localhost:1527/BusDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private Connection conn;
    private PreparedStatement stmt;
    
    


    private ScheduleDa tablemodel;
    private JTable table=new JTable((TableModel)tablemodel);
    private ScheduleDomain schedo;
    
    private JPanel basic;
    private JPanel centerPanel;
  //  private JTable table;
    private JScrollPane scrollPane;
    
    private ImageIcon sch = new ImageIcon(getClass().getResource("schedule.png"));
    private JLabel logo = new JLabel(sch,SwingConstants.CENTER);
    private JPanel logoPanel = new JPanel(new FlowLayout());
    
    private JButton back = new JButton("BACK");
    private JButton search = new JButton("SEARCH");
    private JButton viewall = new JButton("REFRESH");
   
    
    private JTextField jtfview = new JTextField(20);

    ArrayList<String> id = new ArrayList<String>();
    
     private ScheduleControl sControl;
     
     private TableRowSorter<TableModel> sorter =
      new TableRowSorter<TableModel>(table.getModel());
    
    public ViewSchedule(){

         sControl = new ScheduleControl();
        
        setConnection();
         JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("View Bus Schedule and Route");
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
        
        createConnection();
        
        Try();
        
        JPanel top = new JPanel();
        top.add(jtfview);
        jtfview.setEditable(false);
            jtfview.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jtfview.setText("");
            }
            
        }); 

        top.add(search);
//        top.add(viewall);

        

        JPanel center = new JPanel();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout( new BorderLayout() );
        getContentPane().add( centerPanel );
        tablemodel = new ScheduleDa();
        table= new JTable((TableModel)tablemodel);

        scrollPane = new JScrollPane( table );
        centerPanel.add(scrollPane);

        
        JPanel bottompanel = new JPanel();
        bottompanel.add(back);

         setContentPane(basic);
          pack();
        basic.setVisible(true);

         basic.add(top,BorderLayout.NORTH);
         basic.add(centerPanel,BorderLayout.CENTER);
         basic.add(bottompanel,BorderLayout.SOUTH);

        basic.add(jptop);
        
//        addWindowListener(new WindowListener());

        
            table.addMouseListener(new JTableListener()); 
        search.addActionListener(new SearchListener());
        back.addActionListener(new backListener());
//        viewall.addActionListener(new ViewAllListener());
        
        setTitle("View Bus Schedule and Route");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,500);
        setVisible(true);
        
//        table.addMouseListener(new JTableListener()); 
//        search.addActionListener(new SearchListener());
//        back.addActionListener(new backListener());
//        viewall.addActionListener(new ViewAllListener());

        
    }
    
    
    public void Try(){
        
        JTextField departuredest= new JTextField();
        JTextField arrivaldest = new JTextField();
        
        ResultSet rs = selectAll();
        try{
            
        
            while(rs.next()){
          
                String part1 = rs.getString("routeid");
                String part2 = rs.getString("departuredest");
                String part3 = rs.getString("arrivaldest");
                String haha  = part1 + " - " + part2+"-"+part3;
           
                id.add(haha);
               
                
            }
        }catch(SQLException ex){
            
        }

        
        String[] id1 = new String[id.size()];
        id1 = id.toArray(id1);
        
        String staffid1 = (String) JOptionPane.showInputDialog(null, "Choose ROUTE ID to proceed.",
        "ROUTE ID", JOptionPane.QUESTION_MESSAGE, null,id1,id1[0]); 

        jtfview.setText(staffid1.substring(0, 6));
    }
    
    public ResultSet selectAll() {

        String queryStr = " SELECT * FROM route  ";
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(queryStr);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
    
    private void setConnection(){
   String host = "jdbc:derby://localhost:1527/BusDB ";  
     String user = "nbuser";
     String password = "nbuser";
     
     try{
         conn= DriverManager.getConnection(host,user,password);
     }
     catch(Exception ex){
         System.err.println("DB ERROR: "+ ex.toString());
     }
    }
    

    
         private class SearchListener implements ActionListener{
             public void actionPerformed(ActionEvent e){
                 
                
            if(jtfview.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please enter soups item's information to search.","WARNING",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
             ScheduleDomain s = sControl.selectRecord(jtfview.getText());
            if (s != null) {
            tablemodel.retrieveRecordsByRouteid(jtfview.getText());
            }
            else
            {
            String text = jtfview.getText();
            }
            }
             }
         }
//          private class ViewAllListener implements ActionListener{
//        public void actionPerformed(ActionEvent e)
//        {
//        refresh();
//        }
//    }

    

//       private class WindowListener extends WindowAdapter{
//        public void windowClosing(WindowEvent e) {
//        
//           int confirm = JOptionPane.showConfirmDialog(null, "Confirm return to the Main Menu?", "Confirm?", JOptionPane.WARNING_MESSAGE);
//           if(confirm == JOptionPane.YES_OPTION){
//                closeFrame();
//                new Menu();
//           }
//       }
//    }
       
//         private class ViewAllListener implements ActionListener{
//        public void actionPerformed(ActionEvent e)
//        {
//        refresh();
////        }
//    }
       
//         public void refresh()
//     {
//       
//       centerPanel.removeAll();
        
//        JPanel center = new JPanel();
//        JPanel centerPanel = new JPanel();
//        centerPanel.setLayout( new BorderLayout() );
//        getContentPane().add( centerPanel );
//        tablemodel = new ScheduleDa();
//        table= new JTable((TableModel)tablemodel);
//        
//        scrollPane = new JScrollPane( table );
//        centerPanel.add(scrollPane);
        
//        tablemodel = new ScheduleDa();
//        table = new JTable(tablemodel);//add tablemodel inside
//        table.setOpaque(true);
//        table.setBackground(new Color(255,248,220));
//        
//        JScrollPane scrollPane=new JScrollPane(table);
//        centerPanel.add(scrollPane);
//        scrollPane.setOpaque(true);
//        scrollPane.getViewport().setBackground(new Color(255,248,220));
  
//        centerPanel.setBackground(new Color(255,248,220));
//        centerPanel.revalidate();
//        centerPanel.repaint();
//z
//         
//       private class RetrieveListener implements ActionListener{
//        public void actionPerformed(ActionEvent e){
//             
//            try{
//                String Str ="SELECT * FROM" + tableName ;
//                stmt= conn.prepareStatement(Str);
//                rs=stmt.executeQuery(Str);
//            }
//            catch(Exception ex){
//                JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
//            }
//        } 
//    }
    
     public ResultSet selectRecord(String scheduleid) {
        String queryStr = "SELECT * FROM SCHEDULE WHERE SCHEDULEID= ?";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, scheduleid);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("GOT RETURN!"); 
        return rs;
    }
       
       private class JTableListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
            if(e.getClickCount() == 2){
                JTable target = (JTable) e.getSource();
                int row = target.getSelectedRow();
                
                String value =(String)tablemodel.getValueAt(row, 0);
                 try {
                
                ResultSet rs = selectRecord(value);
                if (rs.next()) {
                    String scheduleid=rs.getString("scheduleid");
                    Date departuredate=rs.getDate("departuredate");
                    String departuretime=rs.getString("departuretime");
                    Date arrivaldate=rs.getDate("arrivaldate");
                    String arrivaltime=rs.getString("arrivaltime");
                    String busid=rs.getString("busid");
                    String staffid=rs.getString("staffid");
                    String routeid=rs.getString("routeid");
                    String status = rs.getString("status");
                    int totalseat = rs.getInt("totalseat");
                    
                    schedo = new ScheduleDomain(scheduleid,departuredate,departuretime,arrivaldate,arrivaltime,busid,staffid,routeid,status,totalseat);
                    BookingTicket bookticket=new BookingTicket(schedo);
                 
                } else {
                    JOptionPane.showMessageDialog(null, "No such programme code.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
                closeFrame();
                //JOptionPane.showMessageDialog(null, value);  
            }
           
        }
        public void mouseExited(MouseEvent e) { } 
        public void mouseEntered(MouseEvent e) { }
        public void mouseReleased(MouseEvent e){ }
        public void mousePressed(MouseEvent e) { }
    }
       
       private void createConnection(){
        try{
            conn = DriverManager.getConnection(host,user,password);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
       
       private class backListener implements ActionListener{
            public void actionPerformed(ActionEvent e){

                StaffLogin staff = new StaffLogin();
                closeFrame();
            }
        }
    
        public void closeFrame() {
                this.dispose();
        }
    
    public static void main(String[] args){
        ViewSchedule frame = new ViewSchedule();
}

}