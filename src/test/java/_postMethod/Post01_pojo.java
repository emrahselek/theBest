package _postMethod;

import baseurl.BaseUrl_HerOkuApp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo_pojo.DagittinBiziPojo;
import pojo_pojo.InnerPojo;
import pojo_pojo.OuterPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01_pojo extends BaseUrl_HerOkuApp {

    /**

     Given
        https://restful-booker.herokuapp.com/booking
             {
                 "firstname": "Emrah",
                 "lastname": "Selek",
                 "totalprice": 1000,
                 "depositpaid": true,
                 "bookingdates": {
                         "checkin": "2021-10-01",
                         "checkout": "2021-10-05"
                         }
                 "additionalneeds": "Dinner, smoothie, openbar"
             }
     When
        I send POST Request to the Url
     Then
        Status code is 200
     And
        response body should be like;
         {
             "firstname": "Emrah",
             "lastname": "Selek",
             "totalprice": 1000,
             "depositpaid": true,
             "bookingdates": {
                 "checkin": "2021-10-01",
                 "checkout": "2021-10-05"
                 }
            "additionalneeds": "Dinner, smoothie, openbar"
         }
     */

    @Test
    public void postWithPojo02() {

        //1.Step : Set the URL
        spec.pathParam("first", "booking");

        //2.step : Set request body
        InnerPojo bookingDates = new InnerPojo("2021-10-01", "2021-10-05");
        OuterPojo requestBody = new OuterPojo("Emrah", "Selek", 1000, true, bookingDates, "Dinner, smoothie, openbar");
        System.out.println(requestBody);

        //3.step : send request and get response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        DagittinBiziPojo actualData = response.as(DagittinBiziPojo.class);
        System.out.println(actualData);

        //4.Step: Do assertion
        assertEquals(200, response.getStatusCode());

        assertEquals(requestBody.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(requestBody.getLastname(), actualData.getBooking().getLastname());
        assertEquals(requestBody.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(requestBody.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(requestBody.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());
    }

}