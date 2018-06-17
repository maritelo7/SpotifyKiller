/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import modelo.pojos.Cancion;
import modelo.pojos.Genero;
import modelo.pojos.Usuario;

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
    
    ObservableList<Cancion> items =FXCollections.observableArrayList(); 
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
                System.out.println(theFile.getName());
                input = new FileInputStream(theFile);
                //se enviaría el inputStream
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
           if (validarCampos()){
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
           }else{
               //mensaje de llenar al menos el título
               System.out.println("LLENAR TITULO");
           }
       }
       
        @FXML
       private void subirCancion() {
           if (!items.isEmpty()){
               //se envía lista de canciones dentro de un ciclo, se envia canción x canción
               System.out.println("SUBIENDO");
           }else{
               //no se puede enviar lista porque es vacía
               System.out.println("LISTA VACIA");
           }
       }
       
       
       private boolean validarCampos(){
            if (fieldTitulo.getText().trim().length() != 0)
                return true;       
            else
                return false;
       }

    void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
        
        
        
    }
     
     


