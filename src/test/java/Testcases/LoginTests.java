package Testcases;

import POJO.Login;
import POJO.Product;
import Payloads.Payload;
import Routes.Routes;
import Utils.RestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTests extends BaseClass{

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
        String username=configReader.getProperty("username");
        String password=configReader.getProperty("password");
        Login newlogin = new Login(username,password) ;
        Response response = RestUtils.performPost(Routes.Auth_Login, newlogin);
        response.then()
                .statusCode(200)
                .body("token",notNullValue());
    }

}
