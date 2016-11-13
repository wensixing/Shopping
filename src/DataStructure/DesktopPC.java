package DataStructure;

/**
 * Created by sixing.wen on 11/8/16.
 */
public class DesktopPC extends Product {
    private String processor;
    private String memory;
    private String storage;
    public DesktopPC(String id,
                     String name,
                     float price,
                     int stock,
                     String processor,
                     String memory,
                     String storage) {
        super(id, name, price, stock);
        this.processor = processor;
        this.memory = memory;
        this.storage = storage;
    }
    @Override
    public String getDiscrition() {
        String res = processor + ", " + memory + ", " + storage;
        return res;
    }
}
