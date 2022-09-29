import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class ConverterGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField input;
    private JLabel Input;
    private JButton convertButton;
    private JLabel answer;
    private static final double EUR= 1.95583;
    private static final String pattern ="###.##";
    private static final DecimalFormat df = new DecimalFormat(pattern);
    public ConverterGUI(String title) {
        super(title);
        //close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //content
        this.setContentPane(mainPanel);
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double lv = Double.parseDouble(input.getText());
                String result = df.format(lv/EUR);
                answer.setText(result);
            }
        });
    convertButton.addKeyListener(new KeyAdapter() { } );input.addKeyListener(new KeyAdapter() { } );input.addKeyListener(new KeyAdapter() { } );
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    double lv = Double.parseDouble(input.getText());
                    String result = df.format(lv/EUR);
                    answer.setText(result);
                }
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    answer.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new ConverterGUI("Кoлколатор");
        frame.setSize(300,300);
        frame.setVisible(true);
    }
}
