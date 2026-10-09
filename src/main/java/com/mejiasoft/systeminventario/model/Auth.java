package main.java.com.mejiasoft.systeminventario.model;

public class Auth {

    private String idUsuario;
    private String idRol;
    private String nombre;
    private String email;
    private String password;

    public Auth(String idUsuario, String idRol, String nombre, String email, String password) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }
    
    public Auth(){}

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
