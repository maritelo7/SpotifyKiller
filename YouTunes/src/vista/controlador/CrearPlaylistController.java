/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.HttpUtils;
import modelo.Response;
import modelo.pojos.ListaReproduccion;

/**
 * FXML Controller class
 *
 * @author Mari
 */
public class CrearPlaylistController implements Initializable {

    @FXML
    private JFXTextField fieldTitulo;
    @FXML
    private JFXTextArea textDescripcion;
    @FXML
    private JFXButton buttonCrear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void crearPlaylist(){
        ListaReproduccion lista = new ListaReproduccion();
        lista.setDescripcion(textDescripcion.getText());
        lista.setNombreLista(fieldTitulo.getText());
        lista.setIdUsuario(PaginaPrincipalClienteController.getUsuario().getIdUsuario());        
        fieldTitulo.setText("");
        textDescripcion.setText("");  
        Response resws =   HttpUtils.registrarPlaylist(lista);
        if (!resws.isError()) {   
            System.out.println("REGISTRO EXITOSO");
        } else {
            System.out.println("REGISTRO N OEXITOSO");
        }
       
    }
    
    public boolean validarCampos(){
         if (fieldTitulo.getText().trim().length() != 0) {
                return true;
            }
         return false;
    }
    
}
