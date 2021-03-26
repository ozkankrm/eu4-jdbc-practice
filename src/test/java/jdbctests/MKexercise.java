package jdbctests;

import java.sql.*;

public class MKexercise {
    public static void main(String[] args) throws SQLException {

        String dbUrl = dbUrl = "jdbc:oracle:thin:@54.197.122.229:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement();

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select job_id, avg(salary),count(*) from employees group by job_id");

       // next();//bir sonraki row

       // resultSet.next();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1)+ " - " + resultSet.getString(2)+" - "+
                    resultSet.getString(3));

        }

        resultSet.close();
        statement.close();
        connection.close();
    }





}
