package demo;

import models.Car;
import models.CarEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class MixedTable {
    private JPanel panel1;
    private JTable mixedTable;
    private JTable events;
    final String DB_URL = "jdbc:mysql://localhost/carbase";
    final String USERNAME = "root";
    final String PASS = "root123";
    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

        //TODO: Display table with events only for selected row from main table !
        //Done !
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
        m.addColumn("Автомобил");
        m.addColumn("Събитие");
        m.addColumn("Дата");
        m.addColumn("Изпълнител");
        m.addColumn("Фактура");
        m.addColumn("Километраж");
        m.addColumn("Цена");
        m.addColumn("Цена с ДДС");

        mixedTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    getSelectedCarInfo();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void getSelectedCarInfo() throws SQLException {
        clearTable();
        DefaultTableModel dtm = (DefaultTableModel) mixedTable.getModel();
        DefaultTableModel eventsModel = (DefaultTableModel) events.getModel();
        eventsModel.setRowCount(0);
        int rowSelected = mixedTable.getSelectedRow();
        String carID = String.valueOf(dtm.getValueAt(rowSelected, 1));
        events.setModel(displayTable(carID));
    }

    private void clearTable() {
    }

    public DefaultTableModel displayTable(String carModel) throws SQLException {

        String query = "select model,event,date,contractor,invoice,mileage,price, \n" +
                "                price*1.20as 'price_with_taxex' \n" +
                "                from cars join events on cars.id=events.car_id\n" +
                "                where cars.model like('" + carModel + "')";
        DefaultTableModel model2 = (DefaultTableModel) events.getModel();

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
          String model = rs.getString("model");
          String event = rs.getString("event");
          String date = rs.getString("date");
          String contractor = rs.getString("contractor");
          String invoice = rs.getString("invoice");
          String mileage = rs.getString("mileage");
          String price = rs.getString("price");

          String priceWithTaxes = String.valueOf(Double.parseDouble(price) * 1.2);
            //Table displaying event info price with/without taxes completed
            model2.addRow(new Object[]{model, event, date, contractor, invoice, mileage, price, priceWithTaxes});
        }
        return model2;
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
