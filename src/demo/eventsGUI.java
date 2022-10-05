package demo;

import models.Car;
import models.CarEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class eventsGUI {
    private JPanel panel1;
    private JTable events;
    final String DB_URL = "jdbc:mysql://localhost/carbase";
    final String USERNAME = "root";
   // final String PASS = "root123";
    final String PASS = "root";
    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

    public eventsGUI() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) events.getModel();
        events.setAutoCreateColumnsFromModel(true);
        events.setAutoCreateRowSorter(true);
        events.setPreferredSize(new Dimension(500,500));
        model.addColumn("Car Model");
        model.addColumn("Car-ID");
        model.addColumn("Date");
        model.addColumn("Event");
        model.addColumn("Mileage");
        model.addColumn("Invoice");
        model.addColumn("Contractor");
        model.addColumn("Price");

        ArrayList<CarEvent> carEvents = getEvents();
        Object[] rows = new Object[8];
        ArrayList<Car> cars = getCars();
        for (CarEvent event : carEvents) {
            for (Car car: cars
                 ) {
                if(car.getId()==event.getCar_id()){
                    rows[0] = car.getModel();
                }
            }
            rows[1] = event.getCar_id();
            rows[2] = event.getDate();
            rows[3] = event.getEvent();
            rows[4] = event.getMileage();
            rows[5] = event.getInvoice();
            rows[6] = event.getContractor();
            rows[7] = event.getPrice();
            model.addRow(rows);
        }
        events.setModel(model);
    }
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
    public ArrayList<CarEvent> getEvents() {
        ArrayList<CarEvent> list = new ArrayList<>();
        try {

            String query = "select * from events";

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            CarEvent carEvent;
            while (resultSet.next()) {
                carEvent = new CarEvent(resultSet.getInt("car_id"), resultSet.getString("date"),
                        resultSet.getString("event"), resultSet.getInt("mileage"),
                        resultSet.getString("invoice"), resultSet.getString("contractor"),
                        resultSet.getDouble("price"));
                list.add(carEvent);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
    public static void run() throws SQLException {
        JFrame frame = new JFrame("testTable");
        frame.setContentPane(new eventsGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(550, 550));
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("testTable");
        frame.setContentPane(new eventsGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(550, 550));
    }
}
