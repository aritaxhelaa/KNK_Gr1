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

    public void goToRegjistroVendbanim() {
        try {
            SceneManager.load(SceneLocator.REGISTER_RESIDENCE);
        } catch (Exception e) {
            ErrorLable.setText("Nuk mund të hapet faqja e regjistrimit.");
            e.printStackTrace();
        }
    }

    public void goToMenaxhoPerdoruesit() {
        try {
            SceneManager.load(SceneLocator.MANAGE_USER);
        } catch (Exception e) {
            ErrorLable.setText("Nuk mund të hapet faqja e regjistrimit.");
            e.printStackTrace();
        }
    }

    public void goToMenaxhoVendbanimet() {
        try {
            SceneManager.load(SceneLocator.MANAGE_RESIDENCE);
        } catch (Exception e) {
            ErrorLable.setText("Nuk mund të hapet faqja e regjistrimit.");
            e.printStackTrace();
        }
    }

    public void goToStatistikaKombetare() {
        try {
            SceneManager.load(SceneLocator.STATISTICS);
        } catch (Exception e) {
            ErrorLable.setText("Nuk mund të hapet faqja e regjistrimit.");
            e.printStackTrace();
        }
    }

    public void goToListaVendbanimeve() {
        try {
            SceneManager.load(SceneLocator.LISTA_VENDBANIMEVE);
        } catch (Exception e) {
            ErrorLable.setText("Nuk mund të hapet faqja e regjistrimit.");
            e.printStackTrace();
        }
    }

}

