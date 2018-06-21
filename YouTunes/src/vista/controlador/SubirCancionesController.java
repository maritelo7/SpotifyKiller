/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import modelo.HttpUtils;
import modelo.Response;
import modelo.pojos.Cancion;
import modelo.pojos.Genero;
import modelo.pojos.TipoUsuario;
import modelo.pojos.Usuario;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Mari
 */
public class SubirCancionesController implements Initializable {

    @FXML
    private ListView<Cancion> listCanciones;
    @FXML
    private JFXButton buttonSubir;
    @FXML
    private JFXComboBox<Genero> comboGenero;
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

    ObservableList<Cancion> items = FXCollections.observableArrayList();
    Usuario usuario;
    String path; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        ObservableList<String> calidades = FXCollections.observableArrayList("Alta","Media","Baja");
        comboCalidad.setItems(calidades);
        comboCalidad.getSelectionModel().selectFirst();
        
        Response resws = HttpUtils.recuperarCatalogoGeneros();
        List<Genero> tipos = new Gson().fromJson(resws.getResult(), new TypeToken<List<Genero>>() {
        }.getType());
        ObservableList<Genero> generos = FXCollections.observableArrayList(tipos);
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
            FileInputStream input = null;
            path = audio.getAbsolutePath();
            System.out.println(path);            
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
            Cancion cancion = new Cancion();
            cancion.setIdCancion(324);
            if (LoginController.returnTipoUsuario() == 2) {
                cancion.setColaboradores(fieldColaboradores.getText());
                cancion.setIdGenero(comboGenero.getValue().getIdGenero());
                if (comboCalidad.getValue() == "Baja"); cancion.setCalidad(1);
                if (comboCalidad.getValue() == "Media"); cancion.setCalidad(2);
                if (comboCalidad.getValue() == "Alta"); cancion.setCalidad(3);
                cancion.setIdAlbum(CrearAlbumController.returnIdAlbum());
            }
            cancion.setIdUsuarioSubioCancion(usuario.getIdUsuario());
            cancion.setTitulo(fieldTitulo.getText());
            cancion.setPath(path);
            items.add(cancion);
            listCanciones.setItems(items);
            limpiarCampos();
            buttonSubir.setDisable(false);
        } else {
            //mensaje de llenar al menos el título
            System.out.println("LLENAR TITULO");
        }
    }

    @FXML
    private void subirCanciones() {
        if (items.size()>0){
        FileInputStream input = null;
        try {
            for (int i = 0; i < items.size(); i++) {
                Cancion cancion = items.get(i);
                String ruta = cancion.getPath();
                cancion.setPath("");
                File file = new File(ruta);
                input = new FileInputStream(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) > 0) {
                    baos.write(buffer, 0, bytesRead);
                }
                HttpUtils.subirCancion(cancion, baos.toByteArray());                ;
                items.clear();
                listCanciones.setItems(items);
                System.out.println("MENSAJE DE ÉXITO");
                input.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SubirCancionesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SubirCancionesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SubirCancionesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(SubirCancionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        } else{
            System.out.println("DEBES TENER AL MENOS UNA CANCION EN LA LISTA");
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
