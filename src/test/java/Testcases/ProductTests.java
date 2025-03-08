package Testcases;

import POJO.Product;
import Payloads.Payload;
import Routes.Routes; // Assuming this is where your endpoint routes are defined
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Base64;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductTests extends BaseClass {

    @Test
    public void testGetAllProducts(){
        given()
                .when()
                .get(Routes.GET_ALL_Products) // Replace with the correct endpoint
                .then()
                .statusCode(200)  // Check for a 200 OK status code
                .body("size()", greaterThan(0));// Ensure the response has more than 0 products
    }

    //Create a new product
    @Test
    public void testcreateNewProduct(){
        Product newProduct= Payload.productPayload();
        int productId= given()
                            .contentType(ContentType.JSON)
                            .body(newProduct)
                            .when()
                            .post(Routes.Create_Product)
                            .then()
                             .log().body()
                            .statusCode(200)
                            .body("id",notNullValue())
                            .body("title",equalTo(newProduct.getTitle()))
                            .extract().jsonPath().getInt("id");       ;
    }
    //Update a product
    @Test
    public void updateProduct(){
        int ProductID=configReader.getIntProperty("productId");
        Product updatedPayload= Payload.productPayload();
        given()
                .pathParam("id",ProductID)
                .contentType(ContentType.JSON)
                .body(updatedPayload)
                .when()
                .put(Routes.Update_Product)
                .then()
                .statusCode(200)
                .body("title",equalTo(updatedPayload.getTitle()));
    }

    //Delete the product
    @Test
    public void deleteProduct(){
        int ProductID=configReader.getIntProperty("productId");
        given()
                .pathParam("id",ProductID)
                .when()
                .delete(Routes.Delete_Product)
                .then()
                
                .statusCode(200);
    }
}
