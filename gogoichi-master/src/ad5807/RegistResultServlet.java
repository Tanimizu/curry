/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad5807;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;

@SuppressWarnings("serial")
public class RegistResultServlet extends HttpServlet {
    String userId,pass,name,address,phonenumber;
    
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
        
        request.setCharacterEncoding("UTF-8");
        name = request.getParameter("name");
        userId = request.getParameter("userId");
        pass = request.getParameter("pass");
        address = request.getParameter("address");
        phonenumber = request.getParameter("phonenumber");
        String msg = "";
        String errorMsg = "";
        
        Account account = new Account(userId,pass,name,address,phonenumber);
        
        if (name == null || name.length() == 0) {
           errorMsg += "<br><p><center><font color=red><strong>お名前が入力されていません。</strong></font><br></center></p>";
        }           
        if(userId == null || userId.length() == 0) {
            errorMsg += "<br><p><center><font color=red><strong>ユーザーID(メールアドレス)が入力されていません。</strong></font><br></center></p>";
        }
        if (pass == null || pass.length() == 0) { 
            errorMsg += "<br><p><center><font color=red><strong>パスワードが入力されていません。</strong></font><br></center></p>";
        }
        if (address == null || address.length() == 0) {
            errorMsg += "<br><p><center><font color=red><strong>ご住所が入力されていません。</strong></font><br></center></p>";
        }
        if (phonenumber == null || phonenumber.length() == 0) {
            errorMsg += "<br><p><center><font color=red><strong>電話番号が入力されていません。</strong></font><br></center></p>";
        }
        if(errorMsg.length() != 0) {
            msg = errorMsg;
        } else {
            msg = "<br><h4><center>ご登録ありがとうございます。<br>以下の内容で登録いたしました。</center></h4> <h3><center>お名前："
                    + name + "</center></h3> <h3><center>ユーザーID：" 
                    + userId + "</center></h3> <h3><center> パスワード："
                    + pass + "</center></h3> <h3><center> ご住所："
                    + address + "</center></h3> <h3><center> 電話番号："
                    + phonenumber + "</center></h3>"
                    + "<center><a href=LoginServlet>ログイン画面へ</a></center>";
            
            PersistenceManagerFactory factory = PMF.get();
            PersistenceManager manager = factory.getPersistenceManager();
            try {
                manager.makePersistent(account);
            } finally {
                manager.close();
            }
        } 
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");         
            out.println("<title>送信結果</title>");            
            out.println("</head>");
            out.println("<body bgcolor=#ffd700");
            out.println("<center>" + msg + "</center>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
