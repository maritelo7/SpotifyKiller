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
public class PaginaPrincipalArtistaController implements Initializable {
    
    @FXML
    private JFXButton botonCerrarSesion;

    @FXML
    private JFXButton botonCuenta;
    @FXML
    private JFXDrawer drawerPanel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Método para abrir la información personal del artista.
     *
     * @throws IOException
     */
    @FXML
    public void abrirConsultaCuentaArtista() throws IOException {
        AnchorPane consultaCliente = FXMLLoader.load(getClass()
            .getResource("/vista/ConsultaCuentaArtista.fxml"));
        drawerPanel.setPrefWidth(640);
        drawerPanel.setContent(consultaCliente);
    }
    
    /**
     * Método para abrir la ventana para crear album nuevo.
     *
     * @throws IOException
     */
    @FXML
    public void abrirCrearAlbum() throws IOException {
        AnchorPane consultaCliente = FXMLLoader.load(getClass()
            .getResource("/vista/CrearAlbum.fxml"));
        drawerPanel.setPrefWidth(640);
        drawerPanel.setContent(consultaCliente);
    }
    
}
