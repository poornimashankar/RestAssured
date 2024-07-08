package RESTAssuredDemo.demo1;

import pojo.GetCourse;
import pojo.WebAutomation;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.*;
import java.util.List;
import pojo.Api;

public class OAuthDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParams("scope", "trust")
				.formParams("grant_type", "client_credentials").when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String accessToken = js.getString("access_token");
//String response1 = given().queryParam("access_token", accessToken).when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
//System.out.println(response1);

		GetCourse gc = given().queryParam("access_token", accessToken).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		System.out.println(gc.getLinkedIn());

		List<Api> apis = gc.getCourses().getApi();
		String price = null;
		for(int i=0;i<apis.size();i++) { 
			if(apis.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){ 
				price = apis.get(i).getPrice();
			}
			
		}
		System.out.println(price);
		List<WebAutomation> wa =  gc.getCourses().getWebAutomation();
		int num =  wa.size();
		
	}

}
