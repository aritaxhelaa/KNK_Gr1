package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.AdresaDto.CreateAdresaDto;
import models.KodiPostar;
import models.Komuna;
import models.Rruga;
import repository.AdresaRepository;
import repository.KodiPostarRepository;
import repository.KomunaRepository;
import repository.RrugaRepository;
import services.LanguageManager;

import java.util.List;
import java.util.ResourceBundle;

public class RegjistroVendbanimController extends BaseController {

    @FXML private ComboBox<String> komunaComboBox;
    @FXML private ComboBox<String> rrugaComboBox;
    @FXML private TextField numriField;
    @FXML private Label kodiPostarLabel;
    @FXML private Label messageLabel;
    @FXML private Button regjistroButton;

    private KomunaRepository komunaRepository = new KomunaRepository();
    private RrugaRepository rrugaRepository = new RrugaRepository();
    private KodiPostarRepository kodiPostarRepository = new KodiPostarRepository();
    private AdresaRepository adresaRepository = new AdresaRepository();

    private int kodiPostarAktual = -1;

    private final ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

    @FXML
    public void initialize() {
        mbushKomunat();

        komunaComboBox.setOnAction(event -> {
            String komuna = komunaComboBox.getValue();
            mbushRruget(komuna);
            gjeneroKodinPostar(komuna);
        });
    }

    private void mbushKomunat() {
        try {
            List<Komuna> komunat = komunaRepository.getAll();
            for (Komuna komuna : komunat) {
                komunaComboBox.getItems().add(komuna.getEmri());
            }
        } catch (Exception e) {
            messageLabel.setText(bundle.getString("message.error.load.komuna"));
        }
    }

    private void mbushRruget(String emriKomunes) {
        try {
            int komunaId = komunaRepository.getAll().stream()
                    .filter(k -> k.getEmri().equalsIgnoreCase(emriKomunes))
                    .map(Komuna::getId)
                    .findFirst().orElse(-1);

            if (komunaId == -1) return;

            List<Rruga> rruget = rrugaRepository.getByKomunaId(komunaId);
            rrugaComboBox.getItems().clear();
            for (Rruga rr : rruget) {
                rrugaComboBox.getItems().add(rr.getEmri());
            }
        } catch (Exception e) {
            messageLabel.setText(bundle.getString("message.error.load.rruga"));
        }
    }

    private void gjeneroKodinPostar(String emriKomunes) {
        try {
            KodiPostar kp = kodiPostarRepository.getByKomunaName(emriKomunes);
            if (kp != null) {
                kodiPostarAktual = Integer.parseInt(kp.getKodi());
                kodiPostarLabel.setText(kp.getKodi());
            } else {
                kodiPostarAktual = -1;
                kodiPostarLabel.setText("?");
            }
        } catch (Exception e) {
            kodiPostarLabel.setText(bundle.getString("message.error.kodipostar"));
        }
    }

    @FXML
    private void handleSave() {
        String rruga = rrugaComboBox.getValue();
        String numriText = numriField.getText();

        if (komunaComboBox.getValue() == null || rruga == null || numriText.isEmpty() || kodiPostarAktual == -1) {
            messageLabel.setText(bundle.getString("message.error.fields"));
            return;
        }

        try {
            int numri = Integer.parseInt(numriText);
            CreateAdresaDto dto = new CreateAdresaDto(rruga, numri, kodiPostarAktual);
            adresaRepository.create(dto);

            messageLabel.setText(bundle.getString("message.success1"));
            clearFields();
        } catch (NumberFormatException e) {
            messageLabel.setText(bundle.getString("message.error.format"));
        } catch (Exception e) {
            messageLabel.setText(bundle.getString("message.error.db"));
            e.printStackTrace();
        }
    }

    private void clearFields() {
        komunaComboBox.getSelectionModel().clearSelection();
        rrugaComboBox.getItems().clear();
        numriField.clear();
        kodiPostarLabel.setText("-");
    }
}
