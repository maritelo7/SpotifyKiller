/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.pojos.Cancion;
import modelo.pojos.CancionHistorial;
import modelo.pojos.Historial;

/**
 * FXML Controller class
 *
 * @author Mari
 */
public class HistorialReproduccionController implements Initializable {

    @FXML
    private TableView<CancionHistorial> tableHistorial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTabla();
        
    }    
    
     public void cargarTabla(){         
      TableColumn titulo = new TableColumn("Título de la canción");
      titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
      titulo.setPrefWidth(300);
      TableColumn fecha = new TableColumn("Fecha de reproducción");
      fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
      fecha.setPrefWidth(200);
      tableHistorial.getColumns().addAll(titulo, fecha);
      tableHistorial.setEditable(false);
    }
     
      public void cargarInformacionTablaConversaciones(){
      //se recibiría un Historial con canciones
      Cancion cancion = null;
      CancionHistorial canHis;
      List<Historial> historial = null;
      List<CancionHistorial> cancionHistorial = new ArrayList();
        for (int i = 0; i < historial.size(); i++) {
            canHis = new CancionHistorial();
            canHis.setTitulo(cancion.getTitulo());
            canHis.setFecha(historial.get(i).getFecha());
        }
      
         ObservableList<CancionHistorial> listaHistorialCanciones = FXCollections.observableArrayList();
         for (int i = 0; i < cancionHistorial.size(); i++) {
            listaHistorialCanciones.add(cancionHistorial.get(i));
         }        
         tableHistorial.setItems((ObservableList<CancionHistorial>)listaHistorialCanciones);
      }
    
   
    
}
