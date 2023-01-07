package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.GMIBankBaseUrl;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest07 extends GMIBankBaseUrl {
      /*
   https://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın

   "firstName": "Melva",
   "lastName": "Bernhard",
   "email": "chas.kuhlman@yahoo.com"
   "zipCode": "40207"
   "country.name": "San Jose"
   "user.login": "delilah.metz"
    */


    @Test
    public void test01() {
        //Set the Url
        spec01.pathParams("first","tp-customers","second",110472);
        //Set the Expected Data
        //Send the Request and Get the Response
        Response response = given().spec(spec01).headers("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();
        //Matcher  ile dogrula
        response.then().assertThat().body("firstName", equalTo("Melva"),
                "lastName",equalTo("Bernhard"),
                "email",equalTo("chas.kuhlman@yahoo.com"),
                "zipCode",equalTo("40207"),
                "country.name",equalTo("San Jose"),
                "user.login",equalTo("delilah.metz"));

        //JsonPath ile dogrula;
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Melva",jsonPath.getString("firstName"));
        assertEquals("Bernhard",jsonPath.getString("lastName"));
        assertEquals("chas.kuhlman@yahoo.com",jsonPath.getString("email"));
        assertEquals("40207",jsonPath.getString("zipCode"));
        assertEquals("San Jose",jsonPath.getString("country.name"));
        assertEquals("delilah.metz",jsonPath.getString("user.login"));

    }
}
