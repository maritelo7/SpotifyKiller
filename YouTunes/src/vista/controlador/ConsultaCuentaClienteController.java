/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.HttpUtils;
import modelo.Response;
import modelo.pojos.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author Esmeralda Yamileth Hernández González
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
    
    UsuarioDAO usuario;
    private Response resws;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recuperarInformacionCliente();
    }

    private void recuperarInformacionCliente() {
        UsuarioDAO cliente = new UsuarioDAO();
        cliente.setIdUsuario(LoginController.returnUsuario());
        resws = HttpUtils.recuperarUsuarioPorId(cliente);

        usuario = new Gson().fromJson(resws.getResult(), UsuarioDAO.class);

        fieldNombreUsuario.setText(usuario.getNombreUsuario());
        fieldFechaNacimiento.setText(usuario.getFechaNacimientoFormato());
        fieldApellidos.setText(usuario.getApellidoPat() + " " + usuario.getApellidoMat());
        fieldNombre.setText(usuario.getNombre());
    }

}
