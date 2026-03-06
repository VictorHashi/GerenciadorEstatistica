package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {

    final static String DATABASE = "produtotexto";
    public static Connection connect(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/"+DATABASE;
            Connection conn = DriverManager.getConnection(url,"root","root");
            System.out.println(">> Debug: Conectado ao BD '"+DATABASE+"' com sucesso!");
            return conn;
        }catch (Exception e){
            System.out.println(">> Debug: Falha ao conectar ao BD '"+DATABASE+"'. Motivo: " + e.getMessage());
            return null;
        }

    }

}
