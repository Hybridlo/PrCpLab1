package Lab1a;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class Main extends Application{

    private int priority1 = 1;
    private int priority2 = 1;

    private Slider slider = new Slider(0.0, 100.0, 50.0);

    private SliderThread Tthread1 = new SliderThread(slider, 10);
    private SliderThread Tthread2 = new SliderThread(slider, 90);

    public static void main(String[] args) {

        Application.launch(args);
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

        Label priority_label1 = new Label("1");

        priority_label1.setLayoutX(67);
        priority_label1.setLayoutY(115);

        Button btn_up1 = new Button("↑");

        btn_up1.setLayoutX(50);
        btn_up1.setLayoutY(80);
        btn_up1.setPrefWidth(40);

        btn_up1.setOnAction(event -> {
            if (priority1 < 10) {
                priority1++;
                priority_label1.setText(String.valueOf(priority1));
            }
        });

        Button btn_down1 = new Button("↓");

        btn_down1.setLayoutX(50);
        btn_down1.setLayoutY(140);
        btn_down1.setPrefWidth(40);

        btn_down1.setOnAction(event -> {
            if (priority1 > 1) {
                priority1--;
                priority_label1.setText(String.valueOf(priority1));
            }
        });

        Label priority_label2 = new Label("1");

        priority_label2.setLayoutX(727);
        priority_label2.setLayoutY(115);

        Button btn_up2 = new Button("↑");

        btn_up2.setLayoutX(710);
        btn_up2.setLayoutY(80);
        btn_up2.setPrefWidth(40);

        btn_up2.setOnAction(event -> {
            if (priority2 < 10) {
                priority2++;
                priority_label2.setText(String.valueOf(priority2));
            }
        });

        Button btn_down2 = new Button("↓");

        btn_down2.setLayoutX(710);
        btn_down2.setLayoutY(140);
        btn_down2.setPrefWidth(40);

        btn_down2.setOnAction(event -> {
            if (priority2 > 1) {
                priority2--;
                priority_label2.setText(String.valueOf(priority2));
            }
        });

        Button btn_start = new Button("Start");

        btn_start.setLayoutX(330);
        btn_start.setLayoutY(250);
        btn_start.setPrefWidth(90);

        btn_start.setOnAction(event -> {
            Tthread1.setPriority(priority1);
            Tthread1.start();

            Tthread2.setPriority(priority2);
            Tthread2.start();

            btn_start.setDisable(true);
        });

        Group group = new Group(slider, priority_label1, btn_up1, btn_down1, priority_label2, btn_up2, btn_down2, btn_start);
        Scene scene = new Scene(group, 800, 300);

        stage.setScene(scene);
        stage.setTitle("Lab1a");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Tthread1.interrupt();
        Tthread2.interrupt();
    }
}