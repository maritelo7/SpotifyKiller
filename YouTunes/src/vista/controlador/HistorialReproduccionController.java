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
import modelo.Services;
import modelo.mapeos.Cancion;
import modelo.mapeos.Historial;
import modelo.pojos.CancionDAO;
import modelo.pojos.CancionHistorial;
import modelo.pojos.HistorialDAO;
import modelo.pojos.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author Mari
 */
public class HistorialReproduccionController implements Initializable {

    @FXML
    private TableView<CancionHistorial> tableHistorial;

    UsuarioDAO usuario;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTabla();
        usuario = PaginaPrincipalClienteController.getUsuario();
        cargarTabla();
        cargarInformacionTabla();
        System.out.println(usuario);
    }    
    
     public void cargarTabla(){         
      TableColumn titulo = new TableColumn("Título de la canción");
      titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
      titulo.setPrefWidth(250);
      TableColumn fecha = new TableColumn("Fecha de reproducción");
      fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
      fecha.setPrefWidth(250);
      tableHistorial.setStyle("-fx-my-cell-background: #092a35;");
      tableHistorial.getColumns().addAll(titulo, fecha);
      tableHistorial.setEditable(false);
    }
     
      public void cargarInformacionTabla(){
      List<Historial> historial = Services.recuperarHistorial(usuario.getIdUsuario());

      if (!historial.isEmpty()){
      CancionHistorial canHis;
 
      List<CancionHistorial> cancionHistorial = new ArrayList();
        for (int i = 0; i < historial.size(); i++) {
            canHis = new CancionHistorial();
            canHis.setTitulo(historial.get(i).getCancion().getTitulo());
            canHis.setFecha(historial.get(i).getFecha());
            cancionHistorial.add(canHis);
        }
      
         ObservableList<CancionHistorial> listaHistorialCanciones = FXCollections.observableArrayList();
         for (int i = 0; i < cancionHistorial.size(); i++) {
            listaHistorialCanciones.add(cancionHistorial.get(i));
         }        
         tableHistorial.setItems((ObservableList<CancionHistorial>)listaHistorialCanciones);
      } else {
          System.out.println("No hay canciones en el historial");
      }
      
      }
    
   
    
}
