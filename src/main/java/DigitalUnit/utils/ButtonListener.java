package DigitalUnit.utils;

import DigitalUnit.WorkHandler;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class ButtonListener {

    private WorkHandler workHandler;

    public ButtonListener(WorkHandler workHandler) {
        this.workHandler = workHandler;
    }

    public void listen() {

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);

        // create and register gpio pin listener
        myButton.addListener((GpioPinListenerDigital) event -> {
            // display pin state on console
            if (event.getState() == PinState.HIGH) {
                workHandler.setRegularState(false);
            }
        });
    }
}