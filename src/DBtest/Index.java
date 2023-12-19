package DBtest;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Index {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourist","root","2505");
            System.out.println("Connected !");
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("select * from Booking");
            while(resultSet.next())
                System.out.println(resultSet.getInt(1)+"  "+resultSet.getString(2)+"  "+resultSet.getString(3));
        }catch (Exception e){
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }
}
