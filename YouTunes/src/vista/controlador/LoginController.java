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
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import modelo.Cifrado;

/**
 *
 * @author yamii
 */
public class LoginController extends Application {

    private static int usuarioLog = 0;
    private static int tipoUsuarioLog = 0;
    private static AnchorPane root = new AnchorPane();
    private static AnchorPane paneInicial = new AnchorPane();
    Response resws;

    @FXML
    private JFXPasswordField campoContrasenia;
    @FXML
    private JFXTextField campoUsuario;
    @FXML
    private JFXButton botonSalir;
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
    private JFXTextField campoCorreo;
    @FXML
    private JFXTextField apellidoMatTF;
    @FXML
    private JFXTextField apellidoPatTF;

    @FXML
    private JFXButton cargarCB;

    public static AnchorPane getPrincipal() {
        return root;
    }

    public static int returnUsuario() {
        return usuarioLog;
    }

    public static int returnTipoUsuario() {
        return tipoUsuarioLog;
    }

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
            //Dialogo
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

    @FXML
    public void cargarCombos() {
        cargarComboBoxes();
    }

    /**
     * Permite registrar al usuario en el sistema
     */
    @FXML
    public void accesarUsuario() throws NoSuchAlgorithmException, ParseException {
        if (validarCamposAcceso()) {
            ingresarSistema();
        } else {
            System.out.println("Llene todos los campos obligatorios");
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
                            Stage stagePrincipal = new Stage();
                            URL panePrincipalURL = getClass().getResource(("/vista/PaginaPrincipalCliente.fxml"));
                            AnchorPane paneInicial = FXMLLoader.load(panePrincipalURL);
                            
                            Stage stage = (Stage) botonIniciar.getScene().getWindow();
                            stage.close();
                            Scene sceneDos = new Scene(paneInicial);
                            stagePrincipal.setScene(sceneDos);
                            stagePrincipal.show();
                            break;
                        case 2:
                            System.out.println("Soy Artista");
                            break;
                        default:
                            System.out.println("Error al iniciar sesión");
                            break;
                            
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("El usuario no existe");
            }
        } else {
            System.out.println("Contraseña incorrecta");
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
            System.out.println("Llene todos los campos obligatorios");
        }
    }

    /**
     * Lleva a cabo la llamada al método de HttpUtils para registrar el usuario
     */
    public void tareaRegistrarUsuario() throws NoSuchAlgorithmException, ParseException {
        String dia = diaCB.getValue();
        int mes = mesCB.getSelectionModel().getSelectedIndex() + 1;
        String anio = anioCB.getValue();
        if (validarFecha(dia, mes, anio)) {
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
            resws = HttpUtils.registroUsuario(usuario);
            if (resws != null && !resws.isError() && resws.getResult() != null) {
                if (resws.getStatus() == 200) {
                    System.out.println("registro exitoso");
                } else {
                    System.out.println(resws.getResult());
                }
            } else {
                System.out.println(resws.getResult());
            }
        } else {
            System.out.println("FECHA INVÁLIDA");
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

    public boolean esFechaValida(String dateToValidate) {
        String dateFromat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);
        Date date = new Date();
        try {
            date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            System.out.println(date);
            //desplegarMensaje("No se puede registrar actividad, la fecha indicada es inválida.");
            e.printStackTrace();
            return false;
        }

        return true;
    }

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
        resws = HttpUtils.recuperarCatalogoUsuarios();
        List<TipoUsuario> tipos = new Gson().fromJson(resws.getResult(), new TypeToken<List<TipoUsuario>>() {
        }.getType());
        ObservableList<TipoUsuario> tiposUsuario = FXCollections.observableArrayList(tipos);
        tipoUsuarioCB.setItems(tiposUsuario);
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
