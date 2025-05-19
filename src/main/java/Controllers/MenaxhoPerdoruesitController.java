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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class MenaxhoPerdoruesitController extends BaseController {

    @FXML
    private TableView<User> tblUsers;

    @FXML
    private TableColumn<User, String> colName;

    @FXML
    private TableColumn<User, String> colRoli;

    @FXML
    private TableColumn<User, Timestamp> colCreatedAt;

    @FXML
    private TableColumn<User, Void> colDelete;

    @FXML
    private TableColumn<User, Void> colUpdate;

    @FXML
    private VBox formaUpdate;

    @FXML
    private Label lblEmriZgjedhur;

    @FXML
    private ComboBox<String> comboRoli;

    private final UserRepository userRepository = new UserRepository();
    private User userZgjedhur;

    @FXML
    private void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRoli.setCellValueFactory(new PropertyValueFactory<>("roli"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        // Formatimi i dates
        colCreatedAt.setCellFactory(column -> new TableCell<>() {
            private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            @Override
            protected void updateItem(Timestamp item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "N/A" : formatter.format(item));
            }
        });

        comboRoli.setItems(FXCollections.observableArrayList("qytetar", "zyrtar_komunal", "admin"));
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
        colDelete.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Fshi");

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
                setGraphic(empty ? null : btn);
            }
        });

        colUpdate.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Ndrysho");

            {
                btn.setOnAction(event -> {
                    userZgjedhur = getTableView().getItems().get(getIndex());
                    lblEmriZgjedhur.setText(userZgjedhur.getName());
                    comboRoli.getSelectionModel().select(userZgjedhur.getRoli());
                    formaUpdate.setVisible(true);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    @FXML
    private void ruajNdryshimin() {
        if (userZgjedhur != null) {
            String roliIRi = comboRoli.getSelectionModel().getSelectedItem();
            if (roliIRi != null && !roliIRi.equals(userZgjedhur.getRoli())) {
                userRepository.update(new UpdateUserDto(userZgjedhur.getId(), userZgjedhur.getEmail(), roliIRi));
                formaUpdate.setVisible(false);
                ngarkoPerdoruesit();
            }
        }
    }
}
