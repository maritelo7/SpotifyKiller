package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXProgressBar;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import modelo.HttpUtils;
import modelo.Response;
import modelo.Services;
import modelo.mapeos.Cancion;
import modelo.pojos.CalificacionDAO;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author Maribel Tello Rodríguez
 * @author Esmeralda Yamileth Hernández González
 */
public class ReproductorController extends Application {

    @FXML
    private JFXButton buttonNext;
    @FXML
    private JFXButton buttonPlay;
    @FXML
    private JFXProgressBar progressBarAudio;
    @FXML
    private JFXButton buttonStop;
    @FXML
    private ImageView portada;
    @FXML
    private Label labelTitulo;
    @FXML
    private Label labelArtista;
    @FXML
    private Label labelAlbum;
    @FXML
    private Label labelDuracion;

    private Dialogo dialogo;
    Media hit;
    MediaPlayer mediaPlayer;
    boolean playing = false;
    Task task;
    static Thread taskThread;
    final int FIN = 100;
    double progresoCancion = 0;
    Cancion cancion;
    File archivoAudio;
    Image image;
    boolean primeraVez = true;
    @FXML
    private JFXButton buttonOpciones;

    @Override
    public void start(Stage stage) {
        try {
            AnchorPane root = new AnchorPane();
            root = FXMLLoader.load(getClass().getResource("/vista/Reproductor.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    public void cargarCancion(Cancion cancion) {
        this.cancion = cancion;
        playNewSong();
    }

    public void playNewSong() {
        buttonPlay.setDisable(false);
        buttonNext.setDisable(false);
        buttonStop.setDisable(false);
        buttonOpciones.setDisable(false);
        progresoCancion = 100;
        progressBarAudio.progressProperty().unbind();
        progressBarAudio.setProgress(0);
        if (!primeraVez) {
            mediaPlayer.dispose();
            taskThread.interrupt();
        }

        try {
            String bin = Services.recuperarAudio(cancion.getId(), cancion.getPath());
            System.out.println(cancion.getPath());
            hit = new Media(new File(bin).toURI().toString());
            mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
            creaProgressBar();
            playing = true;
            primeraVez = false;

        } catch (Exception e) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al reproducir la canción.", "Error", ButtonType.OK);
            dialogo.show();
        }

        labelTitulo.setText(cancion.getTitulo());
        try {
            if (!cancion.getColaboradores().equals("") && cancion.getColaboradores() != null) {
                labelArtista.setText(cancion.getUsuario().getNombreArtistico() + " feat. " + cancion.getColaboradores());
            }
        } catch (NullPointerException e) {
            labelArtista.setText(cancion.getUsuario().getNombreArtistico());
        }
        labelAlbum.setText(" - Album: " + cancion.getAlbum().getTitulo());
        try {
            BufferedImage bufferedImage = Services.recuperarImagen(cancion.getAlbum().getId());
            Image imagen = SwingFXUtils.toFXImage(bufferedImage, null);
            portada.setImage(imagen);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al obtener la portada del álbum.", "Error", ButtonType.OK);
            dialogo.show();
        }

    }

    @FXML
    public void playMusic() throws InterruptedException {
        System.out.println("PLAY");
        if (!playing) {
            mediaPlayer.play();
            playing = true;
        } else {
            mediaPlayer.pause();
            playing = false;
        }

    }

    @FXML
    public void stopMusic() {
        try {
            mediaPlayer.stop();
            playing = false;
            taskThread.interrupt();
        } catch (NullPointerException nullEx) {

        }

    }

    @FXML
    public void nextMusic() throws InterruptedException {
        PaginaPrincipalClienteController.siguienteCancion();
    }

    public void creaProgressBar() {
        task = crearTask();
        progressBarAudio.progressProperty().unbind();
        progressBarAudio.progressProperty().bind(task.progressProperty());
        taskThread = new Thread(task);
        taskThread.start();
    }

    public Task crearTask() {
        return new Task() {
            @Override
            protected Object call() {
                Duration progresoActual;
                Duration division;
                String progreso;
                do {
                    try {
                        Thread.sleep(500);
                        progresoActual = mediaPlayer.getCurrentTime().multiply(100);
                        division = progresoActual.divide(hit.getDuration());
                        progreso = division.toString().substring(0, 3);
                        progresoCancion = Double.parseDouble(progreso);
                        updateProgress(progresoCancion, FIN);
                    } catch (Exception ex) {
                        dialogo = new Dialogo(Alert.AlertType.ERROR,
                            "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                        dialogo.show();
                    }
                } while (progresoCancion < FIN);
                PaginaPrincipalClienteController.siguienteCancion();
                return null;
            }
        };
    }

    @FXML
    public void cargarMenuContextual() {
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem calificar = new MenuItem("Calificar esta canción");
        MenuItem radio = new MenuItem("Crear radio personalizada a partir de está canción");
        contextMenu.getItems().addAll(radio, calificar);
        CalificacionDAO valoracion = new CalificacionDAO();

        calificar.setOnAction((ActionEvent event) -> {
            valoracion.setIdCancion(4);
            //valoracion.setIdCancion(cancion.getId());
            //System.out.println("Cancion: " + cancion.getId());
            ObservableList<Integer> calif = FXCollections.observableArrayList(1, 2, 3, 4, 5);

            Dialog evaluacion = new Dialog();
            evaluacion.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            evaluacion.setHeaderText("Calificar está canción:");
            ComboBox<Integer> combo;
            grid.add(combo = new ComboBox(calif), 0, 0);
            evaluacion.getDialogPane().setContent(grid);
            evaluacion.initStyle(StageStyle.UNDECORATED);
            evaluacion.showAndWait();

            valoracion.setValoracion(combo.getSelectionModel().getSelectedItem());

            Response resws = HttpUtils.actualizarValoracion(valoracion);
            if (!resws.isError()) {
                dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                    "¡Gracias por calificar está canción!", "Éxito", ButtonType.OK);
                dialogo.show();
            } else {
                dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Error al evaluar la canción", "Error", ButtonType.OK);
                dialogo.show();
            }
        });

        radio.setOnAction((ActionEvent event) -> {
            List<Cancion> canciones = Services.buscarCancionGenero(cancion.getGenero().getId());
            if (!canciones.isEmpty()) {
                PaginaPrincipalClienteController.generarRadio(canciones);
            } else {
                dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "No se pudo generar la radio, no hay más canciones de este género registradas.", "Error", ButtonType.OK);
                dialogo.show();
            }
        });

        buttonOpciones.setContextMenu(contextMenu);
    }
}
