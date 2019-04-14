/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.obj.CartObj;
import sample.product.ProductDAO;

/**
 *
 * @author Phung Nguyen
 */
public class RemoveItemServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String itemRemove[] = request.getParameterValues("rmv");
            String quantity[] =request.getParameterValues("txtQuantity");
            HttpSession session = request.getSession();
            CartObj cart = (CartObj)session.getAttribute("SHOPPINGCART");
            ProductDAO dao = new ProductDAO();
            
            
            if(cart!=null){
               if(itemRemove!=null){
                   for (int i = 0; i < itemRemove.length; i++) {
                       cart.remove(itemRemove[i]);
                       dao.upQuantity(itemRemove[i], Integer.parseInt(quantity[i]));
                   }
                   session.setAttribute("SHOPPINGCART", cart);
                   
                   
               }
            }
            
            
        }
        catch(Exception e){
            log("ERROR at RemoveItemServlet " + e.getMessage());
        }
        finally{
            request.getRequestDispatcher("viewCart.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}