package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import modelo.HttpUtils;
import modelo.Response;
import modelo.pojos.ListaReproduccion;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author Maribel Tello Rodríguez
 * @author Esmeralda Yamileth Hernández González
 */
public class CrearPlaylistController implements Initializable {

    @FXML
    private JFXTextField fieldTitulo;
    @FXML
    private JFXTextArea textDescripcion;
    @FXML
    private JFXButton buttonCrear;
    
    private Dialogo dialogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void crearPlaylist(){
        ListaReproduccion lista = new ListaReproduccion();
        lista.setDescripcion(textDescripcion.getText());
        lista.setNombreLista(fieldTitulo.getText());
        lista.setIdUsuario(PaginaPrincipalClienteController.getUsuario().getIdUsuario()); 
        textDescripcion.setText("");  
        Response resws =   HttpUtils.registrarPlaylist(lista);
        if (!resws.isError()) {   
            dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                "¡Registro exitoso!", "Éxito", ButtonType.OK);
            dialogo.show();
           textDescripcion.setText("");
           fieldTitulo.setText("");  
        } else {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al crear la playlist, intente nuevamente.", "Error", ButtonType.OK);
            dialogo.show();
        }
       
    }

    /**
     * Regresa true si los campos están llenos, y false si al menos uno de los dos no se llenó
     */
    public boolean validarCampos() {
        if (fieldTitulo.getText().trim().length() != 0 & textDescripcion.getText().trim().length() != 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Permite registrar al usuario en el sistema
     */
    @FXML
    public void registrarPlaylist() {
        if (validarCampos()) {
            crearPlaylist();
        } else {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Ingresar campos obligatorios", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
}
