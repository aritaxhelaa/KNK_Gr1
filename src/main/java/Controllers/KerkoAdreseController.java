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
import java.util.ResourceBundle;

public class KerkoAdreseController extends BaseController {

    @FXML private ComboBox<Komuna> komunaComboBox;
    @FXML private ComboBox<String> llojiComboBox;
    @FXML private TextField vendbanimiField;
    @FXML private TextField rrugaField;
    @FXML private Button searchButton;

    private final AdresaRepository adresaRepository = new AdresaRepository();
    private final KomunaRepository komunaRepository = new KomunaRepository();

    private ResourceBundle bundle;

    @FXML
    private void initialize() {
        bundle = LanguageManager.getInstance().getResourceBundle();
        loadLocalizedTexts();
        populateDropdowns();
    }

    private void loadLocalizedTexts() {
        komunaComboBox.setPromptText(bundle.getString("form.prompt.municipality"));
        llojiComboBox.setPromptText(bundle.getString("form.prompt.lloji"));
        vendbanimiField.setPromptText(bundle.getString("form.prompt.vendbanimi"));
        rrugaField.setPromptText(bundle.getString("form.prompt.street"));
        searchButton.setText(bundle.getString("form.button.search"));
    }

    private void populateDropdowns() {
        komunaComboBox.setItems(FXCollections.observableArrayList(komunaRepository.getAll()));

        llojiComboBox.setItems(FXCollections.observableArrayList(
                bundle.getString("form.city"),
                bundle.getString("form.village")
        ));

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
            showAlert(bundle.getString("alert.warning.title"), bundle.getString("alert.fill_all_fields"));
            return;
        }

        String komunaEmri = komunaObj.getEmri();
        List<AdresaViewDto> rezultatet = adresaRepository.kerkoAdresa(komunaEmri, lloji, vendbanimi, rruga);

        if (rezultatet.isEmpty()) {
            showAlert(bundle.getString("alert.no_results.title"), bundle.getString("alert.no_address_found"));
        } else {
            StringBuilder sb = new StringBuilder();
            for (AdresaViewDto dto : rezultatet) {
                sb.append(bundle.getString("label.municipality")).append(": ").append(dto.getKomuna()).append("\n")
                        .append(bundle.getString("label.settlement")).append(": ").append(dto.getVendbanimi()).append("\n")
                        .append(bundle.getString("label.neighborhood")).append(": ").append(dto.getLagjia()).append("\n")
                        .append(bundle.getString("label.address")).append(": ").append(dto.getRruga()).append("\n")
                        .append(bundle.getString("label.postal_code")).append(": ").append(dto.getKodiPostar()).append("\n\n");
            }
            showAlert(bundle.getString("alert.results.title"), sb.toString());
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
