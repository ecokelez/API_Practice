package day01;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest01 {

    @Test
    public void test01(){
        String url = "https://restful-booker.herokuapp.com/booking/";
         //given().when().get(url); ==> Enpoint'e gondermek icin Request olusturmus olduk
        Response response = given().when().get(url);
        // Response response => api taraf. bana donen response (cevap)
        //Response response = given().auth().basic("username", "password" ).when().get(url)
        // basic auth ile request göndermek için

       // response.prettyPrint();  //reponse'daki body yazdirir
       // response.prettyPeek();   // response'daki herseyi  yazdirir
       // response.peek();         //header'ı ve string olarak datayı verir

        response.print();         //string olarak datayı verir
        // => [{"bookingid":2450},{"bookingid":3264},{"bookingid":1410},....]

        System.out.println(response.statusCode());     //200
        System.out.println(response.statusLine());    //HTTP/1.1 200 OK
        System.out.println(response.contentType());   //application/json; charset=utf-8

        // 1) JUnit Assert leri ile API testi yapabiliriz.
         assertEquals("Status Code hatali","200",response.getStatusCode());
         assertEquals("HTTP/1.1 200 OK",response.statusLine());
         assertEquals("application/json; charset=utf-8",response.contentType());

        // 2) assertThat ile   ( Hard Assert )
        response.then().assertThat().  //==> assertThat()'i sildigimizde de yine calisir
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                contentType("application/json; charset=utf-8");

    }

}
