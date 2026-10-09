package main.java.com.mejiasoft.systeminventario.service;

import java.util.List;
import main.java.com.mejiasoft.systeminventario.exception.AuthException;
import main.java.com.mejiasoft.systeminventario.model.Auth;
import main.java.com.mejiasoft.systeminventario.model.Rol;
import main.java.com.mejiasoft.systeminventario.repository.AuthRepository;
import main.java.com.mejiasoft.systeminventario.repository.RolRepository;
import main.java.com.mejiasoft.systeminventario.security.BCrypt;
import main.java.com.mejiasoft.systeminventario.utils.exception.AuthErrorCode;

public class AuthService {

    private AuthRepository authRepository;
    private RolRepository rolRepository;
    private boolean estado = false;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public AuthService(AuthRepository authRepository, RolRepository rolRepository) {
        this.authRepository = authRepository;
        this.rolRepository = rolRepository;
    }

    public boolean createUsuario(Auth auth) {
        if (auth.getIdUsuario() == null || auth.getIdUsuario().isEmpty()) {
            throw new AuthException(AuthErrorCode.ERROR_ID_USUARIO);
        } else if (auth.getNombre().length() > 36) {
            throw new AuthException(AuthErrorCode.ERROR_LONGITUD_NOMBRE);
        } else if (auth.getPassword().length() > 30) {
            throw new AuthException(AuthErrorCode.ERROR_LONGITUD_PASSWORD);
        } else if (authRepository.findPasswordByNombre(auth.getNombre()) != null) {
            throw new AuthException(AuthErrorCode.ERROR_NOMBRE_DUPLICADO);
        } else if (auth.getEmail().length() > 72) {
            throw new AuthException(AuthErrorCode.ERROR_LONGITUD_EMAIL);
        }

        String passwordHash = BCrypt.hashpw(auth.getPassword(), BCrypt.gensalt(12));

        Auth authAD = new Auth();
        authAD.setIdUsuario(auth.getIdUsuario());
        authAD.setIdRol(auth.getIdRol());
        authAD.setNombre(auth.getNombre());
        authAD.setEmail(auth.getEmail());
        authAD.setPassword(passwordHash);

        return authRepository.create(authAD);
    }

    public boolean login(String nombre, String password) {
        String verificacion = authRepository.findPasswordByNombre(nombre);

        if (verificacion == null) {
            estado = false;
        } else if (BCrypt.checkpw(password, verificacion)) {
            estado = true;
        }
        return estado;
    }

    public List<Rol> listaRol() {
        return rolRepository.listaRol();
    }

}
