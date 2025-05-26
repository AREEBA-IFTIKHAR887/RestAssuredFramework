package Testcases;

import POJO.Product;
import Payloads.Payload;
import Routes.Routes; // Assuming this is where your endpoint routes are defined
import Utils.RestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductTests extends BaseClass {

    private Map<String, Object> pathParams;

    @BeforeMethod
    public void setUp() {
        pathParams = new HashMap<>();
        pathParams.put("id", configReader.getIntProperty("productId"));
    }

    //Get All products
    @Test
    public void testGetAllProducts() {
        Response response = RestUtils.performGet(Routes.GET_ALL_Products);
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0));
    }

    // Create a new product
    @Test
    public void testCreateNewProduct() {
        Product newProduct = Payload.productPayload();
        Response response = RestUtils.performPost(Routes.Create_Product, newProduct);
        int productId = response.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("title", equalTo(newProduct.getTitle()))
                .extract().jsonPath().getInt("id");

    }

    // Update an existing product
    @Test
    public void testUpdateProduct() {
        Product updatedPayload = Payload.productPayload();
        Response response = RestUtils.performPut(Routes.Update_Product, updatedPayload, pathParams);
        response.then()
                .statusCode(200)
                .body("title", equalTo(updatedPayload.getTitle()));
    }
    // Delete the product
    @Test
    public void testDeleteProduct() {
        Response response = RestUtils.performDelete(Routes.Delete_Product, pathParams);
        response.then().statusCode(200);
    }
}
