package day02;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest03 extends Authentication {
    @Test
    public void test01() {
        String url = "https://www.gmibank.com/api/tp-customers/114351";

        Response response  = given().header("Authorization","Bearer " + generateToken()).
                             when().get(url);
        response.prettyPrint();

        //Matcher Class ile musteri bilgilerini dogrulayin
        response.then().assertThat().body("firstName", Matchers.equalTo("Della"),
                "lastName",Matchers.equalTo("Heaney"),
                "email",Matchers.equalTo("ricardo.larkin@yahoo.com"),
                "mobilePhoneNumber",Matchers.equalTo("123-456-7893"),
                "accounts[0].balance",Matchers.equalTo(69700),
                "accounts[0].accountType",Matchers.equalTo("CREDIT_CARD"),
                "accounts[1].balance",Matchers.equalTo(11190),
                "accounts[1].accountType",Matchers.equalTo("CHECKING"));

        //JsonPath  ile musteri bilgilerini dogrulayin
        JsonPath json = response.jsonPath();

        assertEquals("Della",json.getString("firstName"));
        assertEquals("Heaney",json.getString("lastName"));
        assertEquals("ricardo.larkin@yahoo.com",json.getString("email"));
        assertEquals("123-456-7893",json.getString("mobilePhoneNumber"));
        assertEquals(69700,json.getInt("accounts[0].balance"));
        assertEquals("CREDIT_CARD",json.getString("accounts[0].accountType"));
        assertEquals(11190,json.getInt("accounts[1].balance"));
        assertEquals("CHECKING",json.getString("accounts[1].accountType"));
    }
}
