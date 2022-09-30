package models;

import models.Car;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class CarsTable extends JFrame {
    private JTable carsTable;
    private JPanel panel1;
    private DefaultTableModel model;
    private PreparedStatement preparedStatement;
    private String query;
    final String DB_URL = "jdbc:mysql://localhost/carbase";
    final String USERNAME = "root";
    final String PASS = "root";
    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

    public CarsTable(String title) throws SQLException {
        super(title);
        //close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }

    public ArrayList<Car> cars() {
        ArrayList<Car> list = new ArrayList<>();
        try {

            query = "SELECT * from cars";

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

    public void show_id() {
        ArrayList<Car> list1 = cars();
        model = (DefaultTableModel) carsTable.getModel();

        Object[] rows = new Object[2];
        for (Car Car : list1) {
            rows[0] = Car.getBrand();
            rows[1] = Car.getModel();
            model.addRow(rows);
        }

    }

    public static void main(String[] args) throws SQLException {
        Frame frame = new CarsTable("Таблица");
        frame.setSize(500, 500);
        frame.setVisible(true);

    }
}
