package DigitalUnit.utils;

import com.google.gson.Gson;

public class GsonLine{
	
	
	private String name;
	private int value;
	private double timestamp;
	

	/*
	 * Constructs object that GSON can serialize in to a json object.
	 * 
	 * Throws checked exception so that creation must be surrounded by try/catch blocks
	 */
	public GsonLine(String name, int value, double timestamp)throws Exception
	{
		if(!LegalJsonNameAttributes.Names.contains(name))
		{
			
			throw new IllegalArgumentException("Invalid name attribute");
		}
		this.name = name;
		this.value=value;
		this.timestamp=timestamp;
	}
	
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
