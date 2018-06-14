package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author yamii
 */
public class MenuDrawerController implements Initializable {

    @FXML
    private JFXButton botonCerrarSesion;

    @FXML
    private JFXButton botonCuenta;
    @FXML
    private JFXDrawer drawerPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void abrirConsultaCuentaCliente() throws IOException {
        AnchorPane consultaCliente = FXMLLoader.load(getClass()
            .getResource("/vista/ConsultaCuentaCliente.fxml"));
        drawerPane.setPrefWidth(640);
        drawerPane.setContent(consultaCliente);
    }

}
