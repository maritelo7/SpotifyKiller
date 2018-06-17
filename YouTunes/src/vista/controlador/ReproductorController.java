/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.pojos.Cancion;

/**
 * FXML Controller class
 *
 * @author Mari
 */
public class ReproductorController extends Application{

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

    Media hit;
    MediaPlayer mediaPlayer;
    boolean playing = false;
    Task task;
    Thread taskThread;
    final int FIN = 100;
    double progresoCancion = 0;
    Cancion cancion;
    File archivoAudio;
    Image image;
    boolean primeraVez = true;
    
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = new AnchorPane();
        root = FXMLLoader.load(getClass().getResource("/vista/Reproductor.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();                    
    }
     
      
    public void cargarCancion(Cancion cancion){        
        this.cancion = cancion;
        playNewSong();
    }
    
    public void playNewSong(){
        buttonPlay.setDisable(false);
        buttonNext.setDisable(false);
        buttonStop.setDisable(false);
        progresoCancion=100;
        progressBarAudio.progressProperty().unbind();
        progressBarAudio.setProgress(0);
        if (!primeraVez){
            mediaPlayer.dispose();
            taskThread.interrupt();
        }
        archivoAudio = new File(cancion.getPath());
        String bin = archivoAudio.getAbsolutePath();
        hit = new Media(new File(bin).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        labelTitulo.setText(cancion.getTitulo());
        labelArtista.setText("Artista");
        labelAlbum.setText("Album");
        
//        image = new Image("file:" + fotoPortada.getAbsolutePath());
//        portada.setImage(image);
        creaProgressBar();
        playing=true;
        primeraVez=false;
    }   
    
    @FXML
    public void playMusic() throws InterruptedException{  
        System.out.println("PLAY");
        if (!playing){
            mediaPlayer.play();
            playing = true;
        } else {
            mediaPlayer.pause();
            playing = false;
        }
        
    }
    
    @FXML
    public void stopMusic() throws InterruptedException{  
        System.out.println("STOP");
            mediaPlayer.stop();
            playing = false;   
    }
    
    @FXML
    public void nextMusic() throws InterruptedException{  
            PaginaPrincipalClienteController.siguienteCancion();
    }
    
     public void creaProgressBar(){    
        task = crearTask();
        progressBarAudio.progressProperty().unbind();
        progressBarAudio.progressProperty().bind(task.progressProperty());
        taskThread = new Thread(task);
        taskThread.start();
     }
     
     public Task crearTask(){
      return new Task() {
             @Override
             protected Object call()  {
                Duration progresoActual;
                Duration division;
                String progreso;
                 do{ 
                    try {
                        Thread.sleep(500);
                        progresoActual = mediaPlayer.getCurrentTime().multiply(100);
                        division = progresoActual.divide(hit.getDuration());
                        progreso = division.toString().substring(0, 3);
                        progresoCancion = Double.parseDouble(progreso);
                        updateProgress(progresoCancion, FIN);
                            } catch (Exception ex) {
                        Logger.getLogger(ReproductorController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 } while (progresoCancion < FIN);                 
                  PaginaPrincipalClienteController.siguienteCancion();
                 return null;              
             }
         };
     }


    
}
