package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Komuna;
import repository.KomunaRepository;
import services.LanguageManager;

import java.util.List;
import java.util.ResourceBundle;

public class RegjistroVendbanimController extends BaseController {

    @FXML
    private ComboBox<String> komunaComboBox;

    @FXML
    private ComboBox<String> llojiVendbanimitComboBox;

    @FXML
    private TextField emriVendbanimitField;

    @FXML
    private TextField rrugaField;

    @FXML
    private TextField objektiField;

    @FXML
    private TextField kodiPostarField;

    @FXML
    private Button saveButton;

    private KomunaRepository komunaRepository = new KomunaRepository();

    @FXML
    private void initialize() {
        loadKomunat();
        llojiVendbanimitComboBox.getItems().addAll("Qytet", "Fshat", "Lagje");
    }

    private void loadKomunat() {
        try {
            List<Komuna> komunat = komunaRepository.getAll();
            for (Komuna komuna : komunat) {
                komunaComboBox.getItems().add(komuna.getEmri());
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
            showAlert(bundle.getString("alert.error.title"), bundle.getString("alert.no_municipalities"));
        }

    }

    @FXML
    private void handleSave() {
        String komuna = komunaComboBox.getValue();
        String lloji = llojiVendbanimitComboBox.getValue();
        String emri = emriVendbanimitField.getText();
        String rruga = rrugaField.getText();
        String objekti = objektiField.getText();
        String kodiPostar = kodiPostarField.getText();

        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        if (komuna == null || lloji == null || emri.isEmpty() || rruga.isEmpty()
                || objekti.isEmpty() || kodiPostar.isEmpty()) {
            showAlert(bundle.getString("alert.warning.title"), bundle.getString("alert.fill_all_fields"));
            return;
        }

        System.out.println("Regjistruar vendbanimi: ");
        System.out.println("Komuna: " + komuna);
        System.out.println("Lloji: " + lloji);
        System.out.println("Emri: " + emri);
        System.out.println("Rruga: " + rruga);
        System.out.println("Objekti: " + objekti);
        System.out.println("Kodi Postar: " + kodiPostar);


        showAlert(bundle.getString("alert.success.title"), bundle.getString("register.settlement.success"));
        clearFields();
    }

    private void clearFields() {
        komunaComboBox.getSelectionModel().clearSelection();
        llojiVendbanimitComboBox.getSelectionModel().clearSelection();
        emriVendbanimitField.clear();
        rrugaField.clear();
        objektiField.clear();
        kodiPostarField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
