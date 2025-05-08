package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import utils.SceneLocator;

public class SceneManager {
    private static SceneManager instance;
    private Scene scene;
    private String currentPath;

    private SceneManager() {
        this.currentPath = SceneLocator.LOGIN_PAGE;
        this.scene = initScene();
    }

    public static SceneManager getInstance() {
        if (instance == null) instance = new SceneManager();
        return instance;
    }

    private Scene initScene() {
        try {
            return new Scene(loadFXML(currentPath));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void load(String path) throws Exception {
        if (instance == null) throw new Exception("SceneManager not initialized!");
        instance.setRoot(path);
    }

    private void setRoot(String path) throws Exception {
        this.currentPath = path;
        Parent root = loadFXML(path);
        scene.setRoot(root);
    }

    private Parent loadFXML(String path) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        return loader.load();
    }

    public Scene getScene() {
        return scene;
    }
}
