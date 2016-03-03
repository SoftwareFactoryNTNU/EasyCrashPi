package DigitalUnit.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import DigitalUnit.car.CarListenerListener;
import DigitalUnit.car.CarListener;
import DigitalUnit.car.JsonData;



public class DataBuffer implements CarListenerListener, Runnable {

	
	
	private static enum DataAttributes {
		ENGINE_SPEED("engine_speed"), LONGITUDE("longitude"), LATTITUDE("lattitude");
		
		private String name;
		private DataAttributes(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
	};
	
	
	//the car listnener created
	private CarListener carListener;
	
	//stored attribute-values between each samplepoint
	private HashMap<DataAttributes, ArrayList<Object> > currentSampleData =
				new HashMap<DataAttributes, ArrayList<Object> >();
	
	//something that recieves data from this (probably 
	private DataBufferListener dataBufferListener;
	
	
	
	public DataBuffer( DataBufferListener dataBufferListener ) {
		
		this.dataBufferListener = dataBufferListener;
		
		setupSampleDataObject();
		setupCarListener();
		setupCurrentSampleDataArrays();
	}
	
	//fix with new json data object
	private void setupSampleDataObject() {
		 Gson gson = new Gson();
		 String o = new String("{\"latitude\":[], \"longitude\":[],\"vehicle_speed\":[]}");
		 //currentSampleData = o;
	}
	private void setupCarListener() {
		carListener = new CarListener( this );
	}
	private void setupCurrentSampleDataArrays() {
		for ( DataAttributes dataAttribute : DataAttributes.values() ) {
			currentSampleData.put( dataAttribute, new ArrayList<Object>() );
		}
	}
	
	@Override
	public void run() {
		carListener.run();		
	}

	@Override
	public void onCarData(JsonData carDataObj) {
			System.out.println("car data arrived, name: " + carDataObj.toString());
			
			DataAttributes dataTrigger = DataAttributes.LATTITUDE;
			//if we recieve Latitude data, all the data should be sent to listener in one Json Object
			if ( dataTrigger.getName().equals(carDataObj.getName()) ) {
				//send data here
				dataBufferListener.onDataBufferData( "" );
			}
			else {
				storeCurrentSampleData( carDataObj );
			}
				
	}
	
	private void sendDataToListener(  ) {
		
	}
	
	//stores data from JsonData in corresponding array
	private void storeCurrentSampleData( JsonData carDataObj ){
		storeCurrentSampleData( carDataObj );
		//append data of the given type to data array
		for ( DataAttributes dataAttribute : DataAttributes.values() ) {
			if (carDataObj.getName().equals( dataAttribute.getName()) ) {
				addCurrentSampleData( dataAttribute, carDataObj.getValue() );
			}
		}
	}
	//appends data to the corresponding array of the attribute given
	private void addCurrentSampleData( DataAttributes dataName, Object data ) {
		//append data to corresponding arrayList
		currentSampleData.get(dataName).add(data);
	}
	
	
}
