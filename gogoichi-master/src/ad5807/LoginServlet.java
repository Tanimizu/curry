package ad5807;

import java.io.IOException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Login;
import model.LoginLogic;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

        
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
		/** protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         // フォワード 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.html");
        dispatcher.forward(request, response);
    }*/

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	UserService userService = UserServiceFactory.getUserService();

        String thisUrl = req.getRequestURI();

        resp.setContentType("text/html");
        if (req.getUserPrincipal() != null) {
          resp.getWriter().println("<p>Hello, " +req.getUserPrincipal().getName()+"!  You can <a href=\"/select.html\">move on</a>.</p>");
              //+ userService.createLogoutURL(thisUrl)
             // + "\">sign out</a>.</p>");
        } else {
          resp.getWriter().println("<p>Please <a href=\""
                + userService.createLoginURL(thisUrl)
                + "\">sign in</a>.</p>");
        }
      }
       // processRequest(request, response);
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        String url = req.getParameter("url");
        String comment = req.getParameter("comment");
        Date date = Calendar.getInstance().getTime();
        LinkData data = new LinkData(title,url,comment,date);
        PersistenceManagerFactory factory = PMF.get();
        PersistenceManager manager = factory.getPersistenceManager();
        try {
            manager.makePersistent(data);
        } finally {
            manager.close();
        }
        resp.sendRedirect("/login.html");
    }

    
}
