package Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import repository.UserRepository;
import services.LanguageManager;

import java.util.Map;

public class StatistikaKombetareController extends BaseController {

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
        int aktiv = userRepository.countActiveUsers();
        int qytetare = userRepository.countByRoli("qytetar");
        int zyrtare = userRepository.countByRoli("zyrtar_komunal");


        lblGjithsej.setText(String.valueOf(total));
        lblAktiv.setText(String.valueOf(aktiv));
        lblQytetare.setText(String.valueOf(qytetare));
        lblZyrtare.setText(String.valueOf(zyrtare));
    }

    private void ngarkoChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(String.valueOf(java.time.Year.now().getValue()));

        Map<String, Integer> registrimetMujore = userRepository.getRegistrimetMujore();

        // Merr përkthimet nga ResourceBundle
        var bundle = LanguageManager.getInstance().getResourceBundle();
        xMuajt.setLabel(bundle.getString("stats.chart.xaxis"));
        yNumri.setLabel(bundle.getString("stats.chart.yaxis"));

        for (int i = 1; i <= 12; i++) {
            String muaji = String.valueOf(i);
            int numri = registrimetMujore.getOrDefault(muaji, 0);
            series.getData().add(new XYChart.Data<>(muaji, numri));
        }

        barChartPerdorues.getData().clear();
        barChartPerdorues.getData().add(series);
    }

}
