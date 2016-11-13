package Controller;

import DataStructure.Product;
import Module.ProductManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sixing.wen on 11/8/16.
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = "/Checkout")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> cart = request.getParameterMap();
        Map<String, Integer> data = new HashMap<String, Integer>();
        for (Map.Entry<String, String[]> item : cart.entrySet()) {
            data.put(item.getKey(), Integer.parseInt(item.getValue()[0]));
        }
        boolean success = ProductManager.getInstance().saveProducts(data, getServletContext().getRealPath("Products.xml"));
        if (success) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println("{\"Status\":\"OK\"}");
            out.close();
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}