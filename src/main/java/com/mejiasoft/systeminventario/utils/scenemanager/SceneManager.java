package main.java.com.mejiasoft.systeminventario.utils.scenemanager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import main.java.com.mejiasoft.systeminventario.controller.LoginController;
import main.java.com.mejiasoft.systeminventario.controller.RegistroController;
import main.java.com.mejiasoft.systeminventario.repository.AuthRepository;
import main.java.com.mejiasoft.systeminventario.repository.RolRepository;
import main.java.com.mejiasoft.systeminventario.service.AuthService;

public class SceneManager {

    private final Stage primaryStage;
    private final String FXML_PATH = "/main/resources/view/";

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void mostrarVistaLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "login-view.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == LoginController.class) {
                AuthRepository authRepository = new AuthRepository();
                AuthService authService = new AuthService(authRepository);
                return new LoginController(authService, this);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error al crear el constructor" + e.getMessage());
            }
        });

        cargarMostrarVista(loader, "one two three", 600, 600);
    }

    public void mostrarVistaRegistro() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "registro-view.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == RegistroController.class) {
                RolRepository rolRepository = new RolRepository();
                AuthRepository authRepository = new AuthRepository();
                AuthService authService = new AuthService(authRepository, rolRepository);
                return new RegistroController(authService, this);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error al crear el constructor" + e.getMessage());
            }
        });

        cargarMostrarVista(loader, "four five", 600, 600);
    }

    private void cargarMostrarVista(FXMLLoader loader, String titulo, double width, double height) throws Exception {
        Parent root = loader.load();
        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle(titulo);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.initOwner(this.primaryStage);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
