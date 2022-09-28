import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField input;
    private JLabel Input;
    private JButton convertButton;
    private JLabel answer;
    private static final double EUR= 1.95583;

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
                double ans = lv/EUR;
                answer.setText(String.valueOf(ans));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new ConverterGUI("Кoлколатор");
        frame.setSize(300,400);
        frame.setVisible(true);
    }
}
