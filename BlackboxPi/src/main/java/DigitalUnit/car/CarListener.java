package DigitalUnit.car;

import com.google.gson.Gson;

import DigitalUnit.utils.JsonData;

public class CarListener implements AbstractCarListener, Runnable {

	
	public static final String CAR_DATA = "res/JSON/downtown-west.json";
	
	
	private AbstractCar abstractCar;
	
	private CarListenerListener carListenerListener;
	
	
	public CarListener( CarListenerListener carListenerListener ) {
		this.carListenerListener = carListenerListener;
		
		setupSimulatedCar();
	}
	

	private void setupSimulatedCar() {
		setAbstractCar( new CarSimulator( this, CAR_DATA) );
	}
	
	private void setAbstractCar( AbstractCar abstractCar ) {
		this.abstractCar = abstractCar;
	}
	
	
	@Override
	public void run() {
		abstractCar.run();
	}
	
	@Override
	public void handleCarEvent(JsonData jsonData) {
		Gson gson = new Gson();
		System.out.println("[CarListener] got car event: " + jsonData.getName());
		//JsonData jsonDataObj = gson.fromJson(jsonData, JsonData.class);
		
		carListenerListener.onCarData(jsonData);
	}
	
}