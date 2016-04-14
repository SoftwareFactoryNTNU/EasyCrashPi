package DigitalUnit;

import java.util.List;

import DigitalUnit.analyser.Analyser;
import DigitalUnit.data.DataBuffer;
import DigitalUnit.data.DataBufferListener;
import DigitalUnit.database.CarDataMemory;
import DigitalUnit.server.HttpServer;
import DigitalUnit.utils.ButtonListener;
import DigitalUnit.utils.CarData;
import DigitalUnit.utils.GsonCollection;

public class WorkHandler implements DataBufferListener {
	boolean regularState = true;
	HttpServer server = new HttpServer();
	DataBuffer dataBuffer = new DataBuffer(this);
	CarDataMemory carDataMemory = new CarDataMemory();
	ButtonListener buttonListener = new ButtonListener(this);

	private boolean sizeTrigger = false;

	public WorkHandler() {
		buttonListener.listen();
		dataBuffer.run();
	}
	
	/**Called upon when a complete carData object has been created. 
	 * Evaluates data to set system in correct state and sends data to database.
	 * If system recognises a crash situation it will send data to server as well
	 * 
	 * @param dataBufferData		CarData object representing a complete line of data from the car
	 */
	public void onDataBufferData(CarData dataBufferData) {
		if (carDataMemory.getSize() > 120 && !sizeTrigger) {
			setRegularState(false);
			sizeTrigger = true;
		}

		if (regularState) {
			normalState(dataBufferData);
		}
		else {
			crashState(dataBufferData);
		}
	}
	
	private void normalState(CarData data) {
		carDataMemory.insert(data);
		if (Analyser.hasCrashed()) {
			List<CarData> dataSet = carDataMemory.getAll();
			server.sendLines(new GsonCollection(dataSet));
			regularState = false;
		}
	}
	
	private void crashState(CarData data) {
		carDataMemory.insert(data);
		server.sendLine(data);
		if (Analyser.hasCarStopped(data)) {
			regularState = true;
		}
	}
	
	public void setRegularState(boolean state) {
		if (!state && regularState != state) {
			List<CarData> dataSet = carDataMemory.getAll();
			server.sendLines(new GsonCollection(dataSet));
		}
		regularState = state;
	}
	
	public static void main( String[] args ) {
		WorkHandler workHandler = new WorkHandler();
	}

}
