package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Services;
import modelo.mapeos.Cancion;
import modelo.mapeos.ListaReproduccion;
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
    private JFXTextField fieldCanciones;
    @FXML
    private JFXButton buttonBuscar;
    static ColaReproduccionController controllerCola;
    @FXML
    private JFXButton buttonSubirCanciones;
    @FXML
    private JFXButton buttonCrearPlaylist;
    @FXML
    private JFXListView<ListaReproduccion> listPlaylists;
    @FXML
    private JFXButton buttonMisCanciones;
    @FXML
    private JFXButton buttonHistorial;
    @FXML
    private TitledPane titledPanePlaylists;
    @FXML
    private Label labelMostrar;
    
    private Dialogo dialogo;
    static UsuarioDAO usuario;
    static ReproductorController controllerReproductor;  
    static String cancionBuscada;
    static ListaReproduccion listaSeleccionada;


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
            drawerCola.close();
            drawerReproductor.close();
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
            drawerCola.close();
            drawerReproductor.close();
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
            drawerCola.close();
            drawerReproductor.close();
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
            drawerCola.close();
            drawerReproductor.close();
            fieldCanciones.setText("");
        } catch (IOException ex) {
            ex.printStackTrace();
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
            drawerCola.close();
            drawerReproductor.close();
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
            drawerCola.close();
            drawerReproductor.close();
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
    
    @FXML
    public boolean cargarPlaylists(){        
       List<ListaReproduccion> listasRecuperadas = Services.buscarListasUsuario(usuario.getIdUsuario());
       ObservableList<ListaReproduccion> playlists = FXCollections.observableArrayList();
       //MOSTRAR LISTAS RECUPERADAS EN COMBO BOX
       if (!listasRecuperadas.isEmpty()) {    
      
        for (int i = 0; i < listasRecuperadas.size(); i++) {
            playlists.add(listasRecuperadas.get(i));
        }   
        listPlaylists.setItems(playlists);
               
       return true;        
       }       
       return false;      
        
    }
    
    @FXML
    public void cargarCancionesPlaylist(){      
        if (!listPlaylists.getSelectionModel().isEmpty()){
            listaSeleccionada = listPlaylists.getSelectionModel().getSelectedItem(); 
            System.out.println(listaSeleccionada.getId());
          try {
            AnchorPane consultaCanciones = FXMLLoader.load(getClass()
                    .getResource("/vista/MostrarCancionesPlaylists.fxml"));
            pane.setPrefWidth(550);
            pane.setContent(consultaCanciones);
            drawerCola.close();
            drawerReproductor.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        } 
        }  
    }

    public static void cargarCancion(Cancion cancion) {
        controllerReproductor.cargarCancion(cancion);
    }

    public static void siguienteCancion() {
        controllerCola.siguienteCancion();
    }

    public static void agregarCancionFinalCola(Cancion cancion) {
        controllerCola.agregarCancionFinal(cancion);
    }

    public static void agregarCancionPrincipioCola(Cancion cancion) {
        controllerCola.agregarCancionPrincipio(cancion);
    }
    
    public static void generarRadio(List<Cancion> canciones) {
        controllerCola.generarRadio(canciones);
    }

    public String returnBusquedaCancion() {
        String nombreCancion = fieldCanciones.getText();
        return nombreCancion;
    }

    public static ListaReproduccion getPLaylistSeleccionada() {
        System.out.println("desde el get: " + listaSeleccionada.getId());
       return listaSeleccionada;
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
