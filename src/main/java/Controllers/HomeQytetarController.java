package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import models.Komuna;
import models.Fshati;
import repository.FshatiRepository;
import repository.KomunaRepository;
import repository.QytetiRepository;
import services.SceneManager;
import utils.SceneLocator;
import utils.SessionManager;
import utils.SessionSearchData;

import java.util.List;
import java.util.stream.Collectors;

public class HomeQytetarController extends BaseController{

    @FXML
    private ComboBox<String> comboKomuna;

    @FXML
    private ComboBox<String> comboEmriVendbanimit;

    @FXML
    private TextField txtRruga;

    @FXML
    private Label lblKerkoAdrese;

    @FXML
    private Label lblLlojiVendbanimit;

    @FXML
    private Label lblRruga;

    @FXML
    private Button btnKerko;

    @FXML
    private Label lblTitulli;

    @FXML
    private Label lblWelcome;

    @FXML
    private RadioButton radioQytet;

    @FXML
    private RadioButton radioFshat;

    @FXML
    private ToggleGroup llojiGroup;

    private FilteredList<String> filteredVendbanime;

    @FXML
    private void initialize() {
        // Tekst mirëseardhjeje mund të vendoset dinamikisht më vonë
        lblWelcome.setText("Mirë se vini, qytetar!");
        // Mbush dropdown e komunave nga databaza
        List<Komuna> komunaList = komunaRepository.getAll();
        comboKomuna.setItems(FXCollections.observableArrayList(
                komunaList.stream().map(Komuna::getEmri).collect(Collectors.toList())
        ));

        // Përgatit filtrimin për vendbanime
        filteredVendbanime = new FilteredList<>(FXCollections.observableArrayList(), p -> true);
        comboEmriVendbanimit.setItems(filteredVendbanime);
        comboEmriVendbanimit.setEditable(true);

        comboEmriVendbanimit.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            final TextField editor = comboEmriVendbanimit.getEditor();
            final String selected = comboEmriVendbanimit.getSelectionModel().getSelectedItem();

            // Filtro opsionet sipas input-it
            filteredVendbanime.setPredicate(item -> {
                if (newVal == null || newVal.isEmpty()) return true;
                String lower = newVal.toLowerCase();
                return item.toLowerCase().contains(lower);
            });

            // Mos lejo zgjedhje të pavlefshme automatikisht
            if (selected == null || !selected.equals(editor.getText())) {
                comboEmriVendbanimit.getSelectionModel().clearSelection();
            }
        });

        llojiGroup = new ToggleGroup();
        radioQytet.setToggleGroup(llojiGroup);
        radioFshat.setToggleGroup(llojiGroup);

    }

    public QytetiRepository getQytetiRepository() {
        return qytetiRepository;
    }

    @FXML
    private void handleLlojiZgjedhur() {
        if (radioQytet.isSelected()) {
            System.out.println("Qytet");
            // veprime nëse është qytet
        } else if (radioFshat.isSelected()) {
            System.out.println("Fshat");
            // veprime nëse është fshat
        }
    }


    private final KomunaRepository komunaRepository = new KomunaRepository();
    private final QytetiRepository qytetiRepository = new QytetiRepository();
    private final FshatiRepository fshatiRepository = new FshatiRepository();

    @FXML
    private void handleKomunaLlojiChanged() {
        String lloji = null;
        if (radioQytet.isSelected()) {
            lloji = "Qytet";
        } else if (radioFshat.isSelected()) {
            lloji = "Fshat";
        }
        String komuna = comboKomuna.getValue();

        if (lloji == null || komuna == null) {
            comboEmriVendbanimit.getItems().clear();
            return;
        }

        if (lloji.equals("Qytet")) {
            filteredVendbanime.setAll(List.of(komuna));
            comboEmriVendbanimit.getSelectionModel().selectFirst();
        } else if (lloji.equals("Fshat")) {
            List<String> fshatrat = fshatiRepository.getByKomunaName(komuna)
                    .stream()
                    .map(Fshati::getEmri)
                    .collect(Collectors.toList());
            filteredVendbanime.setAll(fshatrat);
        }
    }

    @FXML
    private void kerkoAdrese() {
        String komuna = comboKomuna.getValue();
        String lloji = radioQytet.isSelected() ? "Qytet" : radioFshat.isSelected() ? "Fshat" : null;
        String vendbanimi = comboEmriVendbanimit.getValue();
        String rruga = txtRruga.getText();
        if (komuna == null || lloji == null || vendbanimi == null || rruga.isBlank()) {
            showAlert("Ju lutem plotësoni të gjitha fushat!");
            return;
        }

        SessionManager.setSearchData(new SessionSearchData(komuna, lloji, vendbanimi, rruga));

        try {
            SceneManager.load(SceneLocator.KERKO_INFO);
        } catch (Exception e) {
            e.printStackTrace();
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

