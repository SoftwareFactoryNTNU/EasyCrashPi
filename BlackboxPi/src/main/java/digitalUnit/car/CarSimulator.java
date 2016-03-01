package digitalUnit.car;

import java.io.File;

import digitalUnit.car.CarListener;

/**
 * Created by vegard on 16.02.16.
 */
public class CarSimulator {

    private CarListener carListener;
    private File jsonFile;

    public CarSimulator(CarListener carListener, String datasetLocation) {
        this.carListener = carListener;
        this.jsonFile = new File(datasetLocation);
        System.out.println(jsonFile.exists());
        System.out.println(jsonFile.getAbsolutePath());
    }

    public static void main(String[] args) {
        CarSimulator sim = new CarSimulator(null, "res/JSON/downtown-west.json");
    }

}
