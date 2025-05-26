package Payloads;

import POJO.*;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Payload {
    private static final Faker faker=new Faker();
    private static final String categoreies[]={"electronics","furniture","clothing","books","beauty"};
    private static final Random random=new Random();

    public static Product productPayload(){
           String name=faker.commerce().productName();
           Double price=Double.parseDouble(faker.commerce().price());
           String description=faker.lorem().sentence();
           String imageURl="test@test.com";
           String category=categoreies[random.nextInt(categoreies.length)];
           return  new Product(name,price,description,imageURl,category);
    }
    public static Users usersPayload(){
          String name=faker.name().username();
          String email=faker.internet().emailAddress();
          String password=faker.internet().password();
          return new Users(name,email,password);
    }

    public static Cart cartPayload(int userId){
        List<CartProduct> products=new ArrayList<>();
        int productid=random.nextInt(100);
        double price=Double.parseDouble(faker.commerce().price());
        String title=faker.commerce().productName();
        String description=faker.lorem().sentence();
        String category=faker.lorem().sentence();
        String image="http://example.com/" + faker.internet().slug();

        CartProduct cartProduct=new CartProduct(productid,title,price,description,category,image);
        products.add(cartProduct);
        return new Cart(userId,products);

    }
    public static Login loginPayload(){
        String username=faker.name().username();
        String password=faker.internet().password();
        Login login=new Login(username,password);
        return login;
    }
}
