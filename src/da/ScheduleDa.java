package da;


import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import domain.*;
import java.util.ArrayList;

public class ScheduleDa extends AbstractTableModel{
    
   private ResultSet rs;
   private ResultSetMetaData metaData;
    private int numberOfRows;
    private Connection conn;
    private PreparedStatement pstmt;
    private String host = "jdbc:derby://localhost:1527/BusDB ";  
    private String tableName = "Schedule";
    private String user = "nbuser";
    private String password = "nbuser";
  
    //create JTable
    private String [ ] columnHeaders ={"ScheduleID"," DepartureDate","DepartureTime","ArrivalDate","ArrivalTime","BusID","StaffID","RouteID","Status","Total Seat"};
    public  ScheduleDa(){

            try{
            String query = "SELECT * FROM SCHEDULE";
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
    
       public String getLatestID(){
        String scheduleID = "";
        try{
            
            ResultSet rs = null;
            String queryOne = "SELECT * FROM SCHEDULE ORDER BY SCHEDULEID";
            pstmt = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            
            if(rs.next()){

                rs.last();
                scheduleID = rs.getString("SCHEDULEID");
                        
                    }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        
        
        return scheduleID;
    }
    
       public void retrieveRecordsByRouteid(String routeid){
            try{
                String query = "SELECT * FROM SCHEDULE WHERE routeid=?";
                pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, routeid);
                rs = pstmt.executeQuery();
                metaData = rs.getMetaData();
                rs.last();
                numberOfRows = rs.getRow();
                fireTableStructureChanged();
                
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }        
        }
    
      public ArrayList<ScheduleDomain> getArray(String passValue) {
        String queryStr =  "SELECT * FROM SCHEDULE WHERE routeid LIKE ?";
       
        ArrayList<ScheduleDomain> sList=new ArrayList<ScheduleDomain>();
        
        try {
            pstmt = conn.prepareStatement(queryStr);
            pstmt.setString(1, passValue+"%");
            ResultSet rs = pstmt.executeQuery();
           
            while(rs.next())
            {
                sList.add(new ScheduleDomain(rs.getString("scheduleid"),rs.getDate("departuredate"),rs.getString("departuretime"),rs.getDate("arrivaldate"),rs.getString("arrivaltime"),rs.getString("busid"),rs.getString("staffid"),rs.getString("routeid"),rs.getString("status"),rs.getInt("totalseat")));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return sList;
    }
    
    public ScheduleDomain retrieveDetails(String routeid) {
        String queryStr =   "SELECT scheduleid, departuredate,departuretime,arrivaldate, arrivaltime,busid,routeid,status,totalseat FROM SCHEDULE WHERE routeid= ?";
        ScheduleDomain s = null;
        try {
            pstmt = conn.prepareStatement(queryStr);
            pstmt.setString(1, routeid);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                 s = new ScheduleDomain(rs.getString("scheduleid"),rs.getDate("departuredate"),rs.getString("departuretime"),rs.getDate("arrivaldate"),rs.getString("arrivaltime"),rs.getString("busid"),rs.getString("staffid"),routeid,rs.getString("status"),rs.getInt("totalseat"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return s;
    }
    
  public ScheduleDomain getRecord(String routeid) {
        String queryStr = "SELECT * FROM SCHEDULE WHERE routeid = ?";
        ScheduleDomain s= null;
        try {
            pstmt = conn.prepareStatement(queryStr);
            pstmt.setString(1, routeid);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                 s = new ScheduleDomain(rs.getString("scheduleid"),rs.getDate("departuredate"),rs.getString("departuretime"),rs.getDate("arrivaldate"),rs.getString("arrivaltime"),rs.getString("busid"),rs.getString("staffid"),routeid,rs.getString("status"),rs.getInt("totalseat"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return s;
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