package Controllers;

import javafx.fxml.FXML;
import services.LanguageManager;
import services.SceneManager;

import java.util.Locale;

public abstract class BaseController {

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
}

