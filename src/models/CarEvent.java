package models;

public class CarEvent {
    private final int car_id;
    private final String date;
    private final String event;
    private final int mileage;
    private final String invoice;
    private final String contractor;
    private final double price;

    public CarEvent(int car_id, String date, String event, int mileage, String invoice, String contractor, double price) {
        this.car_id = car_id;
        this.date = date;
        this.event = event;
        this.mileage = mileage;
        this.invoice = invoice;
        this.contractor = contractor;
        this.price = price;
    }

    public int getCar_id() {
        return car_id;
    }

    public String getDate() {
        return date;
    }

    public String getEvent() {
        return event;
    }

    public int getMileage() {
        return mileage;
    }

    public String getInvoice() {
        return invoice;
    }

    public String getContractor() {
        return contractor;
    }

    public double getPrice() {
        return price;
    }
}
