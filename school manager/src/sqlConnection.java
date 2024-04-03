import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.*;
public class sqlConnection {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public sqlConnection(){
        Class.forName("com.mysql.jdbc.Driver");
        this.connect  = DriverManager.getConnection("jdbc:mysql://localhost:3306/akim_p6_school_manager","root","password");
    }
    public void writeStatement(String statement){
        Statement st = connect.createStatement();
        st.execute(statement);
    }

}
