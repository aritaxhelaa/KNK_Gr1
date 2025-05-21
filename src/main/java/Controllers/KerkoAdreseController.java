package Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import models.Dto.AdresaDto.AdresaViewDto;
import models.Komuna;
import repository.AdresaRepository;
import repository.KomunaRepository;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class KerkoAdreseController extends BaseController {

    @FXML private ComboBox<Komuna> komunaComboBox;
    @FXML private ComboBox<String> llojiComboBox;
    @FXML private TextField vendbanimiField;
    @FXML private TextField rrugaField;
    @FXML private Button searchButton;

    private final AdresaRepository adresaRepository = new AdresaRepository();
    private final KomunaRepository komunaRepository = new KomunaRepository();

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
        komunaComboBox.setItems(FXCollections.observableArrayList(komunaRepository.getAll()));
        llojiComboBox.setItems(FXCollections.observableArrayList("Qytet", "Fshat"));

        komunaComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Komuna komuna) {
                return komuna != null ? komuna.getEmri() : "";
            }

            @Override
            public Komuna fromString(String s) {
                return komunaComboBox.getItems().stream()
                        .filter(k -> k.getEmri().equals(s))
                        .findFirst().orElse(null);
            }
        });
    }

    @FXML
    private void handleSearch() {
        Komuna komunaObj = komunaComboBox.getValue();
        String lloji = llojiComboBox.getValue();
        String vendbanimi = vendbanimiField.getText();
        String rruga = rrugaField.getText();

        if (komunaObj == null || lloji == null || vendbanimi.isEmpty() || rruga.isEmpty()) {
            showAlert("Gabim", "Ju lutem plotësoni të gjitha fushat.");
            return;
        }

        String komunaEmri = komunaObj.getEmri();

        List<AdresaViewDto> rezultatet = adresaRepository.kerkoAdresa(komunaEmri, lloji, vendbanimi, rruga);
        if (rezultatet.isEmpty()) {
            showAlert("Nuk u gjet", "Nuk u gjet asnjë adresë.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (AdresaViewDto dto : rezultatet) {
                sb.append("Komuna: ").append(dto.getKomuna()).append("\n")
                        .append("Vendbanimi: ").append(dto.getVendbanimi()).append("\n")
                        .append("Lagjja: ").append(dto.getLagjia()).append("\n")
                        .append("Adresa: ").append(dto.getRruga()).append("\n")
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
