package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.pojos.Usuario;

/**
 * FXML Controller class
 *
 * @author Esmeralda Yamileth Hernández González
 */
public class CrearAlbumController implements Initializable {

    @FXML
    private JFXButton botonBuscarPortada;
    @FXML
    private JFXTextField campoTitulo;
    @FXML
    private JFXTextField campoAnioLanzamiento;
    @FXML
    private JFXTextField campoCompania;
    @FXML
    private ImageView imgPortada;
    @FXML
    private JFXButton botonCancelar;

    private Desktop desktop = Desktop.getDesktop();
    Usuario usuario;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final FileChooser archivoPortada = new FileChooser();
        botonBuscarPortada.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                configureFileChooser(archivoPortada);
                File fotoPortada = archivoPortada.showOpenDialog(null);
                if (fotoPortada != null) {
                    Image image = new Image("file:" + fotoPortada.getAbsolutePath());
                    imgPortada.setImage(image);
                }
            }

        });
    }

    private static void configureFileChooser(final FileChooser archivoPortada) {
        archivoPortada.setTitle("Buscar Portada de Álbum");
        archivoPortada.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    private void peticion () {
        URL url;
        try {
            url = new URL("http://192.168.43.224:8000/prueba/1");
            URLConnection con = url.openConnection();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (MalformedURLException ex) {
            System.out.println("Que pez");
        } catch (IOException ex) {
            Logger.getLogger(CrearAlbumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void subirAlbum(){
    //se crea y sube el album al servidor, se recupera y se pasa ese id a la
    //pagina siguiente de subir canciones
    irSubirCanciones();    
    }
    

    private void irSubirCanciones(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/SubirCanciones.fxml"));
            AnchorPane creaAlbum = new AnchorPane(loader.load());
            SubirCancionesController controller = loader.getController();
            controller.setUsuario(usuario);
            Scene scene = imgPortada.getScene();
            scene.setRoot(creaAlbum);
            
        } catch (IOException ex) {
            Logger.getLogger(CrearAlbumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
