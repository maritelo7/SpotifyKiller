/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mari
 */
public class ReproductorController extends Application {

    @FXML
    private ImageView imagePortada;
    @FXML
    private JFXButton buttonNext;
    @FXML
    private JFXButton buttonPlay;
    @FXML
    private JFXButton buttonPrev;
    @FXML
    private JFXProgressBar progressBarAudio;
    
     private static AnchorPane root = new AnchorPane();
   
    

    /**
     * Inicia la aplicación con la pantalla de Login
     *
     * @param primaryStage
     */
    public void start(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/vista/Reproductor.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Iniciar Sesión");
        stage.setScene(scene);
        stage.show();
        File archivo = new File("src/vista/recursos/STOP_symbol.png");
        Image imagen = new Image(archivo.toURI().toString());
        System.out.println(imagen.getException());
        System.out.println("dfghj");
        //imagePortada.setImage(imagen);
        
    }
  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
