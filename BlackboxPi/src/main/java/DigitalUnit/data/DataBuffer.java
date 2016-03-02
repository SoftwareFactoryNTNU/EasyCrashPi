package DigitalUnit.data;

import DigitalUnit.car.CarListenerListener;



public class DataBuffer implements CarListenerListener, Runnable {

	
	private CarListener carListener;
	
	
	private JSONObject currentSampleData;
	
	
	private DataBufferListener dataBufferListener;
	
	public DataBuffer( DataBufferListener dataBufferListener ) {
		
		this.dataBufferListener = dataBufferListener;
		
		setupSampleDataObject();
		
		setupCarListener();
		
	}
	
	private void setupSampleDataObject() {
		 JSONObject o = new JSONObject("{\"latitude\":[], \"longitude\":[],\"vehicle_speed\":[]}");
		 currentSampleData = o;
	}
	
	private void setupCarListener() {
		carListener = new CarListener( this );
	}
	
	@Override
	public void run() {
		carListener.run();		
	}

	@Override
	public void onCarData(JSONObject carDataObj) {
			System.out.println("car data arrived, name: " + carDataObj.toString());
	}
	
	
}
