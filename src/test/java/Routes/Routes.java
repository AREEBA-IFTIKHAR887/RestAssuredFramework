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

    // Users Endpoints
    public static final String GET_ALL_Users="/users";
    public static final String Create_User="/users";
    public static final String Get_Single_User="/users/{id}";
    public static final String Delete_User="/users/{id}";
    public  static final String Update_User="/users/{id}";

    //Cart Endoints
    public static final String GET_ALL_Carts="/carts";
    public static final String Create_Cart="/carts";
    public static final String Get_Single_Cart="/carts/{id}";
    public static final String Delete_Cart="/carts/{id}";
    public  static final String Update_Cart="/carts/{id}";

    //Login Endpoints
    public static final String Auth_Login="/auth/login";

}
