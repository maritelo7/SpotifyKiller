/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
import modelo.pojos.Cancion;
import modelo.pojos.Genero;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Cuando se inicialice la clase se debe recibir idUsuario y idAlbum creado -> este
        //se debe ya haber subido y sido recuperado para asociarlo a las canciones
        //Como solo pedimos formato ,mp3 entonces se enviará automáticamente con ese valor 
        //El path no se envía se envia el archivo y se guarda en el server, se recupera y se 
        //guarda ese path en la base  
        if (LoginController.returnTipoUsuario() == 1) {
            fieldTitulo.setVisible(false);
            fieldColaboradores.setVisible(false);
            buttonAgregar.setVisible(false);
            labelGenero.setVisible(false);
            labelCalidad.setVisible(false);
            comboGenero.setVisible(false);
            comboCalidad.setVisible(false);

        }
    }

    @FXML
    public void seleccionarCancion() {
        final FileChooser archivoAudio = new FileChooser();
        configureFileChooser(archivoAudio);
        File audio = archivoAudio.showOpenDialog(null);
        if (audio != null) {
            FileInputStream input = null;
            try {
                String path = audio.getAbsolutePath();
                File theFile = new File(path);
                input = new FileInputStream(theFile);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(SubirCancionesController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(SubirCancionesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
            cancion.setTitulo(fieldTitulo.getText().substring(0, 99));
            cancion.setColaboradores(fieldColaboradores.getText().substring(0, 254));
            cancion.setIdGenero(comboGenero.getValue().getIdGenero());
            //cancion.setCalidad();
            //cancion.setIdAlbum();
            //cancion.setIdUsuarioSubioCancion();
            //cancion.setFormato();
            items.add(cancion);
            listCanciones.setItems(items);
        } else {
            //mensaje de llenar al menos el título
            System.out.println("LLENAR TITULO");
        }
    }

    @FXML
    private void subirCancion() {
        System.out.println("SUBIR CANCION");

        /*Cancion cancion = new Cancion();
        cancion.setCalidad(1);
        cancion.setColaboradores("Sean Kingston");
        cancion.setFormato(".mp3");
        cancion.setIdAlbum(1);
        cancion.setIdGenero(1);
        cancion.setIdUsuarioSubioCancion(1);
        cancion.setPath("PATH QUE NO SIRVE PA NADA");
        cancion.setTitulo("Eenie Meenie");*/
        try {
            JSONObject cancion = new JSONObject();

            cancion.accumulate("titulo", "Ya quedó");
            cancion.accumulate("formato", "bueno");
            cancion.accumulate("idGenero", 1);
            cancion.accumulate("path", "esta es la ruta yei");
            cancion.accumulate("colaboradores", "Otro weyes");
            cancion.accumulate("idUsuarioSubioCancion", 1);
            cancion.accumulate("idAlbum", 1);
            cancion.accumulate("calidad", 1);

            String titulo = "con strings";
            String formato = "formato";
            Integer idGenero = 1;
            String path = "dfgkdfjdkg";
            String colaboradores = "manes";
            Integer idUsuarioSubioCancion = 1;
            Integer idAlbum = 1;
            Integer calidad = 1;

            /* URL url = new URL("http://192.168.43.79:8080/guardarCancion/"+titulo+"/"+formato+"/"+String.valueOf(idGenero)+
                "/"+path+"/"+colaboradores+"/"+String.valueOf(idUsuarioSubioCancion)+"/"+String.valueOf(idAlbum)+"/"+
                String.valueOf(calidad));*/
            //URL url = new URL("http://192.168.43.79:8080/guardarCancion2/{\"colaboradores\":\"Otro weyes\",\"path\":\"esta es la ruta yei\",\"idUsuarioSubioCancion\":1,\"formato\":\"bueno\",\"idGenero\":1,\"titulo\":\"Es el bueno\",\"idAlbum\":1,\"calidad\":1}");
            //System.out.println("JSON: " + cancion);
            // URL url = new URL("http://192.168.43.79:8080/guardarGenero/opera");
//            
            //URL url = new URL("http://192.168.43.79:8080/guardarCancion2/" + cancion); 
            URL url = new URL("http://192.168.43.36:8080/recuperar");
            URLConnection con = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
            }

            System.out.println("JSON ENVIADO");
        } catch (MalformedURLException ex) {
            System.out.println("este es el error");

        } catch (IOException ex) {
            Logger.getLogger(SubirCancionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean validarCampos() {
        if (fieldTitulo.getText().trim().length() != 0) {
            return true;
        } else {
            return false;
        }
    }
}
