package DigitalUnit.car;

public interface AbstractCarListener {

	//Implement this to be used in CarSimulator.
	
	//AbstractCar is a parent (that does not exist in the project) to all virtual cars.
	//CarSimulator is one type of AbstractCar.
	
	
	public void handleCarEvent( String data );	
}
