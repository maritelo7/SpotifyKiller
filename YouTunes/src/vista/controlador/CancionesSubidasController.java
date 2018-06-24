/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import modelo.Services;
import modelo.mapeos.Album;
import modelo.mapeos.Cancion;
import modelo.mapeos.ListaReproduccion;
import modelo.mapeos.Usuario;
import modelo.mapeos.UsuarioAgregaCancion;
import modelo.pojos.CancionDAO;
import modelo.pojos.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author Mari
 */
public class CancionesSubidasController implements Initializable {

    @FXML
    private Label misCanciones;
    @FXML
    private JFXListView<Cancion> listaMisCanciones;

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
            System.out.println("NO HAY CANCIONES SUBIDAS");
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

