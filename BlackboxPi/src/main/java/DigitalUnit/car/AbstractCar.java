package DigitalUnit.car;


// Superclass for types of abstract cars
public abstract class AbstractCar implements Runnable {

	
	protected AbstractCarListener carListener;
	
	
	public AbstractCar( AbstractCarListener carListener ) {
		
		this.carListener = carListener;
	}
	
}
