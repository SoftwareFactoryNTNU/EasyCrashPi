package DigitalUnit.database;

import DigitalUnit.utils.CarData;

import java.util.ArrayList;
import java.util.List;

public class CarDataMemory
{

    private List<CarData> cardata = new ArrayList<>();

    public List<CarData> getAll() {

        return new ArrayList<CarData>(cardata);
    }

    public void insert(CarData carData)
    {
        cardata.add(carData);
        System.out.println(this.cardata.size());
    }

    public void removeAll() {
        cardata.clear();
    }

    public void remove(CarData cardata) {
        this.cardata.remove(cardata);
    }

    public int getSize() {
        return cardata.size();
    }

    public boolean isEmpty()
    {
        return cardata.isEmpty();
    }
}
