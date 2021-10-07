package _get_method;

import baseurl.BaseUrl_HerOkuApp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo_pojo.InnerPojo;
import pojo_pojo.OuterPojo;

import static io.restassured.RestAssured.*;

public class Get02_pojo extends BaseUrl_HerOkuApp {

    /* Test Case:

        Given
            https://restful-booker.herokuapp.com/booking/100001
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like
              {
                    "firstname": "Emrah",
                    "lastname": "Selek",
                    "totalprice": 1000,
                    "depositpaid": true,
                    "bookingdates": {
                                "checkin": "2021-10-01",
                                "checkout": "2021-10-05"
                               },
                    "additionalneeds": "Dinner, smoothie, openbar"
                }
     */

    @Test
    public void test(){

        //1.step: set the url
        spec.pathParams("first","booking","second",11);

        //2.step: Expected data
        InnerPojo bookingdates = new InnerPojo("2021-09-21","2021-09-25");
        OuterPojo expectedData = new OuterPojo("Orcun","Selek",780,true,bookingdates,"Dinner");
        System.out.println("expected Data:" + expectedData);

        //3.step: "Hey, API. Listen to me. Now I SEND to you my REQUEST, and I want to GET the RESPONSE."
        Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.step: Do Assertions
        OuterPojo actualData = response.as(OuterPojo.class);
        // Test : Actual data  = Expected Data -----------> Pass  .. ok
        // Test : Actual data != Expected Data -----------> Fail

        System.out.println("actual data: " +actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        Assert.assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());


    }

}
