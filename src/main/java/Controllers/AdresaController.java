package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;

import java.util.Locale;
import java.util.ResourceBundle;

public class AdresaController {

    @FXML
    private ComboBox<String> komunaComboBox;

    @FXML
    private ComboBox<String> rrugaComboBox;

    @FXML
    private TextField numriField;

    @FXML
    private Label messageLabel;

    @FXML
    private Label kodiPostarLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Button shfaqBtn;

    @FXML
    private Button backBtn;

    @FXML
    private WebView mapView;

    @FXML
    private void initialize() {
        loadLocalizedTexts();
    }

    private void loadLocalizedTexts() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        titleLabel.setText(bundle.getString("label.title"));
        shfaqBtn.setText(bundle.getString("button.shfaq"));
        backBtn.setText(bundle.getString("button.back"));

        komunaComboBox.setPromptText(bundle.getString("prompt.komuna"));
        rrugaComboBox.setPromptText(bundle.getString("prompt.rruga"));
        numriField.setPromptText(bundle.getString("prompt.numri"));

        messageLabel.setText("");
        kodiPostarLabel.setText("");
    }

    @FXML
    private void handleShowMap() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        String komuna = komunaComboBox.getValue();
        String rruga = rrugaComboBox.getValue();
        String numri = numriField.getText();

        if (komuna == null || rruga == null || numri.isEmpty()) {
            messageLabel.setText(bundle.getString("message.error.missing"));
            kodiPostarLabel.setText("");
            return;
        }

        // Simulimi për test
        if (komuna.equalsIgnoreCase("Prishtinë")) {
            kodiPostarLabel.setText(bundle.getString("message.success") + ": 10000");
            messageLabel.setText("");
            loadMap("https://www.google.com/maps?q=Prishtina");
        } else {
            kodiPostarLabel.setText("");
            messageLabel.setText(bundle.getString("message.error.notfound"));
        }
    }

    private void loadMap(String url) {
        WebEngine webEngine = mapView.getEngine();
        webEngine.load(url);
    }


    @FXML
    private void handleBack() {
        try {
            SceneManager.load(SceneLocator.LOGIN_PAGE);
        } catch (Exception e) {
            messageLabel.setText("Nuk u kthye dot.");
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToEn() {
        LanguageManager.getInstance().setLocale(Locale.ENGLISH);
        try {
            SceneManager.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToSq() {
        LanguageManager.getInstance().setLocale(new Locale("sq"));
        try {
            SceneManager.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
