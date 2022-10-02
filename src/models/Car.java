package models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Car {
    // private int teamId;
    //    private static int teamIdCounter = 0;
    //
    //    public Team(){
    //        this.teamId= teamIdCounter++;
    //    }
    //    public void printTeamId(){
    //        System.out.println(this.teamId);
    //    }
    //}
    private static int car_id = 0;
    private static final AtomicInteger id = new AtomicInteger(0);
    private String brand;
    private String model;
    private final List<CarEvent> eventList;

    public Car(String brand, String model) {
        setBrand(brand);
        setModel(model);
        car_id = id.incrementAndGet();
        this.eventList = new ArrayList<>();
    }

    public static int getId() {
        return car_id;
    }

    public void addEvent(CarEvent event) {
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

    private void setBrand(String brand) {
        if (brand.trim().isEmpty()) {
            throw new NullPointerException("Invalid brand");
        }
        this.brand = brand;
    }

    private void setModel(String model) {
        if (model.trim().isEmpty()) {
            throw new NullPointerException("Invalid model");
        }
        this.model = model;
    }
}
