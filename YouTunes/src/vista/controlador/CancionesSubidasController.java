/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import modelo.pojos.CancionDAO;

/**
 * FXML Controller class
 *
 * @author Mari
 */
public class CancionesSubidasController implements Initializable {

    @FXML
    private Label misCanciones;
    @FXML
    private JFXListView<CancionDAO> listaMisCanciones;

    ObservableList<CancionDAO> items =FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCanciones();
        
        final ContextMenu contextMenu = new ContextMenu();       
        MenuItem inicio = new MenuItem("Agregar al inicio de la cola de reproducción");
        MenuItem fin = new MenuItem("Agregar al final de la cola de reproducción");       
        contextMenu.getItems().addAll(inicio, fin);  
        
        inicio.setOnAction((ActionEvent event) -> {
            CancionDAO cancion = listaMisCanciones.getSelectionModel().getSelectedItem();
            System.out.println("SELECTED ITEM " + cancion);
            //enviar a cola
        });        
        
        fin.setOnAction((ActionEvent event) -> {
            CancionDAO cancion = listaMisCanciones.getSelectionModel().getSelectedItem();
            System.out.println("SELECTED ITEM " + cancion);
            //enviar a cola
        });        
        
        listaMisCanciones.setContextMenu(contextMenu);        
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
        
        listaMisCanciones.setItems(items);
    }
    
    
    }    
    

