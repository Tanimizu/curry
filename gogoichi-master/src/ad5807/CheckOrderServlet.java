package ad5807;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import model.Order;
import model.OrderLogic;
import model.Product;

/**
 *
 * @author g14958ym
 */

public class CheckOrderServlet extends HttpServlet {

    public String hour, minute;
    public ArrayList<Order> orderList = new ArrayList();
    public ArrayList<String> pid = new ArrayList();
    public ArrayList<String> q = new ArrayList();

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String[] product = request.getParameterValues("product");
        String[] quantity = new String[11];

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String a = "";
        for (int i = 0; i < 11; i++) {
            a = "q" + i;
            if (!"".equals(request.getParameter(a)) || !"0".equals(request.getParameter(a))) {
                quantity[i] = request.getParameter(a);
            }
        }
        int count = 0;
        q.clear();
        orderList.clear();
        for (int i = 0; i < 11; i++) {
            if (quantity[i] != "") {
                count++;
                q.add(quantity[i]);
            }
        }

        PrintWriter out = response.getWriter();
        hour = request.getParameter("hour");
        minute = request.getParameter("minute");

        ProductDAO dao = new ProductDAO();

        String errorMsg = "";
        String msg = "";
        int total = 0;

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");


        if (product == null) {
            errorMsg += "<br><p><center><font color=red><strong>商品が選択されていません。</strong></font><br></center></p>";
        } else if (product.length != q.size()) {
            errorMsg += "<br><p><center><font color=red><strong>選択されていない商品があるか、個数を指定していない商品があります。</strong></font><br></center></p>";
        }
        if (hour == null || hour.length() == 0 || minute == null || minute.length() == 0) {
            errorMsg += "<br><p><center><font color=red><strong>配達時間が指定されていません。</strong></font><br></center></p>";
        }
        if (errorMsg.length() != 0) {
            msg = errorMsg;
        } else {
           // msg += "<br><h4><center>"+userId+"さん、ご注文ありがとうございます。<br>以下の内容で承りました。</center></h4>";
            for (int i = 0; i < product.length; i++) {
                //msg += product[i] + " "+ q.get(i) +"<br>";
                Product p = new Product();
                p = dao.findProduct(product[i]);
                pid.add(product[i]);
                msg += "<h3><center>" + p.getProductName() + " 　" + q.get(i) + "人前</center></h3>";
                total += p.getPrice() * Integer.parseInt(q.get(i));
                Order order = new Order(userId, product[i], Integer.parseInt(q.get(i)), hour + ":" + minute, sdf.format(c.getTime()));
                orderList.add(order);
            }
            msg += "<h3><center>合計金額  " + total + "円</center></h3>";
            msg += "<h3><center>配達時刻  " + hour + ":" + minute + "</center></h3>";

            /*for (int i = 0; i < orderList.size(); i++) {
                msg +=  orderList.get(i).getUserId() + " " + orderList.get(i).getProductId() + " "
                        + String.valueOf(orderList.get(i).getQuantity()) + " " + orderList.get(i).getDeliverytime() + " " + orderList.get(i).getDate() + "<br>";
            }*/
            msg += "<center><a href=/logout>ログアウト</a></center>";

            OrderLogic orderLogic = new OrderLogic();
            orderLogic.execute(orderList);

        }
        Connection conn = null;
        try {

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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
