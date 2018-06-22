package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.pojos.CancionDAO;
import modelo.pojos.UsuarioDAO;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author yamii
 */
public class PaginaPrincipalClienteController implements Initializable {

    @FXML
    private JFXButton botonCerrarSesion;
    @FXML
    private JFXButton botonCuenta;
    @FXML
    private JFXDrawer pane;
    @FXML
    private AnchorPane panePrincipal;
    @FXML
    private JFXDrawer drawerReproductor;
    @FXML
    private JFXDrawer drawerCola;
    @FXML
    private JFXButton buttonReproductor;
    @FXML
    private JFXHamburger hamburguerCola;
    @FXML
    private Separator labelReproductor;
    @FXML
    private TitledPane buttonMiMusica;
    @FXML
    private static JFXTextField fieldCanciones;
    @FXML
    private JFXButton buttonBuscar;
    static ColaReproduccionController controllerCola;
    @FXML
    private JFXButton buttonSubirCanciones;
    @FXML
    private JFXButton buttonCrearPlaylist;
    @FXML
    private JFXListView<?> listPlaylists;
    @FXML
    private JFXButton buttonMisCanciones;
    @FXML
    private JFXButton buttonHistorial;
    
    private Dialogo dialogo;
    static UsuarioDAO usuario;
    static ReproductorController controllerReproductor;  
    static String cancionBuscada;

    /**
     * Initializes the controllerReproductor class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Reproductor.fxml"));
            AnchorPane panel = loader.load();
            controllerReproductor = loader.getController();
            drawerReproductor.setSidePane(panel);

            loader = new FXMLLoader(getClass().getResource("/vista/ColaReproduccion.fxml"));
            panel = loader.load();
            controllerCola = loader.getController();
            drawerCola.setSidePane(panel);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }

    }

    /**
     * Método para abrir la información personal del cliente.
     *
     */
    @FXML
    public void abrirConsultaCuentaCliente()  {
        try {
            AnchorPane consultaCliente = FXMLLoader.load(getClass()
                .getResource("/vista/ConsultaCuentaCliente.fxml"));
            pane.setPrefWidth(550);
            pane.setContent(consultaCliente);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Método para abrir la lista de canciones subidas al servidor
     *
     */
    @FXML
    public void abrirMisCanciones() {
        try {
            AnchorPane cancionesSubidas = FXMLLoader.load(getClass()
                .getResource("/vista/CancionesSubidas.fxml"));
            pane.setPrefWidth(550);
            pane.setContent(cancionesSubidas);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Método para crear una nueva lista de reproducción
     *
     */
    @FXML
    public void crearPlaylist() {
        try {
            AnchorPane consultaCliente = FXMLLoader.load(getClass()
                .getResource("/vista/CrearPlaylist.fxml"));
            pane.setPrefWidth(550);
            pane.setContent(consultaCliente);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Método para abrir las canciones que coinciden con la búsqueda.
     */
    @FXML
    public void abrirFiltroCanciones() {
        cancionBuscada = fieldCanciones.getText();
        try {
            AnchorPane consultaCanciones = FXMLLoader.load(getClass()
                .getResource("/vista/BuscarCanciones.fxml"));
            pane.setPrefWidth(550);
            pane.setContent(consultaCanciones);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Método encargado de enviar a la ventana para subir canciones del cliente.
     */
    @FXML
    private void subirCanciones() {
        try {
            AnchorPane consultaCliente = FXMLLoader.load(getClass()
                .getResource("/vista/SubirCanciones.fxml"));
            pane.setPrefWidth(550);
            pane.setContent(consultaCliente);

        } catch (IOException ioEx) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
    /**
     * Método para abrir el historial de reproducción.
     *
     */
    @FXML
    public void abrirHistorial() {
        try {
            AnchorPane consultaCanciones = FXMLLoader.load(getClass()
                .getResource("/vista/HistorialReproduccion.fxml"));
            pane.setPrefWidth(550);
            pane.setContent(consultaCanciones);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    @FXML
    public void mostrarReproductor() {
        if (drawerReproductor.isShown()) {
            drawerReproductor.close();
            drawerReproductor.setVisible(false);
        } else {
            drawerReproductor.open();
            drawerReproductor.setVisible(true);
        }
    }

    /**
     * Método que da funcionalidad al hamburguer para mostrar la cola de reproducción
     *
     */
    @FXML
    public void mostrarCola() {
        if (drawerCola.isShown()) {
            drawerCola.close();
            drawerCola.setVisible(false);
        } else {
            drawerCola.open();
            drawerCola.setVisible(true);
        }
    }

    /**
     * Método para cerrar sesión y salir del programa
     *
     */
    @FXML
    public void salir() {
        try {
            stopReproduccion();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Login.fxml"));
            Stage pagina = new Stage();
            Scene escena = new Scene(loader.load());
            Stage escenaCliente = (Stage) pane.getScene().getWindow();
            escenaCliente.close();
            pagina.setScene(escena);
            pagina.show();
        } catch (IOException ex) {
        }
    }

    public static void cargarCancion(CancionDAO cancion) {
        controllerReproductor.cargarCancion(cancion);
    }

    public static void siguienteCancion() {
        controllerCola.siguienteCancion();
    }

    public static void agregarCancionFinalCola(CancionDAO cancion) {
        controllerCola.agregarCancionFinal(cancion);
    }

    public static void agregarCancionPrincipioCola(CancionDAO cancion) {
        controllerCola.agregarCancionPrincipio(cancion);
    }

    public static String returnBusquedaCancion() {
        String nombreCancion = fieldCanciones.getText();
        return nombreCancion;
    }

    public static void stopReproduccion() {
        controllerReproductor.stopMusic();
    }

    /**
     * Método para cargar la cuenta del usuario que se logueó
     *
     */
    void setUsuario(UsuarioDAO validado) {
        this.usuario = validado;
        System.out.println(usuario.getNombreUsuario());
    }
    
        /**
     * Método para recuperar la cuenta del usuario que se logueó
     * 
     */
    public static UsuarioDAO getUsuario() {
        return usuario;
    }
    
       /**
     * Método para recuperar la cuenta del usuario que se logueó
     * 
     */
    public static String getCancionBuscada() {
        return cancionBuscada;
    }
}
