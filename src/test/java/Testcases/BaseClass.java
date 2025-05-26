package Testcases;

import Routes.Routes;
import Utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class BaseClass {
    ConfigReader configReader;
    RequestLoggingFilter requestLoggingFilter;
    ResponseLoggingFilter responseLoggingFilter;


    @BeforeClass
    public void setup() throws FileNotFoundException {
        RestAssured.baseURI = Routes.BASE_URL;
        configReader=new ConfigReader();
        FileOutputStream fos = new FileOutputStream(".\\Logs\\test_logging.log");
        PrintStream log=new PrintStream(fos,true);
        requestLoggingFilter=new RequestLoggingFilter(log);
        responseLoggingFilter=new ResponseLoggingFilter(log);
        RestAssured.filters(requestLoggingFilter,responseLoggingFilter);

    }

}
