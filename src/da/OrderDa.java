package da;


import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import domain.*;
import java.util.ArrayList;
//import javax.swing.event.TableModelEvent;
//import javax.swing.table.AbstractTableModel.fireTableChanged;
//import  javax.swing.table.AbstractTableModel.fireTableStructureChanged;

public class OrderDa extends AbstractTableModel{

        
    private ResultSet rs;
   private ResultSetMetaData metaData;
    private int numberOfRows;
    private Connection conn;
    private PreparedStatement pstmt;
    private String host = "jdbc:derby://localhost:1527/BusDB ";  
    private String tableName = "Order";
    private String user = "nbuser";
    private String password = "nbuser";
        
      private String [ ] columnHeaders ={"Schedule ID","Order ID","Quantity","Ticket No"};    
    
        public OrderDa(){
          try{
            String query = "SELECT * FROM ORDERDETAIL where orderid =? ";
            conn = DriverManager.getConnection(host, user, password);
            pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
            
            rs = pstmt.executeQuery();
            metaData = rs.getMetaData();
            rs.last();
            numberOfRows = rs.getRow();
            fireTableStructureChanged();
            
        }catch(SQLException ex){
            ex.printStackTrace();   
        }
        }
      //Autogenerate ID
        public String getLatestOrID(){
        String orderID = "";
        try{
            
            ResultSet rs = null;
            String queryOne = "SELECT * FROM ORDERTABLE ORDER BY ORDERID";
            pstmt = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            
            if(rs.next()){

                rs.last();
                orderID = rs.getString("ORDERID");
                        
                    }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        
        
        return orderID;
    }
        
         public String getLatestTicID(){
        String ticketNo = "";
        try{
            
            ResultSet rs = null;
            String queryOne = "SELECT * FROM ORDERDETAIL ORDER BY TICKETNO";
            pstmt = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            
            if(rs.next()){

                rs.last();
                ticketNo = rs.getString("TICKETNO");
                        
                    }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        
        
        return ticketNo;
    }
      
//        public ArrayList<ScheduleDomain> getScheduleid(String scheduleid){
//            String query = "SELECT * FROM SCHEDULE WHERE SCHEDULEID=?";
//            ArrayList<ScheduleDomain> schedule = new ArrayList<ScheduleDomain>();
//            ResultSet rs = null;
//            try{
//                pstmt = conn.prepareStatement(query);
//                pstmt.setString(1, scheduleid);
//                
//                rs = pstmt.executeQuery();
//                while(rs.next()){
//                    schedule.add(new ScheduleDomain(rs.getString("SCHEDULEID"), rs.getDate("DEPARTUREDATE"), rs.getString("DEPARTURETIME"), rs.getDate("ARRIVALDATE"),rs.getString("ARRIVALTIME"),rs.getString("BUSID"),rs.getString("STAFFID"),rs.getString("ROUTEID"),rs.getString("STATUS")));
//                }
//            }catch(SQLException ex){
//                showErrorDialog(ex.toString());
//            }finally{
//                if(rs !=null){
//                    try{
//                        rs.close();                        
//                    }catch(SQLException ex){
//                        showErrorDialog(ex.toString());
//                    }
//                }
//            } 
//            return schedule;
//        }
        
      private void createConnection(){
        try{
            conn = DriverManager.getConnection(host, user, password);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void shutDown(){
        if(pstmt != null){
            try{
                pstmt.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
        

        public String getColumnName(int column){
            try{
                return metaData.getColumnName(column+1);
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }        
            return "";
        }
    
   
        public int getRowCount(){
            return numberOfRows;
        
        }
   

        public int getColumnCount(){
            try{
                return metaData.getColumnCount();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            return 0;
        }
        

        public Object getValueAt(int rowIndex, int columnIndex){
            try{
                rs.absolute(rowIndex+1);
                return rs.getObject(columnIndex+1);
            }catch(SQLException ex){
            
            }
            return "";
        }

   
}