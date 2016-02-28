package DigitalUnit.Data;

public class CarData {
	private long timeStamp;
	private double latitude;
	private double longitude;
	private Integer engineSpeed;
	private Integer vehicleSpeed;
	private Integer acceleratorPedalPostition;
	private Boolean brakePedalStatus;
	
	public CarData(long timeStamp, double latitude, double longitude, Integer engineSpeed, Integer vehicleSpeed, Integer acceleratorPedalPostition, Boolean brakePedalStatus) {
		this.timeStamp = timeStamp;
		this.latitude = latitude;
		this.longitude = longitude;
		this.engineSpeed = engineSpeed;
		this.vehicleSpeed = vehicleSpeed;
		this.acceleratorPedalPostition = acceleratorPedalPostition;
		this.brakePedalStatus = brakePedalStatus;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public Integer getEngineSpeed() {
		return engineSpeed;
	}
	
	public void setEngineSpeed(Integer engineSpeed) {
		this.engineSpeed = engineSpeed;
	}
	
	public Integer getVehicleSpeed() {
		return vehicleSpeed;
	}
	
	public void setVehicleSpeed(Integer vehicleSpeed) {
		this.vehicleSpeed = vehicleSpeed;
	}
	
	public Integer getAcceleratorPedalPostition() {
		return acceleratorPedalPostition;
	}
	
	public void setAcceleratorPedalPostition(Integer acceleratorPedalPostition) {
		this.acceleratorPedalPostition = acceleratorPedalPostition;
	}
	
	public Boolean getBrakePedalStatus() {
		return brakePedalStatus;
	}
	
	public void setBrakePedalStatus(Boolean brakePedalStatus) {
		this.brakePedalStatus = brakePedalStatus;
	}

}
