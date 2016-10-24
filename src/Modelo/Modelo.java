
package Modelo;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class Modelo extends Conexion{
    
    public Modelo(){}
    
    public boolean hacerPedido(ArrayList nombres, ArrayList cant, String bar, String prov){
        try{
        String a="{call insertarPedido(?)}";
        CallableStatement cstmt = this.conexionSQL().prepareCall(a);
        cstmt.setString(1, prov);
        cstmt.execute();
        String q="{call hacerPedido(?, ?, ?)}";
        cstmt = this.conexionSQL().prepareCall(q);
        cstmt.setInt(2, Integer.parseInt(bar));
        for(int i=0; i<nombres.size(); i++){
           cstmt.setInt(1, Integer.parseInt((String) cant.get(i)));
           cstmt.setString(3, (String) nombres.get(i));
           cstmt.execute();
        }
        return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;      
    }
}
