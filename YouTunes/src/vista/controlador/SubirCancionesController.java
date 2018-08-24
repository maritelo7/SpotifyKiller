package vista.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import modelo.HttpUtils;
import modelo.Response;
import modelo.pojos.CalificacionDAO;
import modelo.pojos.CancionDAO;
import modelo.pojos.GeneroDAO;
import modelo.pojos.UsuarioDAO;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author Esmeralda Yamileth Hernández González
 * @author Maribel Tello Rodríguez
 */
public class SubirCancionesController implements Initializable {

    @FXML
    private ListView<CancionDAO> listCanciones;
    @FXML
    private JFXButton buttonSubir;
    @FXML
    private JFXComboBox<GeneroDAO> comboGenero;
    @FXML
    private JFXComboBox<String> comboCalidad;
    @FXML
    private JFXButton buttonAgregar;
    @FXML
    private JFXTextField fieldTitulo;
    @FXML
    private JFXTextField fieldColaboradores;
    @FXML
    private Button buttonSeleccionar;
    @FXML
    private Label labelGenero;
    @FXML
    private Label labelCalidad;

    ObservableList<CancionDAO> items = FXCollections.observableArrayList();
    UsuarioDAO usuario;
    String path;
    private Dialogo dialogo;
    @FXML
    private Label labelNombre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> calidades = FXCollections.observableArrayList("Alta", "Media", "Baja");
        comboCalidad.setItems(calidades);
        comboCalidad.getSelectionModel().selectFirst();
        Response resws = HttpUtils.recuperarCatalogoGeneros();
        List<GeneroDAO> tipos = new Gson().fromJson(resws.getResult(), new TypeToken<List<GeneroDAO>>() {
        }.getType());
        ObservableList<GeneroDAO> generos = FXCollections.observableArrayList(tipos);
        comboGenero.setItems(generos);
        comboGenero.getSelectionModel().selectFirst();
        
        if (LoginController.returnTipoUsuario() == 1) {
            fieldColaboradores.setVisible(false);
            labelGenero.setVisible(false);
            labelCalidad.setVisible(false);
            comboGenero.setVisible(false);
            comboCalidad.setVisible(false);
            usuario = PaginaPrincipalClienteController.getUsuario();
        } else {
            usuario = PaginaPrincipalArtistaController.getUsuario();
        }
    }

    @FXML
    public void seleccionarCancion() {
        final FileChooser archivoAudio = new FileChooser();
        configureFileChooser(archivoAudio);
        File audio = archivoAudio.showOpenDialog(null);
        if (audio != null) {
            path = audio.getAbsolutePath();
            labelNombre.setText("Seleccionado: " + audio.getName());
            
        }
    }

    private static void configureFileChooser(final FileChooser archivoAudio) {
        archivoAudio.setTitle("Buscar archivo de audio");
        archivoAudio.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );
    }

    @FXML
    private void agregarCancion() {
        if (validarCampos()) {
            CancionDAO cancion = new CancionDAO();
            if (LoginController.returnTipoUsuario() == 2) {
                cancion.setColaboradores(fieldColaboradores.getText());
                if (comboCalidad.getValue() == "Baja");
                cancion.setCalidad(1);
                if (comboCalidad.getValue() == "Media");
                cancion.setCalidad(2);
                if (comboCalidad.getValue() == "Alta");
                cancion.setCalidad(3);                
                cancion.setIdAlbum(CrearAlbumController.returnIdAlbum());
                cancion.setIdGenero(comboGenero.getValue().getIdGenero());
            } else {
                cancion.setIdAlbum(3);
                cancion.setIdGenero(33);                
            }
            cancion.setIdUsuarioSubioCancion(usuario.getIdUsuario());
            cancion.setTitulo(fieldTitulo.getText());
            cancion.setPath(path);
            items.add(cancion);
            listCanciones.setItems(items);
            limpiarCampos();
            labelNombre.setText("Seleccionado: ");
            buttonSubir.setDisable(false);
        } else {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Llenar título", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    @FXML
    private void subirCanciones() {
        if (items.size() > 0) {

            //CalificacionDAO valoracion = new CalificacionDAO();
            FileInputStream input = null;
            try {
                System.out.println(items.size());
                for (int i = 0; i < items.size(); i++) {
                   
                    CancionDAO cancion = items.get(i);

                     System.out.println(cancion.getTitulo());
                    //valoracion.setIdCancion(i);
                    //valoracion.setValoracion(0);

                    String ruta = cancion.getPath();
                    System.out.println(ruta);
                    cancion.setPath("");
                    File file = new File(ruta);
                    input = new FileInputStream(file);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) > 0) {
                        baos.write(buffer, 0, bytesRead);
                    }
                    HttpUtils.subirCancion(cancion, baos.toByteArray());

                    Thread.sleep(1500);
                    System.out.println("Ya debí haber guardado");

                    input.close();
                }
                //HttpUtils.registrarValoracion(valoracion);
                items.clear();
                listCanciones.setItems(items);
                dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                    "¡Las canciones se subieron exitosamente!", "Éxito", ButtonType.OK);
                dialogo.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    input.close();
                } catch (IOException ex) {
                    
                } catch (NullPointerException npex) {
                }
            }
        } else {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Debes tener al menos una canción en la lista", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    private boolean validarCampos() {
        if (fieldTitulo.getText().trim().length() != 0) {
            return true;
        } else {
            return false;
        }
    }

    private void limpiarCampos() {
        if (LoginController.returnTipoUsuario() == 2) {
            fieldColaboradores.setText("");
        }
        fieldTitulo.setText("");
    }

}
