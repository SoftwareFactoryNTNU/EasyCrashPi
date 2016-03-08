package DigitalUnit.data;

import DigitalUnit.utils.CarData;

public interface DataBufferListener {

	//shalll use our predefined GSON-convertable object
	public void onDataBufferData( CarData dataBufferData ); //change parameter data type
}
