package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrApiWithPath {
    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void getCountriesWithPath(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);

        //print limit value
        System.out.println(response.path("limit").toString());

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        String firstCounrtyId = response.path("items.country_id[0]");
        System.out.println("firstCounrtyId = " + firstCounrtyId);

        //second country name
        String secondCountryName = response.path("items.country_name[2]");
        System.out.println("secondCountryName = " + secondCountryName);

        //
        String link2 = response.path("items.links[2].href[0]");
        System.out.println("link2 = " + link2);
        //  System.out.println(response.path("items.links[2].href").toString());

        //get all counrty names
        List<String> countryNames = response.path("items.country_name");

        System.out.println("countryNames = " + countryNames);

        //assert that all regions id are equal to 2
        List<Integer> regionId = response.path("items.region_id");

        for (int regionIDs : regionId) {

          assertEquals(regionIDs, 2);

        }
    }
}
