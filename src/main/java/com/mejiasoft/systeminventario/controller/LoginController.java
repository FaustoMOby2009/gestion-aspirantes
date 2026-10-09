package main.java.com.mejiasoft.systeminventario.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.java.com.mejiasoft.systeminventario.service.AuthService;
import main.java.com.mejiasoft.systeminventario.utils.scenemanager.SceneManager;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private SceneManager stage;
    private AuthService authService;

    public LoginController(AuthService authService, SceneManager stage) {
        this.stage = stage;
        this.authService = authService;
    }

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPassword;

    @FXML
    public void handleIniciarSesion() {

        String nombre = txtNombre.getText().trim();
        String password = txtPassword.getText().trim();

        if (nombre.isEmpty() || password.isEmpty()) {
            stage.mostrarAlerta("ERROR", "Datos incompletos", "Faltan parametros por llenar", Alert.AlertType.WARNING);
        }
        try {
            if (authService.login(nombre, password) == true) {
                stage.mostrarAlerta("Exito", "Exito", "has ingresado al dashboard " + nombre, Alert.AlertType.INFORMATION);
            } else {
                stage.mostrarAlerta("error", "Datos incorrectos", "Los datos son incorrectos. Si no tienes cuenta puedes registrarse", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            stage.mostrarAlerta("Error", "Error en el sistema.", "Vuelva a intentarlo mas tarde.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleMostrarRegistroView() {
        try {
            stage.mostrarVistaRegistro();
        } catch (Exception e) {
            System.out.println("Error con el constructor: " + e.getMessage());
        }
    }
}
