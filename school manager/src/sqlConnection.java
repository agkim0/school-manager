import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<String> getList(String table){
        ArrayList<String> list = new ArrayList<>();
        try{
            Statement st = connect.createStatement();
            ResultSet rs= st.executeQuery("SELECT 1 FROM "+table+";");
            while(!rs.equals(null)&&rs.next()){
                try {
                    list.add(rs.getString("last_name")+", "+rs.getString("first_name"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
