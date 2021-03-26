package apitests.Homework;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class spartanTestHomework2 {

    /*
    Q1:
Given accept type is json
And path param id is 20
When user sends a get request to "/spartans/{id}"
Then status code is 200
And content-type is "application/json;char"
And response header contains Date
And Transfer-Encoding is chunked
And response payload values match the following:
id is 20,
name is "Lothario",
gender is "Male",
phone is 7551551687
     */

    @Test
    public void test1WithPath(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 20)
                .when().get("http://54.197.122.229:8000/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        assertTrue(response.headers().hasHeaderWithName("Date"));

        assertEquals(response.header("Transfer-Encoding"),"chunked");

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(id,20);
        assertEquals(name,"Lothario");
        assertEquals(gender,"Male");
        assertEquals(phone,7551551687l);

    }
    @Test
    public void test1WithJsonPath(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 20)
                .when().get("http://54.197.122.229:8000/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"applicaiton/json");

        JsonPath jsonPath = response.jsonPath();

        int jsonID = jsonPath.getInt("id");
        String jsonName = jsonPath.getString("name");
        String jsonGender = jsonPath.getString("gender");
        long jsonPhone = jsonPath.getLong("phone");

        assertEquals(jsonID,20);
        assertEquals(jsonName,"Lothario");
        assertEquals(jsonGender,"Male");
        assertEquals(jsonPhone,7551551687l);
    }
    @Test
    public void test1WitHamcrestMatchers(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",20)
                .when().get("http://54.197.122.229:8000/spartans/{id}")
                .then().assertThat().statusCode(200)
               // .and().assertThat().contentType(equalTo("application/json"))
                .and().assertThat().headers("headers",hasItem("Date"));
    }

    @Test
    public void test1WithJsonToJava(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 20)
                .when().get("http://54.197.122.229:8000/spartans/{id}");

        assertEquals(response.statusCode(),200);
     //   assertEquals(response.contentType(),"application/json");

        Map<String,Object> spartanMapData = response.body().as(Map.class);

        String contenType = (String) spartanMapData.get("Content-Type");
        System.out.println("contenType = " + contenType);


    }

}
