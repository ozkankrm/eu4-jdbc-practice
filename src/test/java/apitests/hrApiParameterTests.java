package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class hrApiParameterTests {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("hr_api_url");
    }

    /*
       Given accept type is Json
       And parameters: q = {"region_id":2}
       When users sends a GET request to "/countries"
       Then status code is 200
       And Content type is application/json
       And Payload should contain "United States of America"
       {"region_id":2}
    */
    @Test
    public void test(){

        Response response = given().contentType(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("United States of America"));

    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));


        //makes sure we have only IT_PROG as a job_id
        List<String> jobIDs = response.path("items.job_id");
        System.out.println("jobId = " + jobIDs);

        for (String jobId : jobIDs) {

            assertEquals(jobId,"IT_PROG");

        }
    }



}
