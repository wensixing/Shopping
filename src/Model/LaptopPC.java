package Model;

/**
 * Created by sixing.wen on 11/8/16.
 */
public class LaptopPC extends Product {
    private String processor;
    private String memory;
    private String storage;
    private String display;
    private String battery;
    public LaptopPC(String id,
                    String name,
                    float price,
                    int stock,
                    String processor,
                    String memory,
                    String storage,
                    String display,
                    String battery) {
        super(id, name, price, stock);
        this.processor = processor;
        this.memory = memory;
        this.storage = storage;
        this.display = display;
        this.battery = battery;
    }
    @Override
    public String getDiscrition() {
        String res = processor + ", " + memory + ", " + storage + ", " + display + ", " + battery;
        return res;
    }
}
