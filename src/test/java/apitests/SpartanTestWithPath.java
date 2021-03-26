package apitests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithPath {

    @BeforeClass
    public void beforeClass(){
        baseURI = "http://54.197.122.229:8000";
    }
      /*
   Given accept type is json
   And path param id is 10
   When user sends a get request to "api/spartans/{id}"
   Then status code is 200
   And content-type is "application/json;charset=UTF-8"
   And response payload values match the following:
           id is 10,
           name is "Lorenza",
           gender is "Female",
           phone is 3312820936
    */
    @Test
    public void getOneSpartan_path(){

        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json");

       // response.prettyPrint();

        //print each key value in the json body/payload
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        //save json key values
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //verify one by one
        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone,3312820936l); //phone long olduğu için rakamın sonuna l koyduk
    }

    @Test
    public void getAllSpartanWithPath(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(),200);
        //verify content type
        assertEquals(response.getHeader("Content-Type"),"application/json");
        //getHeader(string)=header(string)

        System.out.println(response.path("id[0]").toString());

        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String firstName = response.path("name[0]");
        System.out.println("firstName = " + firstName);

        String lastName = response.path("name[-1]");//listenin en sonundaki ismi getirir
        System.out.println("lastName = " + lastName);

        String lastSecondName = response.path("name[-2]");//sondan önceki kişinin adını getirir
        System.out.println("lastSecondName = " + lastSecondName);

        int lastId = response.path("id[-1]");// -1 last elementtir. Bununla enson kişiy ulaşabiliriz
        System.out.println("lastId = " + lastId);

        //print all names of spartans
        List<String> names = response.path("name"); //tüm isimleri almak için liste koyduk
        System.out.println("names = " + names);

        //print all phone of spartans //iter print one by one
        List<Object> phones = response.path("phone"); //long yazdım hata verdi. onun yerine Object yazdım
        for (Object phone : phones) {
            System.out.println(phone);

        }
        System.out.println("phones = " + phones);//print inside one array



    }
}
