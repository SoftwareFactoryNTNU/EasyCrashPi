package DigitalUnit.car;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


import DigitalUnit.utils.JsonData;

public class CarSimulator extends AbstractCar {

    private AbstractCarListener carListener;
    private List<JsonData> jsonDataList = new ArrayList<>();

    public CarSimulator(AbstractCarListener carListener, String datasetLocation) {
    	super(carListener);
    	//Ikke bry deg om at super tar inn carListener nå, all funksjonalitet ligger i denne klassen
        
    	if (carListener == null) {
            throw new IllegalArgumentException("carListener can't be null");
        }

        this.carListener = carListener;

        // Load the JSON file as a resource stream
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream jsonInputStream = classLoader.getResourceAsStream(datasetLocation);

        if (jsonInputStream != null && datasetLocation.endsWith(".json")) {
            deserializeJSON(jsonInputStream);
        } else {
            throw new IllegalArgumentException("Invalid location for dataset.");
        }

        try {
            jsonInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the JSON formatted lines and sets up the list for simulation.
     *
     * @param jsonStream Stream of JSON formatted lines.
     */

    private void deserializeJSON(InputStream jsonStream) {
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(jsonStream));
            String in;
            while ((in = bufferedReader.readLine()) != null) {
                JsonData jsonData = gson.fromJson(in, JsonData.class);
                jsonDataList.add(jsonData);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // For testing
    /*public static void main(String[] args) {
        CarSimulator sim = new CarSimulator(null, "res/JSON/downtown-west.json");
        System.out.println(System.getProperty("user.home") + "/.tests");
    }*/

    /**
     * Runs the simulation.
     */

    @Override
    public void run() {
        int index = 0;
        double time;
        double nextTime = jsonDataList.get(0).getTimestamp();

        while (index < jsonDataList.size()) {
            time = nextTime;
            carListener.handleCarEvent(jsonDataList.get(index++));
            nextTime = jsonDataList.get(index).getTimestamp();

            try {
                Thread.sleep(Math.max((long)((nextTime - time) * 1000), 0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
