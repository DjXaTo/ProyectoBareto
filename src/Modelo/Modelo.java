package Modelo;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

public class Modelo extends Conexion {

    public Modelo() {
    }

    public boolean hacerPedido(ArrayList nombres, ArrayList cant, String bar, String prov, boolean sql) {
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

    public boolean insertarProducto(String pro, String pre, String nom, boolean sql) {
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

    public boolean actualizarProducto(String pro, String nom, String idpro, String pre, boolean sql) {
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

    public boolean borrarProducto(String idpro, boolean sql) {
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

    public boolean insertaBar(String nom, String dom, String hor, String dias, String lic, boolean sql) {
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

    public boolean borrarBar(String ide, boolean sql) {
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

    public boolean crearEmple(String dn, String nom, String dom, boolean sql) {
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

    public boolean actualizarEmple(String dn, String nom, String dom, boolean sql) {
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
    }

    public boolean borrarEmple(String dn, boolean sql) {
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

    public boolean asignarEmpleBar(String ide, String dn, String ro, boolean sql) {
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

    public boolean borrarEmpleBar(String dn, String ide, boolean sql) {
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

    public boolean actualizarEmpleBar(String ide, String dn, String ro, boolean sql) {
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

    public boolean gastar(String cant, String cod, String bar, boolean sql) {
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

    public boolean borrarInventario(String cod, String bar, boolean sql) {
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

    public DefaultComboBoxModel getBares(boolean sql) {
        DefaultComboBoxModel com = new DefaultComboBoxModel();
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
        return com;
    }

    public DefaultTableModel getEmpleado(boolean sql) {
        DefaultTableModel tab = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        try {
            String p = "{?=call getCountEmpleado()}";
            String q = "{call getEmpleado()}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(p);
            cstmt.registerOutParameter(1, Type.INT);
            cstmt.execute();
            int j = cstmt.getInt(1);
            cstmt = this.conexionSQL().prepareCall(q);
            ResultSet rs = cstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            String column[] = new String[rsmd.getColumnCount()];
            String data[][] = new String[j][rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            tab.setDataVector(data, column);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return tab;
    }

    public DefaultTableModel getProductos(boolean sql) {
        DefaultTableModel tab = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            tab.setDataVector(data, column);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return tab;
    }

    public DefaultTableModel getBar(boolean sql) {
        DefaultTableModel tab = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
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
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            tab.setDataVector(data, column);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return tab;
    }

    public DefaultListModel getEmpleadoList(String nom, String dn, boolean sql) {
        DefaultListModel list = new DefaultListModel();
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
        return list;
    }

    public DefaultListModel getProductoList(String pro, boolean sql) {
        DefaultListModel list = new DefaultListModel();
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
        return list;
    }

    public DefaultTableModel getEmpleadoBar(String ide, boolean sql) {
        DefaultTableModel tab = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            tab.setDataVector(data, column);
        } catch (Exception e) {
            System.err.println(e.getMessage());
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
                if(column==3){
                    return true;
                }
                return false;
            }
        };
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
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            tab.setDataVector(data, column);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return tab;
    }

    public DefaultTableModel getPedidos(String ide, boolean sql) {
        DefaultTableModel tab = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                column[i] = rsmd.getColumnName(i + 1);
            }
            int k = 0;
            while (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    data[k][i] = rs.getString(rsmd.getColumnName(i + 1));
                }
                k++;
            }
            tab.setDataVector(data, column);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return tab;
    }

    public DefaultListModel getPedidosList(String ide, boolean sql) {
        DefaultListModel list = new DefaultListModel();
        try {
            String q = "{call getPedidosList(?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setString(1, ide);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                list.addElement(rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    
    public String[]  getBarString(String nom, boolean sql) {
        String[] a=new String[3];
        try {
            String q = "{call getBarString(?)}";
            CallableStatement cstmt = this.conexionSQL().prepareCall(q);
            cstmt.setString(1, nom);
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            a[0]=rs.getString(2);
            a[1]=rs.getString(3);
            a[2]=rs.getString(1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return a;
    }
    
    public boolean actualizarPrecioInventario(String ide, String bar, String pre, boolean sql) {
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
    }

    public boolean vacio(String a, String b, String c, String d, String e) {
        return !(a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || e.isEmpty());
    }
}
