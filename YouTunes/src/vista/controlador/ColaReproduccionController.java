/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.pojos.CancionDAO;

/**
 * FXML Controller class
 *
 * @author Mari
 */
public class ColaReproduccionController implements Initializable {

    @FXML
    private JFXListView<CancionDAO> listaCanciones;
    
    ObservableList<CancionDAO> items =FXCollections.observableArrayList(); 
         
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCanciones();                
    }    
    
    @FXML
    public void reproduceCancion(){        
        System.out.println("SELECCIONAR CANCION");
        PaginaPrincipalClienteController.cargarCancion(listaCanciones.getSelectionModel().getSelectedItem()); 
        items.remove(listaCanciones.getSelectionModel().getSelectedItem());
        listaCanciones.setItems(items);
    }
    
    public void agregarCancionPrincipio (CancionDAO cancionAgregada){
        items.add(0, cancionAgregada);
        listaCanciones.setItems(items);
    }
    
      public void agregarCancionFinal (CancionDAO cancionAgregada){
        items.add(cancionAgregada);
        listaCanciones.setItems(items);
    }
      
      public void siguienteCancion(){
        if (items.size()>0){
            PaginaPrincipalClienteController.cargarCancion(items.get(0)); 
            items.remove(0);
            listaCanciones.setItems(items);
        }
      }
    
    public void cargarCanciones(){     
        CancionDAO cancion = new CancionDAO();
        cancion.setTitulo("Eenie Meenie");
        cancion.setFormato(".mp3");
        cancion.setPath("Eenie Meenie.mp3");
//        cancion.setNombreArtista("Justin Bieber");
//        cancion.setGenero("Pop");
        items.add(cancion);
        
        cancion = new CancionDAO();
        cancion.setTitulo("Warrior");
        cancion.setFormato(".mp3");
        cancion.setPath("Warrior.mp3");
//        cancion.setNombreArtista("Demi Lovato");
//        cancion.setGenero("Pop");
        items.add(cancion);
        
        listaCanciones.setItems(items);
    }
    
    
    
    
}
