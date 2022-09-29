import models.Car;
import models.CarEvent;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
       // Car car = new Car("Porsche", "911Turbo");
      //  addCarToDatabase(car);
      CarEvent event = new CarEvent(1, "2022/01/03", "Смяна на масло и филтри", 120445, "05143462/03/01/2022", "АЛКАРС", 965.5);
      CarEvent event2 = new CarEvent(1, "2022/06/03", "Смяна  филтри", 13512, "05143432/03/05/2022", "АЛКАРС", 975.5);
      //  car.addEvent(event);
        addEventToDatabase(event);
        addEventToDatabase(event2);

    }

    private static void addEventToDatabase(CarEvent event) throws SQLException {
        final String DB_URL = "jdbc:mysql://localhost/carbase";
        final String USERNAME = "root";
        final String PASS = "root";
        Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

        String query = "insert into events (car_id,date,event,mileage,invoice,contractor,price)" +
                " values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, "1");
        preparedStatement.setString(2, event.getDate());
        preparedStatement.setString(3, event.getEvent());
        preparedStatement.setString(4, String.valueOf(event.getMileage()));
        preparedStatement.setString(5, event.getInvoice());
        preparedStatement.setString(6, event.getContractor());
        preparedStatement.setString(7, String.valueOf(event.getPrice()));
        preparedStatement.executeUpdate();
        System.out.println("Event "+ event.getEvent()+" added successfully to database");
    }

    private static void addCarToDatabase(Car car) {
        final String DB_URL = "jdbc:mysql://localhost/carbase";
        final String USERNAME = "root";
        final String PASS = "root";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            Statement statement = conn.createStatement();
            String query = "insert into cars (id,brand,model) values (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, "1");
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.executeUpdate();
            System.out.println("Car " + car.getBrand() + " " + car.getModel() + " added to database! ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
