package RESTAssuredDemo.demo1;

import io.restassured.path.json.*;
import junit.framework.Assert;

public class parseJsonDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsonFileResponse = "{\r\n" + "\r\n" + "\"dashboard\": {\r\n" + "\r\n" + "\"purchaseAmount\": 910,\r\n"
				+ "\r\n" + "\"website\": \"rahulshettyacademy.com\"\r\n" + "\r\n" + "},\r\n" + "\r\n"
				+ "\"courses\": [\r\n" + "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"Selenium Python\",\r\n" + "\r\n"
				+ "\"price\": 50,\r\n" + "\r\n" + "\"copies\": 6\r\n" + "\r\n" + "},\r\n" + "\r\n" + "{\r\n" + "\r\n"
				+ "\"title\": \"Cypress\",\r\n" + "\r\n" + "\"price\": 40,\r\n" + "\r\n" + "\"copies\": 4\r\n" + "\r\n"
				+ "},\r\n" + "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"RPA\",\r\n" + "\r\n" + "\"price\": 45,\r\n"
				+ "\r\n" + "\"copies\": 10\r\n" + "\r\n" + "}\r\n" + "\r\n" + "]\r\n" + "\r\n" + "}\r\n" + "\r\n"
				+ "\r\n" + "";
		JsonPath js = new JsonPath(jsonFileResponse);
		int noOfCourses = js.getInt("courses.size()");
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		String titleOfFirstCourse = js.get("courses[0].title");
		int totalPurchaseAmount = 0;
		System.out.println("No Of Courses" + noOfCourses);
		System.out.println("totalAmount" + totalAmount);
		System.out.println("Title of first course" + titleOfFirstCourse);
		for (int i = 0; i < noOfCourses; i++) {

			String title = js.get("courses[" + i + "].title");
			int price = js.getInt("courses[" + i + "].price");
			System.out.println(" Title  " + title + " Price " + price);
			String copies = null;
			if (title.equalsIgnoreCase("RPA")) {
				copies = js.getString("courses[" + i + "].copies");
			}
			System.out.println("copies" + copies);

		}
		for (int i = 0; i < noOfCourses; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			totalPurchaseAmount = totalPurchaseAmount+ (price * copies);

		}
		System.out.println(totalPurchaseAmount);
		

	}

}
