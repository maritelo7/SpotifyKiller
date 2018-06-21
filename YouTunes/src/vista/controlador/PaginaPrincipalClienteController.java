package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelo.pojos.Cancion;
import modelo.pojos.Usuario;
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
    
    static Usuario usuario;
    static ReproductorController controllerReproductor;    
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
    
    /**
     * Initializes the controllerReproductor class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {          
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Reproductor.fxml"));
            AnchorPane panel = loader.load();
            controllerReproductor= loader.getController();
            drawerReproductor.setSidePane(panel);               
            
            loader = new FXMLLoader(getClass().getResource("/vista/ColaReproduccion.fxml"));
            panel = loader.load();
            controllerCola = loader.getController();
            drawerCola.setSidePane(panel);            
        } catch (IOException ex) {
            Logger.getLogger(PaginaPrincipalClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Método para abrir la información personal del cliente.
     * @throws IOException 
     */
    @FXML
    public void abrirConsultaCuentaCliente() throws IOException {
        AnchorPane consultaCliente = FXMLLoader.load(getClass()
            .getResource("/vista/ConsultaCuentaCliente.fxml"));
        pane.setPrefWidth(550);
        pane.setContent(consultaCliente);
    }
    
     /**
     * Método para abrir la lista de canciones subidas al servidor
     * @throws IOException 
     */
    @FXML
    public void abrirMisCanciones() throws IOException {
        AnchorPane cancionesSubidas = FXMLLoader.load(getClass()
            .getResource("/vista/CancionesSubidas.fxml"));
        pane.setPrefWidth(550);
        pane.setContent(cancionesSubidas);
    }
    
     /**
     * Método para crear una nueva lista de reproducción
     * @throws IOException 
     */
    @FXML
    public void crearPlaylist() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/CrearPlaylist.fxml"));
            Stage pagina = new Stage();
            Scene escena = new Scene(loader.load());
            pagina.setScene(escena);
            pagina.setTitle("Crear playlist");
            pagina.show();
    }
    
    /**
     * Método para abrir las canciones que coinciden con la búsqueda.
     * @throws IOException 
     */
    @FXML
    public void abrirFiltroCanciones() throws IOException {
        AnchorPane consultaCanciones = FXMLLoader.load(getClass()
            .getResource("/vista/BuscarCanciones.fxml"));
        pane.setPrefWidth(550);
        pane.setContent(consultaCanciones);
    }
    
     /**
     * Método encargado de enviar a la ventana para subir canciones del cliente.
     */
    @FXML
    private void subirCanciones() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/SubirCanciones.fxml"));
            Stage pagina = new Stage();
            Scene escena = new Scene(loader.load());
            pagina.setScene(escena);
            pagina.setTitle("Subir canciones");
            pagina.show();

        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
     @FXML
    public void mostrarReproductor(){
        if (drawerReproductor.isShown()){
            drawerReproductor.close(); 
            drawerReproductor.setVisible(false);
        }else{
            drawerReproductor.open();
             drawerReproductor.setVisible(true);
        }
    }
    
     /**
     * Método que da funcionalidad al hamburguer para mostrar la cola de reproducción
     * 
     */
     @FXML
    public void mostrarCola(){
    if (drawerCola.isShown()){
            drawerCola.close();  
             drawerCola.setVisible(false);
        }else{
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
            stopReproduccion ();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Login.fxml"));
            Stage pagina = new Stage();
            Scene escena = new Scene(loader.load());
            Stage escenaCliente = (Stage) pane.getScene().getWindow();
            escenaCliente.close();
            pagina.setScene(escena);
            pagina.show();
        } catch (IOException ex) {
            //Dialogo
        }
    }

    public static void cargarCancion(Cancion cancion) {
        controllerReproductor.cargarCancion(cancion);        
    }   
    
    public static void siguienteCancion(){
        controllerCola.siguienteCancion();
    }
    
    public static void agregarCancionFinalCola(Cancion cancion){
        controllerCola.agregarCancionFinal(cancion);
    }
    
    public static void agregarCancionPrincipioCola(Cancion cancion){
        controllerCola.agregarCancionPrincipio(cancion);
    }
    
    public static String returnBusquedaCancion () {
        String nombreCancion = fieldCanciones.getText();
        return nombreCancion;
    }
    
     public static void stopReproduccion () {
        try {
            controllerReproductor.stopMusic();
        } catch (InterruptedException ex) {
            Logger.getLogger(PaginaPrincipalClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     /**
     * Método para cargar la cuenta del usuario que se logueó
     * 
     */
    void setUsuario(Usuario validado) {
        this.usuario = validado;        
        System.out.println(usuario.getNombreUsuario());
    }
    
        /**
     * Método para recuperar la cuenta del usuario que se logueó
     * 
     */
    public static Usuario getUsuario() {
        return usuario;
    }
}
