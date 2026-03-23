import java.util.*;

class Room {
    private String type;
    private int beds;
    private int size;
    private double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public String getType() { return type; }

    public String toString(int availability) {
        return type + ":\n" +
                "Beds: " + beds + "\n" +
                "Size: " + size + " sqft\n" +
                "Price per night: " + price + "\n" +
                "Available: " + availability + "\n";
    }
}

class Inventory {
    private Map<String, Integer> availability = new LinkedHashMap<>();

    public void updateInventory(String type, int count) {
        availability.put(type, count);
    }

    public Map<String, Integer> getSnapshot() {
        return new LinkedHashMap<>(availability);
    }
}

class SearchService {
    private Inventory inventory;
    private Map<String, Room> roomCatalog;

    public SearchService(Inventory inventory, Map<String, Room> roomCatalog) {
        this.inventory = inventory;
        this.roomCatalog = roomCatalog;
    }

    public void displayAvailableRooms() {
        System.out.println("Room Search\n");
        Map<String, Integer> counts = inventory.getSnapshot();

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > 0) {
                Room details = roomCatalog.get(entry.getKey());
                if (details != null) {
                    System.out.println(details.toString(entry.getValue()));
                }
            }
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.updateInventory("Single Room", 5);
        inventory.updateInventory("Double Room", 3);
        inventory.updateInventory("Suite Room", 2);

        Map<String, Room> catalog = new LinkedHashMap<>();
        catalog.put("Single Room", new Room("Single Room", 1, 250, 1500.0));
        catalog.put("Double Room", new Room("Double Room", 2, 400, 2500.0));
        catalog.put("Suite Room", new Room("Suite Room", 3, 750, 5000.0));

        SearchService searchService = new SearchService(inventory, catalog);
        searchService.displayAvailableRooms();
    }
}