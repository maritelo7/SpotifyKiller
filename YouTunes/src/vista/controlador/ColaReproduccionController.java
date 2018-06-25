package vista.controlador;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.mapeos.Cancion;
import modelo.pojos.CancionDAO;

/**
 * FXML Controller class
 *
 * @author Maribel Tello Rodríguez
 * @author Esmeralda Yamileth Hernández González
 */
public class ColaReproduccionController implements Initializable {

    @FXML
    private JFXListView<Cancion> listaCanciones;
    
    ObservableList<Cancion> items =FXCollections.observableArrayList(); 
         
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {                       
    }    
    
    @FXML
    public void reproduceCancion(){        
        if (listaCanciones.getSelectionModel().getSelectedItem()!=null){
          PaginaPrincipalClienteController.cargarCancion(listaCanciones.getSelectionModel().getSelectedItem()); 
            items.remove(listaCanciones.getSelectionModel().getSelectedItem());
            listaCanciones.setItems(items);
        }
    }
    
    public void agregarCancionPrincipio (Cancion cancionAgregada){
        items.add(0, cancionAgregada);
        listaCanciones.setItems(items);
    }
    
  
    
      public void agregarCancionFinal (Cancion cancionAgregada){
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
      
         public void generarRadio (List<Cancion> canciones){
        items.clear();
        listaCanciones.setItems(items);
        canciones.forEach((cancione) -> {
            items.add(cancione);
        }); 
        listaCanciones.setItems(items);
    }                   
    
    
    
    
}
