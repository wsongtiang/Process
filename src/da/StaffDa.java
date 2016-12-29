package da;

import da.*;
import ui.*;
import domain.*;
//import control.*;

import java.sql.*;
import javax.swing.*;

public class StaffDa{
    //database variables
    private String host = "jdbc:derby://localhost:1527/BusDB ";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Staff";
    private Connection conn;
    private PreparedStatement ppst;
    
    public StaffDa(){
        createConnection();
    }
    
    public String getLatestID(){
        String staffID = "";
        try{
            
            ResultSet rs = null;
            String queryOne = "SELECT * FROM STAFF ORDER BY STAFFID";
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ppst.executeQuery();
            
            while(rs.next()){
                
                
                staffID = rs.getString("STAFFID");
                        
                    }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        
        
        return staffID;
    }
    
     public String getLatestUsID(){
        String userID = "";
        try{
            
            ResultSet rs = null;
            String queryOne = "SELECT * FROM AUTHENTICATION ORDER BY USERID";
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ppst.executeQuery();
            
            if(rs.next()){

                rs.last();
                userID = rs.getString("USERID");
                        
                    }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        
        
        return userID;
    }
    
    
    public void addRecord(StaffDomain staff){
        try{
            
            
            PreparedStatement ppstInsert = conn.prepareStatement("INSERT INTO STAFF VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            ppstInsert.setString(1, staff.getStaffid());
            ppstInsert.setString(2, staff.getStaffname());
            ppstInsert.setString(3, staff.getEmail());
            ppstInsert.setInt(4, staff.getContactno());
            ppstInsert.setString(5, staff.getGender());
            ppstInsert.setInt(6, staff.getIcno());
            ppstInsert.setString(7, staff.getAddress());
            ppstInsert.setString(8, staff.getCity());
            ppstInsert.setString(9, staff.getState());
            ppstInsert.setInt(10, staff.getPostcode());
          
            
            int result = ppstInsert.executeUpdate();

            if(result > 0){
                JOptionPane.showMessageDialog(null,"New record for staff ID: " + staff.getStaffid() + " has been created.");
            }
            else{
                JOptionPane.showMessageDialog(null,"Result returned is not a positive integer, new record is not created.","Error",JOptionPane.ERROR_MESSAGE);

                    }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public StaffDomain getRecord(String staffid){
        StaffDomain staff = new StaffDomain ();
        try{
            
            ResultSet rs = null;
            String queryOne = "SELECT * FROM STAFF WHERE STAFFID = ?";
            ppst = conn.prepareStatement(queryOne);
            ppst.setString(1,staffid);
            rs = ppst.executeQuery();
            
            if(rs.next()){

                staff.setStaffname(rs.getString("STAFFNAME"));
                staff.setEmail(rs.getString("EMAIL"));
                staff.setContactno(rs.getInt("CONTACTNO"));
                staff.setGender(rs.getString("GENDER"));
                staff.setIcno(rs.getInt("ICNO"));
                staff.setAddress(rs.getString("ADDRESS"));
                staff.setCity(rs.getString("CITY"));
                staff.setState(rs.getString("STATE"));
                staff.setPostcode(rs.getInt("POSTCODE"));
                staff.setPosition(rs.getString("POSITION"));
             
                        
                    }
            else{
                        
                staff = null;
                    }
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
    
        return staff;
    }
    
    public void updateRecord(StaffDomain staff){
        try {
            String updateStr = "UPDATE STAFF SET STAFFNAME = ?, EMAIL = ?, CONTACTNO = ?,GENDER = ?, ICNO = ?, ADDRESS = ?, CITY = ?, STATE = ?, POSTCODE = ?, POSITION = ? WHERE STAFF_ID = ?";
            ppst = conn.prepareStatement(updateStr);
            ppst.setString(1, staff.getStaffname());
            ppst.setString(2, staff.getEmail());
            ppst.setInt(3, staff.getContactno());
            ppst.setString(4, staff.getGender());
            ppst.setInt(5, staff.getIcno());
            ppst.setString(6, staff.getAddress());
            ppst.setString(7, staff.getCity());
            ppst.setString(8, staff.getState());
            ppst.setInt(9, staff.getPostcode());
            ppst.setString(10, staff.getPosition());
            ppst.setString(11, staff.getStaffid());
            
            int result = ppst.executeUpdate();
            
            if(result > 0){
                JOptionPane.showMessageDialog(null, "Record for staff ID: " + staff.getStaffid() + " has been updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteRecord(String staffid){
        try{
            ResultSet rs = null;
            String queryOne = "SELECT * FROM STAFF WHERE STAFFID = ?";
            ppst = conn.prepareStatement(queryOne);
            ppst.setString(1,staffid);
            rs = ppst.executeQuery();
            
            if(rs.next()){
                //DELETE THE RECORD
                String queryTwo = "DELETE FROM STAFF WHERE STAFFID = ?";
                PreparedStatement ppstDelete = conn.prepareStatement(queryTwo);
                ppstDelete.setString(1,staffid);
                
                int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the staff record?","Confirm?",JOptionPane.YES_NO_OPTION);
                
                if(confirm == JOptionPane.YES_OPTION)
                {
                    int result = ppstDelete.executeUpdate();

                    if(result > 0){
                        JOptionPane.showMessageDialog(null,"Record for staff ID: "+ staffid + " has been deleted successfully.","Record Deleted",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Result returned is not a positive integer, deletion has failed.","Error",JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
            else{
               //No action
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    }

   
    
    private void createConnection(){
        try{
            conn = DriverManager.getConnection(host, user, password);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void shutDown(){
        if(ppst != null){
            try{
                ppst.close();
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
    
}