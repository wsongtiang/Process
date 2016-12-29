import java.awt.*;
import javax.swing.*;

public class ExceptionReport extends JFrame{
    private JPanel centerPanel;
    private JTable table;
    private JScrollPane scrollPane;
    
     private JLabel date = new JLabel("Month :");
     
     private JTextField jtfdate = new JTextField(10);
     
     private JLabel route = new JLabel("Top 3 Routes");
     
      private ImageIcon card = new ImageIcon(getClass().getResource("exreport.png"));
    private JLabel logo = new JLabel(card,SwingConstants.CENTER);
    private JPanel logoPanel = new JPanel(new FlowLayout());

    
    public ExceptionReport(){
         JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("Exception Report");
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
        jpnorth.add(route);
        top.add(jpnorth,BorderLayout.CENTER);
        
        JPanel center = new JPanel();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout( new BorderLayout() );
        getContentPane().add( centerPanel );
        
        String columnNames[] = { "Route ID", "From","To", "Total ticket sold out"};
        
        String dataValues[][] =
		{
			{ "ROT001", "Penang", "Kedah","50" },
			 {"ROT020", "Selangor", "Johor","40" },
			{ "ROT010", "Penang", "Selangor","30"},
		};
        
        table = new JTable( dataValues, columnNames );

        scrollPane = new JScrollPane( table );
       
        
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
//        jptop.add(centerPanel,BorderLayout.NORTH);
        basic.add(jptop);
       
        
        setTitle("Exception Report");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,350);
        setVisible(true);
        
    }
    
    public static void main(String[] args){
        ExceptionReport frame = new ExceptionReport();
}

}