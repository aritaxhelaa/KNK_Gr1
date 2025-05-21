package Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import models.Dto.RrugaDto.RrugaViewDto;
import models.Komuna;
import models.Qyteti;
import models.Fshati;
import repository.RrugaRepository;
import repository.QytetiRepository;
import repository.KomunaRepository;
import repository.FshatiRepository;
import services.AdresaService;
import services.SceneManager;
import utils.SceneLocator;
import utils.SessionManager;
import utils.SessionSearchData;

import java.sql.SQLException;
import java.util.List;

public class KerkoInfoController extends BaseController {

    @FXML
    private TableView<RrugaViewDto> tabelaRezultateve;

    @FXML
    private TableColumn<RrugaViewDto, String> colKomuna;

    @FXML
    private TableColumn<RrugaViewDto, String> colVendbanimi;

    @FXML
    private TableColumn<RrugaViewDto, String> colLagjia;

    @FXML
    @SuppressWarnings("unused")
    private TableColumn<RrugaViewDto, String> colRruga;


    @FXML
    @SuppressWarnings("unused")
    private Button kthehuBtn;

    @FXML
    private TableColumn<RrugaViewDto, Integer> colKodiPostar;

    private final AdresaService adresaService = new AdresaService();

    @FXML
    public void initialize() {
        // Inicializimi i kolonave
        colKomuna.setCellValueFactory(new PropertyValueFactory<>("komuna"));
        colVendbanimi.setCellValueFactory(new PropertyValueFactory<>("vendbanimi"));
        colLagjia.setCellValueFactory(new PropertyValueFactory<>("lagjia"));
        colRruga.setCellValueFactory(new PropertyValueFactory<>("rruga"));
        colKodiPostar.setCellValueFactory(new PropertyValueFactory<>("kodiPostar"));

        SessionSearchData data = SessionManager.getSearchData();
        if (data != null) {
            try {
                KomunaRepository komunaRepo = new KomunaRepository();
                Komuna komuna = komunaRepo.getByName(data.getKomuna());

                if (komuna == null) {
                    // Trajto rastin kur komuna nuk gjendet
                    return;
                }

                RrugaRepository rrugaRepo = new RrugaRepository();
                List<RrugaViewDto> rruget;

                if (data.getLloji().equals("Qytet")) {
                    QytetiRepository qytetiRepo = new QytetiRepository();
                    Qyteti qyteti = qytetiRepo.getByNameAndKomunaId(
                            data.getVendbanimi(),
                            komuna.getId()
                    );

                    rruget = rrugaRepo.getByKomunaAndVendbanimi(
                            komuna.getId(),
                            qyteti != null ? qyteti.getId() : null,
                            null  // fshatiId = null për qytet
                    );
                } else {
                    FshatiRepository fshatiRepo = new FshatiRepository();
                    Fshati fshati = fshatiRepo.getByNameAndKomunaId(
                            data.getVendbanimi(),
                            komuna.getId()
                    );

                    rruget = rrugaRepo.getByKomunaAndVendbanimi(
                            komuna.getId(),
                            null,  // qytetiId = null për fshat
                            fshati != null ? fshati.getId() : null
                    );
                }

                tabelaRezultateve.setItems(FXCollections.observableArrayList(rruget));
            } catch (SQLException e) {
                e.printStackTrace();
                // Trajtimi i gabimeve
            }
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