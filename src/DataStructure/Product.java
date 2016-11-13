package DataStructure;

/**
 * Created by sixing.wen on 11/8/16.
 */
public abstract class Product {
    private String id;
    private String name;
    private float price;
    private int stock;
    public abstract String getDiscrition();
    public Product(String id, String name, float price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
}
