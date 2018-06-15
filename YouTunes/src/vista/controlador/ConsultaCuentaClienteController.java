/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Mari
 */
public class ConsultaCuentaClienteController implements Initializable {

    @FXML
    private JFXTextField fieldNombreUsuario;
    @FXML
    private JFXTextField fieldFechaNacimiento;
    @FXML
    private JFXTextField fieldApellidos;
    @FXML
    private JFXTextField fieldNombre;
    @FXML
    private JFXTextField fieldNombreArtistico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
