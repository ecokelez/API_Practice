package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest01 {

       /*
      http://dummy.restapiexample.com/api/v1/employees  url'ine
      GET request'i yolladigimda gelen response'un
      status kodunun 200 ve content type'inin "application/json"
      ve employees sayisinin 24
      ve employee'lerden birinin "Ashton Cox"
      ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
      */


    @Test
    public void test01() {

        String url = "http://dummy.restapiexample.com/api/v1/employees";
        //given().when().get(url); ==> Enpoint'e gondermek icin Request olusturmus olduk
         Response response = given().when().get(url);
        // Response response => api taraf. bana donen response (cevap)
         response.prettyPrint();
        // status kodunun 200 ve content type'inin "application/json
         response.then().assertThat().contentType(ContentType.JSON).statusCode(200);

         //employees sayisinin 24
        //employee'lerden birinin "Ashton Cox"
         response.then().assertThat().
                 body("data", hasSize(24),
                         "data.employee_name",hasItem("Ashton Cox"),
                         "data.employee_age",hasItems(21,61,23));

         //==> Matchers.hasSize(): Datanın size'ını doğrulamak için kullanılır.
        //==> Matchers.hasItem(): Girilen tek bir data'yı doğrulamak için kullanılır.
        //==> Matchers.hasItems(): Girilen birden fazla datayı doğrulamak için kullanılır

    }
}
