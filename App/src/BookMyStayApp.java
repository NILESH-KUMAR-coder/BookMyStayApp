import java.util.*;

class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class ConcurrentBookingProcessor {
    private final Map<String, Integer> inventory = new HashMap<>();
    private final Map<String, Integer> counters = new HashMap<>();

    public ConcurrentBookingProcessor() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
        counters.put("Single", 1);
        counters.put("Double", 1);
        counters.put("Suite", 1);
    }

    // synchronized ensures thread safety for the Critical Section
    public synchronized void processBooking(BookingRequest request) {
        int available = inventory.getOrDefault(request.roomType, 0);
        if (available > 0) {
            int currentId = counters.get(request.roomType);
            System.out.println("Booking confirmed for Guest: " + request.guestName +
                    ", Room ID: " + request.roomType + "-" + currentId);

            inventory.put(request.roomType, available - 1);
            counters.put(request.roomType, currentId + 1);
        }
    }

    public void displayInventory() {
        System.out.println("\nRemaining Inventory:");
        inventory.forEach((type, count) -> System.out.println(type + ": " + count));
    }
}

public class BookMyStayApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Concurrent Booking Simulation");
        ConcurrentBookingProcessor processor = new ConcurrentBookingProcessor();

        // Simulate concurrent requests using threads
        Thread t1 = new Thread(() -> processor.processBooking(new BookingRequest("Om", "Single")));
        Thread t2 = new Thread(() -> processor.processBooking(new BookingRequest("Shubham", "Double")));
        Thread t3 = new Thread(() -> processor.processBooking(new BookingRequest("Shreya", "Suite")));
        Thread t4 = new Thread(() -> processor.processBooking(new BookingRequest("Mohit", "Single")));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Wait for all threads to finish
        t1.join(); t2.join(); t3.join(); t4.join();

        processor.displayInventory();
    }
}