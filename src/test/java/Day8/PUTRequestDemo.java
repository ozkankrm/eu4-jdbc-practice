package Day8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class PUTRequestDemo {
    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void test1(){
        //create one map for the put request json body
        Map<String,Object>  putRequestMap = new HashMap<>();
        putRequestMap.put("name","PUTName");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 1231231231l);

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id",90)
                .body(putRequestMap). //aynı zamanda javayı json a çevirir
        when()
                .put("/api/spartans/{id}")
        .then().log().all()
                .assertThat().statusCode(204);

        //send get request to verify body

        given().log().all().accept(ContentType.JSON)
                .and()
                .pathParam("id",90)
                .when().get("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType("application/json")
                .assertThat().body("name",equalTo("PUTName"),
                                            "gender",equalTo("Male"),
                                           "phone",equalTo(1231231231l));

    }

    @Test
    public void PatchTest(){

        //create one map for the put request json body
        Map<String,Object>  patchRequestMap = new HashMap<>();
        patchRequestMap.put("name","JT");

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id",90)
                .body(patchRequestMap). //aynı zamanda javayı json a çevirir
                when()
                .patch("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);




    }
}
