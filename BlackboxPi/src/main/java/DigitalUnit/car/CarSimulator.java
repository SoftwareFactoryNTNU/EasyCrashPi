package DigitalUnit.car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import DigitalUnit.Utils.JsonData;

public class CarSimulator implements Runnable {

    private CarListener carListener;
    private File jsonFile;
    private List<JsonData> jsonDataList = new ArrayList<>();

    public CarSimulator(CarListener carListener, String datasetLocation) {
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
    public static void main(String[] args) {
        CarSimulator sim = new CarSimulator(null, "res/JSON/downtown-west.json");
        System.out.println(System.getProperty("user.home") + "/.tests");
    }

    @Override
    public void run() {
        int index = 0;
        double time;
        double nextTime = jsonDataList.get(0).getTimestamp();

        while (index < jsonDataList.size()) {
            time = nextTime;
            carListener.onCarData(jsonDataList.get(index++));
            nextTime = jsonDataList.get(index).getTimestamp();

            try {
                Thread.sleep((long) ((nextTime - time) * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
