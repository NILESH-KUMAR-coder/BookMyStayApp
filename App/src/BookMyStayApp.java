import java.util.*;

class Service {
    private String name;
    private double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }
}

class AddOnServiceManager {
    private Map<String, List<Service>> selections = new HashMap<>();

    public void addService(String reservationId, Service service) {
        selections.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
    }

    public void displayAddOns(String reservationId) {
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);

        double totalCost = 0;
        List<Service> services = selections.getOrDefault(reservationId, new ArrayList<>());

        for (Service s : services) {
            totalCost += s.getPrice();
        }

        System.out.println("Total Add-On Cost: " + totalCost);
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        // Simulating selection for Reservation ID: Single-1
        String resId = "Single-1";

        // Guest selects Breakfast (500) and Spa (1000)
        manager.addService(resId, new Service("Breakfast", 500.0));
        manager.addService(resId, new Service("Spa", 1000.0));

        // Display Output
        manager.displayAddOns(resId);
    }
}