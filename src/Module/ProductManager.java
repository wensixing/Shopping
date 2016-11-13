package Module;

import DataStructure.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sixing.wen on 11/8/16.
 */
public class ProductManager {
    private volatile static ProductManager instance = null;
    public static ProductManager getInstance() {
        if (instance == null) {
            synchronized (ProductManager.class) {
                if (instance == null) {
                    instance = new ProductManager();
                }
            }
        }
        return instance;
    }
    public List<Product> parseProducts(String path) {
        List<Product> plist = new ArrayList<Product>();
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.parse(path);
            Node root = document.getDocumentElement();

            NodeList productList = root.getChildNodes();
            for (int i = 0; i < productList.getLength(); i++) {
                Node currentNode = productList.item(i);
                NamedNodeMap attributes = currentNode.getAttributes();
                if (attributes == null) {
                    continue;
                }
                String productName = currentNode.getNodeName();
                String id = attributes.getNamedItem("id").getNodeValue();
                String name = attributes.getNamedItem("name").getNodeValue();
                float price = Float.parseFloat(attributes.getNamedItem("price").getNodeValue());
                int stock = Integer.parseInt(attributes.getNamedItem("stock").getNodeValue());
                if (productName.equalsIgnoreCase("desktop-pc")) {
                    String processor = attributes.getNamedItem("processor").getNodeValue();
                    String memory = attributes.getNamedItem("memory").getNodeValue();
                    String storage = attributes.getNamedItem("storage").getNodeValue();
                    DesktopPC p = new DesktopPC(id, name, price, stock, processor, memory, storage);
                    plist.add(p);
                } else if (productName.equalsIgnoreCase("laptop-pc")) {
                    String processor = attributes.getNamedItem("processor").getNodeValue();
                    String memory = attributes.getNamedItem("memory").getNodeValue();
                    String storage = attributes.getNamedItem("storage").getNodeValue();
                    String display = attributes.getNamedItem("display").getNodeValue();
                    String battery = attributes.getNamedItem("battery").getNodeValue();
                    LaptopPC p = new LaptopPC(id, name, price, stock, processor, memory, storage, display, battery);
                    plist.add(p);
                } else if (productName.equalsIgnoreCase("printer")) {
                    String resolution = attributes.getNamedItem("resolution").getNodeValue();
                    PrinterDevice p = new PrinterDevice(id, name, price, stock, resolution);
                    plist.add(p);
                }
            }
        }
        catch (SAXException | IOException e) {
            System.out.println("Parse XML error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return plist;
    }

    public synchronized boolean saveProducts(Map<String, Integer> data, String path) {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.parse(path);
            Node root = document.getDocumentElement();

            NodeList productList = root.getChildNodes();
            for (int i = 0; i < productList.getLength(); i++) {
                Node currentNode = productList.item(i);
                NamedNodeMap attributes = currentNode.getAttributes();
                if (attributes == null) {
                    continue;
                }
                String id = attributes.getNamedItem("id").getNodeValue();
                if (!data.containsKey(id)) {
                    continue;
                }
                Node stockNode = attributes.getNamedItem("stock");
                int stock = Integer.parseInt(stockNode.getNodeValue());
                if (stock < data.get(id)) {
                    return false;
                }
                stockNode.setTextContent(String.valueOf(stock - data.get(id)));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        }
        catch (SAXException | IOException e) {
            System.out.println("Parse XML error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }
}
