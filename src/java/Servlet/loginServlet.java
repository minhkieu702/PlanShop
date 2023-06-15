/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import data.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import myDAO.AccountDAO;
import myDAO.OrderDAO;

/**
 *
 * @author Acer
 */
public class loginServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String email= request.getParameter("txtemail");
            String password= request.getParameter("txtpassword");
            String save = request.getParameter("savelogin");
            Account acc= null;
            try{
                if(email == null || email.equals("") || password==null || password.equals("")){
                     Cookie [] c = request.getCookies();
                     String token="";
                     if(c!=null){
                         for (Cookie Acookie : c) {
                             if(Acookie.getName().equals("selector")){
                                 token = Acookie.getValue();
                         }
                     }
                }
                     if(!token.equals("")) 
                         request.getRequestDispatcher("beforePP").forward(request, response);
                     else response.sendRedirect("errorpage.html");
                }
                else{
                acc = AccountDAO.getAccount(email, password);
                if(acc!=null){
                    //admin
                    if(acc.getRole()==1){
                        ///chuyen qua adminhome
                          HttpSession session = request.getSession();
                           if(session!=null){
                               session.setAttribute("name", acc.getFullname());
                               session.setAttribute("email", email);
                               if(save!=null){
                                   String token="ABC123";
                                   AccountDAO.updateToken(token, email);
                                   Cookie cookie = new Cookie("selector", token);
                                   cookie.setMaxAge(60 * 2);
                                   response.addCookie(cookie);
                               }
                        response.sendRedirect("AdminIndex.jsp");
                    }
                    }
                    else{
//                        response.sendRedirect("welcome.html");
                            HttpSession session = request.getSession(true);
                           if(session!=null){
                               session.setAttribute("name", acc.getFullname());
                               session.setAttribute("phone", acc.getPhone());
                               session.setAttribute("email", email);
                               if(save!=null){
                                   String token="ABC123";
                                   AccountDAO.updateToken(token, email);
                                   Cookie cookie = new Cookie("selector", token);
                                   cookie.setMaxAge(60 * 2);
                                   response.addCookie(cookie);
                               
                           }
                              request.setAttribute("orders", OrderDAO.getOrders(email));
                              request.getRequestDispatcher("personalPage.jsp").forward(request, response);
                    }
                }
            }     
        }
            }catch(Exception e){
                e.printStackTrace();
            }
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
