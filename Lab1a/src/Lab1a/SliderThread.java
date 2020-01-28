package Lab1a;

import javafx.scene.control.Slider;

public class SliderThread extends Thread {
    private Slider slider;
    private int val;

    SliderThread(Slider slider, int val) {
        this.slider = slider;
        this.val = val;
    }

    @Override
    public void run() {
        while(!Thread.interrupted())
            slider.setValue(val);
    }
}
