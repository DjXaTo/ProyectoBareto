/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
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
    
    private Connection cx;
    private Statement st;
    
    public boolean hacerPedido(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto 
                String sql="insert into inventario_pedidos (inventario_codigo, inventario_bar_idbar, cantidad, pedidos_id)"
                        + " VALUES ((select idproductos from productos where nombre=nom), bar, cant, (select max(id) from pedidos))";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean insertaInventario(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into inventario (codigo, nombre, cantidad, bar_idbar)"
                        + " VALUES (new.inventario_codigo, (select nombre from productos "
                        + "where idproductos=new.inventario_codigo), new.cantidad, new.inventario_bar_idbar)";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean actualizarPedido(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto 
                String sql="update pedidos set precio=precio+cant*(select precio from productos where idproductos=ide) where id=ped";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean insertarPedido(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into pedidos (proveedor, fecha, precio) VALUES (pro, SYSDATE(), 00.00)";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean insertarProducto(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into productos (nombre, proveedor, precio) VALUES (nom, lower(pro), pre)";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean actualizarProducto(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="update productos set nombre=nom, proveedor=lower(pro), precio=pre where idproducto=id";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean borrarProducto(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="delete from productos where idproducto=id";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    
    public boolean actualizarInventario(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="update inventario set nombre=new.nombre where codigo=old.idproductos";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean insertaBar(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into bar (nombre, domicilio, fechaapertura, horario, diasapertura, licenciafiscal)"
                        + " VALUES (nom, dom, SYSDATE(), hor, dias, lic)";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean borrarBar(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="delete from bar where id=ide";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean crearEmple(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into empleado (dni, nombre, domicilio) VALUES (dn, nom, dom)";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean borrarEmple(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="delete from empleado where dni=dn";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean actualizarEmple(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="update empleado set nombre=nomn, domicilio=dom where dni=dn";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean asignarEmpleBar(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into bar_has_empleado (bar_idbar, empleado_dni, rol) VALUES (ide, dn, ro)";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean borrarEmpleBar(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="delete from bar_has_empleado where empleado_dni=dn and bar_idbar=ide";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean actualizarEmpleBar(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="update bar_has_empleado set rol=ro where bar_idbar=ide and empleado_dni=dn";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    
    public boolean recaud(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="insert into recaudacion (fecha, total, bar_idbar) VALUES (fech, pre, bar)";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean gastar(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="update inventario set cantidad=cantidad-cant where codigo=cod and bar_idbar=bar";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    
    public boolean borrarInventario(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="delete from inventario where codigo=cod and bar_idbar=bar";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean getBares(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select nombre,direccion,numero from bar";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean getEmpleados(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select dni,nombre,domicilio,puesto from empleado order by dni";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean getCountEmpleados(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="(select count(*) from empleado) as cont";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    public boolean getProductos(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select idproductos,nombre,precio, proveedor from productos";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean getCountProductos(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="(select count(*) from productos) as cont";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean getBar(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select idbar,nombre,domicilio,fechaapertura,horario,diasapertura,licenciafiscal from bar";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean getCountBar(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="(select count(*) from bar) as cont";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean getEmpleadoList(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select dni, nombre from empleado where nombre like (nom + '%') or dni like (dn + '%')";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    
    public boolean getProductoList(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select nombre from productos where nombre proveedor=pro";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    public boolean getEmpleadoBar(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select dni, nombre from empleado join bar_has_empleado on dni=empleado_dni where bar_idbar=ide";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    
    public boolean getCountEmpleadoBar(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="(select count(*) from empleado join bar_has_empleado on dni=empleado_dni where bar_idbar=ide) as cont";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
    
    
    
     public boolean getInventario(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select codigo, p.nombre, q.cantidad, p.precio, q.proveerdor from inventario p join productos q on codigo=idproductos where bar_idbar=ide";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
     
     
     public boolean getCountInventario(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="(select count(*) from inventario where bar_idbar=ide) as cont";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
     
     
     
     public boolean getPedidos(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select nombre, precio, cantidad from inventario_pedidos join productos on inventario_codigo=idproductos where pedidos_id=ide";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
     
     
     public boolean getCountPedidos(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="(select count(*) from inventario_pedidos join productos on inventario_codigo=idproductos where pedidos_id=ide) as cont";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
     
     
     public boolean getPedidosList(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select fecha, proveedor, precio from inventario_pedidos join pedidos on pedidos_id=id where inventario_bar_idbar=ide";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
     
     
      public boolean getBarString(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="select idbar, nombre, domicilio from bar where nombre=nom";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        
        return exito;
    }
      
      
      public boolean actualizarPrecioInventario(){
        boolean exito=false;

            try{    //creamos la sentencia sql para insertar un contacto
                
                String sql="update inventario set precio=pre where codigo=ide and bar_idbar=bar";
                st=(Statement) cx.createStatement();    //creo la instancia de Statement
                st.executeUpdate(sql);      //uso el metodo ExecuteUpdate pasandoles la sentencia sql
                exito=true;     //si no salta ninguna excepcion se ha insertado el contacto
             }catch(SQLException ex){
                 System.err.println(ex.getMessage());
             }finally{
                try{    //finalmente cerramos la conexion
                    cx.close();
                }catch(SQLException ex){
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
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
