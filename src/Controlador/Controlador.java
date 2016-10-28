
package Controlador;

import Modelo.Modelo;
import Vista.Interfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controlador implements ActionListener, MouseListener, ChangeListener, KeyListener{
    Interfaz vis;
    Modelo mod=new Modelo();
    boolean sql;
    public Controlador(Interfaz vi) {
        vis=vi;
    }

    //Enum contiene los alias para todos los componentes que contengan un actionperformed
    enum btn{
      //dialogSelecbar
        selectbar,
        cancelselectbar,
        combobar,
        //dialogAdministrarBares
        añadirbar,
        borrarbar,
        izquierdatitular,
        derechatitular,
        nuevoempleadobar,
        //dialogEmpleados
        añadirempleado,
        borrarempleado,
        //dialogAñadirEmpleados
        aceptarnuevoempleado,
        cancelarnuevoempleado,
        //dialogProductos
        añadirproducto,
        borrarproducto,
        actualizarproducto,
        //dialogHacerPedido
        comboproveedores,
        añadirproductopedido,
        quitarproductopedido,
        aceptarpedido,
        cancelarpedido,
        //dialogContratar
        aceptarcontratar,
        cancelarcontratar,
        //Jframe menu bar
        menuselecbar,
        menuadminbar,
        //jframe menu persona
        menulistapersona,
        //jframe menu producto
        menuproductos,
        //jframe menu salir
        menusalir,
        //jframe empleado
        contratar,
        despedir,
        cambiarpuesto,
        //jframe inventario
        quitarcantidad,
        borrarinventario,
        hacerpedido,
        menugenerar,
        factura
        
    }

    public void iniciar() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vis);
            SwingUtilities.updateComponentTreeUI(vis.dialogAdministrarBares);
            SwingUtilities.updateComponentTreeUI(vis.dialogAñadirEmpleado);
            SwingUtilities.updateComponentTreeUI(vis.dialogContratar);
            SwingUtilities.updateComponentTreeUI(vis.dialogEmpleados);
            SwingUtilities.updateComponentTreeUI(vis.dialogHacerPedido);
            SwingUtilities.updateComponentTreeUI(vis.dialogProductos);
            SwingUtilities.updateComponentTreeUI(vis.dialogSelecBar);
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        //dialogSelecbar
        vis.comboBares.setActionCommand("combobar");
        vis.comboBares.addActionListener(this);
        vis.btnSelectBar.setActionCommand("selectbar");
        vis.btnSelectBar.addActionListener(this);
        vis.btnCancelSelectBar.setActionCommand("cancelselectbar");
        vis.btnCancelSelectBar.addActionListener(this);
        vis.rbNorte.addChangeListener(this);
        vis.rbSur.addChangeListener(this);
        //dialogAdministrarBares
        vis.btnAñadirBar.setActionCommand("añadirbar");
        vis.btnAñadirBar.addActionListener(this);
        vis.btnBorrarBar.setActionCommand("borrarbar");
        vis.btnBorrarBar.addActionListener(this);
        vis.btnIzquierdaTitular.setActionCommand("izquierdatitular");
        vis.btnIzquierdaTitular.addActionListener(this);
        vis.btnDerechaTitular.setActionCommand("derechatitular");
        vis.btnDerechaTitular.addActionListener(this);
        vis.btnNuevoEmpleadoBar.setActionCommand("nuevoempleadobar");
        vis.btnNuevoEmpleadoBar.addActionListener(this);
        vis.txtBusqueda.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
             vis.listEmpleadosParaBar.setModel(mod.getEmpleadoList(vis.txtBusqueda.getText()+"%", vis.txtBusqueda.getText()+"%", sql));
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
             vis.listEmpleadosParaBar.setModel(mod.getEmpleadoList(vis.txtBusqueda.getText()+"%", vis.txtBusqueda.getText()+"%", sql));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
             vis.listEmpleadosParaBar.setModel(mod.getEmpleadoList(vis.txtBusqueda.getText()+"%", vis.txtBusqueda.getText()+"%", sql));
            }
    });
        //dialogEmpleados
        vis.btnAñadirEmpleado.setActionCommand("añadirempleado");
        vis.btnAñadirEmpleado.addActionListener(this);
        vis.btnBorrarEmpleado.setActionCommand("borrarempleado");
        vis.btnBorrarEmpleado.addActionListener(this);
        vis.tablaEmpleados.addKeyListener(this);
        //dialogAñadirEmpleados
        vis.btnAceptarNuevoEmpleado.setActionCommand("aceptarnuevoempleado");
        vis.btnAceptarNuevoEmpleado.addActionListener(this);
        vis.btnCancelarNuevoEmpleado.setActionCommand("cancelarnuevoempleado");
        vis.btnCancelarNuevoEmpleado.addActionListener(this);
        //dialogProductos
        vis.btnAñadirProducto.setActionCommand("añadirproducto");
        vis.btnAñadirProducto.addActionListener(this);
        vis.btnBorrarProducto.setActionCommand("borrarproducto");
        vis.btnBorrarProducto.addActionListener(this);
        vis.btnActualizarProducto.setActionCommand("actualizarproducto");
        vis.btnActualizarProducto.addActionListener(this);
        vis.tablaProducto.addMouseListener(this);
        //dialogHacerPedido
        vis.comboProveedores.setActionCommand("comboproveedores");
        vis.comboProveedores.addActionListener(this);
        vis.btnAñadirProductoPedido.setActionCommand("añadirproductopedido");
        vis.btnAñadirProductoPedido.addActionListener(this);
        vis.btnQuitarProductoPedido.setActionCommand("quitarproductopedido");
        vis.btnQuitarProductoPedido.addActionListener(this);
        vis.btnAceptarPedido.setActionCommand("aceptarpedido");
        vis.btnAceptarPedido.addActionListener(this);
        vis.btnCancelarPedido.setActionCommand("cancelarpedido");
        vis.btnCancelarPedido.addActionListener(this);
        //dialogContratar
        vis.btnAceptarContratar.setActionCommand("aceptarcontratar");
        vis.btnAceptarContratar.addActionListener(this);
        vis.btnCancelarContratar.setActionCommand("cancelarcontratar");
        vis.btnCancelarContratar.addActionListener(this);
        //Jframe menu bar
        vis.miSelectBar.setActionCommand("menuselecbar");
        vis.miSelectBar.addActionListener(this);
        vis.miAdminBar.setActionCommand("menuadminbar");
        vis.miAdminBar.addActionListener(this);
        //jframe menu persona
        vis.miListarPersonas.setActionCommand("menulistapersona");
        vis.miListarPersonas.addActionListener(this);
        //jframe menu producto
        vis.menuProductos.setActionCommand("menuproductos");
        vis.menuProductos.addActionListener(this);
        //jframe menu salir
        vis.miSalir.setActionCommand("menusalir");
        vis.miSalir.addActionListener(this);
        //jframe empleado
        vis.btnContratarEmple.setActionCommand("contratar");
        vis.btnContratarEmple.addActionListener(this);
        vis.btnDespedirEmple.setActionCommand("despedir");
        vis.btnDespedirEmple.addActionListener(this);
        vis.btnCambiarPuestoEmple.setActionCommand("cambiarpuesto");
        vis.btnCambiarPuestoEmple.addActionListener(this);
        vis.tablaEmple.addMouseListener(this);
        //jframe inventario
        vis.btnQuitarCantidad.setActionCommand("quitarcantidad");
        vis.btnQuitarCantidad.addActionListener(this);
        vis.btnBorrarInventario.setActionCommand("borrarinventario");
        vis.btnBorrarInventario.addActionListener(this);
        vis.btnHacerPedido.setActionCommand("hacerpedido");
        vis.btnHacerPedido.addActionListener(this);
        vis.tablaInventario.addKeyListener(this);
        vis.menuGenerar.setActionCommand("menugenerar");
        vis.menuGenerar.addActionListener(this);
        vis.btnFactura.setActionCommand("factura");
        vis.btnFactura.addActionListener(this);
        //jframe pedidos
        vis.rbNorte.setSelected(true);
        vis.listPedidoFecha.addMouseListener(this);
        vis.comboContratar.setModel(mod.rol());
        vis.comboPuestoEmple.setModel(mod.rol());
        vis.dialogSelecBar.setSize(370, 240);
        vis.dialogSelecBar.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (btn.valueOf(e.getActionCommand())) {
            //dialogSelecbar
            case combobar:
                String[] a = mod.getBarString((String) vis.comboBares.getSelectedItem(), sql);
                vis.txtNombreBar.setText(a[0]);
                vis.txtDirecBar.setText(a[1]);
                vis.txtIdBar.setText(a[2]);
                vis.tablaEmple.setModel(mod.getEmpleadoBar(vis.txtIdBar.getText(), sql));
                vis.tablaInventario.setModel(mod.getInventario(vis.txtIdBar.getText(), sql));
                vis.listPedidoFecha.setModel(mod.getPedidosList(vis.txtIdBar.getText(), sql));
                vis.tablaPedidos.setModel(mod.getPedidos("0", sql));
                break;
            case selectbar:
                vis.comboBares.setModel(mod.getBares(sql));
                //con el dispose cerramos el jDialog
                vis.dialogSelecBar.dispose();
                String[] f = mod.getBarString((String) vis.comboBares.getSelectedItem(), sql);
                vis.txtNombreBar.setText(f[0]);
                vis.txtDirecBar.setText(f[1]);
                vis.txtIdBar.setText(f[2]);
                vis.tablaEmple.setModel(mod.getEmpleadoBar(vis.txtIdBar.getText(), sql));
                vis.tablaInventario.setModel(mod.getInventario(vis.txtIdBar.getText(), sql));
                vis.listPedidoFecha.setModel(mod.getPedidosList(vis.txtIdBar.getText(), sql));
                vis.tablaPedidos.setModel(mod.getPedidos("0", sql));
                vis.setVisible(true);
                break;
            case cancelselectbar:
                if (vis.txtNombreBar.getText().isEmpty()) {
                    System.exit(0);
                } else {
                    vis.dialogSelecBar.dispose();
                }
                break;
            //dialogAdministrarBares
            case añadirbar:
                if (vis.listNuevoTitular.getModel().getSize() != 0) {
                    if (mod.vacio(vis.txtLicencia.getText(), vis.txtBarDomicilioNuevo.getText(), vis.txtNombreBarNuevo.getText(), "u") && vis.txtLicencia.isEditValid()) {
                        try {
                            String hora = String.valueOf(vis.hora1.getValue()) + ":" + String.valueOf(vis.minuto1.getValue()) + "-" + String.valueOf(vis.hora2.getValue()) + ":" + String.valueOf(vis.minuto2.getValue());
                            if (mod.insertaBar(vis.txtNombreBarNuevo.getText(), vis.txtBarDomicilioNuevo.getText(), hora, String.valueOf(vis.txtDias.getValue()), String.valueOf(vis.txtLicencia.getText()), sql)) {
                                vis.tablaBar.setModel(mod.getBar(sql));
                                vis.comboBares.setModel(mod.getBares(sql));
                                vis.txtLicencia.setText("");
                                vis.txtBarDomicilioNuevo.setText("");
                                vis.txtNombreBarNuevo.setText("");
                            } else {
                                JOptionPane.showMessageDialog(null, "Ha ocurrido un error o los campos son muy largos o ya existe ese bar");
                            }
                        } catch (Exception n) {
                            JOptionPane.showMessageDialog(null, "hora y dias sin letras por favor");
                        }
                        for (int i = 0; i < Lista.extraerElementos(vis.listNuevoTitular).size(); i++) {
                            //Utilizamos el split para indicar que, mediante " - " vamos a separar los campos para que los guarde como strings
                            String[] emp = Lista.extraerElementos(vis.listNuevoTitular).get(i).split(" - ");
                            if (mod.asignarEmpleBar((String) vis.tablaBar.getValueAt(vis.tablaBar.getRowCount() - 1, 0), emp[0], "Titular", sql)) {

                            } else {
                                JOptionPane.showMessageDialog(vis, "Error al insertar titular");
                            }
                        }
                    }

                } else {
                    JOptionPane.showConfirmDialog(vis, "No hay titulares");

                }
                break;
            case borrarbar:
                if (vis.tablaBar.getSelectedRow() > -1) {
                    if (mod.borrarBar((String) (vis.tablaBar.getValueAt(vis.tablaBar.getSelectedRow(), 0)), sql)) {
                        vis.tablaBar.setModel(mod.getBar(sql));
                        vis.comboBares.setModel(mod.getBares(sql));
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                    }
                }
                break;
            case izquierdatitular:
                if (vis.listEmpleadosParaBar.getSelectedIndex() > -1) {
                    DefaultListModel l = Lista.extraerDefaultList(vis.listNuevoTitular);
                    l.addElement(vis.listEmpleadosParaBar.getSelectedValue());
                    vis.listNuevoTitular.setModel(l);
                }
                break;
            case derechatitular:
                if (vis.listNuevoTitular.getSelectedIndex() > -1) {
                    DefaultListModel l = Lista.extraerDefaultList(vis.listNuevoTitular);
                    l.removeElement(vis.listNuevoTitular.getSelectedValue());
                    vis.listNuevoTitular.setModel(l);
                }
                break;
            case nuevoempleadobar:
                vis.dialogAñadirEmpleado.setSize(250, 250);
                vis.dialogAñadirEmpleado.setVisible(true);
                break;
            //dialogEmpleados
            case añadirempleado:
                vis.dialogAñadirEmpleado.setSize(250, 250);
                vis.dialogAñadirEmpleado.setVisible(true);
                break;
            case borrarempleado:
                if (vis.tablaEmpleados.getSelectedRow() > -1) {
                    if (mod.borrarEmple((String) (vis.tablaEmpleados.getValueAt(vis.tablaEmpleados.getSelectedRow(), 0)), sql)) {
                        vis.tablaEmpleados.setModel(mod.getEmpleado(sql));
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                    }
                }
                break;
            //dialogAñadirEmpleados
            case aceptarnuevoempleado:
                if (mod.vacio(vis.txtDni.getText(), vis.txtDomicilioEmpleado.getText(), vis.txtNombreEmpleado.getText(), "i") && Letras.dni(vis.txtDni.getText())) {
                    if (mod.crearEmple(vis.txtDni.getText(), vis.txtNombreEmpleado.getText(), vis.txtDomicilioEmpleado.getText(), sql)) {
                        vis.tablaEmpleados.setModel(mod.getEmpleado(sql));
                        vis.listEmpleadosParaBar.setModel(mod.getEmpleadoList("%", "%", sql));
                        vis.txtDni.setText("");
                        vis.txtNombreEmpleado.setText("");
                        vis.txtDomicilioEmpleado.setText("");
                        vis.dialogAñadirEmpleado.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Rellene los campos o dni incorrecto");
                }
                break;
            case cancelarnuevoempleado:
                vis.dialogAñadirEmpleado.dispose();
                vis.txtDni.setText("");
                vis.txtNombreEmpleado.setText("");
                vis.txtDomicilioEmpleado.setText("");
                break;
            //dialogProductos
            case añadirproducto:
                if (mod.vacio(vis.txtNombreProducto.getText(), vis.txtPrecioProducto.getText(), vis.txtProveedorProducto.getText(), "o") && vis.txtPrecioProducto.isEditValid()) {
                    if (mod.insertarProducto(vis.txtProveedorProducto.getText(), vis.txtPrecioProducto.getText(), vis.txtNombreProducto.getText(), sql)) {
                        vis.tablaProducto.setModel(mod.getProductos(sql));
                        vis.txtNombreProducto.setText("");
                        vis.txtPrecioProducto.setText("");
                        vis.txtProveedorProducto.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error o ya existe el producto");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Rellene los campos");
                }
                break;
            case borrarproducto:
                if (vis.tablaProducto.getSelectedRow() > -1) {
                    if (mod.borrarProducto((String) vis.tablaProducto.getValueAt(vis.tablaProducto.getSelectedRow(), 0), sql)) {
                        vis.tablaProducto.setModel(mod.getProductos(sql));
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "selecciona una fila");
                }
                break;
            case actualizarproducto:
                if (vis.tablaProducto.getSelectedRow() > -1) {
                    if (mod.vacio(vis.txtNombreProducto.getText(), vis.txtPrecioProducto.getText(), vis.txtProveedorProducto.getText(), "o") && vis.txtPrecioProducto.isEditValid()) {
                        if (mod.actualizarProducto(vis.txtProveedorProducto.getText(), vis.txtNombreProducto.getText(), (String) vis.tablaProducto.getValueAt(vis.tablaProducto.getSelectedRow(), 0), vis.txtPrecioProducto.getText(), sql)) {
                            vis.tablaProducto.setModel(mod.getProductos(sql));
                            vis.txtNombreProducto.setText("");
                            vis.txtPrecioProducto.setText("");
                            vis.txtProveedorProducto.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Ha ocurrido un error o ya existe el producto");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Rellene los campos");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "selecciona una fila");
                }
                break;
            //dialogHacerPedido
            case comboproveedores:
                vis.listProductoPedido.setModel(mod.getProductoList((String) vis.comboProveedores.getSelectedItem(), sql));
                vis.listProductoAPedir.setModel(new DefaultListModel());
                break;
            case añadirproductopedido:
                try {
                    if (vis.listProductoPedido.getSelectedIndex() > -1) {
                        DefaultListModel p = Lista.extraerDefaultList(vis.listProductoAPedir);
                        p.addElement(vis.listProductoPedido.getSelectedValue() + "  -  " + vis.txtCantidadPedido.getValue());
                        DefaultListModel p1 = Lista.extraerDefaultList(vis.listProductoPedido);
                        p1.remove(vis.listProductoPedido.getSelectedIndex());
                        vis.listProductoAPedir.setModel(p);
                        vis.listProductoPedido.setModel(p1);
                    }
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(null, "cantidad sin letras por favor");
                }
                break;
            case quitarproductopedido:
                if (vis.listProductoAPedir.getSelectedIndex() > -1) {
                    DefaultListModel p = Lista.extraerDefaultList(vis.listProductoPedido);
                    String h[] = vis.listProductoAPedir.getSelectedValue().split("  -  ");
                    p.addElement(h[0]);
                    DefaultListModel p1 = Lista.extraerDefaultList(vis.listProductoAPedir);
                    p1.remove(vis.listProductoAPedir.getSelectedIndex());
                    vis.listProductoAPedir.setModel(p1);
                    vis.listProductoPedido.setModel(p);
                }
                break;
            case aceptarpedido:
                ArrayList<String> pe = Lista.extraerElementos(vis.listProductoAPedir);
                if (!pe.isEmpty()) {
                    ArrayList cant = new ArrayList();
                    ArrayList nom = new ArrayList();
                    for (int i = 0; i < pe.size(); i++) {
                        String[] dat = pe.get(i).split("  -  ");
                        nom.add(dat[0]);
                        cant.add(dat[1]);
                    }
                    if (mod.hacerPedido(nom, cant, vis.txtIdBar.getText(), (String) vis.comboProveedores.getSelectedItem(), sql)) {
                        vis.listProductoAPedir.setModel(new DefaultListModel());
                        vis.listProductoPedido.setModel(new DefaultListModel());
                        vis.tablaInventario.setModel(mod.getInventario(vis.txtIdBar.getText(), sql));
                        vis.listPedidoFecha.setModel(mod.getPedidosList(vis.txtIdBar.getText(), sql));
                        vis.dialogHacerPedido.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "lista vacia");
                }
                break;
            case cancelarpedido:
                vis.listProductoPedido.setModel(new DefaultListModel());
                vis.listProductoAPedir.setModel(new DefaultListModel());
                vis.dialogHacerPedido.dispose();
                break;
            //dialogContrata
            case aceptarcontratar:
                if (vis.listContratar.getSelectedIndex() > -1) {
                    String[] emp = vis.listContratar.getSelectedValue().split("-");
                    if (mod.asignarEmpleBar(vis.txtIdBar.getText(), emp[0], (String) vis.comboContratar.getSelectedItem(), sql)) {
                        
                        vis.dialogContratar.dispose();
                        vis.tablaEmple.setModel(mod.getEmpleadoBar(vis.txtIdBar.getText(), sql));
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                    }
                }
                break;
            case cancelarcontratar:
                vis.dialogContratar.dispose();
                break;
            //Jframe menu bar
            case menuselecbar:
                vis.dialogSelecBar.setSize(360, 230);
                vis.dialogSelecBar.setVisible(true);
                break;
            case menuadminbar:
                 vis.tablaBar.setModel(mod.getBar(sql));
                vis.listEmpleadosParaBar.setModel(mod.getEmpleadoList("%", "%", sql));
                vis.dialogAdministrarBares.setSize(790, 500);
                vis.dialogAdministrarBares.setVisible(true);
               
                
                break;
            //jframe menu persona
            case menulistapersona:
                vis.tablaEmpleados.setModel(mod.getEmpleado(sql));
                vis.dialogEmpleados.setSize(790, 350);
                vis.dialogEmpleados.setVisible(true);
                
                break;
            //jframe menu producto
            case menuproductos:
                vis.tablaProducto.setModel(mod.getProductos(sql));
                vis.dialogProductos.setSize(790, 290);
                vis.dialogProductos.setVisible(true);
                
                break;
            //jframe menu salir
            case menusalir:
                System.exit(0);
                break;
            //jframe empleado
            case contratar:
                vis.listContratar.setModel(mod.getEmpleadoList("%", "%", sql));
                vis.dialogContratar.setSize(420, 320);
                vis.dialogContratar.setVisible(true);
                
                break;
            case despedir:
                if (vis.tablaEmple.getSelectedRow() > -1) {
                    if (mod.borrarEmpleBar((String) vis.tablaEmple.getValueAt(vis.tablaEmple.getSelectedRow(), 0), vis.txtIdBar.getText(), sql)) {
                        vis.tablaEmple.setModel(mod.getEmpleadoBar(vis.txtIdBar.getText(), sql));
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una fila");
                }
                break;
            case cambiarpuesto:
                if (vis.tablaEmple.getSelectedRow() > -1) {
                    if (mod.actualizarEmpleBar(vis.txtIdBar.getText(), vis.txtDNIEmple.getText(), (String) vis.comboPuestoEmple.getSelectedItem(), sql)) {
                        vis.tablaEmple.setModel(mod.getEmpleadoBar(vis.txtIdBar.getText(), sql));
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una fila");
                }
                break;
            //jframe inventario
            case quitarcantidad:
                if (vis.tablaInventario.getSelectedRow() > -1) {
                    try {
                        if (mod.gastar(String.valueOf(vis.txtQuitarCantidad.getValue()), (String) vis.tablaInventario.getValueAt(vis.tablaInventario.getSelectedRow(), 0), vis.txtIdBar.getText(), (String) vis.tablaInventario.getValueAt(vis.tablaInventario.getSelectedRow(), 2) ,sql)) {
                            vis.tablaInventario.setModel(mod.getInventario(vis.txtIdBar.getText(), sql));
                        } else {
                            JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                        }
                    } catch (Exception n) {
                        JOptionPane.showMessageDialog(null, "Valor incorrecto");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona fila");
                }
                break;
            case borrarinventario:
                if (vis.tablaInventario.getSelectedRow() > -1) {
                    if (mod.borrarInventario((String) vis.tablaInventario.getValueAt(vis.tablaInventario.getSelectedRow(), 0), vis.txtIdBar.getText(), sql)) {
                        vis.tablaInventario.setModel(mod.getInventario(vis.txtIdBar.getText(), sql));
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "selecciona fila");
                }
                break;
            case hacerpedido:
                vis.comboProveedores.setModel(mod.getProveedores(sql));
                vis.listProductoPedido.setModel(mod.getProductoList((String) vis.comboProveedores.getSelectedItem(), sql));
                vis.dialogHacerPedido.setSize(750, 320);
                vis.dialogHacerPedido.setVisible(true);
                
                break;
            case menugenerar:
                mod.JasperReport(sql);
                break;
            case factura:
                if(vis.listPedidoFecha.getSelectedIndex()>-1){
                    
                    String g[]=vis.listPedidoFecha.getSelectedValue().split(" - ");
                    mod.generarFactura(g[1], g[0], g[2], sql);
                }
            break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(vis.tablaProducto)) {
            if (vis.tablaProducto.getSelectedRow() > -1) {
                vis.txtProveedorProducto.setText((String) vis.tablaProducto.getValueAt(vis.tablaProducto.getSelectedRow(), 2));
                vis.txtPrecioProducto.setText((String) vis.tablaProducto.getValueAt(vis.tablaProducto.getSelectedRow(), 3));
                vis.txtNombreProducto.setText((String) vis.tablaProducto.getValueAt(vis.tablaProducto.getSelectedRow(), 1));
            }
        } else if (e.getSource().equals(vis.tablaEmple)) {
            if (vis.tablaEmple.getSelectedRow() > -1) {
                vis.txtDNIEmple.setText((String) vis.tablaEmple.getValueAt(vis.tablaEmple.getSelectedRow(), 0));
                vis.txtNombreEmple.setText((String) vis.tablaEmple.getValueAt(vis.tablaEmple.getSelectedRow(), 1));
                vis.txtDomicilioEmple.setText((String) vis.tablaEmple.getValueAt(vis.tablaEmple.getSelectedRow(), 2));
            }
        } else if (vis.listPedidoFecha.getSelectedIndex() > -1) {
            String[] h = vis.listPedidoFecha.getSelectedValue().split(" - ");
            vis.tablaPedidos.setModel(mod.getPedidos(h[0], sql));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == VK_ENTER) {
            if (e.getSource().equals(vis.tablaEmpleados)) {
                int a = vis.tablaEmpleados.getSelectedRow();
                if (mod.vacio((String) vis.tablaEmpleados.getValueAt(a, 0), (String) vis.tablaEmpleados.getValueAt(a, 1), (String) vis.tablaEmpleados.getValueAt(a, 2), "o")) {
                    if (mod.actualizarEmple((String) vis.tablaEmpleados.getValueAt(a, 0), (String) vis.tablaEmpleados.getValueAt(a, 1), (String) vis.tablaEmpleados.getValueAt(a, 2), sql)) {
                        vis.tablaEmpleados.setModel(mod.getEmpleado(sql));
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No puede estar vacio");
                }
            } else {
                int a = vis.tablaInventario.getSelectedRow();
                if (mod.vacio((String) vis.tablaInventario.getValueAt(a, 3), "u", "o", "o")) {
                    if (mod.actualizarPrecioInventario((String) vis.tablaInventario.getValueAt(a, 0), vis.txtIdBar.getText(), (String) vis.tablaInventario.getValueAt(a, 3), sql)) {
                        vis.tablaInventario.setModel(mod.getInventario(vis.txtIdBar.getText(), sql));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No puede estar vacio");
                }
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        if (vis.rbNorte.isSelected()) {
            sql = true;
        } else {
            sql = false;
        }
    }
}
