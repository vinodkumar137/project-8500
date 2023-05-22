import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
// import com.twilio.rest.api.v2010.account.Message;

public class VerifySignup extends HttpServlet {
    public static final String ACCOUNT_SID = "ACd26f0bb5897dd0e8e15e0167eeb6c9fc";
    public static final String AUTH_TOKEN = "446e663f39d0b07ed07b0866551dd51c";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = session.getAttribute("user");
        if ( user == null) {
            user = new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("password"), req.getParameter("phno"));
            session.setAttribute("user", user);
        }
        String otp = (String) session.getAttribute("otp");
        if (otp == null) {
            otp = String.valueOf((int) (Math.random() * 1e6));
            session.setAttribute("otp", otp);
        }
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+91" + user.getPhno()),
                new com.twilio.type.PhoneNumber("+12542563743"),
                "One time password is:\n" + otp)
                .create();
        if (message.getErrorCode() != null)
            resp.sendRedirect("error.html");
        resp.sendRedirect("signup-verification.html");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
        super.doPost(req, resp);
    }

}
