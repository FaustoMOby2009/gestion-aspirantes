package main.java.com.mejiasoft.systeminventario.repository;

import main.java.com.mejiasoft.systeminventario.config.DataBaseConnection;
import main.java.com.mejiasoft.systeminventario.model.Aspirante;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AspiranteRepository {

    private boolean estado = false;
    
    public boolean create(Aspirante aspirante) {
        String sql = "insert into aspirantes values (?,?,?,?,?,?,?)";
        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, aspirante.getIdAspirante());
            pstm.setString(2, aspirante.getNombre());
            pstm.setString(3, aspirante.getApellido());
            pstm.setString(4, aspirante.getCorreo());
            pstm.setString(5, aspirante.getTelefono());
            pstm.setString(6, aspirante.getDireccion());
            pstm.setInt(7, aspirante.getEdad());

            estado = pstm.execute();
            this.estado = true;
        } catch (SQLException e) {
            System.out.println("Error al intentar crear al usuario: " + e.getMessage());
        }
        return estado;
    }
    
    public boolean update(Aspirante aspirante) {
        String sql = "update aspirantes set nombre = ?, apellido = ?, correo = ?, telefono = ?, direccion = ?, edad = ? where id_aspirante";
        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, aspirante.getIdAspirante());
            pstm.setString(2, aspirante.getNombre());
            pstm.setString(3, aspirante.getApellido());
            pstm.setString(4, aspirante.getCorreo());
            pstm.setString(5, aspirante.getTelefono());
            pstm.setString(6, aspirante.getDireccion());
            pstm.setInt(7, aspirante.getEdad());

            estado = pstm.execute();
            this.estado = true;
        } catch (SQLException e) {
            System.out.println("Error al intentar actualizar al usuario: " + e.getMessage());
        }
        return estado;
    }
    
    public boolean delete(Aspirante aspirante){
    String sql = "delele from aspirantes where id_aspirante = ?";
    
    try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, aspirante.getIdAspirante());
            
            estado = pstm.executeUpdate() > 0;
            this.estado = true;
            
    }catch(SQLException e){
        System.out.println("Error al eliminar al aspirante: " + e.getMessage());
    }
    return estado;
    }
    
}
