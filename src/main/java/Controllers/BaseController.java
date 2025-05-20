package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class BaseController {

    @FXML
    private Label ErrorLable;

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

    private void showError(String key) {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        String message = bundle.containsKey(key) ? bundle.getString(key) : "Gabim gjatë ngarkimit.";
        if (ErrorLable != null) {
            ErrorLable.setText(message);
        } else {
            System.out.println("[Gabim UI] " + message);
        }
    }

    public void goToKerkoAdrese() {
        try {
            SceneManager.load(SceneLocator.KOMUNAL_DASHBOARD);
        } catch (Exception e) {
            showError("error.page.load");
            e.printStackTrace();
        }
    }

    public void goToRegjistroVendbanim() {
        try {
            SceneManager.load(SceneLocator.REGISTER_RESIDENCE);
        } catch (Exception e) {
            showError("error.page.load");
            e.printStackTrace();
        }
    }

    public void goToMenaxhoPerdoruesit() {
        try {
            SceneManager.load(SceneLocator.MANAGE_USER);
        } catch (Exception e) {
            showError("error.page.load");
            e.printStackTrace();
        }
    }

    public void goToMenaxhoVendbanimet() {
        try {
            SceneManager.load(SceneLocator.MANAGE_RESIDENCE);
        } catch (Exception e) {
            showError("error.page.load");
            e.printStackTrace();
        }
    }

    public void goToStatistikaKombetare() {
        try {
            SceneManager.load(SceneLocator.STATISTICS);
        } catch (Exception e) {
            showError("error.page.load");
            e.printStackTrace();
        }
    }

    public void goToListaVendbanimeve() {
        try {
            SceneManager.load(SceneLocator.LISTA_VENDBANIMEVE);
        } catch (Exception e) {
            showError("error.page.load");
            e.printStackTrace();
        }
    }

    public void goToKerkimetEFundit() {
        try {
            SceneManager.load(SceneLocator.KERKIMET_FUNDIT);
        } catch (Exception e) {
            showError("error.page.load");
            e.printStackTrace();
        }
    }

    public void goToQytetariView() {
        try {
            SceneManager.load(SceneLocator.QYTETAR_DASHBOARD);
        } catch (Exception e) {
            showError("error.page.load");
            e.printStackTrace();
        }
    }

    public void goToAdresaView() {
        try {
            SceneManager.load(SceneLocator.ADRESA_PAGE);
        } catch (Exception e) {
            showError("error.page.load");
            e.printStackTrace();
        }
    }
}
