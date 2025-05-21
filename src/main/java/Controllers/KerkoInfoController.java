package Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Dto.AdresaDto.AdresaViewDto;
import repository.AdresaRepository;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;
import utils.SessionManager;
import utils.SessionSearchData;

import java.util.List;
import java.util.ResourceBundle;

public class KerkoInfoController extends BaseController {

    @FXML
    private TableView<AdresaViewDto> tabelaRezultateve;

    @FXML
    private TableColumn<AdresaViewDto, String> colKomuna;

    @FXML
    private TableColumn<AdresaViewDto, String> colVendbanimi;

    @FXML
    private TableColumn<AdresaViewDto, String> colLagjia;

    @FXML
    private TableColumn<AdresaViewDto, String> colRruga;

    @FXML
    private TableColumn<AdresaViewDto, Integer> colKodiPostar;

    @FXML
    private Button kthehuBtn;

    private final AdresaRepository adresaRepository = new AdresaRepository();

    @FXML
    public void initialize() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        // Inicializimi i kolonave
        colKomuna.setCellValueFactory(new PropertyValueFactory<>("komuna"));
        colVendbanimi.setCellValueFactory(new PropertyValueFactory<>("vendbanimi"));
        colLagjia.setCellValueFactory(new PropertyValueFactory<>("lagjia"));
        colRruga.setCellValueFactory(new PropertyValueFactory<>("rruga"));
        colKodiPostar.setCellValueFactory(new PropertyValueFactory<>("kodiPostar"));

        SessionSearchData data = SessionManager.getSearchData();

        if (data == null) {
            showAlert(bundle.getString("alert.warning.title"), bundle.getString("alert.no_search_data"));
            return;
        }

        try {
            List<AdresaViewDto> rezultatet = adresaRepository.kerkoAdresa(
                    data.getKomuna(),
                    data.getLloji(),
                    data.getVendbanimi(),
                    data.getAdresa()
            );

            if (rezultatet == null || rezultatet.isEmpty()) {
                showAlert(bundle.getString("alert.warning.title"), bundle.getString("alert.no_results_found"));
            } else {
                tabelaRezultateve.setItems(FXCollections.observableArrayList(rezultatet));
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(bundle.getString("alert.warning.title"),
                    bundle.getString("alert.search_failed") + ": " + e.getMessage());
        }
    }

    @FXML
    private void handleKthehu() {
        try {
            SceneManager.load(SceneLocator.QYTETAR_DASHBOARD);
        } catch (Exception e) {
            e.printStackTrace();
            ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
            showAlert(bundle.getString("alert.warning.title"), bundle.getString("alert.scene_load_failed"));
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
