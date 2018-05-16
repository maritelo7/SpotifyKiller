package vista;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author yamii
 */
public class YouTunes extends Application {

    private static BorderPane root = new BorderPane();
    private static BorderPane panePrincipal = new BorderPane();

    @Override
    public void start(Stage stage) throws Exception {

        URL panePrincipalURL = getClass().getResource(("/vista/PaginaInicio.fxml"));
        AnchorPane paneInicial = FXMLLoader.load(panePrincipalURL);

        panePrincipal.setCenter(paneInicial);
        Scene sceneDos = new Scene(panePrincipal, 900, 600);
        stage.setScene(sceneDos);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
