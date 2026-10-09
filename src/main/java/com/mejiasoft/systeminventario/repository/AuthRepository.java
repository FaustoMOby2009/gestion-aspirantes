package main.java.com.mejiasoft.systeminventario.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import main.java.com.mejiasoft.systeminventario.config.DataBaseConnection;
import main.java.com.mejiasoft.systeminventario.model.Auth;
import java.sql.ResultSet;

public class AuthRepository {

    private boolean estado = false;

    public boolean create(Auth auth) {
        String sql = "insert into usuarios values (?,?,?,?,?)";
        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, auth.getIdUsuario());
            pstm.setString(2, auth.getIdRol());
            pstm.setString(3, auth.getNombre());
            pstm.setString(4, auth.getEmail());
            pstm.setString(5, auth.getPassword());

            estado = pstm.execute();
            this.estado = true;
        } catch (SQLException e) {
            System.out.println("Error al intentar crear al usuario: " + e.getMessage());
        }
        return estado;
    }

    public String findPasswordByNombre(String nombre) {
        String sql = "select password_hash from usuarios where nombre = ?";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, nombre);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString("password_hash");
            }
        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
        }
        return null;
    }
}
