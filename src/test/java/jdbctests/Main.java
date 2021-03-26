package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        String dbUrl = dbUrl = "jdbc:oracle:thin:@54.197.122.229:1521:xe";
        String  dbUsername = "hr";
        String dbPassword = "hr";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement();

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");
                                                    //regions
                                                    //employees
/*
        //move pointer to first row
        //tablodaki column isimlerin olduğu row atla demek istiyor next() metodu
        resultSet.next();//--> her yazıldığında bir satır aşağıya gide

        //getting information with column name
        System.out.println(resultSet.getString("region_name"));//Europe
        //getting information with column index(starts from 1)
        System.out.println(resultSet.getString(2)); //Europe
        // 1 - Europe
        System.out.println(resultSet.getInt(1 )+ " - "+ resultSet.getString("region_name"));

        //move pointer second row
        resultSet.next();
        //getting information with column name
        System.out.println(resultSet.getString("region_name"));//Americas
        //getting information with column index(starts from 1)
        System.out.println(resultSet.getString(2)); //Americas
        // 2 - Americas
        System.out.println(resultSet.getInt(1 )+ " - "+ resultSet.getString(2));

 */


   /*
        //while ile tüm table yazdırılır.
        //string manipulation kullanılabilir
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1 )+ " - "+ resultSet.getString(2));
        }

    */
        /*
        //firstname,lastname, salary from employees
        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+ " - " + resultSet.getString(2)+
                    " - "+resultSet.getString(3));
        }

         */

        //from departments
        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+" - "+resultSet.getString(2)+ " - " +
                resultSet.getString(3)+ " - "+ resultSet.getString(4));
        }



        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
