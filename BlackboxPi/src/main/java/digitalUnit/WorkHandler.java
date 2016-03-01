package DigitalUnit;

import java.util.ArrayList;

import DigitalUnit.Data.CarData;
import DigitalUnit.Database.DBClient;

public class WorkHandler {
	boolean regularState = true;
	
	/**Called upon when a complete carData object has been created. 
	 * Evaluates data to set system in correct state and sends data to database.
	 * If system recognizes a crash situation it will send data to server as well
	 * 
	 * @param data		CarData object representing a complete line of data from the car
	 */
	public void processData(CarData data) {
		if (regularState) {
			normalState(data);
		}
		else {
			crashState(data);
		}
	}
	
	private void normalState(CarData data) {
		DBClient.insert(data);
		if (DataAnalyser.evalateData()) {
			ArrayList<CarData> dataSet = DBClient.getAll();
			SClient.insertAll(dataSet);
			regularState = false;
		}
	}
	
	private void crashState(CarData data) {
		DBClient.insert(data);
		SClient.send(data);
		if (DataAnalyser.hasCarStopped()) {
			regularState = true;
		}
	}
}
