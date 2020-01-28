package Lab1b;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class Main extends Application{

    private Slider slider = new Slider(0.0, 100.0, 50.0);

    private SliderThread Tthread1;
    private SliderThread Tthread2;

    private int semaphore = 0;

    public static void main(String[] args) {

        Application.launch(args);
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Semaphore is locked");

        alert.showAndWait();
    }

    @Override
    public void start(Stage stage) throws Exception {

        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setBlockIncrement(2.0);
        slider.setMajorTickUnit(10.0);
        slider.setMinorTickCount(9);
        slider.setSnapToTicks(true);
        slider.setLayoutX(150);
        slider.setLayoutY(130);
        slider.setPrefSize(500, 80);

        Button btn_start1 = new Button("Start1");
        Button btn_start2 = new Button("Start2");
        Button btn_end1 = new Button("End1");
        Button btn_end2 = new Button("End2");

        btn_start1.setLayoutX(50);
        btn_start1.setLayoutY(100);
        btn_start1.setPrefWidth(50);

        btn_end1.setLayoutX(50);
        btn_end1.setLayoutY(200);
        btn_end1.setPrefWidth(50);

        btn_start2.setLayoutX(700);
        btn_start2.setLayoutY(100);
        btn_start2.setPrefWidth(50);

        btn_end2.setLayoutX(700);
        btn_end2.setLayoutY(200);
        btn_end2.setPrefWidth(50);

        btn_start1.setOnAction(event -> {
            if (semaphore != 0) {
                showAlert();
            }
            else {
                semaphore = 1;

                Tthread1 = new SliderThread(slider, 10);
                Tthread1.setPriority(Thread.MIN_PRIORITY);
                Tthread1.start();

                btn_end2.setDisable(true);
            }
        });

        btn_start2.setOnAction(event -> {
            if (semaphore != 0) {
                showAlert();
            }
            else {
                semaphore = 1;

                Tthread2 = new SliderThread(slider, 90);
                Tthread2.setPriority(Thread.MAX_PRIORITY);
                Tthread2.start();

                btn_end1.setDisable(true);
            }
        });

        btn_end1.setOnAction(event -> {
            Tthread1.interrupt();
            semaphore = 0;
            btn_end2.setDisable(false);
        });

        btn_end2.setOnAction(event ->{
            Tthread2.interrupt();
            semaphore = 0;
            btn_end1.setDisable(false);
        });

        Group group = new Group(slider, btn_end1, btn_end2, btn_start1, btn_start2);
        Scene scene = new Scene(group, 800, 300);

        stage.setScene(scene);
        stage.setTitle("Lab1b");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Tthread1.interrupt();
        Tthread2.interrupt();
    }
}