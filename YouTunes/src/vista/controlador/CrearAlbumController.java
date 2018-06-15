package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    private Desktop desktop = Desktop.getDesktop();

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


}
