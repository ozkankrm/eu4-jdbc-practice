package jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class dbutils_practice {

    @Test
    public void test1(){
        //create connection
        DBUtils.createConnection();

        //getQueryResultMap()--> accept the query and keep result
        //using method to get result as a list of maps
        List<Map<String, Object>> queryResult = DBUtils.getQueryResultMap("select * from departments");

        for (Map<String, Object> map : queryResult) {
            System.out.println(map.toString());
        }

        //close connection
        DBUtils.destroy();
    }

    @Test
    public void test2(){
        //create connection
        DBUtils.createConnection();

        //bir row istenirse getRowMap() methodunu kullan
       Map<String, Object> rowMap = DBUtils.getRowMap("select first_name, last_name, salary from employees where employee_id = 100");

        System.out.println(rowMap.toString());
        //close connection
        DBUtils.destroy();
    }
}
