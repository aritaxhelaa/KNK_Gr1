package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Adresa;
import models.UserActivity;
import repository.AdresaRepository;
import services.LanguageManager;
import utils.SessionManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class KerkimetEFunditController extends BaseController {

    @FXML
    private TableView<UserActivity> activityTable;

    @FXML
    private TableColumn<UserActivity, String> colData;

    @FXML
    private TableColumn<UserActivity, String> colAdresa;

    private final AdresaRepository adresaRepo = new AdresaRepository();

    @FXML
    public void initialize() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colAdresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        activityTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        if (SessionManager.getCurrentUser() == null) {
            System.out.println(bundle.getString("log.no_user_logged_in"));
            return;
        }

        int userId = SessionManager.getCurrentUser().getId();
        List<Adresa> recent = adresaRepo.getRecentSearchesByUser(userId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // mund ta bëjmë lokal nëse do

        List<UserActivity> formatted = recent.stream()
                .map(a -> new UserActivity(
                        userId,
                        a.getDataKerkimit() != null
                                ? a.getDataKerkimit().toLocalDateTime().toLocalDate().format(formatter)
                                : "",
                        a.getRruga() + " " +
                                bundle.getString("label.number_abbr") + " " + a.getNumri() +
                                ", " + bundle.getString("label.postal_code") + ": " + a.getKodiPostar()
                ))
                .toList();

        activityTable.getItems().setAll(formatted);
    }
}
