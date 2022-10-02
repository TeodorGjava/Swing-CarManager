package demo;

import models.Car;
import models.CarEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class testTable {

    final String DB_URL = "jdbc:mysql://localhost/carbase";
    final String USERNAME = "root";
    final String PASS = "root123";
    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
    private JPanel panel1;
    private JTable test;
    private JButton openDetails;


    public ArrayList<Car> getCars() {
        ArrayList<Car> list = new ArrayList<>();
        try {

            String query = "SELECT * from cars";

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Car AllCars;
            while (resultSet.next()) {
                AllCars = new Car(resultSet.getString("brand"), resultSet.getString("model"));
                list.add(AllCars);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
    public testTable() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) test.getModel();
        test.setAutoCreateColumnsFromModel(true);
        test.setAutoCreateRowSorter(true);
        test.setPreferredSize(new Dimension(500,500));
        model.addColumn("Brand");
        model.addColumn("Model");

        ArrayList<Car> carsList = getCars();
        Object[] rows = new Object[2];
        for (Car car : carsList) {
            List<CarEvent> eventList = car.getEventList();
            for (CarEvent event: eventList
                 ) {

            }
            rows[0] = car.getBrand();
            rows[1] = car.getModel();
            model.addRow(rows);
        }



        test.setModel(model);
        openDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    eventsGUI.run();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        test.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int column = 0;
                int row = test.getSelectedRow();
                String value = test.getModel().getValueAt(row, column).toString();
                //select c.brand,c.model,e.event from cars as c join events as e on c.id=e.car_id;

                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    JFrame repairs = new JFrame("Repairs");

                }
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("testTable");
        frame.setContentPane(new testTable().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(550, 550));
    }
}
