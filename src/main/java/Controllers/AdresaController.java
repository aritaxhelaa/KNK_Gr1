package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import models.KodiPostar;
import models.Komuna;
import models.Rruga;
import repository.KodiPostarRepository;
import repository.KomunaRepository;
import repository.RrugaRepository;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdresaController extends BaseController{

    @FXML private ComboBox<String> komunaComboBox;
    @FXML private ComboBox<String> rrugaComboBox;
    @FXML private TextField numriField;
    @FXML private Label kodiPostarLabel;
    @FXML private Label messageLabel;
    @FXML private Label titleLabel;
    @FXML private Button shfaqBtn;
    @FXML private Button backBtn;
    @FXML private WebView mapView;

    private KomunaRepository komunaRepository;
    private RrugaRepository rrugaRepository;
    private KodiPostarRepository kodiPostarRepository;

    @FXML
    public void initialize() {
        loadLocalizedTexts();
        mbushKomunat();
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

    private void mbushKomunat() {
        try {
            if (komunaRepository == null) komunaRepository = new KomunaRepository();
            ArrayList<Komuna> komunat = komunaRepository.getAll();

            for (Komuna komuna : komunat) {
                komunaComboBox.getItems().add(komuna.getEmri());
            }

            komunaComboBox.setOnAction(event -> {
                String emriKomunes = komunaComboBox.getValue();
                mbushRruget(emriKomunes);
            });

        } catch (Exception e) {
            messageLabel.setText("Gabim gjatë ngarkimit të komunave.");
            e.printStackTrace();
        }
    }

    private void mbushRruget(String emriKomunes) {
        try {
            if (rrugaRepository == null) rrugaRepository = new RrugaRepository();
            if (komunaRepository == null) komunaRepository = new KomunaRepository();

            int komunaId = komunaRepository.getAll().stream()
                    .filter(k -> k.getEmri().equalsIgnoreCase(emriKomunes))
                    .map(Komuna::getId)
                    .findFirst()
                    .orElse(-1);

            if (komunaId == -1) return;

            List<Rruga> rruget = rrugaRepository.getByKomunaId(komunaId);
            rrugaComboBox.getItems().clear();
            for (Rruga rr : rruget) {
                rrugaComboBox.getItems().add(rr.getEmri());
            }

        } catch (Exception e) {
            messageLabel.setText("Gabim gjatë ngarkimit të rrugëve.");
            e.printStackTrace();
        }
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

        try {
            if (kodiPostarRepository == null) kodiPostarRepository = new KodiPostarRepository();
            KodiPostar kodiPostar = kodiPostarRepository.getByKomunaName(komuna);

            if (kodiPostar != null) {
                kodiPostarLabel.setText(bundle.getString("message.success") + ": " + kodiPostar.getKodi());
                messageLabel.setText("");
            } else {
                messageLabel.setText(bundle.getString("message.error.notfound"));
                kodiPostarLabel.setText("");
            }

            String query = rruga + " " + numri + ", " + komuna + ", Kosovo";
            loadMap("https://www.google.com/maps?q=" + query.replace(" ", "+"));

        } catch (Exception e) {
            messageLabel.setText("Gabim gjatë kërkimit.");
            e.printStackTrace();
        }
    }

    private void loadMap(String url) {
        WebEngine engine = mapView.getEngine();
        engine.load(url);
    }

    @FXML
    private void handleBack() {
        try {
            SceneManager.load(SceneLocator.QYTETAR_DASHBOARD);
        } catch (Exception e) {
            messageLabel.setText("Nuk u kthye dot.");
            e.printStackTrace();
        }
    }


}
