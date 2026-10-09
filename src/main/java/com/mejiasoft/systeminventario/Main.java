package main.java.com.mejiasoft.systeminventario;

import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import main.java.com.mejiasoft.systeminventario.config.DataBaseConnection;
import main.java.com.mejiasoft.systeminventario.utils.scenemanager.SceneManager;
import java.sql.SQLException;

public class Main extends Application{

    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.mostrarVistaLogin();
        primaryStage.show();
    }

    public static void main(String[] args) {
        try{
        DataBaseConnection.getConnectionDataBase();
            System.out.println("Conectado!");
        }catch(SQLException e){
            System.out.println("Error al conectar: " + e.getMessage());
        }
        launch();
    }
}
