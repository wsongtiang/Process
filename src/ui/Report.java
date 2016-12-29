package ui;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Report {
    private String host = "jdbc:derby://localhost:1527/BusDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private Connection conn;
    
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel jpButton = new JPanel(new GridLayout(3,1,2,3));
        final JPanel jpDropDown = new JPanel(new GridLayout(3,1,2,3));
        final ButtonGroup btg = new ButtonGroup();
        
        final JRadioButton jrbTransaction = new JRadioButton("Transaction");
        final JRadioButton jrbException = new JRadioButton("Exception");
        final JRadioButton jrbSummary = new JRadioButton("Summary");
        
        String[] tranArr = {"--------------------- Please Select ---------------------","Bus Ticket Sales Report"};
        final JComboBox jcboTrans = new JComboBox(tranArr);
        
        String[] excepArr = {"--------------------- Please Select ---------------------","Top 3 High Purchased Route Report"};
        final JComboBox jcboExcept = new JComboBox(excepArr);
        
        String[] sumArr = {"--------------------- Please Select ---------------------","Bus Ticket Sales Summary Report", "Ticket Sales Summary Report"};
        final JComboBox jcboSum = new JComboBox(sumArr);
        
    public Report(){
        panel.add(new JLabel("Which report would you like to generate?"), BorderLayout.NORTH);      
        panel.setVisible(true);
        
        jpButton.add(jrbTransaction);
        jpButton.add(jrbException);
        jpButton.add(jrbSummary);
        btg.add(jrbTransaction);
        btg.add(jrbException);
        btg.add(jrbSummary);
        panel.add(jpButton, BorderLayout.WEST);
        
        jpDropDown.add(jcboTrans);
        jpDropDown.add(jcboExcept);
        jpDropDown.add(jcboSum);
        panel.add(jpDropDown);

        JOptionPane.showMessageDialog(null, panel, "GENERATE REPORT", JOptionPane.QUESTION_MESSAGE);
        
        if(jrbTransaction.isSelected()){
            if(jcboTrans.getSelectedItem().toString().equals("Bus Ticket Sales Report")){
            
                String reportSource = "././reportTemplates/TransactionReport.jrxml";
        
        Map<String, Object> params = new HashMap<String, Object>();
        
        try{	 
            Connection conn = DriverManager.getConnection(host,user,password);
            JasperReport jasperReport =JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,conn);
            JasperViewer.viewReport(jasperPrint, false); 
        }catch (JRException jrex)
        {
//            JOptionPane.showMessageDialog(this, "error in generating report");
            jrex.printStackTrace();
        }catch(Exception ex)
        {
//            JOptionPane.showMessageDialog(this, "Unble to generate report~!");
            ex.printStackTrace();
        }
            
            }
            else if(jcboTrans.getSelectedItem().toString().equals("--------------------- Please Select ---------------------")){
                JOptionPane.showMessageDialog(null, "Please select the type of report you want to generate.", "ERROR", JOptionPane.ERROR_MESSAGE);
                Report report = new Report();
            }
        }
        else if(jrbException.isSelected()){
            if(jcboExcept.getSelectedItem().toString().equals("Top 3 High Purchased Route Report")){
                 String reportSource = "././reportTemplates/ExceptionReport.jrxml";
        
        Map<String, Object> params = new HashMap<String, Object>();
        
        try{	 
            Connection conn = DriverManager.getConnection(host,user,password);
            JasperReport jasperReport =JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,conn);
            JasperViewer.viewReport(jasperPrint, false); 
        }catch (JRException jrex)
        {
//            JOptionPane.showMessageDialog(this, "error in generating report");
            jrex.printStackTrace();
        }catch(Exception ex)
        {
//            JOptionPane.showMessageDialog(this, "Unble to generate report~!");
            ex.printStackTrace();
        }
            }
            else if(jcboExcept.getSelectedItem().toString().equals("Cancelled Route Report")){
                
            }
            else if(jcboExcept.getSelectedItem().toString().equals("--------------------- Please Select ---------------------")){
                JOptionPane.showMessageDialog(null, "Please select the type of report you want to generate.", "ERROR", JOptionPane.ERROR_MESSAGE);
                Report report = new Report();
            }
        }
        else if(jrbSummary.isSelected()){
            if(jcboSum.getSelectedItem().toString().equals("Bus Ticket Sales Summary Report")){
                 String reportSource = "././reportTemplates/SummaryReport.jrxml";
        
        Map<String, Object> params = new HashMap<String, Object>();
        
        try{	 
            Connection conn = DriverManager.getConnection(host,user,password);
            JasperReport jasperReport =JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,conn);
            JasperViewer.viewReport(jasperPrint, false); 
        }catch (JRException jrex)
        {
//            JOptionPane.showMessageDialog(this, "error in generating report");
            jrex.printStackTrace();
        }catch(Exception ex)
        {
//            JOptionPane.showMessageDialog(this, "Unble to generate report~!");
            ex.printStackTrace();
        }
            }
            else if(jcboSum.getSelectedItem().toString().equals("Ticket Sales Summary Report")){
                 String reportSource = "././reportTemplates/SummaryReport1.jrxml";
        
        Map<String, Object> params = new HashMap<String, Object>();
        
        try{	 
            Connection conn = DriverManager.getConnection(host,user,password);
            JasperReport jasperReport =JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,conn);
            JasperViewer.viewReport(jasperPrint, false); 
        }catch (JRException jrex)
        {
//            JOptionPane.showMessageDialog(this, "error in generating report");
            jrex.printStackTrace();
        }catch(Exception ex)
        {
//            JOptionPane.showMessageDialog(this, "Unble to generate report~!");
            ex.printStackTrace();
        }
            }
            else if(jcboSum.getSelectedItem().toString().equals("--------------------- Please Select ---------------------")){
                JOptionPane.showMessageDialog(null, "Please select the type of report you want to generate.", "ERROR", JOptionPane.ERROR_MESSAGE);
                Report report = new Report();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select the type of report you want to generate.", "ERROR", JOptionPane.ERROR_MESSAGE);
            Report report = new Report();
        }
    }
        
    public static void main(final String[] args) {
        Report report = new Report();
    }
}