/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuela;

import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Jeiison
 */
public class Vista extends JFrame{
    public JTextField textNombre,textTele, textId;
    public JLabel labelNombre,labelTele;
    public JButton btConectar, btGuardar, btLimpiar, btBuscar, btCargar,btModificar,btEliminar,btcrearTabla;
   
    public JTable tabla;
    public JScrollPane scrollpanel;
    public DefaultTableModel md;
    
    private String [] titulo = new String[]{"Nombres","Telefono"};
    
    public Vista(){
        setLayout(null);
        
        labelNombre = new JLabel ("Nombre: ");
        labelNombre.setBounds(50, 35, 100, 30);
        add(labelNombre);
        
        labelTele = new JLabel("Telefono: ");
        labelTele.setBounds(50, 65, 100, 30);
        add(labelTele);
        
        textNombre = new JTextField();
        textNombre.setBounds(120, 35, 150, 20);
        add(textNombre);
        
        textTele = new JTextField();
        textTele.setBounds(120, 65, 150, 20);
        add(textTele);
        
        textId = new JTextField();
        textId.setBounds(120, 95, 150, 20);
        add(textId);
        
        tabla = new JTable();
        scrollpanel = new JScrollPane(tabla);
        scrollpanel.setBounds(10, 300, 400, 200);
        add(scrollpanel);
        
        btConectar = new JButton("Conectar");
        btConectar.setBounds(10, 100, 100, 30);
        add(btConectar);
        
        btGuardar = new JButton("Guardar");
        btGuardar.setBounds(10, 150, 100, 30);
        add(btGuardar);
        
        btLimpiar = new JButton("Limpiar");
        btLimpiar.setBounds(300, 5, 100, 30);
        add(btLimpiar);
        
        btBuscar = new JButton("Buscar");
        btBuscar.setBounds(300, 40, 100, 30);
        add(btBuscar);
        
        btCargar = new JButton("Cargar");
        btCargar.setBounds(300, 80, 100, 30);
        add(btCargar);
        
        btModificar = new JButton("Modificar");
        btModificar.setBounds(300, 120, 100, 30);
        add(btModificar);
        
        btEliminar = new JButton("Eliminar");
        btEliminar.setBounds(300, 160, 100, 30);
        add(btEliminar);
        
        btcrearTabla = new JButton("Crear Tabla");
        btcrearTabla.setBounds(300, 200, 100, 30);
        add(btcrearTabla);
    }
}
