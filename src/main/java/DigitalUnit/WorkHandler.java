package DigitalUnit;

import java.sql.SQLException;
import java.util.List;

import DigitalUnit.analyser.Analyser;
import DigitalUnit.data.DataBuffer;
import DigitalUnit.data.DataBufferListener;
import DigitalUnit.database.DBClient;
import DigitalUnit.server.HttpServer;
import DigitalUnit.utils.CarData;
import DigitalUnit.utils.GsonCollection;

public class WorkHandler implements DataBufferListener {
	boolean regularState = true;
	HttpServer server = new HttpServer();
	DataBuffer dataBuffer = new DataBuffer(this);

	public static long starttime;
	
	public WorkHandler() {
		starttime = System.currentTimeMillis();
		dataBuffer.run();
	}
	
	/**Called upon when a complete carData object has been created. 
	 * Evaluates data to set system in correct state and sends data to database.
	 * If system recognises a crash situation it will send data to server as well
	 * 
	 * @param dataBufferData		CarData object representing a complete line of data from the car
	 */
	public void onDataBufferData(CarData dataBufferData) {
		if (regularState) {
			normalState(dataBufferData);
		}
		else {
			crashState(dataBufferData);
		}
	}
	
	private void normalState(CarData data) {
		DBClient.insert(data);
		if (Analyser.hasCrashed()) {
			List<CarData> dataSet = DBClient.getAll();
			server.sendLines(new GsonCollection(dataSet));
			regularState = false;
		}
	}
	
	private void crashState(CarData data) {
		DBClient.insert(data);
		System.out.println(server.sendLine(data));
		if (Analyser.hasCarStopped(data)) {
			regularState = true;
		}
	}
	
	public void setRegularState(boolean state) {
		if (!state && regularState != state) {
			List<CarData> dataSet = DBClient.getAll();
			System.out.println(server.sendLines(new GsonCollection(dataSet)));
		}
		regularState = state;
	}
	
	public static void main( String[] args ) {
		try {
			DBClient.connect(System.getProperty("user.home"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			DBClient.createTable();
		} catch (SQLException e) {
			boolean tableExists = DBClient.tableAlreadyExists(e);
			if (!tableExists) {
				e.printStackTrace();
			} else {
				DBClient.removeAll();
			}
			System.out.println("Table already exists.");
		}

		WorkHandler workHandler = new WorkHandler();
	}

}
