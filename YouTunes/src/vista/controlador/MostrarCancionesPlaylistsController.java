package vista.controlador;

import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import modelo.Services;
import modelo.mapeos.Cancion;
import modelo.mapeos.ListaReproduccion;
import modelo.mapeos.UsuarioAgregaCancion;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author Maribel Tello Rodríguez
 * @author Esmeralda Yamileth Hernández González
 */
public class MostrarCancionesPlaylistsController implements Initializable {

    @FXML
    private ListView<Cancion> listCancionesPlaylist;
    @FXML
    private Label labelTitulo;

    ObservableList<Cancion> items = FXCollections.observableArrayList();
    @FXML
    private JFXTextArea textDescripcion;
    private Dialogo dialogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ListaReproduccion lista = PaginaPrincipalClienteController.getPLaylistSeleccionada();

        labelTitulo.setText(lista.getNombreLista());
        textDescripcion.setText("Descripción:\n" + lista.getDescripcion());
        List<UsuarioAgregaCancion> listUsuarioCancion = Services.buscarCancionesDeLista(lista.getId());
        if (!listUsuarioCancion.isEmpty()) {

            listUsuarioCancion.forEach((usuarioCancion) -> {
                items.add(usuarioCancion.getCancion());
            });

            listCancionesPlaylist.setItems(items);
            menuContextual();

        } else {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Playlist vacía", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    public void menuContextual() {
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem inicio = new MenuItem("Agregar al inicio de la cola de reproducción");
        MenuItem fin = new MenuItem("Agregar al final de la cola de reproducción");
        contextMenu.getItems().addAll(inicio, fin);

        inicio.setOnAction((ActionEvent event) -> {
            Cancion cancion = listCancionesPlaylist.getSelectionModel().getSelectedItem();
            PaginaPrincipalClienteController.agregarCancionPrincipioCola(cancion);
            //enviar a cola
        });

        fin.setOnAction((ActionEvent event) -> {
            Cancion cancion = listCancionesPlaylist.getSelectionModel().getSelectedItem();
            PaginaPrincipalClienteController.agregarCancionFinalCola(cancion);
            //enviar a cola
        });

        listCancionesPlaylist.setContextMenu(contextMenu);
    }

    @FXML
    public void handle(MouseEvent me) {
        if (me.getButton().equals(MouseButton.PRIMARY)) {
            if (!items.isEmpty()) {
                if (listCancionesPlaylist.getSelectionModel().getSelectedItem() != null) {                   
                    PaginaPrincipalClienteController.cargarCancion(listCancionesPlaylist.getSelectionModel().getSelectedItem());
                } else {
                     dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                        "Seleccionar canción", "Alerta", ButtonType.OK);
                    dialogo.show();
                }
            }
        }
    }
;

}
