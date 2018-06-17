package vista.controlador;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author yamii
 */
public class BuscarCancionesController implements Initializable {
    @FXML
    private JFXListView listCanciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // recuperarCanciones();
        //System.out.println("Nombre ingresado: " + PaginaPrincipalClienteController.returnBusquedaCancion());
    }
    
    
    
}
