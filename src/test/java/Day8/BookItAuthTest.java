package Day8;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class BookItAuthTest {

    @BeforeClass
    public void before(){
        baseURI = "http://cybertek-reservation-api-qa2.herokuapp.com";
    }

    @Test
    public void getAllCampuses(){

        //postman ile api dokumana bağlı olarak token aldık ve burda string e atadık
        //email ve password ile girdikten sonra accesstokeni aldık
        String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1NyIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.a_N9URDBPGOMcDdEVoaMHsJtk3jOnig0v0SCtSWcsGE";

        //aldığımız tokeni request headera key-value ile atadık (Authorization-accesstoken)
        Response response = given().header("Authorization",accessToken).
                when().get("/api/campuses");

        response.prettyPrint();
        System.out.println(response.statusCode());
    }

}


