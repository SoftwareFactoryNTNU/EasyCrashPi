package digitalUnit;

import java.util.ArrayList;

import digitalUnit.analyser.Analyser;
import digitalUnit.data.CarData;
import digitalUnit.database.DBClient;

public class WorkHandler {
	boolean inCrash = true;
	
	/**Called upon when a complete carData object has been created. 
	 * Evaluates data to set system in correct state and sends data to database.
	 * If system recognizes a crash situation it will send data to server as well
	 * 
	 * @param data		CarData object representing a complete line of data from the car
	 */
	public void processData(CarData data) {
		if (inCrash) {
			normalState(data);
		}
		else {
			crashState(data);
		}
	}
	
	private void normalState(CarData data) {
		DBClient.insert(data);
		if (Analyser.hasCrashed()) {
			ArrayList<CarData> dataSet = DBClient.getAll();
			SClient.insertLines(dataSet);
			inCrash = false;
		}
	}
	
	private void crashState(CarData data) {
		DBClient.insert(data);
		SClient.insertLine(data);
		if (Analyser.hasCarStopped()) {
			inCrash = true;
		}
	}
}
