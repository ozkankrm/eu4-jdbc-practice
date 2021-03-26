package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class Jdbc_example {

    String dbUrl = dbUrl = "jdbc:oracle:thin:@54.197.122.229:1521:xe";
    String  dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //ResultSet.TYPE_SCROLL_INSENSITIVE-->allow us to navigate up and down in query result.
        //ResultSet.CONCUR_READ_ONLY-->Read only, don’t update the database

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //TASK:gow to find how many rows we have for the query
        //go to last row  -->önce son satıra gittik, last()
        resultSet.last();
        //get the row count--> sonra son satırın numarasını aldık, getRow() ile
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        //we need move before first row to get full list since we are at the last row now
        resultSet.beforeFirst();//yukarda last() methodu ile son satıra gittik. ve yeniden başa dönmek için beforeFirst() kullandık

        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }
        //resultSet yukarda tanımlı olduğu için burda da kullanabiliriz
        resultSet = statement.executeQuery("select * from regions");

        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }

    @Test
    public void MetaDataExample() throws SQLException {

            //create connection
            Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

            //create statement object
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //ResultSet.TYPE_SCROLL_INSENSITIVE-->allow us to navigate up and down in query result.
            //ResultSet.CONCUR_READ_ONLY-->Read only, don’t update the database

            //run query and get the result in resultset object
            ResultSet resultSet = statement.executeQuery("select * from departments");

            //get the database related data inside the dbMetadate object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User =" + dbMetadata.getUserName());
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name = "+ dbMetadata.getDriverName());
        System.out.println("Driver version = " + dbMetadata.getDriverVersion());


        //important for us
        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns we have?
        int columnCount = rsMetadata.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        //column names
        System.out.println("rsMetadata.getColumnName(1) = " + rsMetadata.getColumnName(1));
        System.out.println("rsMetadata.getColumnName(2) = " + rsMetadata.getColumnName(2));

        //print all the column names dynamically
        for (int i = 1; i <= rsMetadata.getColumnCount(); i++) {

            System.out.println(rsMetadata.getColumnName(i));
        }



        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
