package Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Adresa;
import repository.AdresaRepository;
import services.LanguageManager;

import java.util.List;
import java.util.ResourceBundle;

public class MenaxhoVendbaniminController extends BaseController {

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
        tabelaPerdoruesve.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        konfiguroKolonat();
        ngarkoTeDhenat();
    }

    private void konfiguroKolonat() {
        colUser.setCellValueFactory(new PropertyValueFactory<>("rruga"));
        colPozita.setCellValueFactory(cellData -> new SimpleStringProperty("Prishtinë")); // shembull
        colData.setCellValueFactory(cellData -> new SimpleStringProperty("Qytet"));       // shembull
        colKodiPostar.setCellValueFactory(new PropertyValueFactory<>("kodiPostar"));

        colDelete.setCellFactory(column -> new TableCell<>() {
            private final Button btn = new Button();

            {
                btn.setOnAction(e -> {
                    Adresa adresa = getTableView().getItems().get(getIndex());
                    ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                    confirm.setTitle(bundle.getString("address.confirm.title"));
                    confirm.setHeaderText(null); // ose përkthim tjetër nëse do
                    confirm.setContentText(bundle.getString("address.confirm.delete"));

                    confirm.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            boolean success = adresaRepository.delete(adresa.getId());
                            if (success) {
                                tabelaPerdoruesve.getItems().remove(adresa);
                                showAlert(bundle.getString("address.success.title"), bundle.getString("address.success.message"));
                            } else {
                                showAlert(bundle.getString("address.error.title"), bundle.getString("address.error.message"));
                            }
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
                    btn.setText(bundle.getString("address.button.delete"));
                    setGraphic(btn);
                }
            }
        });

        colUpdate.setCellFactory(column -> new TableCell<>() {
            private final Button btn = new Button();

            {
                btn.setOnAction(e -> {
                    vendbanimZgjedhur = getTableView().getItems().get(getIndex());
                    ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
                    showAlert(bundle.getString("address.info.title"), bundle.getString("address.info.message"));
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
                    btn.setText(bundle.getString("address.button.edit"));
                    setGraphic(btn);
                }
            }
        });
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void ngarkoTeDhenat() {
        List<Adresa> lista = adresaRepository.getAll();
        tabelaPerdoruesve.setItems(FXCollections.observableArrayList(lista));
    }
}
