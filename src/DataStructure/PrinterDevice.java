package DataStructure;

/**
 * Created by sixing.wen on 11/8/16.
 */
public class PrinterDevice extends Product {
    String resolution;
    public PrinterDevice(String id,
                   String name,
                   float price,
                   int stock,
                   String resolution) {
        super(id, name, price, stock);
        this.resolution = resolution;
    }
    @Override
    public String getDiscrition() {
        return resolution;
    }
}

