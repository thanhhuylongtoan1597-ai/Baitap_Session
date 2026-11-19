package org.example.data;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.example.business.*;


public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        ServletContext sc = getServletContext();

        String action = request.getParameter("action");
        if (action == null) {
            action = "cart";
        }

        String url = "/index.html";

        if (action.equals("shop")) {
            url = "/index.html";
        } else if (action.equals("cart")||action.equals("update")) {
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }

            Product product = null;
            if (productCode != null) {
                switch (productCode) {
                    case "86 (the band)":
                        product = new Product("86 (the band)", "86 (the band) - True Life Songs and Pictures", 14.95);
                        break;
                    case "Paddlefoot01":
                        product = new Product("Paddlefoot01", "Paddlefoot - The first CD", 12.95);
                        break;
                    case "Paddlefoot02":
                        product = new Product("Paddlefoot02", "Paddlefoot - The second CD", 14.95);
                        break;
                    case "JoeRut":
                        product = new Product("JoeRut", "Joe Rut - Genuine Wood Grained Finish", 14.95);
                        break;
                }
            }

            if (product != null) {
                LineItem lineItem = new LineItem();
                lineItem.setProduct(product);
                lineItem.setQuantity(quantity);

                if (quantity == 0) {
                    cart.removeItem(lineItem);
                } else if (action.equals("update")) {
                    cart.updateItem(lineItem);
                }else{
                    cart.addItem(lineItem);
                }
            }

            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        } else if (action.equals("checkout")) {
            url = "/checkout.jsp";
        } else if (action.equals("checkout")) {
            url = "/checkout.jsp";
        } else if (action.equals("cancel")) {
            HttpSession session = request.getSession();
            session.removeAttribute("cart"); // Xóa sạch giỏ hàng khỏi Session
            url = "/index.html";
        }

        sc.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}