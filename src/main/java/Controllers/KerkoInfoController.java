package Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import models.Dto.AdresaDto.AdresaViewDto;
import services.AdresaService;
import services.SceneManager;
import utils.SceneLocator;
import utils.SessionManager;
import utils.SessionSearchData;

import java.util.List;

public class KerkoInfoController {

    @FXML
    private TableView<AdresaViewDto> tabelaRezultateve;

    @FXML
    private TableColumn<AdresaViewDto, String> colKomuna;

    @FXML
    private TableColumn<AdresaViewDto, String> colVendbanimi;

    @FXML
    private TableColumn<AdresaViewDto, String> colLagjia;

    @FXML
    private TableColumn<AdresaViewDto, String> colAdresa;

    @FXML
    private Button kthehuBtn;

    @FXML
    private TableColumn<AdresaViewDto, Integer> colKodiPostar;

    private AdresaService adresaService = new AdresaService();

    public void setKerkimi(String komuna, String lloji, String vendbanimi, String rruga) {
        List<AdresaViewDto> rezultatet = adresaService.kerkoAdresa(komuna, lloji, vendbanimi, rruga);
        tabelaRezultateve.setItems(FXCollections.observableArrayList(rezultatet));
    }

    @FXML
    public void initialize() {
        colKomuna.setCellValueFactory(new PropertyValueFactory<>("komuna"));
        colVendbanimi.setCellValueFactory(new PropertyValueFactory<>("vendbanimi"));
        colLagjia.setCellValueFactory(new PropertyValueFactory<>("lagjia"));
        colAdresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        colKodiPostar.setCellValueFactory(new PropertyValueFactory<>("kodiPostar"));

        SessionSearchData data = SessionManager.getSearchData();
        if (data != null) {
            List<AdresaViewDto> rezultatet = adresaService.kerkoAdresa(
                    data.getKomuna(),
                    data.getLloji(),
                    data.getVendbanimi(),
                    data.getRruga()
            );
            tabelaRezultateve.setItems(FXCollections.observableArrayList(rezultatet));
        }
    }

    @FXML
    private void handleKthehu() {
        try {
            SceneManager.load(SceneLocator.QYTETAR_DASHBOARD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}