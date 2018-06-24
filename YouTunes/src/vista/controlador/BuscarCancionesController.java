package vista.controlador;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import modelo.HttpUtils;
import modelo.Response;
import modelo.Services;
import modelo.mapeos.Cancion;
import modelo.mapeos.ListaReproduccion;
import modelo.pojos.CancionDAO;
import modelo.pojos.ListaReproduccionDAO;
import modelo.pojos.UsuarioDAO;
import modelo.pojos.UsuarioAgregaCancionDAO;

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
    String cancionBuscada;
    UsuarioDAO usuario;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cancionBuscada = PaginaPrincipalClienteController.getCancionBuscada();
        usuario = PaginaPrincipalClienteController.getUsuario();
        System.out.println("cancion: " + cancionBuscada);
        
        if (cancionBuscada != null && !cancionBuscada.equals("")) {
            List<Cancion> resultados = Services.buscarCancion(cancionBuscada);
            
            if (!resultados.isEmpty()){  
            for (int i = 0; i < resultados.size(); i++) {
                if (resultados.get(i).getAlbum().getId() != 3)
                    items.add(resultados.get(i));
            }           
           
            menuContextual();
                       
            
            } else {
                 System.out.println("¡Lo sentimos! No encontramos ninguna coincidencia."); 
            }    
            
        } else {
            System.out.println("POR FAVOR INGRESE ALGO PARA BUSCAR");      
        }
        
         listaCanciones.setItems(items);
    }
    
    
    @FXML
    public void handle(MouseEvent me) {
        if (me.getButton().equals(MouseButton.PRIMARY)) {
            if (!items.isEmpty()) {
                PaginaPrincipalClienteController.cargarCancion(listaCanciones.getSelectionModel().getSelectedItem());
            }
        }
    }
        

        
    
    
     @FXML
    public void agregarCancionPlaylist(){
        comboBox.setItems(playlists);
        System.out.println(listaCanciones.getSelectionModel().getSelectedItem());
        System.out.println(comboBox.getValue());
        Cancion cancion = listaCanciones.getSelectionModel().getSelectedItem();
        ListaReproduccion lista = comboBox.getValue();
        
        UsuarioAgregaCancionDAO usuarioCancion= new UsuarioAgregaCancionDAO();
        usuarioCancion.setIdUsuario(usuario.getIdUsuario());
        usuarioCancion.setIdCancion(cancion.getId());
        usuarioCancion.setIdListaReproduccion(lista.getId());        
        
        Response res = HttpUtils.agregarCancionPlaylist(usuarioCancion);
        if (res.getStatus()==200 || res.getStatus() == 201){
            System.out.println("CANCIONES AGREGADAS A PLAYLSIT EXITOSAMENTE");
        } else {
            System.out.println("CANCIONES NO AGREGADAS CON ÉXITO");
        }
        
        comboBox.setVisible(false);
        listaCanciones.setVisible(true);
    }
    
    public boolean cargarPlaylists(){
       List<ListaReproduccion> listasRecuperadas = Services.buscarListasUsuario(usuario.getIdUsuario());
       
       //MOSTRAR LISTAS RECUPERADAS EN COMBO BOX
       if (!listasRecuperadas.isEmpty()) {    
      
        for (int i = 0; i < listasRecuperadas.size(); i++) {
            playlists.add(listasRecuperadas.get(i));
        }   
        comboBox.setItems(playlists);
       return true;
        
       }
       
       return false;      
        
    }
    
    public void menuContextual(){
         final ContextMenu contextMenu = new ContextMenu();
            MenuItem inicio = new MenuItem("Agregar al inicio de la cola de reproducción");
            MenuItem fin = new MenuItem("Agregar al final de la cola de reproducción");
            MenuItem playlist = new MenuItem("Agregar a playlist");
            contextMenu.getItems().addAll(inicio, fin, playlist);

            inicio.setOnAction((ActionEvent event) -> {
                Cancion cancion = listaCanciones.getSelectionModel().getSelectedItem();
                PaginaPrincipalClienteController.agregarCancionPrincipioCola(cancion);
                //enviar a cola
            });

            fin.setOnAction((ActionEvent event) -> {
                Cancion cancion = listaCanciones.getSelectionModel().getSelectedItem();
                PaginaPrincipalClienteController.agregarCancionFinalCola(cancion);
                //enviar a cola
            });

            playlist.setOnAction((ActionEvent event) -> {
                Cancion cancion = listaCanciones.getSelectionModel().getSelectedItem();
                if (cargarPlaylists()){
                    comboBox.setVisible(true);
                    listaCanciones.setVisible(false);
                } else {
                    System.out.println("NO HAY LISTAS DE REPRODUCCIÓN CREADAS");
                }
            });

            listaCanciones.setContextMenu(contextMenu);
    }
    
       
    
}
