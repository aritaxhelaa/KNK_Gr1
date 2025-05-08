package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.User;
import services.UserService;
import models.Dto.UserDto.LoginUserDto;

public class LogInController {

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private Button LogInBttn;

    @FXML
    private Label ErrorLable;

    private final UserService userService = new UserService();

    @FXML
    private void initialize() {
        LogInBttn.setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        String email = Username.getText().trim();
        String password = Password.getText();

        ErrorLable.setText(""); // Pastro mesazhin para validimit

        if (email.isEmpty() || password.isEmpty()) {
            ErrorLable.setText("Ju lutem plotësoni të gjitha fushat.");
            return;
        }

        try {
            LoginUserDto loginDto = new LoginUserDto(email, password);
            User user = userService.login(loginDto);


            // Roli: admin, komunal, qytetar → hap faqen përkatëse
//            switch (user.getRoli()) {
//                case "admin" -> openDashboard("admin-view.fxml");
//                case "komunal" -> openDashboard("komunal-view.fxml");
//                case "qytetar" -> openDashboard("qytetar-view.fxml");
//                default -> ErrorLable.setText("Roli i panjohur.");
//            }

        } catch (Exception e) {
            ErrorLable.setText(e.getMessage());
        }
    }

//    private void openDashboard(String fxmlPath) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/" + fxmlPath));
//            Scene scene = new Scene(loader.load());
//            Stage stage = (Stage) Username.getScene().getWindow();
//            stage.setScene(scene);
//        } catch (Exception e) {
//            ErrorLable.setText("Nuk u hap pamja përkatëse.");
//            e.printStackTrace();
//        }
//    }
}
