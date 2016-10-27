package Modelo;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import sun.tools.java.Type;

public class Modelo extends Conexion {

    public Modelo() {
    }

    public boolean hacerPedido(ArrayList nombres, ArrayList cant, String bar, String prov, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto
                String q = "insert into pedidos (proveedor, fecha, precio) VALUES ('" + prov + "', date('now'), 00.00)";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                for (int i = 0; i < nombres.size(); i++) {
                    String p = "insert into inventario_pedidos (inventario_codigo, inventario_bar_idbar, cantidad, pedidos_id)"
                            + " VALUES ((select idproductos from productos where nombre='" + nombres.get(i) + "'), " + bar + ", " + cant.get(i) + ", (select max(id) from pedidos))";
                    ps = this.conexionLITE().prepareStatement(p);
                    ps.execute();
                    p = "update pedidos set precio=precio+" + cant.get(i) + "*(select precio from productos where nombre='" + nombres.get(i) + "') where id=(select max(id) from pedidos)";
                    ps = this.conexionLITE().prepareStatement(p);
                    ps.execute();
                }
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean insertarProducto(String pro, String pre, String nom, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "insert into productos (nombre, proveedor, precio) VALUES ('" + nom + "', lower('" + pro + "'), " + pre + ")";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean actualizarProducto(String pro, String nom, String idpro, String pre, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "update productos set nombre='" + nom + "', proveedor=lower('" + pro + "'), precio='" + pre + "' where idproductos=" + idpro + "";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean borrarProducto(String idpro, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "delete from productos where idproducto=" + idpro + "";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean insertaBar(String nom, String dom, String hor, String dias, String lic, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "insert into bar (nombre, domicilio, fechaapertura, horario, diasapertura, licenciafiscal)"
                        + " VALUES ('" + nom + "', '" + dom + "', date('now'), '" + hor + "', " + dias + ", " + lic + ")";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean borrarBar(String ide, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "delete from bar where id=" + ide + "";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean crearEmple(String dn, String nom, String dom, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "insert into empleado (dni, nombre, domicilio) VALUES ('" + dn + "', '" + nom + "', '" + dom + "')";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean actualizarEmple(String dn, String nom, String dom, boolean sql) {
        if (sql) {
            try {
                String q = "{call  actualizarEmple(?, ?, ?)}";
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "update empleado set nombre='" + nom + "', domicilio='" + dom + "' where dni='" + dn + "'";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean borrarEmple(String dn, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "delete from empleado where dni='" + dn + "'";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean asignarEmpleBar(String ide, String dn, String ro, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "insert into bar_has_empleado (bar_idbar, empleado_dni, rol) VALUES (" + ide + ", '" + dn + "', '" + ro + "')";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean borrarEmpleBar(String dn, String ide, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "delete from bar_has_empleado where empleado_dni='" + dn + "' bar_idbar=" + ide;
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean actualizarEmpleBar(String ide, String dn, String ro, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String p = "update bar_has_empleado set rol='" + ro + "' where bar_idbar=" + ide + " and empleado_dni='" + dn + "'";
                PreparedStatement ps = this.conexionLITE().prepareStatement(p);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public boolean gastar(String cant, String cod, String bar, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto
                String p = "select cantidad from inventario where codigo=" + cod + " and bar_idbar=" + bar;
                PreparedStatement ps = this.conexionLITE().prepareStatement(p);
                ResultSet rs = ps.executeQuery();
                rs.next();
                int i = rs.getInt("cantidad");
                if (i >= Integer.parseInt(cant) || Integer.parseInt(cant) > 0) {
                    String q = "update inventario set cantidad=cantidad-" + cant + " where codigo=" + cod + " and bar_idbar=" + bar;
                    ps = this.conexionLITE().prepareStatement(q);
                    ps.execute();
                    if (comprobarRecaudacion(bar)) {
                        p = "update recaudacion set total=total+" + cant + "*(select precio from inventario where codigo=" + cod + " and bar_idbar=" + bar + ") where bar_idbar=" + bar + " and fecha=date('now')";
                        ps = this.conexionLITE().prepareStatement(p);
                        ps.execute();
                        ps.close();
                    } else {
                        p = "insert into recaudacion (fecha, total, bar_idbar) VALUES (date('now'), " + cant + "*(select precio from inventario where codigo=" + cod + " and bar_idbar=" + bar + "), " + bar + ")";
                        ps = this.conexionLITE().prepareStatement(p);
                        ps.execute();
                        ps.close();
                    }
                    return true;
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    private boolean comprobarRecaudacion(String bar) {
        try {
            String p = "select fecha from recaudacion where fecha=date('now') and bar_idbar=" + bar;
            PreparedStatement ps = this.conexionLITE().prepareStatement(p);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String i = rs.getString("fecha");
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public boolean borrarInventario(String cod, String bar, boolean sql) {
        if (sql) {
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
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "delete from inventario where codigo=" + cod + " and bar_idbar=" + bar + "";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public DefaultComboBoxModel getBares(boolean sql) {
        DefaultComboBoxModel com = new DefaultComboBoxModel();
        if (sql) {
            try {
                String q = "{call getBares()}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(q);
                ResultSet rs = cstmt.executeQuery();
                while (rs.next()) {
                    com.addElement(rs.getString("nombre"));
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "select nombre from bar";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    com.addElement(rs.getString("nombre"));
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return com;
    }

    public DefaultTableModel getEmpleado(boolean sql) {
        DefaultTableModel tab = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return true;
            }
        };
        if (sql) {
            try {
                String p = "{?=call getCountEmpleado()}";
                String q = "{call getEmpleados()}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(p);
                cstmt.registerOutParameter(1, Type.INT);
                cstmt.execute();
                int j = cstmt.getInt(1);
                cstmt = this.conexionSQL().prepareCall(q);
                ResultSet rs = cstmt.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[j][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            int a = 0;
            try {    //creamos la sentencia sql para insertar un contacto

                String p = "select count(*) from empleado order by dni";
                PreparedStatement ps = this.conexionLITE().prepareStatement(p);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt(1);
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            String q = "select dni, nombre, domicilio from empleado order by dni";
            try {
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.print(e.getMessage());
            }
        }
        return tab;
    }

    public DefaultTableModel getProductos(boolean sql) {
        DefaultTableModel tab = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        if (sql) {
            try {
                String p = "{?=call getCountProductos()}";
                String q = "{call getProductos()}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(p);
                cstmt.registerOutParameter(1, Type.INT);
                cstmt.execute();
                int j = cstmt.getInt(1);
                cstmt = this.conexionSQL().prepareCall(q);
                ResultSet rs = cstmt.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[j][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            int a = 0;

            try {    //creamos la sentencia sql para insertar un contacto

                String q = "select count(*) from productos";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt(1);
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            String q = "select idproductos, nombre, proveedor, precio from productos";
            try {
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.print(e.getMessage());
            }
        }
        return tab;
    }

    public DefaultTableModel getBar(boolean sql) {
        DefaultTableModel tab = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        if (sql) {
            try {
                String p = "{?=call getCountBar()}";
                String q = "{call getBar()}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(p);
                cstmt.registerOutParameter(1, Type.INT);
                cstmt.execute();
                int j = cstmt.getInt(1);
                cstmt = this.conexionSQL().prepareCall(q);
                ResultSet rs = cstmt.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[j][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } else {
            int a = 0;
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "select count(*) from bar";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt(1);
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            String q = "select idbar, nombre, domicilio, fechaapertura as fecha_apertura, horario, diasapertura as dias_apertura, licenciafiscal as licencia_fiscal from bar order by idbar";
            try {
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.print(e.getMessage());
            }
        }
        return tab;
    }

    public DefaultListModel getEmpleadoList(String nom, String dn, boolean sql) {
        DefaultListModel list = new DefaultListModel();
        if (sql) {
            try {
                String q = "{call getEmpleadoList(?, ?)}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(q);
                cstmt.setString(1, nom);
                cstmt.setString(2, dn);
                ResultSet rs = cstmt.executeQuery();
                while (rs.next()) {
                    list.addElement(rs.getString(1) + "-" + rs.getString(2));
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "select dni, nombre from empleado where nombre like '" + nom + "' or dni like '" + dn + "'";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    list.addElement(res.getString(1) + "-" + res.getString(2));
                }
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return list;
    }

    public DefaultListModel getProductoList(String pro, boolean sql) {
        DefaultListModel list = new DefaultListModel();
        if (sql) {
            try {
                String q = "{call getProductoList(?)}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(q);
                cstmt.setString(1, pro);
                ResultSet rs = cstmt.executeQuery();
                while (rs.next()) {
                    list.addElement(rs.getString(1));
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "select nombre from productos where proveedor='" + pro + "'";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    list.addElement(res.getString(1));
                }
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return list;
    }

    public DefaultTableModel getEmpleadoBar(String ide, boolean sql) {
        DefaultTableModel tab = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        if (sql) {
            try {
                String p = "{?=call getCountEmpleadoBar(?)}";
                String q = "{call getEmpleadoBar(?)}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(p);
                cstmt.registerOutParameter(1, Type.INT);
                cstmt.setInt(2, Integer.parseInt(ide));
                cstmt.execute();
                int j = cstmt.getInt(1);
                cstmt = this.conexionSQL().prepareCall(q);
                cstmt.setInt(1, Integer.parseInt(ide));
                ResultSet rs = cstmt.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[j][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            int a = 0;

            try {    //creamos la sentencia sql para insertar un contacto

                String q = "select count(*) from empleado join bar_has_empleado on dni=empleado_dni where bar_idbar=" + ide;
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt(1);
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            String q = "select dni, nombre, domicilio, rol from empleado join bar_has_empleado on dni=empleado_dni where bar_idbar=" + ide;
            try {
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.print(e.getMessage());
            }
        }
        return tab;
    }

    public DefaultComboBoxModel rol() {
        String[] a = {"Titular", "Gerente", "Cocinero", "Camarero", "Barman"};
        return new DefaultComboBoxModel(a);
    }

    public DefaultTableModel getInventario(String ide, boolean sql) {
        DefaultTableModel tab = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                }
                return false;
            }
        };
        if (sql) {
            try {
                String p = "{?=call getCountInventario(?)}";
                String q = "{call getInventario(?)}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(p);
                cstmt.registerOutParameter(1, Type.INT);
                cstmt.setInt(2, Integer.parseInt(ide));
                cstmt.execute();
                int j = cstmt.getInt(1);
                cstmt = this.conexionSQL().prepareCall(q);
                cstmt.setInt(1, Integer.parseInt(ide));
                ResultSet rs = cstmt.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[j][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            int a = 0;

            try {    //creamos la sentencia sql para insertar un contacto

                String q = "select count(*) from inventario where bar_idbar=" + ide;
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt(1);
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            String q = "select codigo, nombre, cantidad, precio from inventario  where bar_idbar=" + ide;
            try {
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.print(e.getMessage());
            }
        }
        return tab;
    }

    public DefaultTableModel getPedidos(String ide, boolean sql) {
        DefaultTableModel tab = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        if (sql) {
            try {
                String p = "{?=call getCountPedidos(?)}";
                String q = "{call getPedidos(?)}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(p);
                cstmt.registerOutParameter(1, Type.INT);
                cstmt.setInt(2, Integer.parseInt(ide));
                cstmt.execute();
                int j = cstmt.getInt(1);
                cstmt = this.conexionSQL().prepareCall(q);
                cstmt.setInt(1, Integer.parseInt(ide));
                ResultSet rs = cstmt.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[j][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            int a = 0;

            try {    //creamos la sentencia sql para insertar un contacto

                String q = "select count(*) from inventario_pedidos join productos on inventario_codigo=idproductos where pedidos_id=" + ide;
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet res = ps.executeQuery();
                res.next();
                a = res.getInt(1);
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            String q = "select nombre, precio, cantidad from inventario_pedidos join productos on inventario_codigo=idproductos where pedidos_id=" + ide;
            try {
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String column[] = new String[rsmd.getColumnCount()];
                String data[][] = new String[a][rsmd.getColumnCount()];
                for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                    column[i] = rsmd.getColumnName(i + 1);
                }
                int k = 0;
                while (rs.next()) {
                    for (int i = 0; i <= rsmd.getColumnCount() - 1; i++) {
                        data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                    }
                    k++;
                }
                tab.setDataVector(data, column);
            } catch (Exception e) {
                System.err.print(e.getMessage());
            }

        }
        return tab;
    }

    public DefaultListModel getPedidosList(String ide, boolean sql) {
        DefaultListModel list = new DefaultListModel();
        if (sql) {
            try {
                String q = "{call getPedidosList(?)}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(q);
                cstmt.setString(1, ide);
                ResultSet rs = cstmt.executeQuery();
                while (rs.next()) {
                    list.addElement(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4));
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "select distinct id, fecha, proveedor, precio from inventario_pedidos join pedidos on pedidos_id=id where inventario_bar_idbar=" + ide;
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.addElement(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4));
                }
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }
        return list;
    }

    public String[] getBarString(String nom, boolean sql) {
        String[] a = new String[3];
        if (sql) {
            try {
                String q = "{call getBarString(?)}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(q);
                cstmt.setString(1, nom);
                ResultSet rs = cstmt.executeQuery();
                rs.next();
                a[0] = rs.getString(2);
                a[1] = rs.getString(3);
                a[2] = rs.getString(1);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            try {

                String q = "select idbar, nombre, domicilio from bar where nombre='" + nom + "'";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                rs.next();
                a[0] = rs.getString(2);
                a[1] = rs.getString(3);
                a[2] = rs.getString(1);

                ps.close();

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return a;
    }

    public boolean actualizarPrecioInventario(String ide, String bar, String pre, boolean sql) {
        if (sql) {
            try {
                String q = "{call actualizarPrecioInventario(?, ?, ?)}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(q);
                cstmt.setInt(1, Integer.parseInt(ide));
                cstmt.setInt(2, Integer.parseInt(bar));
                cstmt.setDouble(3, Double.parseDouble(pre));
                cstmt.execute();
                return true;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return false;
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "update inventario set precio=" + pre + " where codigo=" + ide + " and bar_idbar=" + bar + "";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ps.execute();
                ps.close();
                return true;     //si no salta ninguna excepcion se ha insertado el contacto
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        }
    }

    public DefaultComboBoxModel getProveedores(boolean sql) {
        DefaultComboBoxModel com = new DefaultComboBoxModel();
        if (sql) {
            try {
                String q = "{call getProveedores()}";
                CallableStatement cstmt = this.conexionSQL().prepareCall(q);
                ResultSet rs = cstmt.executeQuery();
                while (rs.next()) {
                    com.addElement(rs.getString("proveedor"));
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            try {    //creamos la sentencia sql para insertar un contacto

                String q = "select distinct proveedor from productos";
                PreparedStatement ps = this.conexionLITE().prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    com.addElement(rs.getString("proveedor"));
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return com;
    }

    public boolean vacio(String a, String b, String c, String d) {
        return !(a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty());
    }
    
    public void JasperReport(boolean sql) {
        Connection con;
        String reportSource = "plantilla/plantilla.jrxml";
        String reportPDF;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fecha", (new java.util.Date()).toString());
        if (sql) {
            con = this.conexionSQL();
            reportPDF = "informes/InformeNorte.pdf";
            params.put("titulo", "RESUMEN DATOS DE BARES NORTE.");
        } else {
            con = this.conexionLITE();
            reportPDF = "informes/InformeSur.pdf";
            params.put("titulo", "RESUMEN DATOS DE BARES SUR.");
        }
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint MiInforme = JasperFillManager.fillReport(jasperReport, params, con);
            JasperViewer.viewReport(MiInforme);
            JasperExportManager.exportReportToPdfFile(MiInforme, reportPDF);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
        public void generarFactura(String fecha, String idpedido, String pro, boolean sql){
            Connection con;
        String reportSource = "plantilla/Factura.jrxml";
        
        String reportPDF="informes/Factura"+fecha+".pdf";
        Map<String, Object> params = new HashMap<String, Object>();
         params.put("titulo", "FACTURA "+pro+".");
        params.put("fecha", fecha);
        params.put("idpedido", idpedido);
        if (sql) {
            con = this.conexionSQL();           
        } else {
            con = this.conexionLITE();
        }
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint MiInforme = JasperFillManager.fillReport(jasperReport, params, con);
            JasperViewer.viewReport(MiInforme);
            JasperExportManager.exportReportToPdfFile(MiInforme, reportPDF);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
