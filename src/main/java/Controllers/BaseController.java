package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;

import java.text.CollationElementIterator;
import java.util.Locale;

public abstract class BaseController {

    @FXML private Label ErrorLable;

    @FXML
    protected void switchToEnglish() {
        LanguageManager.getInstance().setLocale(Locale.ENGLISH);
        reloadCurrentScene();
    }

    @FXML
    protected void switchToAlbanian() {
        LanguageManager.getInstance().setLocale(new Locale("sq"));
        reloadCurrentScene();
    }

    private void reloadCurrentScene() {
        try {
            SceneManager.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToKerkoAdrese() {
        try {
            SceneManager.load(SceneLocator.KOMUNAL_DASHBOARD);
        } catch (Exception e) {
            ErrorLable.setText("Nuk mund të hapet faqja e regjistrimit.");
            e.printStackTrace();
        }
    }

}

