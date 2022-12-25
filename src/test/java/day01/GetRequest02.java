package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.regex.Matcher;

import static io.restassured.RestAssured.given;

public class GetRequest02 {
    @Test
    public void test01() {
        //Set the url
        String url = " https://reqres.in/api/users";
        //Set the Request and Get the Response
        Response response = given().when().get(url);
        //Header Test
        response.then().assertThat().
                statusLine("HTTP/1.1 200 OK").
                statusCode(200).
                contentType(ContentType.JSON);

        //Body Test
        //id'si 1 olan datalarinin asagidaki gibi old. test ediniz
        //"email": "george.bluth@reqres.in",
        //            "first_name": "George",
        //            "last_name": "Bluth",

        //Matcher Class ile
        response.then().body("data[0].email",Matchers.equalTo("george.bluth@reqres.in"),
               "data[0].first_name",Matchers.equalTo("George"),
                "data[0].last_name",Matchers.equalTo("Bluth"));


    }
}
