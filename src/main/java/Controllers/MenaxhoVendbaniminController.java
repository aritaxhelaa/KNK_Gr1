package Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Adresa;
import repository.AdresaRepository;

import java.util.List;

public class MenaxhoVendbaniminController extends BaseController{

    @FXML
    private TableView<Adresa> tabelaPerdoruesve;

    @FXML
    private TableColumn<Adresa, String> colUser;

    @FXML
    private TableColumn<Adresa, String> colPozita;

    @FXML
    private TableColumn<Adresa, String> colData;

    @FXML
    private TableColumn<Adresa, Integer> colKodiPostar;

    @FXML
    private TableColumn<Adresa, Void> colDelete;

    @FXML
    private TableColumn<Adresa, Void> colUpdate;

    private final AdresaRepository adresaRepository = new AdresaRepository();
    private Adresa vendbanimZgjedhur;

    @FXML
    private void initialize() {
        konfiguroKolonat();
        ngarkoTeDhenat();
    }

    private void konfiguroKolonat() {
        colUser.setCellValueFactory(new PropertyValueFactory<>("rruga"));
        colPozita.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty("Prishtinë")); // shembull
        colData.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty("Qytet"));       // shembull
        colKodiPostar.setCellValueFactory(new PropertyValueFactory<>("kodiPostar"));

        colDelete.setCellFactory(column -> new TableCell<>() {
            private final Button btn = new Button("Fshi");

            {
                btn.setOnAction(e -> {
                    Adresa adresa = getTableView().getItems().get(getIndex());
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "A jeni i sigurt për fshirjen?");
                    confirm.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            boolean success = adresaRepository.delete(adresa.getId()); // ✅ përdor nga BaseRepository
                            if (success) {
                                tabelaPerdoruesve.getItems().remove(adresa);
                                showAlert("Sukses", "Vendbanimi u fshi me sukses.");
                            } else {
                                showAlert("Gabim", "Fshirja dështoi.");
                            }
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        colUpdate.setCellFactory(column -> new TableCell<>() {
            private final Button btn = new Button("Ndrysho");

            {
                btn.setOnAction(e -> {
                    vendbanimZgjedhur = getTableView().getItems().get(getIndex());
                    showAlert("Info", "Funksioni i përditësimit mund të implementohet këtu.");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    private void ngarkoTeDhenat() {
        List<Adresa> lista = adresaRepository.getAll(); // ✅ nga BaseRepository
        tabelaPerdoruesve.setItems(FXCollections.observableArrayList(lista));
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
