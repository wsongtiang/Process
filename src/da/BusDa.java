package da;


import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import domain.*;
import java.util.ArrayList;

public class BusDa extends AbstractTableModel{
    
   private ResultSet rs;
   private ResultSetMetaData metaData;
    private int numberOfRows;
    private Connection conn;
    private PreparedStatement pstmt;
    private String host = "jdbc:derby://localhost:1527/BusDB ";  
    private String tableName = "Schedule";
    private String user = "nbuser";
    private String password = "nbuser";
  
    public  BusDa(){
        createConnection();
    
    }
    
       public String getLatestID(){
        String busID = "";
        try{
            
            ResultSet rs = null;
            String queryOne = "SELECT * FROM BUS ORDER BY BUSID";
            pstmt = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            
            if(rs.next()){

                rs.last();
                busID = rs.getString("BUSID");
                        
                    }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        
        
        return busID;
    }
    
       
    
    
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