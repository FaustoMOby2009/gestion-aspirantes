package main.java.com.mejiasoft.systeminventario.exception;

import main.java.com.mejiasoft.systeminventario.utils.exception.AuthErrorCode;

public class AuthException extends RuntimeException {
    private final AuthErrorCode codigo;

    public AuthException(AuthErrorCode codigo) {
        super(codigo.name());
        this.codigo = codigo;
    }
    
    public AuthErrorCode getCodigo(){
    return codigo;
    }
}
