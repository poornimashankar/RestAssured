package RESTAssuredDemo.demo1;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import static io.restassured.RestAssured.given;
import pojo.LoginRequest;
import pojo.LoginResponse;
import java.io.File;
import io.restassured.path.json.*;
import pojo.Orders;
import pojo.OrderDetails;
import java.util.ArrayList;
import java.util.List;

public class EcommerceAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("abc@gmail.com");
		loginRequest.setUserPassword("Hello@123");
		RequestSpecification reqLogin = given().log().all().spec(reqSpec).body(loginRequest);
		LoginResponse loginResponse = reqLogin.when().post("api/ecom/auth/login").then().log().all().extract()
				.response().as(LoginResponse.class);
		String token = loginResponse.getToken();
		String userId = loginResponse.getUserId();

		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();
		RequestSpecification addProductReq = given().log().all().spec(addProductBaseReq).param("productName", "Laptop")
				.param("productAddedBy", userId).param("productCategory", "fashion")
				.param("productSubCategory", "shirts").param("productPrice", "11500")
				.param("productDescription", "Addias Originals").param("productFor", "women")
				.multiPart("productImage", new File("C:\\Users\\poorn\\Postman\\files\\image.jpg"));
		String addProductRes = addProductReq.when().post("api/ecom/product/add-product").then().log().all().extract()
				.response().asString();
		JsonPath jPath = new JsonPath(addProductRes);
		String productId = jPath.get("productId");
		String country = "Canada";

		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).addHeader("Authorization", token).build();
		Orders orders = new Orders();
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setCountry(country);
		orderDetails.setProductOrderedId(productId);
		List<OrderDetails> orderList = new ArrayList<OrderDetails>();
		orderList.add(orderDetails);
		orders.setOrders(orderList);

		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(orders);
		String responseCreateOrder = createOrderReq.when().post("api/ecom/order/create-order").then().log().all()
				.extract().response().asString();
		System.out.println(responseCreateOrder);

		RequestSpecification deleteOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).addHeader("Authorization", token).build();

		RequestSpecification deleteOrderReq = given().log().all().spec(deleteOrderBaseReq).pathParam("productId",
				productId);
		String deleteProductResponse = deleteOrderReq.when().delete("api/ecom/product/delete-product/{productId}")
				.then().log().all().extract().response().asString();
		JsonPath jsonPath = new JsonPath(deleteProductResponse);
		String msg = jsonPath.get("message");
		Assert.assertEquals("Product Deleted Successfully", msg);

	}
}