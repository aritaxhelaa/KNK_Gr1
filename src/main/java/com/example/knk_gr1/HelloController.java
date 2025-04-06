package com.example.knk_gr1;

import com.example.models.Komuna;
import com.example.repository.KomunaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HelloController {

    @FXML
    private TableView<Komuna> tableView;

    @FXML
    private TableColumn<Komuna, Integer> idColumn;

    @FXML
    private TableColumn<Komuna, String> emriColumn;

    @FXML
    private TableColumn<Komuna, String> kodiPostarColumn;

    @FXML
    private TextField emriInput;

    @FXML
    private TextField kodiPostarInput;

    private final KomunaRepository repository = new KomunaRepository();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        emriColumn.setCellValueFactory(new PropertyValueFactory<>("emri"));
        kodiPostarColumn.setCellValueFactory(new PropertyValueFactory<>("kodiPostar"));

        List<Komuna> komunat = repository.getAll();
        ObservableList<Komuna> observableKomunat = FXCollections.observableArrayList(komunat);
        tableView.setItems(observableKomunat);
    }

    @FXML
    public void onHelloButtonClick() {
        System.out.println("Hello button clicked!");
    }

    @FXML
    public void onAddKomunaButtonClick() {

        String emri = emriInput.getText();
        String kodiPostar = kodiPostarInput.getText();


        Komuna komunë = new Komuna(0, emri, kodiPostar);
        repository.addKomuna(komunë);


        List<Komuna> komunat = repository.getAll();
        ObservableList<Komuna> observableKomunat = FXCollections.observableArrayList(komunat);
        tableView.setItems(observableKomunat);

        // Pastroni inputet
        emriInput.clear();
        kodiPostarInput.clear();
    }
}
