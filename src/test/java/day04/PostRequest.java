package day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.CountryPost;
import utilities.GMIBankBaseUrl;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class PostRequest extends GMIBankBaseUrl {

     /*
    http://www.gmibank.com/api/tp-customers adresine yeni bir ulke ekleyin

     */

    @Test
    public void test01() {
        //Set the Url
        spec01.pathParams("first","tp-countries");
        //Set the Expected Data
        CountryPost countryPost = new CountryPost("Batch81");
        System.out.println("countryPost = " + countryPost);
        //Send the Request and Get the Response
       Response response = given().spec(spec01).
                headers("Authorization","Bearer " + generateToken()).
                contentType(ContentType.JSON).body(countryPost).
                when().post("/{first}");

       response.prettyPrint();
        //Do Assertion
       CountryPost actualData = response.as(CountryPost.class);
        System.out.println("actualData = " + actualData);
        assertEquals(countryPost.getName(),actualData.getName());
    }
}
