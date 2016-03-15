package DigitalUnit.analyser;

import DigitalUnit.utils.CarData;

public class Analyser {
	
	public static boolean hasCrashed() {
		return false;
	}
	
	public static boolean hasCarStopped(CarData data) {
		return data.getVehicleSpeed() == 0;
	}
}
