package Controller;

import DataStructure.Product;
import Module.ProductManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by sixing.wen on 11/8/16.
 */
@WebServlet(name = "LoadServlet", urlPatterns = "/Load")
public class LoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> plist = ProductManager.getInstance().parseProducts(getServletContext().getRealPath("Products.xml"));
        request.setAttribute("products", plist);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/load.jsp");
        rd.forward(request, response);
    }
}
