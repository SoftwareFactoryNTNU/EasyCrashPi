package DigitalUnit.analyser;

import DigitalUnit.utils.CarData;

public class Analyser {
	
	/**Checks automatically whether the car has crashed or not
	 */
	public static boolean hasCrashed() {
		return false;
	}
	
	/**Checks if the car has stop
	 * 
	 * @param data		A line of data
	 */
	public static boolean hasCarStopped(CarData data) {
		return data.getVehicleSpeed() == 0;
	}
}
