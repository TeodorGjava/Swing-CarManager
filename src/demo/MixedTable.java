package demo;

import models.Car;
import models.CarEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MixedTable {
    private JPanel panel1;
    private JTable mixedTable;
    private JTable events;
    final String DB_URL = "jdbc:mysql://localhost/carbase";
    final String USERNAME = "root";
    final String PASS = "root123";
    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);


    public MixedTable() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) mixedTable.getModel();
        mixedTable.setAutoCreateColumnsFromModel(true);
        mixedTable.setAutoCreateRowSorter(true);
        mixedTable.setPreferredSize(new Dimension(200, 300));
        model.addColumn("Brand");
        model.addColumn("Model");

        ArrayList<Car> carsList = getCars();
        Object[] rows = new Object[2];
        for (Car car : carsList) {
            rows[0] = car.getBrand();
            rows[1] = car.getModel();
            model.addRow(rows);
        }

        mixedTable.setModel(model);
        DefaultTableModel m = (DefaultTableModel) events.getModel();
        events.setAutoCreateColumnsFromModel(true);
        events.setAutoCreateRowSorter(true);
        events.setPreferredSize(new Dimension(500, 500));
        m.addColumn("Car Model");
        m.addColumn("Car-ID");
        m.addColumn("Date");
        m.addColumn("Event");
        m.addColumn("Mileage");
        m.addColumn("Invoice");
        m.addColumn("Contractor");
        m.addColumn("Price");

        ArrayList<CarEvent> carEvents = getEvents();
        Object[] rowz = new Object[8];
        ArrayList<Car> cars = getCars();
        for (CarEvent event : carEvents) {
            // for (Car car : cars
            // ) {
            //     if (car.getId() == event.getCar_id()) {
            //         rowz[0] = car.getModel();
            //     }
            // }
            //select `event`,`date`,`contractor`,`invoice`,`mileage`,`price`as`priceBeforeTaxes`,`price`*1.2 as `fullPrice` from events where car_id = 2;
            rowz[0] = event.getCar_id();
            rowz[1] = event.getDate();
            rowz[2] = event.getEvent();
            rowz[3] = event.getMileage();
            rowz[4] = event.getInvoice();
            rowz[5] = event.getContractor();
            rowz[6] = event.getPrice();
            m.addRow(rowz);
        }
        events.setModel(displayTable());
    }

    public DefaultTableModel displayTable() throws SQLException {
        //select c.model,e.event,e.date,e.contractor,e.invoice,e.mileage,e.price,
        // e.price*1.20as price_with_taxex from cars as c join events as e on c.id=e.car_id;
        String query = "select model,event,date,contractor,invoice,mileage,price," +
                "price*1.20as 'price_with_taxex' from cars  join events on cars.id=events.car_id;";
        DefaultTableModel model2 = (DefaultTableModel) events.getModel();

        String model = "";
        String event = "";
        String date = "";
        String contractor = "";
        String invoice = "";
        String mileage = "";
        String price = "";
        String priceWithTaxes = "";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            model = rs.getString("model");
            event = rs.getString("event");
            date = rs.getString("date");
            contractor = rs.getString("contractor");
            invoice = rs.getString("invoice");
            mileage = rs.getString("mileage");
            price = rs.getString("price");
            priceWithTaxes = String.valueOf(Double.parseDouble(price)*1.2);
            //Table displaying event info price with/without taxes completed
            model2.addRow(new Object[]{model, event, date, contractor, invoice, mileage,price,priceWithTaxes});
        }
        //TODO: Display table with events only for selected row from main table !


        return model2;
    }

    public ArrayList<CarEvent> getEvents() {
        ArrayList<CarEvent> list = new ArrayList<>();
        try {
            //select `event`,`date`,`contractor`,`invoice`,`mileage`,`price`as`priceBeforeTaxes`,
            // `price`*1.2 as `fullPrice` from events where car_id = 2;
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

    public static void run() throws SQLException {
        JFrame frame = new JFrame("testTable");
        frame.setContentPane(new MixedTable().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(800, 800));
    }

    public static void main(String[] args) throws SQLException {
        run();
    }
}
