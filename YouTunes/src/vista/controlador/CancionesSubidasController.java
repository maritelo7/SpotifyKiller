package vista.controlador;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import modelo.Services;
import modelo.mapeos.Cancion;
import modelo.pojos.UsuarioDAO;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author Maribel Tello Rodríguez
 * @author Esmeralda Yamileth Hernández González
 */
public class CancionesSubidasController implements Initializable {

    @FXML
    private Label misCanciones;
    @FXML
    private JFXListView<Cancion> listaMisCanciones;
    private Dialogo dialogo;

    ObservableList<Cancion> items = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioDAO usuario = PaginaPrincipalClienteController.getUsuario();

        List<Cancion> listUsuario = Services.buscarUsuarioCancion(usuario.getIdUsuario());

        if (!listUsuario.isEmpty()) {
            listUsuario.forEach((usuarioCancion) -> {
                items.add(usuarioCancion);
            });
            listaMisCanciones.setItems(items);
            menuContextual();

        } else {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "No se han subido canciones", "Error", ButtonType.OK);
            dialogo.show();
        }

    }
    
    

 public void menuContextual(){
        final ContextMenu contextMenu = new ContextMenu();
            MenuItem inicio = new MenuItem("Agregar al inicio de la cola de reproducción");
            MenuItem fin = new MenuItem("Agregar al final de la cola de reproducción");
            contextMenu.getItems().addAll(inicio, fin);

            inicio.setOnAction((ActionEvent event) -> {
                Cancion cancion = listaMisCanciones.getSelectionModel().getSelectedItem();
                PaginaPrincipalClienteController.agregarCancionPrincipioCola(cancion);
                //enviar a cola
            });

            fin.setOnAction((ActionEvent event) -> {
                Cancion cancion = listaMisCanciones.getSelectionModel().getSelectedItem();
                PaginaPrincipalClienteController.agregarCancionFinalCola(cancion);
                //enviar a cola
            });
    
            listaMisCanciones.setContextMenu(contextMenu);
    }
    
 
    public void handle(MouseEvent me) {
        if (me.getButton().equals(MouseButton.PRIMARY)) {
            if (!items.isEmpty()) {
                Cancion cancion = listaMisCanciones.getSelectionModel().getSelectedItem();
                PaginaPrincipalClienteController.cargarCancion(cancion);
            }
        }
    }
;
    

}

