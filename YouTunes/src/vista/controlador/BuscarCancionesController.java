package vista.controlador;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import modelo.pojos.Cancion;
import modelo.pojos.ListaReproduccion;

/**
 * FXML Controller class
 *
 * @author yamii
 */
public class BuscarCancionesController implements Initializable {
    

    @FXML
    private JFXListView<Cancion> listaCanciones;
    @FXML
    private JFXComboBox<ListaReproduccion> comboBox;    
    ObservableList<Cancion> items =FXCollections.observableArrayList(); 
    ObservableList<ListaReproduccion> playlists = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      cargarCanciones();
      cargarPlaylists();
      
        final ContextMenu contextMenu = new ContextMenu();       
        MenuItem inicio = new MenuItem("Agregar al inicio de la cola de reproducción");
        MenuItem fin = new MenuItem("Agregar al final de la cola de reproducción"); 
        MenuItem playlist = new MenuItem("Agregar a playlist"); 
        contextMenu.getItems().addAll(inicio, fin, playlist);  
        
        inicio.setOnAction((ActionEvent event) -> {
            Cancion cancion = listaCanciones.getSelectionModel().getSelectedItem();
            System.out.println("SELECTED ITEM " + cancion);
            //enviar a cola
        });        
        
        fin.setOnAction((ActionEvent event) -> {
            Cancion cancion = listaCanciones.getSelectionModel().getSelectedItem();
            System.out.println("SELECTED ITEM " + cancion);
            //enviar a cola
        });   
        
        playlist.setOnAction((ActionEvent event) -> {
            Cancion cancion = listaCanciones.getSelectionModel().getSelectedItem();
            System.out.println("SELECTED ITEM " + cancion);
            comboBox.setVisible(true);
            listaCanciones.setVisible(false);
        });   
        
        listaCanciones.setContextMenu(contextMenu);   
    }
    
    public void cargarPlaylists(){
       List<ListaReproduccion> listaRecuperada = new ArrayList();
       ListaReproduccion e = new ListaReproduccion();
       e.setNombreLista("Uno");
       listaRecuperada.add(e);
       e = new ListaReproduccion();
       e.setNombreLista("Dos");
       listaRecuperada.add(e);
       
      
        for (int i = 0; i < listaRecuperada.size(); i++) {
            playlists.add(listaRecuperada.get(i));
        }   
        comboBox.setItems(playlists);
        
//        comboBox.valueProperty().addListener(new ChangeListener<ListaReproduccion>() {
//           @Override
//           public void changed(ObservableValue<? extends ListaReproduccion> observable, ListaReproduccion oldValue, ListaReproduccion newValue) {
//              agregarCancionPlaylist();
//           }
//        });
        
    }
    
    @FXML
    public void agregarCancionPlaylist(){
        comboBox.setItems(playlists);
        System.out.println(listaCanciones.getSelectionModel().getSelectedItem());
        System.out.println(comboBox.getValue());
        comboBox.setVisible(false);
        listaCanciones.setVisible(true);
    }
    
     public void cargarCanciones(){     
        Cancion cancion = new Cancion();
        cancion.setTitulo("Eenie Meenie");
        cancion.setFormato(".mp3");
        cancion.setPath("Eenie Meenie.mp3");
//        cancion.setNombreArtista("Justin Bieber");
//        cancion.setGenero("Pop");
        items.add(cancion);
        
        cancion = new Cancion();
        cancion.setTitulo("Warrior");
        cancion.setFormato(".mp3");
        cancion.setPath("Warrior.mp3");
//        cancion.setNombreArtista("Demi Lovato");
//        cancion.setGenero("Pop");
        items.add(cancion);
        
        listaCanciones.setItems(items);
    }
    
    
    
}
