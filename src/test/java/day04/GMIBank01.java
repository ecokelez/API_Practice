package day04;

import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.Customer;
import utilities.GMIBankBaseUrl;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GMIBank01 extends GMIBankBaseUrl {

     /*
    http://www.gmibank.com/api/tp-customers endpoint'ine request gonderin
    1- Tum Customer bilgilerini ekrana yazdirin
    2-  Tum Customer SSN'lerini ekrana yazdirin
    3-  Tum Customer SSN'lerini text dosyasi olarak kaydedin
    4-  Olusturdugumuz text dosyasindan SSN'leri okuyarak
    "531-95-8437", "849-43-2360","123-34-3434" SSN2lerinin old. dogrulayin
     */

    @Test
    public void test01() throws IOException {
        Customer[] customers;

        //Set the Url
        spec01.pathParams("first","tp-customers");
        //Set the expected Data
        //Send the Request and Get the Response
        Response response = given().spec(spec01).
                headers("Authorization","Bearer " + generateToken()).
                when().get("/{first}");

        response.prettyPrint();

        //Do Assertion
        ObjectMapper obj = new ObjectMapper();
        customers = obj.readValue(response.asString(), Customer[].class);
        //1- Tum Customer bilgilerini ekrana yazdirin
        for (int i = 0; i < customers.length; i++) {
            System.out.println(customers[i]);
             // 2- Tum Customer SSN'lerini ekrana yazdirin
            for (int i1 = 0; i1 <customers.length ; i1++) {
                System.out.println(customers[i1]);
            }
            //3-  Tum Customer SSN'lerini text dosyasi olarak kaydedin
            //4-  Olusturdugumuz text dosyasindan SSN'leri okuyarak
            // "531-95-8437", "849-43-2360","123-34-3434" SSN2lerinin old. dogrulayin

        }

    }
}
