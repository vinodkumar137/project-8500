import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private static Connection connection;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost/temp", "postgres", "postgres");
    }

    public static void authenticate(User user) throws SQLException {
        String query = "INSERT INTO public.user(email,name,password,phno)VALUES(?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getPhno());
        preparedStatement.executeUpdate();
    }

    public static boolean isAuthenticated(User user) {
        String query = "select password from public.user where email=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            return user.getPassword() == preparedStatement.executeQuery().getString(1);
        } catch (SQLException error) {
            return false;
        }
    }

    public static void close() throws SQLException {
        connection.close();
    }
}
