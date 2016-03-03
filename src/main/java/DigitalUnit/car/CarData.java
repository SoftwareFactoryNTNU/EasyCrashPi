package DigitalUnit.car;

public class CarData {

    private double latitude;
    private double longitude;
    private double vehicleSpeed;
    private int engineSpeed;
    private double acceleratorPedal;
    private boolean breakingPedal;
    private double timestamp;

    public CarData(double latitude, double longitude, double vehicleSpeed, int engineSpeed, double acceleratorPedal, boolean breakingPedal, double timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicleSpeed = vehicleSpeed;
        this.engineSpeed = engineSpeed;
        this.acceleratorPedal = acceleratorPedal;
        this.breakingPedal = breakingPedal;
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getVehicleSpeed() {
        return vehicleSpeed;
    }

    public int getEngineSpeed() {
        return engineSpeed;
    }

    public double getAcceleratorPedal() {
        return acceleratorPedal;
    }

    public boolean isBreakingPedal() {
        return breakingPedal;
    }

    public double getTimestamp() {
        return timestamp;
    }
}
