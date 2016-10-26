package Modelo;

import java.sql.Connection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import sun.util.logging.PlatformLogger;

public class Conexion {

    String usuarioSQL;
    String passSQL;
    String URLSQL;
    String bdSQL;
    
    String usuarioLITE;
    String passLITE;
    String URLLITE;
    String bdLITE;
    
    Connection conn = null;

    public Conexion() {
    }

    public void RecogeDatos() {
        try {
            File fe = new File("datos_conexion.txt");
            FileReader fr = new FileReader(fe);
            BufferedReader br = new BufferedReader(fr);

            usuarioSQL = br.readLine();
            passSQL = br.readLine();
            URLSQL = br.readLine();
            bdSQL = br.readLine();
            
            URLLITE = br.readLine();
            
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al coger los datos de conexion");
        }
    }

    public Connection conexionSQL() {
        RecogeDatos();
        conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + URLSQL + "/" + bdSQL, usuarioSQL, passSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }
    
    public Connection conexionLITE(){
        RecogeDatos();
        conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + URLLITE);
            if(conn!=null) {
               // System.out.print("Conectado");
            }
        } catch (SQLException e) {
            System.err.println("No se ha podido conectar a la base de datos" + e.getMessage());
        }
        return conn;
    }
    
}
