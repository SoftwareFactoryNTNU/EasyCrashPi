package DigitalUnit.data;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import DigitalUnit.car.CarListenerListener;
import DigitalUnit.car.CarListener;
import DigitalUnit.utils.CarData;
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
		dataStringOrdinals = new HashMap<String, DataAttributes>();
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
	
	public static void main( String[] args ) {
		DataBuffer dataBuffer = new DataBuffer((CarData d)->{});
		dataBuffer.run();
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
			
			String[] dt = {DataAttributes.LATITUDE.toString(), DataAttributes.LONGITUDE.toString()};
			List<String> dataTriggers = new ArrayList<String>( Arrays.asList( dt ) );
			
			if (dataStringOrdinals.containsKey( carDataObj.getName()) ) {
				storeAttributeData( carDataObj );
			}
			
			/*if (carDataObj.getName().equals( DataAttributes.LATITUDE.toString())) {
				System.out.println("[DataBuffer] got latitude!----------------------------------------------");
			}*/
			
			//if we recieve Latitude data, all the data should be sent to listener in one Json Object
			if ( dataTriggers.contains( carDataObj.getName() ) &&
					attributeData.latitudeData.getDataSize() != 0 &&
					attributeData.longitudeData.getDataSize() != 0) {
				
				//System.out.println("[DataBuffer] got lan and lon, ready to send data");
				sendDataToListener( carDataObj.getTimestamp() );
				attributeData.clearAll();
			}
				
	}
	
	private void sendDataToListener( double timestamp ) {
	    double latitude = attributeData.longitudeData.getFirst().doubleValue();
	    double longitude = attributeData.latitudeData.getFirst().doubleValue();
	    double vehicleSpeed = attributeData.vehicleSpeedData.getDataMean().doubleValue();
	    int engineSpeed = 0; //attributeData.engineSpeedData.getDataMean().intValue();
	    double acceleratorPedal = attributeData.acceleratorPedalPostitionData.getDataMean().doubleValue();
	    boolean breakingPedal = false; // <-------------------------------------
		
	    //quickfix: swapping position of longitude and latitude, the have opposite values
	    CarData carData = new CarData( longitude, latitude, vehicleSpeed, engineSpeed, acceleratorPedal, breakingPedal, timestamp);
	    
	    System.out.println("[DataBuffer] send data to listener: " + carData.toString());
	    
	    dataBufferListener.onDataBufferData( carData );
	}
	
	
	private void storeAttributeData( JsonData data ) {
		System.out.println("[DataBuffer] Store attribute: " + data.getName());
		System.out.println("[DataBuffer] Store value: " + data.getValue().toString());
		attributeData.addData(dataStringOrdinals.get( data.getName() ), data.getValue() );
	}
	
}
