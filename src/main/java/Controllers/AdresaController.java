package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import models.KodiPostar;
import models.Komuna;
import models.Rruga;
import repository.KodiPostarRepository;
import repository.KomunaRepository;
import repository.RrugaRepository;
import services.SceneManager;
import utils.SceneLocator;

import java.util.ArrayList;

public class AdresaController {

    @FXML private ComboBox<String> komunaComboBox;
    @FXML private ComboBox<String> rrugaComboBox;
    @FXML private TextField numriField;
    @FXML private Label kodiPostarLabel;
    @FXML private WebView mapView;

    // Repozitorit do inicializohen vetëm në momentin kur kërkohen
    private KomunaRepository komunaRepository;
    private RrugaRepository rrugaRepository;
    private KodiPostarRepository kodiPostarRepository;

    @FXML
    public void initialize() {
        try {
            // Lazy initialization
            if (komunaRepository == null) komunaRepository = new KomunaRepository();
            if (rrugaRepository == null) rrugaRepository = new RrugaRepository();

            ArrayList<Komuna> komunat = komunaRepository.getAll();
            ArrayList<Rruga> rruget = rrugaRepository.getAll();

            for (Komuna k : komunat) {
                komunaComboBox.getItems().add(k.getEmri());
            }

            for (Rruga r : rruget) {
                rrugaComboBox.getItems().add(r.getEmri());
            }

        } catch (Exception e) {
            System.out.println("Gabim gjatë mbushjes së kombo-boxëve: " + e.getMessage());
        }
    }

    @FXML
    private void handleShowMap() {
        String komuna = komunaComboBox.getValue();
        String rruga = rrugaComboBox.getValue();
        String numri = numriField.getText();

        if (komuna == null || rruga == null || numri.isEmpty()) {
            kodiPostarLabel.setText("Ju lutem plotësoni të gjitha fushat.");
            return;
        }

        try {
            if (kodiPostarRepository == null) kodiPostarRepository = new KodiPostarRepository();

            KodiPostar kodiPostar = kodiPostarRepository.getByKomunaName(komuna);
            if (kodiPostar != null) {
                kodiPostarLabel.setText(kodiPostar.getKodi());
            } else {
                kodiPostarLabel.setText("Kodi postar nuk u gjet.");
            }

            // Harta
            String query = rruga + " " + numri + ", " + komuna + ", Kosovo";
            String mapUrl = "https://www.google.com/maps?q=" + query.replace(" ", "+");
            WebEngine webEngine = mapView.getEngine();
            webEngine.load(mapUrl);

        } catch (Exception e) {
            kodiPostarLabel.setText("Gabim gjatë kërkimit të kodit postar.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            SceneManager.load(SceneLocator.KOMUNAL_DASHBOARD);
//            SceneManager.load("/view/QytetariView.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
