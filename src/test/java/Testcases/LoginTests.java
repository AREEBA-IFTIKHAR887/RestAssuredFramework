package Testcases;

import POJO.Login;
import Payloads.Payload;
import Routes.Routes;
import Utils.JsonUtils;
import Utils.RestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTests extends BaseClass{
    public static Map<String, Object> dataFromJsonFile;

    @Test
    public void testInvalidLogin() {
       Login newlogin = Payload.loginPayload();
        Response response = RestUtils.performPost(Routes.Auth_Login, newlogin);
        response.then()
                .statusCode(401)
                .body(equalTo("username or password is incorrect"));
    }
    @Test
    public void testSuccessFullLogin() {
            try{
                dataFromJsonFile = JsonUtils.getJsonDataAsMap("logindata.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        String username = (String) dataFromJsonFile.get("username");
        String password = (String) dataFromJsonFile.get("password");

        Login newlogin=new Login(username,password);
        Response response = RestUtils.performPost(Routes.Auth_Login, newlogin);
        response.then()
                .statusCode(200)
                .body("token",notNullValue());
    }
}
