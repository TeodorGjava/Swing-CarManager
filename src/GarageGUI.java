import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class GarageGUI extends JFrame{
    private JPanel garagePanel;
    private JButton openTable;
    private JButton button2;
    private JButton button3;
    private JLabel background;
    JFrame converter = new ConverterGUI("Garage");

    public GarageGUI(String title){
        super(title);
        this. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(garagePanel);


        openTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                converter.setSize(300,300);
                converter.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                converter.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new GarageGUI("Garage");
        frame.setSize(912,608);
        frame.setVisible(true);


          }
}
