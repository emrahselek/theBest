package get_method;

import baseurl.BaseUrl_HerOkuApp;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class Get01 extends BaseUrl_HerOkuApp {

    /** ---> Test case:
     Given
        https://restful-booker.herokuapp.com/booking/1001
     When
        User send a GET Request to the url
     Then
        HTTP Status code should be 404
     And
         Status Line should be HTTP/1.1 404 Not Found
     And
        Response body contains "Not Found"
     And
        Response body does not contain "TechProEd"
     And
         Server is "Cowboy"
     */

    @Test
    public void get02(){

        //1.Step: Set the URL
        spec.pathParams("first","booking", "second", 1001);

        //2.Step: Set the expected data.

        //3.Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();//Not Found

        //4.Step:
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        //System.out.println(response.asString().contains("Not Found")); //return boolean (true)

        //assertTrue(true) ==> Green tick            assertTrue(false) ==> Red cross
        assertTrue(response.asString().contains("Not Found"));//If response.asString().contains("Not Found") returns true, you will get green tick

        //assertFalse(false) ==> Green tick            assertFalse(true) ==> Red cross
        assertFalse(response.asString().contains("TechProEd"));//If response.asString().contains("TechProEd") returns false, you will get green tick

        //Expected Data comes from test case, Actual Data comes from API
        //assertEquals() returns true(test passes) if the arguments match
        assertEquals("Server name is not matching","Cowboyxx", response.getHeader("Server"));

    }


}