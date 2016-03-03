package DigitalUnit.data;

import com.google.gson.Gson;

import DigitalUnit.car.CarListener;
import DigitalUnit.car.CarListenerListener;



public class DataBuffer implements CarListenerListener, Runnable {

	
	private CarListener carListener;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCarData(Gson carDataObj) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	//private JSONObject currentSampleData;
	
	
	private DataBufferListener dataBufferListener;
	
	public DataBuffer( DataBufferListener dataBufferListener ) {
		
		this.dataBufferListener = dataBufferListener;
		
		//setupSampleDataObject();
		
		//setupCarListener();
		
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
	
	*/
}
