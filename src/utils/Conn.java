package utils;
import java.sql.*;
public class Conn {
	
	public String  url="jdbc:mysql://localhost:3306/libb";
    public String login="root";
    public String pwd="";

        public Connection c;
       
    public static Conn instance;  
    
    public static Conn getInstance() {
        if(instance==null){
            instance= new Conn();
        }
        return instance;
    }
    
    public Conn() {
        try {
          c = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return c;
    }
}
	

