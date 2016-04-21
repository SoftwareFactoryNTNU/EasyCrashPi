package DigitalUnit.utils;

import DigitalUnit.WorkHandler;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class ButtonListener {

    private WorkHandler workHandler;

    public ButtonListener(WorkHandler workHandler) {
        this.workHandler = workHandler;
    }

    /**
     * Listens for a button press, and alerts WorkHandler when a press occurs.
     */
    public void listen() {

        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);

        myButton.addListener((GpioPinListenerDigital) event -> {
            if (event.getState() == PinState.HIGH) {
                System.out.println("CRASH!!!");
                workHandler.setRegularState(false);
            }
        });
    }
}