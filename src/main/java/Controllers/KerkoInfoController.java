package Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import models.Dto.AdresaDto.AdresaViewDto;
import services.AdresaService;

import java.io.IOException;
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
    }

    @FXML
    private void handleKthehu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/QytetariView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) kthehuBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Kërkimi");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
