package Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import repository.UserRepository;

public class StatistikaKombetareController {

    @FXML
    private Label lblGjithsej;

    @FXML
    private Label lblAktiv;

    @FXML
    private Label lblQytetare;

    @FXML
    private Label lblZyrtare;

    @FXML
    private BarChart<String, Number> barChartPerdorues;

    @FXML
    private CategoryAxis xMuajt;

    @FXML
    private NumberAxis yNumri;

    private final UserRepository userRepository = new UserRepository();

    @FXML
    private void initialize() {
        ngarkoStatistikat();
        ngarkoChart();
    }

    private void ngarkoStatistikat() {
        int total = userRepository.countAll();
//        int aktiv = userRepository.countAktiv(); // duhet me implementu
        int qytetare = userRepository.countByRoli("Qytetar");
        int zyrtare = userRepository.countByRoli("Zyrtar Komunal");

        lblGjithsej.setText(String.valueOf(total));
//        lblAktiv.setText(String.valueOf(aktiv));
        lblQytetare.setText(String.valueOf(qytetare));
        lblZyrtare.setText(String.valueOf(zyrtare));
    }

    private void ngarkoChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("2025");

        // Shembull statik - zëvendëso me të dhëna nga DB nëse ke tabelë me datë regjistrimi
        series.getData().add(new XYChart.Data<>("Janar", 20));
        series.getData().add(new XYChart.Data<>("Shkurt", 35));
        series.getData().add(new XYChart.Data<>("Mars", 50));
        series.getData().add(new XYChart.Data<>("Prill", 45));
        series.getData().add(new XYChart.Data<>("Maj", 60));
        series.getData().add(new XYChart.Data<>("Qershor", 0));

        barChartPerdorues.getData().clear();
        barChartPerdorues.getData().add(series);
    }
}
