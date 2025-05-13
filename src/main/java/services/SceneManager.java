package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import utils.SceneLocator;

public class SceneManager {
    private static SceneManager sceneManager;
    private Scene scene;
    private LanguageManager languageManager;
    private String currentPath;

    // Konstruktor privat për të inicializuar vetëm një instancë
    private SceneManager(){
        this.languageManager = LanguageManager.getInstance();
        this.currentPath = SceneLocator.LOGIN_PAGE;
        this.scene = this.initScene();
    }

    // Pjesa që do të përdoret për të krijuar vetëm një instancë të SceneManager
    public static SceneManager getInstance(){
        if(sceneManager == null)
            sceneManager = new SceneManager();
        return sceneManager;
    }

    // Kjo metodë krijon një skenë me parent të ngarkuar nga FXML
    private Scene initScene(){
        try{
            return new Scene(this.getParent(currentPath));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // Kjo metodë ngarkon pamjen nga një FXML dhe e vendos si root të skenës
    public static void load(String path) throws Exception{
        if(sceneManager == null){
            throw new Exception("Scene manager is not initialized yet!");
        }
        sceneManager.loadParent(path);
    }

    // Kjo metodë mbush një pane specifik me një FXML
    public static void load(String path, Pane pane) throws Exception{
        if(sceneManager == null){
            throw new Exception("Scene manager is not initialized yet!");
        }
        sceneManager.loadParent(path, pane);
    }

    // Kjo metodë ngarkon parent nga FXML dhe e vendos si root të skenës
    private void loadParent(String path) throws Exception{
        Parent parent = getParent(path);
        this.currentPath = path;
        scene.setRoot(parent);
    }

    // Kjo metodë mbush një pane me një parent të ngarkuar nga FXML
    private void loadParent(String path, Pane pane) throws Exception{
        pane.getChildren().clear();
        Parent parent = getParent(path);
        pane.getChildren().add(parent);
    }

    // Kjo metodë merr një parent nga FXML dhe vendos edhe bundles për gjuhën aktuale
    private Parent getParent(String path) throws Exception{
        FXMLLoader loader = new FXMLLoader(
                this.getClass().getResource(path)
        );
        loader.setResources(this.languageManager.getResourceBundle());
        return loader.load();
    }

    // Pjesa që ngarkon përsëri skenën aktuale
    public static void reload() throws Exception{
        load(sceneManager.currentPath);
    }

    // Ky është getteri për të marrë skenën
    public Scene getScene() {
        return scene;
    }
}
