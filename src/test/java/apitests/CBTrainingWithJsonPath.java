package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeClass(){

        baseURI = ConfigurationReader.get("cbt_api_url");
    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 20001)
                .when().get("/student/{id}");

        //verify status code
        assertEquals(response.statusCode(),200);

        //assign  response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        //get value from jsonpath
        String firstname = jsonPath.getString("students.firstName[0]");
        System.out.println("firstname = " + firstname);

        String lastName = jsonPath.getString("students.lastName[0]");
        System.out.println("lastName = " + lastName);

        String phoneNumber = jsonPath.getString("students.contact[0].phone");
        System.out.println("phoneNumber = " + phoneNumber);

        //get me city and zipcode, do assertion
        String cityName = jsonPath.getString("students.company[0].address.city");
        System.out.println("cityName = " + cityName);
        assertEquals(cityName,"Chicago");

        int zipCode = jsonPath.getInt("students.company[0].address.zipCode");
        System.out.println("zipCode = " + zipCode);
        assertEquals(zipCode,60606);

        String firstName2 = jsonPath.getString("students.firstName");
        System.out.println("firstName2 = " + firstName2);

        String firstName3 = response.path("students.firstName");
        System.out.println("firstName3 = " + firstName3);

    }
}
