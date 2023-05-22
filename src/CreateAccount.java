import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String enteredOtp = req.getParameter("otp");
        String expectedOtp = (String) session.getAttribute("otp");
        if (enteredOtp.equals(expectedOtp)) {
            try {
                SqlHelper.connect();
                SqlHelper.authenticate((User) session.getAttribute("user"));
                SqlHelper.close();
            } catch (SQLException error) {
                resp.sendRedirect("error.html");
            }
            resp.sendRedirect("account-created.html");
        } else
            resp.sendRedirect("signup-verification.html");

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
        super.doPost(req, resp);
    }

}
