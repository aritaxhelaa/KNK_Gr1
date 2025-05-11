package Application;

import javafx.application.Application;
import javafx.stage.Stage;
import services.SceneManager;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            SceneManager sceneManager = SceneManager.getInstance();
            stage.setScene(sceneManager.getScene());
            stage.setTitle("Sistemi për Regjistrim të Adresave");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
