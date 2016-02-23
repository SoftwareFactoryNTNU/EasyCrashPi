package DigitalUnit;

public class WorkHandler {
	boolean regularState = false;
	
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
			SClient.sendSet(dataSet);
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
