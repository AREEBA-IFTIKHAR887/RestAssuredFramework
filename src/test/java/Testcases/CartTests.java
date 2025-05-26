package Testcases;

import POJO.Cart;
import POJO.Product;
import Payloads.Payload;
import Routes.Routes;
import Utils.RestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class CartTests extends BaseClass{
    private Map<String, Object> pathParams;

    @BeforeMethod
    public void setUp() {
        pathParams = new HashMap<>();
        pathParams.put("id", configReader.getIntProperty("cartID"));
    }
    //Get All carts
    @Test
    public void testGetAllCarts() {
        Response response = RestUtils.performGet(Routes.GET_ALL_Carts);
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0));
    }
    // Create a new cart
    @Test
    public void  testCreateCart(){
        int userId=configReader.getIntProperty("userID");
        Cart newCart= Payload.cartPayload(userId);
        Response response = RestUtils.performPost(Routes.Create_Cart,newCart);
        response.then()
                .statusCode(200)
                .body("userId",notNullValue())
                .body("products.size()",greaterThan(0));
    }
    //Get single Cart

    @Test
    public void testSingleCart() {
        Response response = RestUtils.performGet(Routes.Get_Single_Cart, pathParams);
        response.then()
                .statusCode(200)
                .body("id", equalTo(pathParams.get("id")));
    }
    // Update an existing cart
    @Test
    public void testUpdateCart() {
        int userId=configReader.getIntProperty("userID");
        Cart updcartPayload = Payload.cartPayload(userId);
        Response response = RestUtils.performPut(Routes.Update_Cart, updcartPayload, pathParams);
        response.then()
                .statusCode(200)
                .body("products.size()",equalTo(1));
    }
    // Delete the cart
    @Test
    public void testDeleteCart() {
        Response response = RestUtils.performDelete(Routes.Delete_Cart, pathParams);
        response.then().statusCode(200);
    }


}
