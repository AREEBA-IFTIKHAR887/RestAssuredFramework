package Testcases;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import POJO.Users;
import Payloads.Payload;
import Routes.Routes;
import Utils.RestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UserTests extends BaseClass{

    private Map<String, Object> pathParams;

    @BeforeMethod
    public void setUp() {
        pathParams = new HashMap<>();
        pathParams.put("id", configReader.getIntProperty("userID"));
    }
    @Test
    public void testGetAllUsers() {
        Response response = RestUtils.performGet(Routes.GET_ALL_Users);
        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("size()", greaterThan(0));
    }
//Get single User
    @Test
    public void testSingleUser() {
        Response response = RestUtils.performGet(Routes.Get_Single_User, pathParams);
        response.then()
                .statusCode(200)
                .body("id", equalTo(pathParams.get("id")));
    }
    //Create New User
    @Test
    public void testCreateUser() {
        Users newUser = Payload.usersPayload();
        Response response = RestUtils.performPost(Routes.Create_User, newUser);
        response.then()
                .statusCode(200)
                .body("id", notNullValue());
    }


    //Update User
    @Test
    public void testUpdateUser() {
        int userId = configReader.getIntProperty("userID");
        Users updatePayload = Payload.usersPayload();
        pathParams.put("id", userId);
        Response response = RestUtils.performPut(Routes.Update_User, updatePayload, pathParams);
        response.then()
                .statusCode(200)
                .body("username", equalTo(updatePayload.getUsername()));
    }
    //Delete User
    @Test
    public void testDeleteUser() {
        Response response = RestUtils.performDelete(Routes.Delete_User, pathParams);
        response.then().statusCode(200);
    }

}
