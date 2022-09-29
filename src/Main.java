import models.Car;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Porsche","911Turbo");
        addCarToDatabase(car);
    }
    private static void addCarToDatabase(Car car){
    final String DB_URL = "jdbc:mysql://localhost/carbase";
    final String USERNAME = "root";
    final String PASS = "root";

    try{
        Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASS);
        Statement statement = conn.createStatement();
        String query = "insert into cars (id,brand,model) values (?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1,"1");
        preparedStatement.setString(2,car.getBrand());
        preparedStatement.setString(3,car.getModel());
        preparedStatement.executeUpdate();
        System.out.println("Car "+ car.getBrand()+" "+ car.getModel()+" added to database! ");
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }
}
