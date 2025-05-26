package POJO;
import java.util.List;

public class Cart {
    private int userId;
    private List<CartProduct> products;

    public Cart( int userId, List<CartProduct> products) {
        this.userId = userId;
        this.products = products;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CartProduct> products) {
        this.products = products;
    }}
