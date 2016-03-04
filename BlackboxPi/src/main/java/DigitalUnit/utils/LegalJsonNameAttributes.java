package DigitalUnit.utils;

import java.util.ArrayList;
import java.util.Arrays;



public class LegalJsonNameAttributes {

	/*
	 * Keeps track of all legal name attributes a json object is allowed to send to the server
	 */
	public static final ArrayList<String> Names = new ArrayList<String>(Arrays.asList
	("vehicle_speed","accelerator_pedal_position","engine_speed","torque_at_transmission",
	"latitude","longitude","steering_wheel_angle","fuel_consumed_since_restart","odometer",
	"fuel_level"));
			
}
