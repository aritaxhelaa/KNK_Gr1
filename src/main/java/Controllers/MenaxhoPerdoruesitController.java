package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.User;
import models.Dto.UserDto.UpdateUserDto;
import repository.UserRepository;
import javafx.scene.layout.VBox;


import java.util.List;

public class MenaxhoPerdoruesitController {

    @FXML
    private TableView<User> tabelaPerdoruesve;

    @FXML
    private TableColumn<User, String> colUser;

    @FXML
    private TableColumn<User, String> colPozita;

    @FXML
    private TableColumn<User, String> colData;

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
        comboRoli.getItems().addAll("Qytetar", "Zyrtar Komunal", "Admin");
        formaUpdate.setVisible(false);
        configureTable();
        loadUsers();
    }

    private void configureTable() {
        colUser.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        colPozita.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRoli()));
        colData.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty("N/A"));

        colDelete.setCellFactory(column -> new TableCell<>() {
            private final Button btn = new Button("Fshi");

            {
                btn.setOnAction(e -> {
                    User u = getTableView().getItems().get(getIndex());
                    // Fshi përdoruesin në DB (opsionale)
                    System.out.println("Përdoruesi për fshirje: " + u.getName());
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
                    userZgjedhur = getTableView().getItems().get(getIndex());
                    lblEmriZgjedhur.setText(userZgjedhur.getName());
                    comboRoli.setValue(userZgjedhur.getRoli());
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

    private void loadUsers() {
        List<User> users = userRepository.getAll(); // sigurohu që ekziston kjo metodë
        tabelaPerdoruesve.setItems(FXCollections.observableArrayList(users));
    }

    @FXML
    private void ruajNdryshimin() {
        if (userZgjedhur == null || comboRoli.getValue() == null) return;

        UpdateUserDto dto = new UpdateUserDto();
        dto.setId(userZgjedhur.getId());
        dto.setRoli(comboRoli.getValue());

        User updated = userRepository.update(dto);
        if (updated != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Roli u ndryshua me sukses.");
            alert.showAndWait();
            loadUsers();
            formaUpdate.setVisible(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ndodhi një gabim.");
            alert.showAndWait();
        }
    }
}
