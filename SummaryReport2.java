import java.awt.*;
import javax.swing.*;

public class SummaryReport2 extends JFrame{
    private JPanel centerPanel;
    private JTable table;
    private JScrollPane scrollPane;
    
     private JLabel month = new JLabel("Month :");
    private JLabel total = new JLabel("Grand Total Ticket : ");
    
    private JTextField jtfmonth = new JTextField(10);
    private JTextField jtftotal = new JTextField(10);
    
        private ImageIcon card = new ImageIcon(getClass().getResource("sumreport.png"));
    private JLabel logo = new JLabel(card,SwingConstants.CENTER);
    private JPanel logoPanel = new JPanel(new FlowLayout());

    
    public SummaryReport2(){
         JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("Summary Report");
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
        
//        JPanel jpChoose = new JPanel(new FlowLayout());
//        jpChoose.add(new JButton(""));
        
          JPanel top = new JPanel();
        JPanel jpnorth = new JPanel();
        jpnorth.add(month);
        jpnorth.add(jtfmonth);
        top.add(jpnorth,BorderLayout.CENTER);
        
        JPanel center = new JPanel();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout( new BorderLayout() );
        getContentPane().add( centerPanel );
        
        String columnNames[] = { "Bus ID","Bus No", "Bus Name", "Total ticket sold out"};
        
        String dataValues[][] =
		{
			{ "BUS001", "PMA2000", "Super Bus","40"},
			 {"BUS001", "PEA999", "Nine Bus","60"},
			{ "BUS001", "PJQ166", "Fly Bus","20"},
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
//        jptop.add(centerPanel,BorderLayout.NORTH);
        basic.add(jptop);
       
        
        setTitle("Summary Report");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,350);
        setVisible(true);
        
    }
    
    public static void main(String[] args){
        SummaryReport2 frame = new SummaryReport2();
}

}