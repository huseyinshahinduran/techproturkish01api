package techproedturkish01.techproedturkish01api;
import static io.restassured.RestAssured.*;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class PostRequest04 extends TestBase01{
	
									
										/*
											POST Scenario:
									Accept type Json olsun
									When
									POST request yolladigimda
									1) https://restful-booker.herokuapp.com/booking
									2) Request Body 
									{
									"firstname": "Suleyman",
									"lastname": "Alptekin",
									"totalprice": 123,
									"depositpaid": true,
									"bookingdates": {
										"checkin": "2020-05-02",
										"checkout": "2020-05-05"
									},
									"additionalneeds": "Wifi"
									}
									Then 
									Status Code 200 olmali
									Response Body, Request Body ile ayni olmali.
									*/
	
	/*
	 * POJO: Plain Old Java Object
	 */
	
	@Test
	public void post01() {
		
		BookingDates bookingDates=new BookingDates("2020-05-02","2020-05-05");
		Booking booking=new Booking("Huseyn", "Shahinduran", 5842, true, bookingDates, "Wifi");
		

		Response response= given().
							contentType(ContentType.JSON).
							spec(spec01).
							auth().
							basic("admin", "password123").
							body(booking).
							when().
							post("/booking");
		
		response.prettyPrint();
		
		response.
		then().
		assertThat().
		statusCode(200);
	
	JsonPath json=response.jsonPath();
	SoftAssert softAssert=new SoftAssert();
	
	//firstname assertion
	softAssert.assertEquals(json.getString("booking.firstname"),"Suleyman");
	
	//lastname assertion
	softAssert.assertEquals(json.getString("booking.lastname"),"Shahinduran");
		
	//totalprice assertion
	softAssert.assertEquals(json.getInt("booking.totalprice"),5842);
	
	//depositpaid assertion
	softAssert.assertEquals(json.getBoolean("booking.depositpaid"),true);
	
	//checkin assertion
	softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),"2020-05-02");
	
	//checkout assertion
	softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),"2020-05-05");
		
	//additionalneeds assertion
	softAssert.assertEquals(json.getString("booking.additionalneeds"),"Wifi");	
		
		softAssert.assertAll();
		
	}
	
	
	
	
	
	
	
	

}
