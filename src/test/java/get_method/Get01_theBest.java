package get_method;

import baseurl.Herokuappbaseurlemrah;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo_pojo.BookingDatesPojo;
import pojo_pojo.InfoPojo;

import static io.restassured.RestAssured.*;


public class Get01_theBest extends Herokuappbaseurlemrah {

    /* Test Case:

        Given
            https://restful-booker.herokuapp.com/booking/11
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like
              {
                    "firstname": "Emrah",
                    "lastname": "Selek",
                    "totalprice": 780,
                    "depositpaid": true,
                    "bookingdates": {
                                "checkin": "2021-09-21",
                                "checkout": "2021-09-25"
                               },
                    "additionalneeds": "Dinner"
                }
     */

    @Test
    public void testtoget(){

        //1.step: set the url
        spec.pathParams("first","booking","second",11);


        //2.step: Expected data
        BookingDatesPojo bookingdates = new BookingDatesPojo("2021-09-21","2021-09-25");
        InfoPojo expectedData = new InfoPojo("Orcun","Selek",780,true,bookingdates,"Dinner");
        System.out.println("expected Data:" + expectedData);

        //3.step: "Hey, API. Listen to me. Now I SEND to you my REQUEST, and I want to GET the RESPONSE."
        Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.step: Do Assertions
        InfoPojo actualData = response.as(InfoPojo.class);
        // Test : Actual data  = Expected Data -----------> Pass  .. ok
        // Test : Actual data != Expected Data -----------> Fail

        System.out.println("actual data: " +actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        Assert.assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());













    }






}
