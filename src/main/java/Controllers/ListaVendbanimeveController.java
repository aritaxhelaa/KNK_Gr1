package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Adresa;
import models.User;
import repository.AdresaRepository;
import utils.SessionManager;


import java.util.List;

public class ListaVendbanimeveController extends BaseController {

    @FXML
    private TableView<Adresa> vendbanimeTable;

    @FXML
    private TableColumn<Adresa, String> rrugaColumn;

    @FXML
    private TableColumn<Adresa, Integer> numriColumn;

    @FXML
    private TableColumn<Adresa, Integer> kodiPostarColumn;

    private final AdresaRepository adresaRepository = new AdresaRepository();

    @FXML
    private void initialize() {
        configureTableColumns();
        loadRecentSearches();
    }

    private void configureTableColumns() {
        rrugaColumn.setCellValueFactory(new PropertyValueFactory<>("rruga"));
        numriColumn.setCellValueFactory(new PropertyValueFactory<>("numri"));
        kodiPostarColumn.setCellValueFactory(new PropertyValueFactory<>("kodiPostar"));
    }

    private void loadRecentSearches() {
        User currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) {
            System.err.println("Përdoruesi nuk është i kyçur!");
            return;
        }

        List<Adresa> kerkime = adresaRepository.getRecentSearchesByUser(currentUser.getId());
        ObservableList<Adresa> observableList = FXCollections.observableArrayList(kerkime);
        vendbanimeTable.setItems(observableList);
    }
}
