package DigitalUnit.car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


import DigitalUnit.utils.JsonData;

public class CarSimulator extends AbstractCar {

    private AbstractCarListener carListener;
    private File jsonFile;
    private List<JsonData> jsonDataList = new ArrayList<>();

    public CarSimulator(AbstractCarListener carListener, String datasetLocation) {
    	super(carListener);
    	//Ikke bry deg om at super tar inn carListener nå, all funksjonalitet ligger i denne klassen
        
    	if (carListener == null) {
            throw new IllegalArgumentException("carListener can't be null");
        }

        this.carListener = carListener;

        this.jsonFile = new File(datasetLocation);

        if (jsonFile.exists() && datasetLocation.endsWith(".json")) {
            deserializeJSON();
        } else {
            throw new IllegalArgumentException("Invalid location for dataset.");
        }
    }

    private void deserializeJSON() {
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile));
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

    @Override
    public void run() {
        int index = 0;
        double time;
        double nextTime = jsonDataList.get(0).getTimestamp();

        while (index < jsonDataList.size()) {
            time = nextTime;
            
            //legger til .toString() for å gi tekstinout fra "bilen", vi er vel ikke sikre på hva vi faktisk ville fått
            //se bort ifra kommentaren over for nå
            carListener.handleCarEvent(jsonDataList.get(index++));
            nextTime = jsonDataList.get(index).getTimestamp();

            try {
            	//la inn sov minst 0
                Thread.sleep( Math.max(( (long)((nextTime - time) * 1000) ), 0) );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
