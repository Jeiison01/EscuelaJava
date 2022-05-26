/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuela;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Jeiison
 */
public class Conexion {
    Connection conect = null;
    public Connection conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/escuela?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            JOptionPane.showMessageDialog(null, "Conectado");
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexion"+ e);
        }
        return conect;
    }
}
