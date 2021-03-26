package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynnamic_list {

    String dbUrl = dbUrl = "jdbc:oracle:thin:@54.197.122.229:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void MetaDataExample2() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //ResultSet.TYPE_SCROLL_INSENSITIVE-->allow us to navigate up and down in query result.
        //ResultSet.CONCUR_READ_ONLY-->Read only, don’t update the database

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from countries");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String, Object>> queryData = new ArrayList<>();
        //Object yazmamısın sebebi; string, integer, boolen vs. farklı data türünü kullanabilmek için. object top tır, hepsini kapsar

        //number of columns
        int columnCount = rsMetadata.getColumnCount();

        //loop through each row
        while (resultSet.next()){
            Map<String, Object> row =new HashMap<>();

            //her bir value u map'e eklemek için for loop kullandık
            for (int i = 1; i <=columnCount; i++) {
                                    //key                       //value
                row.put(rsMetadata.getColumnName(i), resultSet.getObject(i));
            }

            //add your map to your list
            queryData.add(row);
        }
        //print the result
        for (Map<String, Object> queryDatum : queryData) {
            System.out.println(queryDatum.toString());
        }


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }


}
