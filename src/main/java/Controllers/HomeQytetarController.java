package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.*;
import models.Dto.AdresaDto.AdresaViewDto;
import models.Dto.AdresaDto.CreateAdresaDto;
import models.Dto.QytetiDto.CreateQytetiDto;
import models.Dto.RrugaDto.RrugaViewDto;
import repository.*;
import services.SceneManager;
import utils.SceneLocator;
import utils.SessionManager;
import utils.SessionSearchData;

import java.util.List;
import java.util.stream.Collectors;

public class HomeQytetarController extends BaseController {

    @FXML private ComboBox<String> comboKomuna;
    @FXML private ComboBox<String> comboEmriVendbanimit;
    @FXML private ComboBox<String> comboRruga;
    @FXML private TextField txtRruga;
    @FXML private Label lblKerkoAdrese;
    @FXML private Label lblLlojiVendbanimit;
    @FXML private Label lblRruga;
    @FXML private Button btnKerko;
    @FXML private Label lblTitulli;
    @FXML private Label lblWelcome;
    @FXML private RadioButton radioQytet;
    @FXML private RadioButton radioFshat;
    @FXML private ToggleGroup llojiGroup;

    private ObservableList<String> vendbanimeList;
    private FilteredList<String> filteredVendbanime;
    private ObservableList<String> rrugaList;

    private final KomunaRepository komunaRepository = new KomunaRepository();
    private final QytetiRepository qytetiRepository = new QytetiRepository();
    private final FshatiRepository fshatiRepository = new FshatiRepository();
    private final RrugaRepository rrugaRepository = new RrugaRepository();

    @FXML
    private void initialize() {
        try {
            // Inicializimi i përdoruesit
            if (SessionManager.getCurrentUser() != null) {
                String emri = SessionManager.getCurrentUser().getName();
                lblWelcome.setText("Mirë se vini, " + emri + "!");
            } else {
                lblWelcome.setText("Mirë se vini!");
                System.err.println("[Gabim] Përdoruesi nuk është loguar");
            }

            // Ngarkimi i komunave
            List<Komuna> komunaList = komunaRepository.getAll();
            if (komunaList == null || komunaList.isEmpty()) {
                showAlert("Nuk u gjetën komuna në bazën e të dhënave!");
                return;
            }

            comboKomuna.setItems(FXCollections.observableArrayList(
                    komunaList.stream().map(Komuna::getEmri).collect(Collectors.toList())
            ));

            comboEmriVendbanimit.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (radioFshat.isSelected() && newVal != null) {
                    handleKomunaLlojiChanged();
                }
            });

            // Inicializimi i listave të vendbanimeve dhe rrugëve
            vendbanimeList = FXCollections.observableArrayList();
            filteredVendbanime = new FilteredList<>(vendbanimeList, p -> true);
            comboEmriVendbanimit.setItems(filteredVendbanime);
            comboEmriVendbanimit.setEditable(true);

            rrugaList = FXCollections.observableArrayList();
            comboRruga.setItems(rrugaList);

            // Filter për vendbanimet
            comboEmriVendbanimit.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
                final TextField editor = comboEmriVendbanimit.getEditor();
                final String selected = comboEmriVendbanimit.getSelectionModel().getSelectedItem();

                filteredVendbanime.setPredicate(item -> {
                    if (newVal == null || newVal.isEmpty()) return true;
                    String lower = newVal.toLowerCase();
                    return item.toLowerCase().contains(lower);
                });

                if (selected == null || !selected.equals(editor.getText())) {
                    comboEmriVendbanimit.getSelectionModel().clearSelection();
                }
            });

            // Inicializimi i toggle group për llojin e vendbanimit
            llojiGroup = new ToggleGroup();
            radioQytet.setToggleGroup(llojiGroup);
            radioFshat.setToggleGroup(llojiGroup);

            // Listener për ndryshime
            comboKomuna.setOnAction(event -> handleKomunaLlojiChanged());
            radioQytet.setOnAction(event -> handleKomunaLlojiChanged());
            radioFshat.setOnAction(event -> handleKomunaLlojiChanged());

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Gabim në inicializimin e faqes: " + e.getMessage());
        }
    }

    @FXML
    private void handleKomunaLlojiChanged() {
        try {
            String lloji = radioQytet.isSelected() ? "Qytet" : radioFshat.isSelected() ? "Fshat" : null;
            String komuna = comboKomuna.getValue();

            if (lloji == null || komuna == null) {
                vendbanimeList.clear();
                rrugaList.clear();
                return;
            }

            Komuna selectedKomuna = komunaRepository.getAll().stream()
                    .filter(k -> k.getEmri().equalsIgnoreCase(komuna))
                    .findFirst()
                    .orElse(null);

            if (selectedKomuna == null) {
                vendbanimeList.clear();
                rrugaList.clear();
                showAlert("Komuna e zgjedhur nuk u gjet në bazën e të dhënave!");
                return;
            }

            if (lloji.equals("Qytet")) {
                // Për qytet
                vendbanimeList.setAll(List.of(komuna));
                comboEmriVendbanimit.getSelectionModel().selectFirst();

                Qyteti qyteti = qytetiRepository.getByNameAndKomunaId(komuna, selectedKomuna.getId());

                if (qyteti == null) {
                    qyteti = qytetiRepository.getAll().stream()
                            .filter(q -> q.getEmri().equalsIgnoreCase(komuna) &&
                                    q.getKomunaId() == selectedKomuna.getId())
                            .findFirst()
                            .orElse(null);

                    if (qyteti == null) {
                        CreateQytetiDto qytetiDto = new CreateQytetiDto(komuna, selectedKomuna.getId());
                        qyteti = qytetiRepository.create(qytetiDto);

                        if (qyteti == null) {
                            showAlert("Gabim në krijimin e qytetit të ri!");
                            rrugaList.clear();
                            return;
                        }
                    }
                }

                // Merr rrugët për këtë qytet
                List<RrugaViewDto> rruget = rrugaRepository.getByKomunaAndVendbanimi(
                        selectedKomuna.getId(), qyteti.getId(), null);

                if (rruget != null && !rruget.isEmpty()) {
                    for (RrugaViewDto r : rruget) {
                        rrugaList.add(r.getRruga());
                    }


                } else {
                    rrugaList.clear();
                    showAlert("Nuk u gjetën rrugë për këtë qytet!");
                }
            } else {
                // Për fshat
                List<Fshati> fshatrat = fshatiRepository.getByKomunaName(komuna);

                if (fshatrat != null && !fshatrat.isEmpty()) {
                    vendbanimeList.setAll(fshatrat.stream()
                            .map(Fshati::getEmri)
                            .collect(Collectors.toList()));

                    // Nëse është zgjedhur një fshat, merr rrugët për atë fshat
                    String selectedFshat = comboEmriVendbanimit.getValue();
                    if (selectedFshat != null) {
                        Fshati fshati = fshatrat.stream()
                                .filter(f -> f.getEmri().equals(selectedFshat))
                                .findFirst()
                                .orElse(null);

                        if (fshati != null) {
                            List<RrugaViewDto> rruget = rrugaRepository.getByKomunaAndVendbanimi(
                                    selectedKomuna.getId(), null, fshati.getId());

                            if (rruget != null && !rruget.isEmpty()) {
                                for (RrugaViewDto r : rruget) {
                                    rrugaList.add(r.getRruga());
                                }

                            } else {
                                rrugaList.clear();
                            }
                        }
                    }
                } else {
                    vendbanimeList.clear();
                    showAlert("Nuk u gjetën fshatra për këtë komunë!");
                    rrugaList.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Gabim në përpunimin e të dhënave: " + e.getMessage());
        }
    }



    @FXML
    private void kerkoAdrese() {
        try {
            String komuna = comboKomuna.getValue();
            String lloji = radioQytet.isSelected() ? "Qytet" : radioFshat.isSelected() ? "Fshat" : null;
            String vendbanimi = comboEmriVendbanimit.getValue();
            String rruga = comboRruga.getValue();

            if (komuna == null || lloji == null || vendbanimi == null || rruga == null || rruga.isBlank()) {
                showAlert("Ju lutem plotësoni të gjitha fushat!");
                return;
            }

            // Ruaj në session për me i shfaq në faqen tjetër
            SessionManager.setSearchData(new SessionSearchData(komuna, lloji, vendbanimi, rruga));

            AdresaRepository adresaRepository = new AdresaRepository();
            List<AdresaViewDto> gjetura = adresaRepository.kerkoAdresa(komuna, lloji, vendbanimi, rruga);

            if (gjetura.isEmpty()) {
                showAlert("Nuk u gjet asnjë adresë për ruajtje.");
                return;
            }

            // Gjej adresën ekzistuese që përputhet për me e ruajt si kërkim
            AdresaViewDto dto = gjetura.get(0);
            Adresa ekzistuese = adresaRepository.gjejNgaDto(dto);

            if (ekzistuese != null && SessionManager.getCurrentUser() != null) {
                adresaRepository.ruajKerkim(SessionManager.getCurrentUser().getId(), ekzistuese.getId());
            }

            // Shko në faqen tjetër për rezultatet
            SceneManager.load(SceneLocator.KERKO_INFO);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Gabim në kërkimin e adresës: " + e.getMessage());
        }
    }





    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Vërejtje");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}