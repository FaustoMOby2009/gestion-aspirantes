package main.java.com.mejiasoft.systeminventario.model;

public class Rol {

    private String idRol;
    private String nombreRol;

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Rol(String idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    @Override
    public String toString() {
        return this.nombreRol;
    }
}
