import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SqlHelper.connect();
            if (SqlHelper.isAuthenticated(new User(req.getParameter("email"), req.getParameter("password"))))
                resp.sendRedirect("logged-in.html");
            ;
            SqlHelper.close();
        } catch (SQLException error) {
            resp.sendRedirect("error.html");
        }
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
        super.doPost(req, resp);
    }

}
