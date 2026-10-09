package main.java.com.mejiasoft.systeminventario.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.mejiasoft.systeminventario.config.DataBaseConnection;
import main.java.com.mejiasoft.systeminventario.model.Rol;

public class RolRepository {
    
      public List<Rol> listaRol() {
        List<Rol> listaRoles = new ArrayList<>();
        String sql = "select id_rol, nombre_rol from roles";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {

                String id = rs.getString("id_rol");
                String nombre = rs.getString("nombre_rol");
                

                Rol rol = new Rol(id, nombre);

                listaRoles.add(rol);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar los roles: " + e.getMessage());
        }
        return listaRoles;
    }
}

