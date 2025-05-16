package Application;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.LanguageManager;

import java.util.ResourceBundle;

public class Main1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/QytetariView.fxml")  // kontrollo pathin
            );
            loader.setResources(bundle);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Home Qytetar");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Nuk mund të ngarkohet FXML.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

