package DigitalUnit.data;

import java.util.ArrayList;

public abstract class AttributeData <T> {

	
	ArrayList<T> data;
	
	
	public AttributeData() {
		data = new ArrayList<T>();
	}
	
	
	public ArrayList<T> getData() {
		return data;
	}
	
	public void addData( T data ) {
		this.data.add( data );
	}
	
	//@SuppressWarnings("unchecked")
	public void addDataObject( Object data ) {
		addData( (T) data );
	}

	
	abstract public T getDataMean();
	
	public void clearData() {
		data.clear();
	}
	
	public int getDataSize() {
		return data.size();
	}
	
	public T getFirst() {
		return data.get(0);
	}
	
	
	protected double getArrayMeanDouble( ArrayList<Double> array ) {
		double totalValue = 0;
		for (double d : array) {
			totalValue = d;
		}
		//should not return 0
		if (array.size() == 0) {
			return 0;
		}
		else {
			return totalValue/array.size();
		}
	}
	protected int getArrayMeanInt( ArrayList<Integer> array ) {
		int totalValue = 0;
		for (int d : array) {
			totalValue = d;
		}
		//should not return 0
		if (array.size() == 0) {
			return 0;
		}
		else {
			return totalValue/array.size();
		}
	}
}