package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*; //burda yıldız koyunca steplerde RestAAssured yazmaya gerek kalmıyor

public class SimpleGetRequest {
    //http:// eklemek gerekti.
    String hrurl = "http://54.197.122.229:1000/ords/hr/regions";

    @Test
    public void test1(){

       // rest assured library-->api methodlarını barındırır

        Response response = RestAssured.get(hrurl);

        //status codunu verir. ve check et başarılı mı değil mi diye//200/400 vs gibi
        //statusCode() method-->return status code
        System.out.println(response.statusCode());

        //print the json body  (print içine yazmaya gerek yok, kendisi yazıyor)
        //prettyPrint() method
        response.prettyPrint();


        }

     /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then response status code must be 200
        and body is json format
         */

    String hrurl1 = "http://54.197.122.229:1000/ords/hr/regions";
        @Test
        public void test2(){
            Response response = RestAssured.given().accept(ContentType.JSON)
                                .when().get(hrurl1);

            //verify response status code is 200
            Assert.assertEquals(response.statusCode(), 200);

            //verify content-type is application/json
            System.out.println("response.contentType() = " + response.contentType());
           Assert.assertEquals(response.contentType(),"application/json");
        }

        @Test
        public void test3(){
                RestAssured.given().accept(ContentType.JSON) //header da accepti belirledin
                            .when().get(hrurl1).then()  // get requesti ve url girdin
                            .assertThat().statusCode(200) //verify status code is 2000
                            .and().contentType("application/json"); //verify response headerdeki content-type is application/json
        }

        /*
        Given accept type is json
        When uer sends get request to regions/2
        Then response status code must be 200
        and body is json format
        and response body contains Amricas
         */

    @Test
    public void test4(){
        //import static io.restassured.RestAssured.*; //burda yıldız koyunca steplerde RestAAssured yazmaya gerek kalmıyor

        Response response = given().accept(ContentType.JSON)
                .when().get(hrurl + "/2");
        //yukardaki iki adım request bölümüdür


        //aşağıdaki adımlar response bölümüdür
        //verify status code
        Assert.assertEquals(response.statusCode(),200);

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json");

        //verify body contents Americas
        Assert.assertTrue(response.body().asString().contains("Americas"));


    }


}
