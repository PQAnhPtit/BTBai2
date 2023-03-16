import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckOrder {
    private Connection conn;

    public CheckOrder() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BTBai2", "root", "password");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String checkOrderStatus(String OrderNumber, String companyID) {
        String result = "";
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM order WHERE order_number = ? and company_id = ?");
            stmt.setString(1, OrderNumber);
            stmt.setString(2, companyID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	String order_number = rs.getString("order_number");
    			String company_id = rs.getString("company_id");
    			result += order_number + " " + company_id;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
}