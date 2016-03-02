package DigitalUnit.Car;



public class CarListener implements AbstractCarListener, Runnable {

	//The car we are listening to
	private AbstractCar abstractCar;
	
	//Something that listens to this. Probably DataBuffer
	private CarListenerListener carListenerListener;
	
	
	public final static String CAR_DATA = "res/JSON/downtown-west.json";
	
	
	public CarListener( CarListenerListener carListenerListener ) {
		this.carListenerListener = carListenerListener;
		
		setupSimulatedCar();
	}
	
	//private void setupTestCar() {
	//	setAbstractCar( new TestCar( this ) );
	//}
	private void setupSimulatedCar() {
		setAbstractCar( new CarSimulator( this, CAR_DATA ) );
	}
	
	private void setAbstractCar( AbstractCar abstractCar ) {
		this.abstractCar = abstractCar;
	}
	
	
	@Override
	public void run() {
		abstractCar.run();
	}
	
	@Override
	public void handleCarEvent(String jsonData) {
		System.out.println("got car event");
		
		//Put the JSON string into our predefined GSON object
		JSONObject jsonDataObj = new JSONObject( jsonData );
		
		carListenerListener.onCarData(jsonDataObj);
	}
	
}
