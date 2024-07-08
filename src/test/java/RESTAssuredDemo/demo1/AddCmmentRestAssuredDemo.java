package RESTAssuredDemo.demo1;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.filter.session.SessionFilter;
import java.io.File;

public class AddCmmentRestAssuredDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://localhost:8080";
		SessionFilter session  =  new SessionFilter();
	String response =	given().header("Content-Type","application/json").body("{ \"username\": \"abc\", \"password\": \"abc@123\" }").log().all().filter(session).when().post(" http://localhost:8080/rest/auth/1/session").then().extract().response().asString();
	
	given().pathParam("key", "10000").header("Content-Type","application/json").log().all().body("{\r\n"
				+ "    \"body\": \"My first comment.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201);

	
	given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("key","10000").header("Content-Type","multipart/form-data").multiPart("file",new File("note.txt")).when().post("rest/api/2/issue/{key}/attachments").then().assertThat().statusCode(200);
	}

}
