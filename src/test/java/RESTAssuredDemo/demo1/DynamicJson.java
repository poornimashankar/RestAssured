package RESTAssuredDemo.demo1;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.*;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

@Test(dataProvider = "BooksData")
public class DynamicJson {
	public void addBook(String bookName, String bookId) {
		RestAssured.baseURI = "http://216.10.245.166";

		String response = given().log().all().header("Content-Type", "application/json")
				.body(getJsonfile(bookName, bookId)).when().post("Library/Addbook.php").then().assertThat()
				.statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(response);
		String id = js.getString("ID");
		System.out.println(id);
	}

	public static String getJsonfile(String bookName, String bookId) {

		String file = "{ \r\n"
				+ "\r\n"
				+ " \r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\", \r\n"
				+ "\r\n"
				+ "\"isbn\":\""+bookName+"\", \r\n"
				+ "\r\n"
				+ "\"aisle\":\""+bookId+"\", \r\n"
				+ "\r\n"
				+ "\"author\":\"John foe\" \r\n"
				+ "\r\n"
				+ "} ";
		return file;
	}

	@DataProvider(name = "BooksData")
	public Object[] getListOfData() {
		Object[][] obj = { { "abc", "123" }, { "def", "456" }, { "xyz", "990" } };
		return obj;

	}

}
