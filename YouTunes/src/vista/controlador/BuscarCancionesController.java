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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import modelo.HttpUtils;
import modelo.Response;
import modelo.Services;
import modelo.pojos.Cancion;
import modelo.pojos.ListaReproduccion;
import modelo.pojos.Usuario;
import modelo.pojos.UsuarioAgregaCancion;

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
    Usuario usuario;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cancionBuscada = PaginaPrincipalClienteController.getCancionBuscada();
        usuario = PaginaPrincipalClienteController.getUsuario();
        
        
        if (cancionBuscada != null) {
            Services.buscarCancion(cancionBuscada, usuario.getCalidadStream());
            //MOSTRAR CANCIONES RECUPERADAS EN LISTA 
            
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
                PaginaPrincipalClienteController.agregarCancionPrincipioCola(cancion);
                //enviar a cola
            });

            fin.setOnAction((ActionEvent event) -> {
                Cancion cancion = listaCanciones.getSelectionModel().getSelectedItem();
                System.out.println("SELECTED ITEM " + cancion);
                PaginaPrincipalClienteController.agregarCancionFinalCola(cancion);
                //enviar a cola
            });

            playlist.setOnAction((ActionEvent event) -> {
                Cancion cancion = listaCanciones.getSelectionModel().getSelectedItem();
                System.out.println("SELECTED ITEM " + cancion);
                comboBox.setVisible(true);
                listaCanciones.setVisible(false);
            });

            listaCanciones.setContextMenu(contextMenu);
            
            
            
        } else {
            Cancion cancion = new Cancion();
            cancion.setTitulo("¡Lo sentimos! No hay coincidencias para esa búsqueda.");        
            items.add(cancion);
            listaCanciones.setItems(items);
        }
    }
    
    
    @FXML
    public void reproduceCancion(){        
        System.out.println("SELECCIONAR CANCION");
        PaginaPrincipalClienteController.cargarCancion(listaCanciones.getSelectionModel().getSelectedItem());       
    }
    
    
     @FXML
    public void agregarCancionPlaylist(){
        comboBox.setItems(playlists);
        System.out.println(listaCanciones.getSelectionModel().getSelectedItem());
        System.out.println(comboBox.getValue());
        Cancion cancion = listaCanciones.getSelectionModel().getSelectedItem();
        ListaReproduccion lista = comboBox.getValue();
        
        UsuarioAgregaCancion usuarioCancion= new UsuarioAgregaCancion();
        usuarioCancion.setIdUsuario(usuario.getIdUsuario());
        usuarioCancion.setIdCancion(cancion.getIdCancion());
        usuarioCancion.setIdListaReproduccion(lista.getIdListaReproduccion());        
        
        Response res = HttpUtils.agregarCancionPlaylist(usuarioCancion);
        if (res.getStatus()==200 || res.getStatus() == 201){
            System.out.println("CANCIONES AGREGADAS A PLAYLSIT EXITOSAMENTE");
        } else {
            System.out.println("CANCIONES NO AGREGADAS CON ÉXITO");
        }
        
        comboBox.setVisible(false);
        listaCanciones.setVisible(true);
    }
    
    public void cargarPlaylists(){
       Services.recuperarListasReproduccion(usuario.getIdUsuario());
       //MOSTRAR LISTAS RECUPERADAS EN COMBO BOX
        
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
