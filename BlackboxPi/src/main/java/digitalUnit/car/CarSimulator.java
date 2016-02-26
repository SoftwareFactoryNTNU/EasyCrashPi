package digitalUnit.car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class CarSimulator implements Runnable {

    private CarListener carListener;
    private File jsonFile;
    private List<JsonData> jsonDataList = new ArrayList<>();

    public CarSimulator(CarListener carListener, String datasetLocation) {
        this.carListener = carListener;
        this.jsonFile = new File(datasetLocation);

        if (jsonFile.exists()) {
            deserializeJSON();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CarSimulator sim = new CarSimulator(null, "res/JSON/downtown-west.json");
    }

    @Override
    public void run() {

    }
}
