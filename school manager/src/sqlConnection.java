import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.*;
public class sqlConnection {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public sqlConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.connect  = DriverManager.getConnection("jdbc:mysql://localhost:3306/akim_p6_school_manager","root","password");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public void writeStatement(String statement){
        try{
            Statement st = connect.createStatement();
            st.execute(statement);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet getRS(int ind, String table){
        try{
            Statement st = connect.createStatement();
            ResultSet rs= st.executeQuery("SELECT "+ind+" FROM "+table+";");
            return rs;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
