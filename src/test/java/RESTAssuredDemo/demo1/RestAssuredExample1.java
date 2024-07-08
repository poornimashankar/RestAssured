package RESTAssuredDemo.demo1;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import io.restassured.path.json.*;
import org.hamcrest.Matchers;


public class RestAssuredExample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI =  "https://rahulshettyacademy.com/";
	String response =	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{ \r\n"
				+ "\r\n"
				+ "  \"location\": { \r\n"
				+ "\r\n"
				+ "    \"lat\": -38.383494, \r\n"
				+ "\r\n"
				+ "    \"lng\": 33.427362 \r\n"
				+ "\r\n"
				+ "  }, \r\n"
				+ "\r\n"
				+ "  \"accuracy\": 50, \r\n"
				+ "\r\n"
				+ "  \"name\": \"Frontline house\", \r\n"
				+ "\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\", \r\n"
				+ "\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\", \r\n"
				+ "\r\n"
				+ "  \"types\": [ \r\n"
				+ "\r\n"
				+ "    \"shoe park\", \r\n"
				+ "\r\n"
				+ "    \"shop\" \r\n"
				+ "\r\n"
				+ "  ], \r\n"
				+ "\r\n"
				+ "  \"website\": \"http://google.com\", \r\n"
				+ "\r\n"
				+ "  \"language\": \"French-IN\" \r\n"
				+ "\r\n"
				+ "} \r\n"
				+ "\r\n"
				+ " ").when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",Matchers.equalTo("APP")).extract().response().asString();
	System.out.println(response);
	JsonPath js =  new JsonPath(response);
	String placeId = js.getString("place_id");
	String address = "98 Summer walk, USA";
	System.out.println(placeId);
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{ \r\n"
			+ "\r\n"
			+ "\"place_id\":\""+placeId+"\", \r\n"
			+ "\r\n"
			+ "\"address\":\""+address+"\", \r\n"
			+ "\r\n"
			+ "\"key\":\"qaclick123\" \r\n"
			+ "\r\n"
			+ "} ").when().put("/maps/api/place/update/json").then().assertThat().statusCode(200).body("msg", Matchers.equalTo("Address successfully updated"));
	String strPlaceResponse =given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId).when().get("/maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response().asString();
JsonPath js1 = new JsonPath(strPlaceResponse);
String address1 = js1.getString("address");
System.out.println(address1);

	}

	

}
