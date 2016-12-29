import java.awt.*;
import javax.swing.*;

public class TransactionReport extends JFrame{
    private JPanel centerPanel;
    private JTable table;
    private JScrollPane scrollPane;

    private JLabel date = new JLabel("Date :");
    private JLabel total = new JLabel("Grand Total Price : RM");
    
    private JTextField jtfdate = new JTextField(10);
    private JTextField jtftotal = new JTextField(10);
    
     private ImageIcon card = new ImageIcon(getClass().getResource("transreport.png"));
    private JLabel logo = new JLabel(card,SwingConstants.CENTER);
    private JPanel logoPanel = new JPanel(new FlowLayout());
    
    public TransactionReport(){
         JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("Transaction Report");
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
        
        JPanel top = new JPanel();
        JPanel jpnorth = new JPanel();
        jpnorth.add(date);
        jpnorth.add(jtfdate);
        top.add(jpnorth,BorderLayout.CENTER);
        
        JPanel center = new JPanel();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout( new BorderLayout() );
        getContentPane().add( centerPanel );
        
        String columnNames[] = { "Order ID", "From","To", "Departure Date","Departure Time", "Price(RM)","Total ticket sold out","Total price(RM)","Payment ID"};
        
        String dataValues[][] =
		{
			{ "ORD001", "Penang", "Kedah","1 MAR 2016","09:00 a.m.","10","10","100","PAY001" },
			 {"ORD007", "Penang", "Selangor","10 MAR 2016","09:00 a.m.","60","5","300","PAY007" },
			{ "ORD010", "Selangor", "Johor","11 MAR 2016","08:00 p.m.","40","20","800","PAY010" },
		};
        
        table = new JTable( dataValues, columnNames );

        scrollPane = new JScrollPane( table );
       
        JPanel bottom = new JPanel();
        JPanel jpsouth = new JPanel(new GridLayout(1,8));
        jpsouth.add(new JLabel(""));
        jpsouth.add(new JLabel(""));
        jpsouth.add(new JLabel(""));
        jpsouth.add(new JLabel(""));
        jpsouth.add(new JLabel(""));
        jpsouth.add(new JLabel(""));
        jpsouth.add(new JLabel(""));
        jpsouth.add(new JLabel(""));
        jpsouth.add(total);
        jpsouth.add(jtftotal);
        bottom.add(jpsouth);
        
//        JPanel jpSchedule = new JPanel(new GridLayout());
//        jpSchedule.add(new JLabel("Schedule ID"));
//        jpSchedule.add(new JLabel("Bus Number"));
//        jpSchedule.add(new JLabel("From"));
//        jpSchedule.add(new JLabel("To"));
//        jpSchedule.add(new JLabel("Departure Date"));
//        jpSchedule.add(new JLabel("Departure Time"));
//        jpSchedule.add(new JLabel("Arrival Date"));
//        jpSchedule.add(new JLabel("Arrival Time"));
//        jpSchedule.add(new JLabel("Distance"));
        
//        jptop.add(jpChoose, BorderLayout.NORTH);
//        jptop.add(jpSchedule, BorderLayout.NORTH);
         setContentPane(basic);
        pack();
        basic.setVisible(true);
        basic.add(top,BorderLayout.NORTH);
         centerPanel.add( scrollPane);
         basic.add(centerPanel,BorderLayout.CENTER);
        basic.add(bottom,BorderLayout.SOUTH);
        basic.add(jptop);
       
        
        setTitle("Transaction Report");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,350);
        setVisible(true);
        
    }
    
    public static void main(String[] args){
        TransactionReport frame = new TransactionReport();
}

}