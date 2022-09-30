package demo;

import models.Car;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class testTable {

    final String DB_URL = "jdbc:mysql://localhost/carbase";
    final String USERNAME = "root";
    final String PASS = "root";
    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
    private JPanel panel1;
    private JTable test;

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
        for (Car Car : carsList) {
            rows[0] = Car.getBrand();
            rows[1] = Car.getModel();
            model.addRow(rows);
        }
        test.setModel(model);
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
