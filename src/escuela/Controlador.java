/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuela;

import com.mysql.cj.protocol.Resultset;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Jeiison
 */
public class Controlador implements ActionListener {

    Vista view = new Vista();
    Modelo model = new Modelo();

    public Controlador() {

        this.view.btConectar.addActionListener(this);
        this.view.btGuardar.addActionListener(this);
        this.view.btLimpiar.addActionListener(this);
        this.view.btBuscar.addActionListener(this);
        this.view.btCargar.addActionListener(this);
        this.view.btEliminar.addActionListener(this);
        this.view.btModificar.addActionListener(this);
        this.view.btcrearTabla.addActionListener(this);
    }

    public void iniciar() {
        view.setBounds(0, 0, 450, 600);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btConectar) {
            Conexion con = new Conexion();
            con.conexion();
        }
        if (e.getSource() == view.btLimpiar) {
            view.textNombre.setText(null);
            view.textTele.setText(null);
            view.textId.setText(null);
        }
        if (e.getSource() == view.btCargar) {
            String[] titulos = {"Nombre", "Telefono"};
            String[] registros = new String[2];

            String sql = "SELECT * FROM persona";
            view.md = new DefaultTableModel(null, titulos);

            Conexion con = new Conexion();
            Connection cn = con.conexion();
            Statement st;
            try {
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    registros[0] = rs.getString("nombre");
                    registros[1] = rs.getString("telefono");
                    view.md.addRow(registros);
                }
                view.tabla.setModel(view.md);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        if (e.getSource() == view.btGuardar) {
            Conexion con = new Conexion();
            Connection cn = con.conexion();
            String nom;
            int id, tel;
            String sql = "";

            nom = view.textNombre.getText();
            tel = Integer.parseInt(view.textTele.getText());
            id = Integer.parseInt(view.textId.getText());
            sql = "INSERT INTO persona VALUES (?,?,?)";
            try {
                PreparedStatement psd = cn.prepareStatement(sql);

                psd.setString(2, nom);
                psd.setInt(3, tel);
                psd.setInt(1, id);

                int n = psd.executeUpdate();

                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == view.btBuscar) {
            Conexion con = new Conexion();
            Connection cn = con.conexion();
            String sql = "";

            String ID = view.textId.getText();
            sql = "SELECT * FROM persona WHERE id=?";

            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, ID);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    view.textNombre.setText(rs.getString("nombre"));
                    view.textTele.setText(rs.getString("telefono"));
                } else {
                    JOptionPane.showMessageDialog(null, "encontrado");
                }
            } catch (SQLException ex) {
            }
        }
        if (e.getSource() == view.btModificar) {
            Conexion con = new Conexion();
            Connection cn = con.conexion();

            String ID = view.textId.getText().trim();
            String nom, tel;
            String sql = "";

            nom = view.textNombre.getText();
            tel = view.textTele.getText();
            sql = "UPDATE persona SET nombre = ?, telefono = ? WHERE id=" + ID;
            try {
                PreparedStatement psd = cn.prepareStatement(sql);

                psd.setString(1, nom);
                psd.setString(2, tel);

                int n = psd.executeUpdate();

                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == view.btEliminar) {
            Conexion con = new Conexion();
            Connection cn = con.conexion();

            String ID = view.textId.getText();
            String sql = "";

            sql = "DELETE FROM persona WHERE id=?";
            try {
                PreparedStatement psd = cn.prepareStatement(sql);

                psd.setString(1, ID);

                int n = psd.executeUpdate();

                view.textId.setText("");

                view.textNombre.setText("");
                view.textTele.setText("");

                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                }
            } catch (SQLException ex) {

            }
        }
        if (e.getSource() == view.btcrearTabla) {
            Conexion con = new Conexion();
            Connection cn = con.conexion();

            String primary = "";
            int contador = 0;

            Scanner sc = new Scanner(System.in);
            String sql = "";
            String nombretabla = JOptionPane.showInputDialog("Ingrese el nombre de la tabla a crear");
            int numcolum = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de columnas: "));

            sql = "CREATE TABLE " + nombretabla + "(";
            String tdato[] = new String[numcolum];
            String nullono[] = new String[numcolum];
            String nomcolum[] = new String[numcolum];
            int cant[] = new int[numcolum];
//Recoge informacion
            for (int i = 0; i < numcolum; i++) {

                int tipodato = Integer.parseInt(JOptionPane.showInputDialog("Elija un tipo de dato:\n1-Para int \n2-Para varchar"));
                if (tipodato == 1) {
                    tdato[i] = "int";
                } else if (tipodato == 2) {
                    tdato[i] = "varchar";
                }
                nomcolum[i] = JOptionPane.showInputDialog("Ingrese el nombre de la columna" + (i + 1));
                cant[i] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el espacio el numero maximo de caracteres que tendra la columna: " + (i + 1)));
                int esnull = Integer.parseInt(JOptionPane.showInputDialog("Ingrese: \n1-Para que sea un valor NULL\n2-Para que NO sea un valor NULL: " + (i + 1)));
                if (esnull == 1) {
                    nullono[i] = "NULL";
                } else if (esnull == 2) {
                    nullono[i] = "NOT NULL";
                }
            }
            //Guarda la info
            do {
                sql += nomcolum[contador] + " " + tdato[contador] + "(" + cant[contador] + ")" + " " + nullono[contador];
                if (contador < numcolum) {
                    sql += ",";
                    contador++;
                }
            } while (contador != numcolum);
            int llave = Integer.parseInt(JOptionPane.showInputDialog("Tiene llave primaria? Ingrese 1-Para SI o 2-Para NO "));
            if (llave == 1) {
                String pKey = JOptionPane.showInputDialog("Ingrese el nombre de la columna que sera la llave PRIMARIA");
                primary = "PRIMARY KEY(" + pKey + ")";
            } else if (llave == 2) {
            }

            sql += " " + primary + ")";
            JOptionPane.showMessageDialog(null, sql);
            try {
                Statement psd = cn.createStatement();
                int n = psd.executeUpdate(sql);
                
                if (psd.execute(sql) == true) {
                    JOptionPane.showMessageDialog(null, "Tabla creada");
                }
            } catch (SQLException ex) {

            }
        }
    }
}
