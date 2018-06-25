package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Services;
import modelo.mapeos.Album;
import modelo.pojos.UsuarioDAO;
import vista.Dialogo;
import static vista.controlador.PaginaPrincipalClienteController.listaSeleccionada;

/**
 * FXML Controller class
 *
 * @author Esmeralda Yamileth Hernández González
 * @author Maribel Tello Rodríguez
 */
public class PaginaPrincipalArtistaController implements Initializable {
    
    @FXML
    private JFXButton botonCerrarSesion;
    @FXML
    public JFXDrawer drawerPanel;
    @FXML
    private JFXButton botonCuentaArtista;
    @FXML
    private JFXButton botonCrearAlbum;
    @FXML
    private JFXListView<Album> listaAlbumes;
    
    private static Album albumSeleccionado;
    private Dialogo dialogo;
    static UsuarioDAO usuario;
    
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
    public void abrirConsultaCuentaArtista() {
        try {
            AnchorPane consultaCliente = FXMLLoader.load(getClass()
                .getResource("/vista/ConsultaCuentaArtista.fxml"));
            drawerPanel.setPrefWidth(640);
            drawerPanel.setContent(consultaCliente);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
        
    }
    
    /**
     * Método para abrir la ventana para crear album nuevo.
     *
     * @throws IOException
     */
    @FXML
    public void abrirCrearAlbum() {
        try {
            AnchorPane consultaCliente = FXMLLoader.load(getClass()
                .getResource("/vista/CrearAlbum.fxml"));
            drawerPanel.setPrefWidth(640);
            drawerPanel.setContent(consultaCliente);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
     /**
     * Método para cerrar sesión y salir del programa
     * 
     */
    @FXML
    public void salir() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Login.fxml"));
            Stage pagina = new Stage();
            Scene escena = new Scene(loader.load());
            Stage escenaCliente = (Stage) drawerPanel.getScene().getWindow();
            escenaCliente.close();
            pagina.setScene(escena);
            pagina.show();
        } catch (IOException ex) {
        }
    }
    
    /**
     * Método para abrir la ventana para ver las canciones de un álbum
     * 
     */
    
    @FXML
    public void abrirCancionesDeAlbum() {    
         if (!listaAlbumes.getSelectionModel().isEmpty()){
            albumSeleccionado = listaAlbumes.getSelectionModel().getSelectedItem(); 
        try {
            AnchorPane consultaCliente = FXMLLoader.load(getClass()
                .getResource("/vista/CancionesPorAlbum.fxml"));
            drawerPanel.setPrefWidth(640);
            drawerPanel.setContent(consultaCliente);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
            }
        }
    }
    
    
    @FXML
     public void recuperarAlbumes() {        
       List<Album> albumes = Services.recuperarAlbumes(usuario.getIdUsuario());
       ObservableList<Album> playlists = FXCollections.observableArrayList();
      
       if (!albumes.isEmpty()) {          
        for (int i = 0; i < albumes.size(); i++) {
            playlists.add(albumes.get(i));
        }   
        listaAlbumes.setItems(playlists);
       }
    }
    
    /**
     * Método para cargar la cuenta del usuario que se logueó
     * 
     */
    public void setUsuario(UsuarioDAO validado) {
        this.usuario = validado;        
        //System.out.println(usuario.getNombreUsuario());
    }
    
    public static UsuarioDAO getUsuario () {
        return usuario;
    }
    
    public static Album getAlbum(){
        return albumSeleccionado;
    }
    
    
    
}
