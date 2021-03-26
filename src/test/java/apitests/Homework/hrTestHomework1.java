package apitests.Homework;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;


public class hrTestHomework1 {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("hr_api_url");
    }
    /*
    Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is
     */
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("country_id", "US")
                .when().get("/countries/{country_id}");

        //verify status code
        assertEquals(response.statusCode(),200);
        //verify content type
        assertEquals(response.contentType(),"application/json");

        //verify country_id
        assertEquals(response.path("country_id"),"US");

        //verify country_name
        assertEquals(response.path("country_name"),"United States of America");

        //verify region_id
        int regionID = response.path("region_id");
        assertEquals(regionID,2);
    }
    /*
    - Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
     */

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //assign renponse to jsonpath
        JsonPath jsonPath = response.jsonPath();

        //- And all job_ids start with 'SA'
        List<String> allJobID = jsonPath.getList("items.job_id");
        System.out.println("allJobID = " + allJobID);

        for (String jobID : allJobID) {
            assertTrue(jobID.startsWith("SA"));
        }

        //- And all department_ids are 80
        List<Integer> allDepartmentID = jsonPath.getList("items.department_id");
        System.out.println("allDepartmentID = " + allDepartmentID);

        for (int departmentID : allDepartmentID) {
            assertEquals(departmentID,80);
        }

        //- Count is 25
        int count = jsonPath.getInt("count");
        System.out.println("count = " + count);
        assertEquals(count,25);
    }
    /*
    - Given accept type is Json
-Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
     */

    @Test
    public void test3WithPath(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //- And all regions_id is 3
        List<Integer> allRegionIDs = response.path("items.region_id");

        for (int regionID : allRegionIDs) {
            System.out.println("regionID = " + regionID);
            assertEquals(regionID,3);
        }

        //- And count is 6
        int count = response.path("count");
        System.out.println("count = " + count);
        assertEquals(count,6);

        //- And hasMore is false
        boolean hasMore = response.path("hasMore");
        System.out.println("hasMore = " + hasMore);
        assertFalse(hasMore,"false");

        //- And Country_name are; Australia,China,India,Japan,Malaysia,Singapore
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

      //  String[] expectedCountry = {"Australia","China","India","Japan","Malaysia","Singapore"};

        ArrayList<String> country = new ArrayList<>(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));
        System.out.println("country = " + country);

        assertEquals(countryNames,country);

    }

    @Test
    public void test3WithJsonPath(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);

        JsonPath jsonPath = response.jsonPath();

        //- And all regions_id is 3
        List<Integer> allRegionsID = jsonPath.getList("items.region_id");
        System.out.println("allRegionsID = " + allRegionsID);

        for (int regionID : allRegionsID) {
            assertEquals(regionID,3);
        }

        //- And count is 6
        int count = jsonPath.getInt("count");
        assertEquals(count,6);

        //- And hasMore is false
        String hasMore = jsonPath.getString("hasMore");
        System.out.println("hasMore = " + hasMore);
        assertEquals(hasMore,"false");

        //- And Country_name are; Australia,China,India,Japan,Malaysia,Singapore
        List<String> countryNames = jsonPath.getList("items.country_name");
        System.out.println("countryNames = " + countryNames);

        List<String> expectedCountry = new ArrayList<>();
        expectedCountry.add("Australia");
        expectedCountry.add("China");
        expectedCountry.add("India");
        expectedCountry.add("Japan");
        expectedCountry.add("Malaysia");
        expectedCountry.add("Singapore");
        System.out.println("expectedCountry = " + expectedCountry);

        assertEquals(countryNames, expectedCountry);

    }
    @Test
    public void test3WithHamcrestMatchers(){
        //Q3 with hamcrest matchers
    given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType(equalTo("application/json"))
            .and().assertThat().body("items.region_id",hasItem(3),
                    "count",equalTo(6),
                    "hasMore",equalTo(false),
                "items.country_name",hasItems("Australia","China","India","Japan","Malaysia","Singapore"));
    }
}
