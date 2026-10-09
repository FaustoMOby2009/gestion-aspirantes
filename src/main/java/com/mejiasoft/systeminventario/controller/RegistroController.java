package main.java.com.mejiasoft.systeminventario.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.java.com.mejiasoft.systeminventario.exception.AuthException;
import main.java.com.mejiasoft.systeminventario.model.Auth;
import main.java.com.mejiasoft.systeminventario.model.Rol;
import main.java.com.mejiasoft.systeminventario.service.AuthService;
import main.java.com.mejiasoft.systeminventario.utils.scenemanager.SceneManager;

public class RegistroController implements Initializable {

    private AuthService authService;
    private SceneManager stage;

    public RegistroController(AuthService authService, SceneManager stage) {
        this.authService = authService;
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarRolesComboBox();
    }

    @FXML
    private ComboBox<Rol> cmbRoles;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtEmail;

    private void cargarRolesComboBox() {
        try {
            List<Rol> listaRoles = authService.listaRol();
            ObservableList<Rol> datosObservable = FXCollections.observableArrayList(listaRoles);
            cmbRoles.setItems(datosObservable);
        } catch (Exception e) {
            System.out.println("Error al cargar los roles en el ComboBox: " + e.getMessage());
        }
    }

    @FXML
    private void handleCrearUsuario() {

        String nombre = txtNombre.getText().trim();
        String password = txtPassword.getText().trim();
        String email = txtEmail.getText().trim();
        Rol rolSelect = cmbRoles.getSelectionModel().getSelectedItem();

        if (nombre.isEmpty() || password.isEmpty() || email.isEmpty() || rolSelect == null) {
            stage.mostrarAlerta("Error", "Campos incompletos", "Rellenar campos vacios", Alert.AlertType.ERROR);
            return;
        }
        try {
            Auth authAD = new Auth();
            authAD.setIdUsuario(UUID.randomUUID().toString());
            authAD.setIdRol(rolSelect.getIdRol());
            authAD.setNombre(nombre);
            authAD.setEmail(email);
            authAD.setPassword(password);

            if (authService.createUsuario(authAD) == true) {
                stage.mostrarAlerta("Ingreso exitoso", "Que exitoso", "Has ingresado con exito " + authAD.getNombre(), Alert.AlertType.INFORMATION);
            }
        } catch (AuthException a) {
            String mensajeError = "";

            switch (a.getCodigo()) {
                case ERROR_LONGITUD_NOMBRE:
                    mensajeError = "El nombre de usuario es demasiado largo (Máximo 36 letras).";
                    break;
                case ERROR_LONGITUD_PASSWORD:
                    mensajeError = "La contraseña es demasiado larga (Máximo 30 caracteres).";
                    break;
                case ERROR_NOMBRE_DUPLICADO:
                    mensajeError = "Ese nombre de usuario ya existe. Por favor elige otro.";
                    break;
                case ERROR_ID_USUARIO:
                    mensajeError = "Error en el sistema, vuelva mas tarde.";
                    break;
                case ERROR_LONGITUD_EMAIL:
                    mensajeError = "El email es muy largo (Maximo 72 caracteres).";
                default:
                    mensajeError = "Error en el sistema.";
                    break;
            }

            stage.mostrarAlerta("Error", "Error de validaciones", mensajeError, Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleRegresar() {
        try {
            stage.mostrarVistaLogin();
        } catch (Exception e) {
            System.out.println("Error en el constructor: " + e.getMessage());
        }
    }
}
