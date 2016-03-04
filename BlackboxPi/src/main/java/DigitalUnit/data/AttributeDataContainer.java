package DigitalUnit.data;

//import java.util.ArrayList;
import java.util.HashMap;

public class AttributeDataContainer {

	
	/*private ArrayList<Double> longitudeData = new ArrayList<>();
	private ArrayList<Double> latitudeData = new ArrayList<>();
	private ArrayList<Integer> engineSpeedData = new ArrayList<>();
	private ArrayList<Integer> vehicleSpeedData = new ArrayList<>();
	private ArrayList<Integer> acceleratorPedalPostitionData = new ArrayList<>();
	private ArrayList<Boolean> brakePedalStatusData = new ArrayList<>();
	*/
	public final AttributeData<Double> longitudeData;
	public final AttributeData<Double> latitudeData;
	public final AttributeData<Integer> engineSpeedData;
	public final AttributeData<Double> vehicleSpeedData;
	public final AttributeData<Double> acceleratorPedalPostitionData;
	public final AttributeData<Boolean> brakePedalStatusData;
	
	private final HashMap<DataBuffer.DataAttributes, AttributeData<?> > attributeDataMap;
	
	public AttributeDataContainer() {
		
		longitudeData = new AttributeData<Double>() {
			@Override
			public Double getDataMean() {
				return super.getArrayMeanDouble(super.data);
			}
		};
		latitudeData = new AttributeData<Double>() {
			@Override
			public Double getDataMean() {
				return super.getArrayMeanDouble(super.data);
			}
		};
		engineSpeedData = new AttributeData<Integer>() {
			@Override
			public Integer getDataMean() {
				return super.getArrayMeanInt(super.data);
			}
		};
		vehicleSpeedData = new AttributeData<Double>() {
			@Override
			public Double getDataMean() {
				return super.getArrayMeanDouble(super.data);
			}
		};
		acceleratorPedalPostitionData = new AttributeData<Double>() {
			@Override
			public Double getDataMean() {
				return super.getArrayMeanDouble(super.data);
			}
		};
		brakePedalStatusData = new AttributeData<Boolean>() {
			@Override
			public Boolean getDataMean() {
				throw new UnsupportedOperationException("Can't get mean of booleans");
			}
		};
		
		//setup attributes map
		attributeDataMap = new HashMap<DataBuffer.DataAttributes, AttributeData<?> >();
		setupAttributeDataMaps();
		
	}

	public void addData( DataBuffer.DataAttributes attribute, Object value ) {
		//this is done with unchecked casting
		
		attributeDataMap.get(attribute).addDataObject(value);
	}
	
	//don't know what you will get
	public Object getData( DataBuffer.DataAttributes attribute ) {
		return attributeDataMap.get(attribute).getData();
	}
	
	//don't know what you will get
	public Object getDataMean( DataBuffer.DataAttributes attribute ) {
		return attributeDataMap.get(attribute).getDataMean();
	}
	
	public void clearAll() {
		for ( AttributeData<?> attr : attributeDataMap.values()) {
			attr.clearData();
		}
	}
	
	private void setupAttributeDataMaps() {
		attributeDataMap.put(DataBuffer.DataAttributes.LONGITUDE, longitudeData);
		attributeDataMap.put(DataBuffer.DataAttributes.LATITUDE, latitudeData);
		attributeDataMap.put(DataBuffer.DataAttributes.ENGINE_SPEED, engineSpeedData);
		attributeDataMap.put(DataBuffer.DataAttributes.VEHICLE_SPEED, vehicleSpeedData);
		attributeDataMap.put(DataBuffer.DataAttributes.ACCELERATION_PEDAL_POSITION, acceleratorPedalPostitionData);
		attributeDataMap.put(DataBuffer.DataAttributes.BREAKE_PEDAL_STATUS, brakePedalStatusData);
	}
	
	
}
