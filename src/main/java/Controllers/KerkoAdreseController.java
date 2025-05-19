package Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.AdresaDto.AdresaViewDto;
import repository.AdresaRepository;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class KerkoAdreseController extends BaseController{

    @FXML private ComboBox<String> komunaComboBox;
    @FXML private ComboBox<String> llojiComboBox;
    @FXML private TextField vendbanimiField;
    @FXML private TextField rrugaField;
    @FXML private Button searchButton;

    private final AdresaRepository adresaRepository = new AdresaRepository();

    @FXML
    private void initialize() {
        loadLocalizedTexts();
        populateDropdowns();
    }

    private void loadLocalizedTexts() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        komunaComboBox.setPromptText(bundle.getString("form.prompt.municipality"));
        llojiComboBox.setPromptText(bundle.getString("form.prompt.lloji"));
        vendbanimiField.setPromptText(bundle.getString("form.prompt.vendbanimi"));
        rrugaField.setPromptText(bundle.getString("form.prompt.street"));
        searchButton.setText(bundle.getString("form.button.search"));
    }

    private void populateDropdowns() {
        komunaComboBox.getItems().addAll("Prishtinë", "Pejë", "Mitrovicë", "Gjakovë");
        llojiComboBox.getItems().addAll("Qytet", "Fshat");
    }

    @FXML
    private void handleSearch() {
        String komuna = komunaComboBox.getValue();
        String lloji = llojiComboBox.getValue();
        String vendbanimi = vendbanimiField.getText();
        String rruga = rrugaField.getText();

        if (komuna == null || lloji == null || vendbanimi.isEmpty() || rruga.isEmpty()) {
            showAlert("Gabim", "Ju lutem plotësoni të gjitha fushat.");
            return;
        }

        List<AdresaViewDto> rezultatet = adresaRepository.kerkoAdresa(komuna, lloji, vendbanimi, rruga);
        if (rezultatet.isEmpty()) {
            showAlert("Nuk u gjet", "Nuk u gjet asnjë adresë.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (AdresaViewDto dto : rezultatet) {
                sb.append("Komuna: ").append(dto.getKomuna()).append("\n")
                        .append("Vendbanimi: ").append(dto.getVendbanimi()).append("\n")
                        .append("Lagjja: ").append(dto.getLagjia()).append("\n")
                        .append("Adresa: ").append(dto.getAdresa()).append("\n")
                        .append("Kodi postar: ").append(dto.getKodiPostar()).append("\n\n");
            }
            showAlert("Rezultate", sb.toString());
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
