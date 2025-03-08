package Routes;

import groovy.lang.GString;
import groovy.transform.Final;

public class Routes {
    public static final String BASE_URL="https://fakestoreapi.com";

    //Products Endpoints
    public static final String GET_ALL_Products="/products";
    public static final String Create_Product="/products";
    public static final String Update_Product="/products/{id}";
    public static final String Delete_Product="/products/{id}";

}
