package DigitalUnit.database;

import DigitalUnit.utils.CarData;

import java.util.ArrayList;
import java.util.List;

public class CarDataMemory
{

    public List<CarData> cardata= new ArrayList<>();

    public List<CarData> getAll() {

        return new ArrayList<CarData>(cardata);
    }

    public void insert(CarData carData)
    {
        cardata.add(carData);
    }
}
