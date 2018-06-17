package vista.controlador;

import modelo.HttpUtils;
import modelo.Response;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.pojos.TipoUsuario;
import modelo.pojos.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.ConnectException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import modelo.Cifrado;
import vista.Dialogo;

/**
 *
 * @author Esmeralda Yamileth Hernández González
 */
public class LoginController extends Application {

    private static int usuarioLog = 0;
    private static int tipoUsuarioLog = 0;
    private static AnchorPane root = new AnchorPane();
    private static AnchorPane paneInicial = new AnchorPane();
    private Response resws;
    private Dialogo dialogo;

    @FXML
    private JFXPasswordField campoContrasenia;
    @FXML
    private JFXTextField campoUsuario;
    @FXML
    private JFXButton botonIniciar;
    @FXML
    private JFXComboBox<TipoUsuario> tipoUsuarioCB;
    @FXML
    private JFXTextField nombreTF;
    @FXML
    private JFXComboBox<String> diaCB;
    @FXML
    private JFXComboBox<String> mesCB;
    @FXML
    private JFXComboBox<String> anioCB;
    @FXML
    private JFXTextField nombreUsuarioTF;
    @FXML
    private JFXPasswordField contrasenaRTF;
    @FXML
    private JFXButton registrarBT;
    @FXML
    private JFXTextField nombreArtisticoTF;
    @FXML
    private JFXTextField apellidoMatTF;
    @FXML
    private JFXTextField apellidoPatTF;


    public static AnchorPane getPrincipal() {
        return root;
    }

    public static int returnUsuario() {
        return usuarioLog;
    }

    public static int returnTipoUsuario() {
        return tipoUsuarioLog;
    }
    @FXML
    private Tab tabRegistrarse;

    /**
     * Inicia la aplicación con la pantalla de Login
     *
     * //@param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            root = FXMLLoader.load(getClass().getResource("/vista/Login.fxml"));
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde.", "Error", ButtonType.OK);
            dialogo.show();
        }
        Scene scene = new Scene(root);
        primaryStage.setTitle("Iniciar Sesión");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Activa el botón de registrar con base de los campos de ingreso de usuario y contraseña
     */
    @FXML
    public void activarBotonIngresar() {
        if (validarCamposAcceso()) {
            botonIniciar.setDisable(false);
        } else {
            botonIniciar.setDisable(true);
        }
    }

    /**
     * Método asignado al tabpane de Registrarse, para cargar los combos de tipo de usuario y de las
     * fechas.
     */
    @FXML
    public void cargarCombos() {
        cargarComboBoxes();
        nombreArtisticoTF.setVisible(false);
    }
    
     /**
     * Método asignado para que al elegir una opción de tipo de usuario se muestre o no el campo
     * de nombre artístico
     * fechas.
     */
    @FXML
    public void mostrarNombreArtistico() {
        if (tipoUsuarioCB.getValue().getIdTipoUsuario()==2){
            nombreArtisticoTF.setVisible(true);
        } else {
            nombreArtisticoTF.setVisible(false);
        }
    }

    /**
     * Permite registrar al usuario en el sistema
     */
    @FXML
    public void accesarUsuario() {
        if (validarCamposAcceso()) {
            ingresarSistema();
        } else {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Ingresar campos obligatorios", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Permite ingresar al sistema como usuario Artista o Consumidor
     */
    public void ingresarSistema() {
        Usuario ingresado = new Usuario();
        Cifrado cifrado = new Cifrado();
        ingresado.setNombreUsuario(campoUsuario.getText());
        ingresado.setClave(cifrado.cifrarCadena(campoContrasenia.getText()));
        resws = HttpUtils.accesoUsuario(ingresado);
        if (resws != null && !resws.isError() && resws.getResult() != null) {
            if (resws.getResult().contains("idUsuario")) {
                try {
                    Usuario validado = new Usuario();
                    validado = new Gson().fromJson(resws.getResult(), Usuario.class);
                    System.out.println("tipo Usuario: " + validado.getTipoUsuario());
                    System.out.println("usuario: " + validado.getIdUsuario());
                    tipoUsuarioLog = validado.getTipoUsuario();
                    usuarioLog = validado.getIdUsuario();
                    switch (tipoUsuarioLog) {
                        case 1:
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PaginaPrincipalCliente.fxml"));
                            paneInicial = loader.load();
                            PaginaPrincipalClienteController controller = loader.getController();
                            controller.setUsuario(validado);
                            Stage paginaCliente = new Stage();
                            Stage escenaLoginCliente = (Stage) botonIniciar.getScene().getWindow();
                            escenaLoginCliente.close();
                            Scene escenaCliente = new Scene(paneInicial);
                            paginaCliente.setScene(escenaCliente);
                            paginaCliente.show();
                            break;
                        case 2:
                            FXMLLoader loaderArtista = new FXMLLoader(getClass().getResource("/vista/PaginaPrincipalArtista.fxml"));
                            paneInicial = loaderArtista.load();
                            PaginaPrincipalArtistaController controllerArtista = loaderArtista.getController();
                            controllerArtista.setUsuario(validado);
                            Stage paginaArtista = new Stage();
                            Stage escenaLoginArtista = (Stage) botonIniciar.getScene().getWindow();
                            escenaLoginArtista.close();
                            Scene escenaArtista = new Scene(paneInicial);
                            paginaArtista.setScene(escenaArtista);
                            paginaArtista.show();
                            break;
                        default:
                            dialogo = new Dialogo(Alert.AlertType.ERROR,
                                "El Usuario ingresado no existe", "Error", ButtonType.OK);
                            dialogo.show();
                            break;

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    dialogo = new Dialogo(Alert.AlertType.ERROR,
                        "Servidor no disponible, intente más tarde.", "Error", ButtonType.OK);
                    dialogo.show();
                }
            } else {
                
                dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "El Usuario ingresado no existe", "Error", ButtonType.OK);
                dialogo.show();
            }
        } else {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Nombre de Usuario o Contraseña incorrectos", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Permite registrar al usuario en el sistema
     */
    @FXML
    public void registrarUsuario() throws NoSuchAlgorithmException, ParseException {
        if (validarCamposRegistro()) {
            tareaRegistrarUsuario();
        } else {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Ingresar campos obligatorios", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
    public void limpiarCamposRegistrar(){
        nombreTF.setText("");
        apellidoPatTF.setText("");
        apellidoMatTF.setText("");
        contrasenaRTF.setText("");
        nombreUsuarioTF.setText("");
        nombreArtisticoTF.setText("");
    }

    /**
     * Lleva a cabo la llamada al método de HttpUtils para registrar el usuario
     */
    public void tareaRegistrarUsuario() {
        String dia = diaCB.getValue();
        int mes = mesCB.getSelectionModel().getSelectedIndex() + 1;
        String anio = anioCB.getValue();
        if (validarFecha(dia, mes, anio)) {
            try {
                Usuario usuario = new Usuario();
                usuario.setNombre(nombreTF.getText());
                usuario.setApellidoPat(apellidoPatTF.getText());
                usuario.setApellidoMat(apellidoMatTF.getText());
                usuario.setClave(getHash(contrasenaRTF.getText()));
                Date date = convertirFecha(dia, mes, anio);
                usuario.setFechaNacimiento(date);
                usuario.setNombreUsuario(nombreUsuarioTF.getText());
                usuario.setTipoUsuario(tipoUsuarioCB.getValue().getIdTipoUsuario());
                usuario.setNombreArtistico(nombreArtisticoTF.getText());
                resws = HttpUtils.recuperarUsuarioPorNombreUsuario(usuario);
                if (!resws.isError()) {                    
                    if (resws.getStatus()==204){    
                resws = HttpUtils.registroUsuario(usuario);
                if (resws != null && !resws.isError() && resws.getResult() != null) {
                    if (resws.getStatus() == 200) {
                        dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                            "¡Registro exitoso! Inicie sesión con sus datos por favor", "Éxito", ButtonType.OK);
                        dialogo.show();
                        limpiarCamposRegistrar();
                    } 
                } else {
                    dialogo = new Dialogo(Alert.AlertType.ERROR,
                        "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                    dialogo.show();
                }
            }else{
                   dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Usuario con ese nombre de usuario ya existe. Por favor elija otro.", "Error", ButtonType.OK);
                dialogo.show(); 
                    }
                }else {
                dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                dialogo.show(); 
                }
            } catch (NoSuchAlgorithmException ex) {
                dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "No se pudo cifrar la contraseña", "Error", ButtonType.OK);
                dialogo.show();
            } catch (ParseException ex) {
                dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Formato de fecha incorrecto", "Error", ButtonType.OK);
                dialogo.show();
            }
        } else {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Fecha Inválida", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Regresa true si los campos están llenos, y false si al menos uno de los dos no se llenó
     */
    public boolean validarCamposAcceso() {
        if (campoUsuario.getText().trim().length() != 0 & campoContrasenia.getText().trim().length() != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Regresa true si los campos obligatorios están llenos, y false si no se llenaron
     */
    public boolean validarCamposRegistro() {
        //1 es cliente, 2 es artista        
        if (nombreTF.getText().trim().length() != 0 & apellidoPatTF.getText().trim().length() != 0
            & nombreUsuarioTF.getText().trim().length() != 0 & contrasenaRTF.getText().trim().length() != 0) {
            if (tipoUsuarioCB.getValue().getIdTipoUsuario() == 1) {
                return true;
            }
            if (nombreArtisticoTF.getText().trim().length() != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para convertir la fecha en Date y almacenarla en la base de datos.
     *
     * @param dia dia de nacimeinto.
     * @param mes mes de nacimiento.
     * @param anio año de nacimiento.
     * @return fecha de nacimiento en formato DATE.
     * @throws ParseException
     */
    public Date convertirFecha(String dia, int mes, String anio) throws ParseException {
        String mesAux;
        if (mes < 10) {
            mesAux = "0" + mes;
        } else {
            mesAux = mes + "";
        }
        String fecha = anio + "-" + mesAux + "-" + dia;
        String dateFromat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);
        Date date = sdf.parse(fecha);
        return date;
    }

    /**
     * Método para validar si la fecha es correcta.
     *
     * @param dateToValidate
     * @return
     */
    public boolean esFechaValida(String dateToValidate) {
        String dateFromat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);
        Date date = new Date();
        try {
            date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "No se puede registrar actividad, la fecha indicada es inválida.",
                "Error", ButtonType.OK);
            dialogo.show();
            return false;
        }

        return true;
    }

    /**
     * Método para validar la fecha.
     *
     * @param dia día de nacimiento.
     * @param mes mes de nacimiento.
     * @param anio año de nacimiento.
     * @return true si es válida, false sino.
     */
    public boolean validarFecha(String dia, int mes, String anio) {
        String mesAux;
        if (mes < 10) {
            mesAux = "0" + mes;
        } else {
            mesAux = mes + "";
        }
        String fecha = dia + "/" + mesAux + "/" + anio;
        if (!esFechaValida(fecha)) {
            return false;
        }
        return true;
    }

    /**
     * Método que carga el combo box de los tipos de usuarios
     */
    public void cargarComboUsuarios() {
        try {
            resws = HttpUtils.recuperarCatalogoUsuarios();
        } catch (Exception ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde.",
                "Error", ButtonType.OK);
            dialogo.show();
        }

        List<TipoUsuario> tipos = new Gson().fromJson(resws.getResult(), new TypeToken<List<TipoUsuario>>() {
        }.getType());
        ObservableList<TipoUsuario> tiposUsuario = FXCollections.observableArrayList(tipos);
        tipoUsuarioCB.setItems(tiposUsuario);
        tipoUsuarioCB.getSelectionModel().selectFirst();
    }

    /**
     * Método para cifrar la contraseña que ingresa el usuario
     */
    private String getHash(String string) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(string.getBytes(Charset.forName("UTF-8")));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }

    /**
     * Método que carga los combo boxes de dia, mes, año y tipo de usuario
     */
    public void cargarComboBoxes() {
        ObservableList<String> opciones;
        opciones = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
            "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
        diaCB.setItems(opciones);
        diaCB.getSelectionModel().selectFirst();
        //Llenar combo de mes
        opciones = FXCollections.observableArrayList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
            "Septiembre", "Octubre", "Noviembre", "Diciembre");
        mesCB.setItems(opciones);
        mesCB.getSelectionModel().selectFirst();
        //Llenar combo de año
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        opciones = FXCollections.observableArrayList();
        for (int i = 0; i < 100; i++) {
            opciones.add(anioActual - i + "");
        }
        anioCB.setItems(opciones);
        anioCB.getSelectionModel().selectFirst();
        cargarComboUsuarios();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
