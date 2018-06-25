package vista.controlador;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maribel Tello Rodríguez
 * @author Esmeralda Yamileth Hernández González
 */
public class ConfiguracionReproductorController  implements Initializable {

    @FXML
    private MenuButton comboCalidadStream;
    @FXML
    private MenuButton comboCalidadDescarga;
    @FXML
    private JFXButton buttonGuardarCambios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    } 
    
}
