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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modelo.Services;
import modelo.mapeos.Album;
import modelo.mapeos.Cancion;

/**
 * FXML Controller class
 *
 * @author maritello
 */
public class CancionesPorAlbumController implements Initializable {

    @FXML
    private JFXListView<Cancion> listaCanciones;
    @FXML
    private Label labelAlbum;
    
    Album albumRecuperado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        albumRecuperado = PaginaPrincipalArtistaController.getAlbum();
        List<Cancion> canciones = Services.recuperarCancionesPorAlbum(albumRecuperado.getId());

        labelAlbum.setText(albumRecuperado.getTitulo());

        if (!canciones.isEmpty()) {

            ObservableList<Cancion> playlists = FXCollections.observableArrayList();

            if (!canciones.isEmpty()) {
                for (int i = 0; i < canciones.size(); i++) {
                    playlists.add(canciones.get(i));
                }
                
                listaCanciones.setItems(playlists);
            }

        }
    }    
    
}
