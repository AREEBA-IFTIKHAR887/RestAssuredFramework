package Payloads;

import POJO.Product;
import com.github.javafaker.Faker;

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
}
