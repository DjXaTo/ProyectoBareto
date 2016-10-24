package Modelo;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;
import java.sql.CallableStatement;

import java.sql.SQLException;
import java.util.ArrayList;

public class Modelo extends Conexion {

    public Modelo() {
    }

    public boolean hacerPedido(ArrayList nombres, ArrayList cant, String bar, String prov) {
        try {
            String a = "{call insertarPedido(?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(a);
            cstmt.setString(1, prov);
            cstmt.execute();
            String q = "{call hacerPedido(?, ?, ?)}";
            for (int i = 0; i < nombres.size(); i++) {
                cstmt = this.conexionSQL().prepareCall(q);
                cstmt.setInt(2, Integer.parseInt(bar));
                cstmt.setInt(1, Integer.parseInt((String) cant.get(i)));
                cstmt.setString(3, (String) nombres.get(i));
                cstmt.execute();
            }
            return true;
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean insertarProducto(String pro, String pre, String nom) {
        try {
            String q = "{call  insertarProducto(? , ?, ?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setString(1, pro);
            cstmt.setDouble(2, Double.parseDouble(pre));
            cstmt.setString(3, nom);
            cstmt.execute();
            return true;
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean actualizarProducto(String pro, String nom, String idpro, String pre) {
        try {
            String q = "{call  actualizarProducto(?, ?, ?, ?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setString(1, pro);
            cstmt.setString(2, nom);
            cstmt.setInt(3, Integer.parseInt(idpro));
            cstmt.setDouble(4, Double.parseDouble(pre));
            cstmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean borrarProducto(String idpro) {
        try {
            String q = "{call  borrarProducto(?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setInt(1, Integer.parseInt(idpro));
            cstmt.execute();
            return true;
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean insertaBar(String nom, String dom, String hor, String dias, String lic) {
        try {
            String q = "{call  insertaBar(?, ?, ? , ?, ?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setString(1, nom);
            cstmt.setString(2, dom);
            cstmt.setString(3, hor);
            cstmt.setInt(4, Integer.parseInt(dias));
            cstmt.setLong(5, Long.parseLong(lic));
            cstmt.execute();
            return true;
        } catch (Exception e) {
             System.err.println(e.getMessage());
        }
        return false;
    }
    
    public boolean borrarBar(String ide){
       try {
            String q = "{call  borrarBar(?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setInt(1, Integer.parseInt(ide));
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false; 
    }
    
    public boolean crearEmple(String dn, String nom, String dom){
        try {
            String q = "{call  crearEmple(?, ?, ?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setString(1, dn);
            cstmt.setString(2, nom);
            cstmt.setString(3, dom);
            cstmt.execute();
            return true;
        } catch (Exception e) {
             System.err.println(e.getMessage());
        }
        return false; 
    }
    
    public boolean borrarEmple(String dn){
         try {
            String q = "{call  borrarEmple(?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setString(1, dn);
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false; 
    }
    
    public boolean asignarEmpleBar(String ide, String dn, String ro){
         try {
            String q = "{call asignarEmpleBar(?, ?, ?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setInt(1, Integer.parseInt(ide));
            cstmt.setString(2, dn);
            cstmt.setString(3, ro);
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false; 
    }
            
    public boolean borrarEmpleBar(String dn, String  ide){
        try {
            String q = "{call borrarEmpleBar(?, ?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setString(1, dn);
            cstmt.setInt(2, Integer.parseInt(ide));
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false; 
    }
    
    public boolean actualizarEmpleBar(String ide, String dn, String ro){
        try {
            String q = "{call actualizarEmpleBar(?, ?, ?)}"; 
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setInt(1, Integer.parseInt(ide));
            cstmt.setString(2, dn);
            cstmt.setString(3, ro);
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    public boolean gastar(String cant, String cod, String bar) {
        try {
            String q = "{?=call gastar(?, ?, ?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.registerOutParameter(1, Type.BOOLEAN);
            cstmt.setInt(2, Integer.parseInt(cant));
            cstmt.setInt(3, Integer.parseInt(cod));
            cstmt.setInt(4, Integer.parseInt(bar));
            cstmt.execute();
            return cstmt.getBoolean(1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    public boolean borrarInventario(String cod, String bar){
        try {
            String q = "{call borrarInventario(?, ?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setInt(1, Integer.parseInt(cod));
            cstmt.setInt(2, Integer.parseInt(bar));
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
