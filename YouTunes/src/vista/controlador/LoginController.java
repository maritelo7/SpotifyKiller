package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author yamii
 */
public class LoginController extends Application {

    private static int usuarioLog = 0;
    private static AnchorPane root = new AnchorPane();
    private static AnchorPane paneInicial = new AnchorPane();

    @FXML
    private JFXPasswordField campoContrasenia;

    @FXML
    private JFXTextField campoUsuario;

    @FXML
    private JFXButton botonSalir;

    @FXML
    private JFXButton botonIniciar;

    public static AnchorPane getPrincipal() {
        return root;
    }

    public static int returnUsuario() {
        return usuarioLog;
    }

    /**
     * Inicia la aplicación con la pantalla de Login
     *
     //@param primaryStage
     */
    @Override
    public void start(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/vista/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Iniciar Sesión");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Activa el botón de registrar con base de los campos de ingreso de usuario y contraseña
     */
    @FXML
    public void activarBotonIngresar() {
        if (campoUsuario.getText().length() != 0 & campoContrasenia.getText().length() != 0) {
            botonIniciar.setDisable(false);
        } else {
            botonIniciar.setDisable(true);
        }
    }

    /**
     * Permite ingresar al sistema, recupera un usuario si existe ese número de personal y compara
     * las contraseñas para verificar si puede ingresar o no
     */
    @FXML
    public void ingresarSistema() throws IOException {
        paneInicial = FXMLLoader.load(getClass().getResource("/vista/PaginaInicio.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(paneInicial);
        stage.setTitle("Iniciar Sesión");
        stage.setScene(scene);
        stage.show();
        Stage principal = (Stage) botonIniciar.getScene().getWindow();
        principal.close();

    }
    
    public void validarCampos(){
        
    }

    /**
     * Cierra la aplicación
     */
    @FXML
    public void salirSistema() {
        System.exit(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
