package Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import models.Dto.AdresaDto.AdresaViewDto;
import repository.AdresaRepository;
import services.SceneManager;
import utils.SceneLocator;
import utils.SessionManager;
import utils.SessionSearchData;

import java.util.List;

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
        // Inicializimi i kolonave
        colKomuna.setCellValueFactory(new PropertyValueFactory<>("komuna"));
        colVendbanimi.setCellValueFactory(new PropertyValueFactory<>("vendbanimi"));
        colLagjia.setCellValueFactory(new PropertyValueFactory<>("lagjia"));
        colRruga.setCellValueFactory(new PropertyValueFactory<>("rruga"));
        colKodiPostar.setCellValueFactory(new PropertyValueFactory<>("kodiPostar"));

        // Merr të dhënat nga sesioni
        SessionSearchData data = SessionManager.getSearchData();
        if (data != null) {
            List<AdresaViewDto> rezultatet = adresaRepository.kerkoAdresa(
                    data.getKomuna(),
                    data.getLloji(),
                    data.getVendbanimi(),
                    data.getAdresa()
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
