package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMap_Example {

    String dbUrl = dbUrl = "jdbc:oracle:thin:@54.197.122.229:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void MetaDataExample() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //ResultSet.TYPE_SCROLL_INSENSITIVE-->allow us to navigate up and down in query result.
        //ResultSet.CONCUR_READ_ONLY-->Read only, don’t update the database

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String, Object>> queryData = new ArrayList<>();
        //Object yazmamısın sebebi; string, integer, boolen vs. farklı data türünü kullanabilmek için. object top tır, hepsini kapsar

        //bir row  bir maptir ve key value ile işler
        Map<String, Object> row1 = new HashMap<>();
        row1.put("first_name", "steven");
        row1.put("last_name", "King");
        row1.put("salary", 24000);
        row1.put("job_id", "AD_PRE");
        System.out.println(row1.toString());

        Map<String, Object> row2 = new HashMap<>();
        row2.put("first_name", "Neena");
        row2.put("last_name", "Kochhar");
        row2.put("salary", 17000);
        row2.put("job_id", "AD_VP");
        System.out.println(row2.toString());
        System.out.println(row2.get("salary"));

        //adding rows to my list
        queryData.add(row1);//row1 ile ilk maptaki infoyu listmap e ekledik
        queryData.add(row2);//row2 ile ikinci mapdaki infoyu list ekledik

        //get the steven last name directly from the list
        queryData.get(0).get("last_name");//ilk row un lastname ini çağırdık
        System.out.println("queryData.get(0) = " + queryData.get(0));//birinci rowun hepsini veriri. get(0)

        queryData.get(0);//first row
        queryData.get(1) ; // second row

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void MetaDataExample2() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //ResultSet.TYPE_SCROLL_INSENSITIVE-->allow us to navigate up and down in query result.
        //ResultSet.CONCUR_READ_ONLY-->Read only, don’t update the database

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select first_name, last_name,salary, job_id from employees where rownum<6");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String, Object>> queryData = new ArrayList<>();
        //Object yazmamısın sebebi; string, integer, boolen vs. farklı data türünü kullanabilmek için. object top tır, hepsini kapsar

        //move to first row
        resultSet.next();

        //bir row  bir maptir ve key value ile işler
        Map<String, Object> row1 = new HashMap<>();
        row1.put(rsMetadata.getColumnName(1), resultSet.getString(1));
        row1.put(rsMetadata.getColumnName(2), resultSet.getString(2));
        row1.put(rsMetadata.getColumnName(3), resultSet.getString(3));
        row1.put(rsMetadata.getColumnName(4), resultSet.getString(4));

        System.out.println(row1.toString());

        //move to second row
        resultSet.next();

        Map<String, Object> row2 = new HashMap<>();
        row2.put(rsMetadata.getColumnName(1), resultSet.getString(1));//return column name and cloumn value
        row2.put(rsMetadata.getColumnName(2), resultSet.getString(2));
        row2.put(rsMetadata.getColumnName(3), resultSet.getString(3));
        row2.put(rsMetadata.getColumnName(4), resultSet.getString(4));

        System.out.println(row2.toString());
        System.out.println(row2.get("salary"));

        //adding rows to my list
        queryData.add(row1);//row1 ile ilk maptaki infoyu listmap e ekledik
        queryData.add(row2);//row2 ile ikinci mapdaki infoyu list ekledik

        //get the steven last name directly from the list
        System.out.println(queryData.get(0).get("LAST_NAME"));
        System.out.println("queryData.get(0) = " + queryData.get(0));//birinci rowun hepsini veriri. get(0)

        queryData.get(0);//first row
        queryData.get(1) ; // second row

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
