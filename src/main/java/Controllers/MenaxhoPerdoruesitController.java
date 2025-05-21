package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.User;
import models.Dto.UserDto.UpdateUserDto;
import repository.UserRepository;
import services.LanguageManager;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class MenaxhoPerdoruesitController extends BaseController {

    @FXML private TableView<User> tblUsers;
    @FXML private TableColumn<User, String> colName;
    @FXML private TableColumn<User, String> colRoli;
    @FXML private TableColumn<User, Timestamp> colCreatedAt;
    @FXML private TableColumn<User, Void> colDelete;
    @FXML private TableColumn<User, Void> colUpdate;
    @FXML private VBox formaUpdate;
    @FXML private Label lblEmriZgjedhur;
    @FXML private ComboBox<String> comboRoli;

    private final UserRepository userRepository = new UserRepository();
    private User userZgjedhur;

    @FXML
    private void initialize() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        // Përkthim titujsh kolonash
        colName.setText(bundle.getString("manage.column.user"));
        colRoli.setText(bundle.getString("manage.column.position"));
        colCreatedAt.setText(bundle.getString("manage.column.created"));
        colDelete.setText(bundle.getString("manage.column.delete"));
        colUpdate.setText(bundle.getString("manage.column.update"));

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRoli.setCellValueFactory(new PropertyValueFactory<>("roli"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        tblUsers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Formatimi i datës
        colCreatedAt.setCellFactory(column -> new TableCell<>() {
            private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            @Override
            protected void updateItem(Timestamp item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "N/A" : formatter.format(item));
            }
        });

        comboRoli.setItems(FXCollections.observableArrayList(
                bundle.getString("role.citizen"),
                bundle.getString("role.municipal"),
                bundle.getString("role.admin")
        ));

        formaUpdate.setVisible(false);
        ngarkoPerdoruesit();
    }

    private void ngarkoPerdoruesit() {
        List<User> lista = userRepository.getAll();
        ObservableList<User> data = FXCollections.observableArrayList(lista);
        tblUsers.setItems(data);
        shtoButonat();
    }

    private void shtoButonat() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        colDelete.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button();

            {
                btn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    userRepository.delete(user.getId());
                    ngarkoPerdoruesit();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    btn.setText(bundle.getString("button.delete"));
                    setGraphic(btn);
                }
            }
        });

        colUpdate.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button();

            {
                btn.setOnAction(event -> {
                    userZgjedhur = getTableView().getItems().get(getIndex());
                    lblEmriZgjedhur.setText(userZgjedhur.getName());

                    // Krahasim sipas kodit të brendshëm (p.sh. "qytetar") dhe përkthim për shfaqje
                    String roli = userZgjedhur.getRoli();
                    ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

                    if (roli != null) {
                        if (roli.equals("qytetar")) comboRoli.getSelectionModel().select(bundle.getString("role.citizen"));
                        else if (roli.equals("zyrtar_komunal")) comboRoli.getSelectionModel().select(bundle.getString("role.municipal"));
                        else if (roli.equals("admin")) comboRoli.getSelectionModel().select(bundle.getString("role.admin"));
                    }

                    formaUpdate.setVisible(true);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    btn.setText(bundle.getString("button.edit"));
                    setGraphic(btn);
                }
            }
        });
    }

    @FXML
    private void ruajNdryshimin() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        if (userZgjedhur != null) {
            String roliZgjedhur = comboRoli.getSelectionModel().getSelectedItem();
            String roliIRi = null;

            if (roliZgjedhur.equals(bundle.getString("role.citizen"))) roliIRi = "qytetar";
            else if (roliZgjedhur.equals(bundle.getString("role.municipal"))) roliIRi = "zyrtar_komunal";
            else if (roliZgjedhur.equals(bundle.getString("role.admin"))) roliIRi = "admin";

            if (roliIRi != null && !roliIRi.equals(userZgjedhur.getRoli())) {
                userRepository.update(new UpdateUserDto(userZgjedhur.getId(), userZgjedhur.getEmail(), roliIRi));
                formaUpdate.setVisible(false);
                ngarkoPerdoruesit();
            }
        }
    }
}
