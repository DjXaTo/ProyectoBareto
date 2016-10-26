/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alfre
 */
public class Modelo_Alfredo extends Conexion{

    

    
    public boolean hacerPedido(int inventario_codigo, int invetario_bar_idbar, int cantidad, int pedidos_id){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto 
                String sql="insert into inventario_pedidos (inventario_codigo, inventario_bar_idbar, cantidad, pedidos_id)"
                        + " VALUES ((select idproductos from productos where nombre='"+nom+"'), "+bar+", "+cant+", (select max(id) from pedidos))";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean insertaInventario(int codigo, String nombre, int cantidad, int bar_idbar){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into inventario (codigo, nombre, cantidad, bar_idbar)"
                        + " VALUES (new.inventario_codigo, (select nombre from productos "
                        + "where idproductos=new.inventario_codigo), new.cantidad, new.inventario_bar_idbar)";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean actualizarPedido(int ped){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto 
                String sql="update pedidos set precio=precio+cant*(select precio from productos where idproductos=ide) where id="+ped+"";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean insertarPedido(String proveedor, Date fecha, double precio){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into pedidos (proveedor, fecha, precio) VALUES (pro, SYSDATE(), 00.00)";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean insertarProducto(String nombre, String proveedor, double precio){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into productos (nombre, proveedor, precio) VALUES (nom, lower(pro), pre)";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean actualizarProducto(int id){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="update productos set nombre=nom, proveedor=lower(pro), precio=pre where idproducto="+id+"";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean borrarProducto(int id){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="delete from productos where idproducto="+id+"";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    

    
    
    public boolean insertaBar(String nombre, String domicilio, Date fechaapertura, String horario, int diasapertura, long licenciafiscal){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into bar (nombre, domicilio, fechaapertura, horario, diasapertura, licenciafiscal)"
                        + " VALUES (nom, dom, SYSDATE(), hor, dias, lic)";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean borrarBar(int ide){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="delete from bar where id="+ide+"";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean crearEmple(String dni, String nombre, String domicilio){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into empleado (dni, nombre, domicilio) VALUES (dn, nom, dom)";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean borrarEmple(String dn){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="delete from empleado where dni='"+dn+"'";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean actualizarEmple(String dn){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="update empleado set nombre=nomn, domicilio=dom where dni='"+dn+"'";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean asignarEmpleBar(int bar_idbar, String empleado_dni, String rol){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into bar_has_empleado (bar_idbar, empleado_dni, rol) VALUES (ide, dn, ro)";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean borrarEmpleBar(String dn, int ide){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto

                String sql="delete from bar_has_empleado where empleado_dni='"+dn+"' bar_idbar="+ide+" ";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean actualizarEmpleBar(int ide, String dn){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="update bar_has_empleado set rol=ro where bar_idbar="+ide+" and empleado_dni='"+dn+"'";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    
    public boolean recaud(Date fecha, double total, int bar_idbar){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into recaudacion (fecha, total, bar_idbar) VALUES (fech, pre, bar)";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean gastar(int cod, int bar){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="update inventario set cantidad=cantidad-cant where codigo="+cod +"and bar_idbar="+bar+"";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    
    
    
    public boolean borrarInventario(int cod, int bar){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="delete from inventario where codigo="+cod +"and bar_idbar="+bar+"";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public boolean getBares(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select nombre,direccion,numero from bar";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
    
    
    public DefaultTableModel getEmpleados(){
        DefaultTableModel md = new DefaultTableModel();
        int a = 0;
            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select (dni,nombre,domicilio,puesto) as todo from empleado order by dni";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt("todo");
                ps.close();
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        String q = "select * from empleado";
        try{
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            md.setDataVector(data, column);
            }catch(Exception e){
                System.err.print(e.getMessage());  
            }
        return md;
    }
    

    
    public DefaultTableModel getProductos(){
        DefaultTableModel md = new DefaultTableModel();
        int a = 0;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select (idproductos,nombre,precio, proveedor) as todo from productos";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt("todo");
                ps.close();
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
            String q = "select * from productos";
        try{
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            md.setDataVector(data, column);
            }catch(Exception e){
                System.err.print(e.getMessage());  
            }
        return md;
    }
    

    
    
    
    public DefaultTableModel getBar(){
        DefaultTableModel md = new DefaultTableModel();
        int a = 0;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select (idbar,nombre,domicilio,fechaapertura,horario,diasapertura,licenciafiscal)as todo from bar";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt("todo");
                ps.close();
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
            String q = "select * from bar";
            try{
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            md.setDataVector(data, column);
            }catch(Exception e){
                System.err.print(e.getMessage());  
            }
        return md;
    }

    
    public DefaultListModel getEmpleadoList(){
            DefaultListModel list = new DefaultListModel();
            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select dni, nombre from empleado where nombre like (nom + '%') or dni like (dn + '%')";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ResultSet res = ps.executeQuery();
            while (res.next()) {
               list.addElement(res.getString(1)+"-"+res.getString(2));
            }
                ps.close();
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return list;
    }
    
    
  
    
    public DefaultListModel getProductoList(){
        DefaultListModel list = new DefaultListModel();
            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select nombre from productos where proveedor=pro";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ResultSet res = ps.executeQuery();
            while (res.next()) {
                list.addElement(res.getString(1));
            }
                ps.close();
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return list;
    }
    
    
    public DefaultTableModel getEmpleadoBar(){
        DefaultTableModel md = new DefaultTableModel();
        int a = 0;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select (dni, nombre) as todo from empleado join bar_has_empleado on dni=empleado_dni where bar_idbar=ide";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt("todo");
                ps.close();
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        String q = "select * from empleado join bar_has_empleado";
        try{
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            md.setDataVector(data, column);
            }catch(Exception e){
                System.err.print(e.getMessage());  
            }
        return md;
    }
    
    
    
    
     public DefaultTableModel getInventario(){
         DefaultTableModel md = new DefaultTableModel();
        int a = 0;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select (codigo, p.nombre, q.cantidad, p.precio, q.proveerdor) as todo from inventario p join productos q on codigo=idproductos where bar_idbar=ide";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt("todo");
                ps.close();
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
            String q = "select * from inventario";
        try{
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            md.setDataVector(data, column);
            }catch(Exception e){
                System.err.print(e.getMessage());  
            }
        return md;
    }
     
     
     
     public DefaultTableModel getPedidos() throws SQLException{
        DefaultTableModel md = new DefaultTableModel();
        int a = 0;
            
            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select (nombre, precio, cantidad)as todo  from inventario_pedidos join productos on inventario_codigo=idproductos where pedidos_id=ide";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt("todo");
                ps.close();
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
            String q = "select * from inventario_pedidos";
            try{
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            md.setDataVector(data, column);
            }catch(Exception e){
                System.err.print(e.getMessage());  
            }
            
            
        
        return md;
    }
     
     
     
     public DefaultListModel getPedidosList(){
        DefaultListModel list = new DefaultListModel();

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select fecha, proveedor, precio from inventario_pedidos join pedidos on pedidos_id=id where inventario_bar_idbar=ide";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    list.addElement(res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3));
                     }
                ps.close();
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return list;
    }
     
 
     
      public DefaultListModel getBarString(){
        DefaultListModel list = new DefaultListModel();
            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select idbar, nombre, domicilio from bar where nombre=nom";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    list.addElement(res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3));
                     }
                ps.close();
                   
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return list;
    }
      
      
      
      public boolean actualizarPrecioInventario(int ide, int bar){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="update inventario set precio=pre where codigo="+ide +"and bar_idbar="+bar+"";
                PreparedStatement ps=this.conexionLITE().prepareStatement(sql);
                ps.execute();
                ps.close();
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }
        
        return exito;
    }
      
      
      
      
      
      
      
      
      //ejemplo de ejecutar un insert delete o update
      public boolean borrarProfesor(String dni){
         try{
            String q="delete from profesores where dni='"+dni+"'";
            PreparedStatement ps=this.conexionLITE().prepareStatement(q);
            ps.execute();
            ps.close();
            return true;
        }catch(SQLException e){
           
        }
        return false;
    }
      //ejemplo de tabla
       public DefaultTableModel getAlumnos() {
        DefaultTableModel md = new DefaultTableModel();
        int a = 0;
        //saca el total de filas
        try {
            String q = "select count(*) as todo from alumno";
            PreparedStatement ps = this.conexionLITE().prepareStatement(q);
            ResultSet res = ps.executeQuery();
            res.next();
            a = res.getInt("todo");
            res.close();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
      //rellena con los datos
            String q = "select * from alumno order by nombre, numaula";
            try {
                //a partir de aqui es copiar y pegar
            PreparedStatement ps = this.conexionLITE().prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            String column[] = new String[rsmd.getColumnCount()];
            String data[][] = new String[a][rsmd.getColumnCount()];
            for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i <= rsmd.getColumnCount()-1; i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            md.setDataVector(data, column);
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
        return md;
    }
       //ejemplo de lista 
       public DefaultListModel getListaProfesores() {
        DefaultListModel list = new DefaultListModel();//para combobox es lo mismo solo en vez de ser listmodel es comboboxmodel
        try {
            String q = "select nombre,dni from profesores";
            PreparedStatement ps = this.conexionLITE().prepareStatement(q);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                list.addElement(res.getString(1)+"-"+res.getString(2));
            }
            res.close();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }

        return list;
    }
     
}
