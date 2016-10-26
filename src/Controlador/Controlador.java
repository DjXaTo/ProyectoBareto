
package Controlador;

import Modelo.Modelo;
import Vista.Interfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controlador implements ActionListener, MouseListener, PropertyChangeListener, ChangeListener, KeyListener{
    Interfaz vis;
    Modelo mod=new Modelo();
    boolean sql;
    public Controlador(Interfaz vi) {
        vis=vi;
    }

    enum btn{
      //dialogSelecbar
        selectbar,
        cancelselectbar,
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
        combocontratar,
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
        hacerpedido
        //
    }
    
    public void iniciar(){
       //dialogSelecbar
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
        vis.txtBusqueda.addPropertyChangeListener(this);
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
        vis.comboContratar.setActionCommand("combocontratar");
        vis.comboContratar.addActionListener(this);
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
        //jframe pedidos
        vis.listPedidoFecha.addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       switch(btn.valueOf(e.getActionCommand())){
           //dialogSelecbar
           case selectbar:
               String[] a=mod.getBarString((String)vis.comboBares.getSelectedItem(), sql);
               vis.txtNombreBar.setText(a[0]);
               vis.txtDirecBar.setText(a[1]);
               vis.txtIdBar.setText(a[2]);
               vis.tablaEmple.setModel(mod.getEmpleadoBar(vis.txtIdBar.getText(), sql));
               vis.tablaInventario.setModel(mod.getInventario(vis.txtIdBar.getText(), sql));
               vis.listPedidoFecha.setModel(mod.getPedidosList(vis.txtIdBar.getText(), sql));
               vis.tablaPedidos.setModel(mod.getPedidos("0", sql));
               break;
           case cancelselectbar:
               if(vis.txtNombreBar.getText().isEmpty()){
                   System.exit(0);
               }else{
                   vis.dialogSelecBar.dispose();
               }
               break;
                //dialogAdministrarBares
           case añadirbar:
               if(mod.vacio(vis.txtLicencia.getText(), vis.txtBarDomicilioNuevo.getText(), vis.txtNombreBarNuevo.getText(), "u","u") && vis.txtLicencia.isEditValid()){
                   try{
                   String hora=String.valueOf(vis.hora1.getValue())+":"+String.valueOf(vis.minuto1.getValue())+"-"+String.valueOf(vis.hora2.getValue())+":"+String.valueOf(vis.minuto2.getValue());
                   if(mod.insertaBar(vis.txtNombreBarNuevo.getText(), vis.txtBarDomicilioNuevo.getText(), hora, String.valueOf(vis.txtDias.getValue()), String.valueOf(vis.txtLicencia.getText()), sql)){
                       vis.tablaBar.setModel(mod.getBar(sql));
                       vis.txtLicencia.setText(""); 
                       vis.txtBarDomicilioNuevo.setText("");
                       vis.txtNombreBarNuevo.setText("");
                   }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error o los campos son muy largos");   
                   }
                   }catch(Exception n){
                       JOptionPane.showMessageDialog(null, "hora y dias sin letras por favor");
                   }
               }
               break;
           case borrarbar:
               if(vis.tablaBar.getSelectedRow()>-1){
               if(mod.borrarBar(String.valueOf(vis.tablaBar.getValueAt(vis.tablaBar.getSelectedRow(), 0)), sql)){
                    vis.tablaBar.setModel(mod.getBar(sql));
               }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error");   
               }
                   }
               break;
           case izquierdatitular:
               if(vis.listEmpleadosParaBar.getSelectedIndex()>-1){
                   DefaultListModel l=Lista.extraerDefaultList(vis.listNuevoTitular);
                   l.addElement(vis.listEmpleadosParaBar.getSelectedValue());
                   vis.listNuevoTitular.setModel(l);
               }
               break;
           case derechatitular:
               if(vis.listNuevoTitular.getSelectedIndex()>-1){
                   DefaultListModel l=Lista.extraerDefaultList(vis.listNuevoTitular);
                   l.removeElement(vis.listNuevoTitular.getSelectedValue());
                   vis.listNuevoTitular.setModel(l);
               }
               break;
           case  nuevoempleadobar:
               vis.dialogAñadirEmpleado.setSize(250, 250);
               vis.dialogAñadirEmpleado.setVisible(true);
               break;
       }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
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
    public void propertyChange(PropertyChangeEvent evt) {
        
    }
   
      @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
   
    }
    
    public void stateChanged(ChangeEvent e) {
       if(vis.rbNorte.isSelected()){
           sql=true;
       }else{
           sql=false;
       }
       vis.comboBares.setModel(mod.getBares(sql));
    }
}
