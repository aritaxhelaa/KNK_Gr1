package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.UserActivity;
import services.UserActivityService;
import utils.SessionManager;

import java.time.LocalDate;
import java.util.List;

public class KerkimetEFunditController extends BaseController {

    @FXML
    private TableView<UserActivity> activityTable;

    @FXML
    private TableColumn<UserActivity, String> colData;

    @FXML
    private TableColumn<UserActivity, String> colAdresa;

    private final UserActivityService userActivityService = new UserActivityService();



    @FXML
    public void initialize() {
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colAdresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        activityTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        int userId = SessionManager.getCurrentUser().getId(); // ← KJO është kyçe
        List<UserActivity> teGjitha = userActivityService.getByUserId(userId);

        List<UserActivity> vetem14Ditet = teGjitha.stream()
                .filter(a -> {
                    LocalDate dataAktivitetit = LocalDate.parse(a.getData());
                    return dataAktivitetit.isAfter(LocalDate.now().minusDays(14));
                })
                .toList();
        activityTable.getItems().setAll(vetem14Ditet);
    }

}
