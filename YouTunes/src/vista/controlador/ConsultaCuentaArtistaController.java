package vista.controlador;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.HttpUtils;
import modelo.Response;
import modelo.pojos.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author Maribel Tello Rodríguez
 * @author Esmeralda Yamileth Hernández González
 */
public class ConsultaCuentaArtistaController implements Initializable {

    @FXML
    private JFXTextField fieldNombreUsuario;
    @FXML
    private JFXTextField fieldFechaNacimiento;
    @FXML
    private JFXTextField fieldApellidos;
    @FXML
    private JFXTextField fieldNombre;
    @FXML
    private JFXTextField fieldNombreArtistico;
    private Response resws;

    UsuarioDAO usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recuperarInformacionArtista();
    }

    private void recuperarInformacionArtista() {
        UsuarioDAO artista = new UsuarioDAO();
        artista.setIdUsuario(LoginController.returnUsuario());
        resws = HttpUtils.recuperarUsuarioPorId(artista);

        usuario = new Gson().fromJson(resws.getResult(), UsuarioDAO.class);

        fieldNombreUsuario.setText(usuario.getNombreUsuario());
        fieldFechaNacimiento.setText(usuario.getFechaNacimientoFormato());
        fieldApellidos.setText(usuario.getApellidoPat() + " " + usuario.getApellidoMat());
        fieldNombre.setText(usuario.getNombre());
        fieldNombreArtistico.setText(usuario.getNombreArtistico());  
    }

}
