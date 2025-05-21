package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.UserActivity;
import services.LanguageManager;
import services.UserActivityService;
import utils.SessionManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;

public class ListaVendbanimeveController extends BaseController {

    @FXML
    private TableView<UserActivity> activityTable;

    @FXML
    private TableColumn<UserActivity, String> colData;

    @FXML
    private TableColumn<UserActivity, String> colAdresa;

    private final UserActivityService userActivityService = new UserActivityService();

    @FXML
    public void initialize() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        // Përkthim i kolonave
        colData.setText(bundle.getString("recent.column.date"));
        colAdresa.setText(bundle.getString("recent.column.address"));
        activityTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        if (SessionManager.getCurrentUser() == null) {
            showAlert(bundle.getString("alert.warning.title"), bundle.getString("log.no_user_logged_in"));
            return;
        }

        int userId = SessionManager.getCurrentUser().getId();
        List<UserActivity> teGjitha = userActivityService.getByUserId(userId);

        List<UserActivity> vetem14Ditet = teGjitha.stream()
                .filter(a -> {
                    try {
                        LocalDate dataAktivitetit = LocalDate.parse(a.getData());
                        return dataAktivitetit.isAfter(LocalDate.now().minusDays(14));
                    } catch (DateTimeParseException e) {
                        System.err.println("Data e gabuar: " + a.getData());
                        return false;
                    }
                })
                .toList();

        activityTable.getItems().setAll(vetem14Ditet);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
