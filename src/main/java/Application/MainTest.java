package Application;

import javafx.application.Application;
import javafx.stage.Stage;
import services.SceneManager;
import utils.SceneLocator;

public class MainTest extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Inicializo SceneManager
            SceneManager sceneManager = SceneManager.getInstance();

            // Ngarko skenën e AdresaView (adresës) direkt
            SceneManager.load(SceneLocator.QYTETAR_DASHBOARD); // duhet të kesh këtë në SceneLocator

            primaryStage.setScene(sceneManager.getScene());
            primaryStage.setTitle("Testo - AdresaView me shumëgjuhësi");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
