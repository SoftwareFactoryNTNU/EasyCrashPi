package DigitalUnit.data;


import java.util.HashMap;

import com.google.gson.Gson;

import DigitalUnit.car.CarListenerListener;
import DigitalUnit.car.CarListener;
import DigitalUnit.utils.JsonData;



public class DataBuffer implements CarListenerListener, Runnable {

	
	//public static enum DataType {STRING, INT, DOUBLE, BOOLEAN};
	public static enum DataAttributes {
		LONGITUDE("longitude"),
		LATITUDE("latitude"),
		ENGINE_SPEED("engine_speed"),
		VEHICLE_SPEED("vehicle_speed"),
		ACCELERATION_PEDAL_POSITION("accelerator_pedal_position"),
		BREAKE_PEDAL_STATUS("break_pedal_status");
		
		private String text;
		private DataAttributes(String text ) {
			this.text = text;
		}
		//corresponds with string in dataset
		public String toString() {
			return text;
		}
	};
	
	public static String[] dataStrings;
	public static HashMap<String, DataAttributes> dataStringOrdinals;
	
	static {
		//fill dataStrings and dataStringOrdinals with corresponding values
		int dataAttributesLength = DataAttributes.values().length;
		dataStrings = new String[dataAttributesLength];
		for (DataAttributes attr : DataAttributes.values()) {
			dataStrings[attr.ordinal()] = attr.toString();
			dataStringOrdinals.put( attr.toString(), attr );
		}
	}
	
	
	//the car listnener created
	private CarListener carListener;

	
	//stored attribute-values between each samplepoint
	AttributeDataContainer attributeData;
	
	
	private DataBufferListener dataBufferListener;
	
	
	
	public DataBuffer( DataBufferListener dataBufferListener ) {
		
		this.dataBufferListener = dataBufferListener;
		
		attributeData = new AttributeDataContainer();
		setupSampleDataObject();
		setupCarListener();

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

	
	@Override
	public void run() {
		carListener.run();		
	}

	@Override
	public void onCarData(JsonData carDataObj) {
			System.out.println("car data arrived, name: " + carDataObj.toString());
			
			DataAttributes dataTrigger = DataAttributes.LATITUDE;
			//if we recieve Latitude data, all the data should be sent to listener in one Json Object
			if ( dataTrigger.toString().equals(carDataObj.getName()) ) {
				
				sendDataToListener();
				attributeData.clearAll();
			}
			else {
				storeAttributeData( carDataObj );
			}
				
	}
	
	private void sendDataToListener(  ) {
		
		dataBufferListener.onDataBufferData( "" );
	}
	
	
	private void storeAttributeData( JsonData data ) {
		attributeData.addData(dataStringOrdinals.get( data.getName() ), data.getValue() );
	}
	
}
