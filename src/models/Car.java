package models;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private static int id = 0;
    private String brand;
    private String model;
    private List<CarEvent> eventList;

    public Car(String brand, String model) {
        setBrand(brand);
        setModel(model);
        id++;
        this.eventList = new ArrayList<>();
    }

    public static int getId() {
        return id;
    }

    public void addEvent(CarEvent event){
        eventList.add(event);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public List<CarEvent> getEventList() {
        return eventList;
    }

    private void setBrand(String brand){
        if(brand.trim().isEmpty()){
            throw new NullPointerException("Invalid brand");
        }
        this.brand=brand;
    }
    private void setModel(String model){
        if(model.trim().isEmpty()){
            throw new NullPointerException("Invalid model");
        }
        this.model=model;
    }
}
