package DigitalUnit.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.google.gson.Gson;

/*
 * Creates a collection of GsonLines. Use this to send large chunks of data at the same time
 */
public class GsonCollection 
{

	private ArrayList<GsonLine> lines= new ArrayList<GsonLine>();
	
	public GsonCollection(){}
	
	public GsonCollection(Collection<GsonLine> lines)
	{
		lines.removeAll(null);
		this.lines.addAll(lines);
	}
	
	public GsonCollection(GsonLine[] lines)
	{
		this(Arrays.asList(lines));
	}
	public void add(GsonLine line)
	{
		if(line!=null)
		{
			this.lines.add(line);
		}
	}
	
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
