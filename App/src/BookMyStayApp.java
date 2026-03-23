import java.util.*;

class BookingRequest {
    private String guestName;
    private String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

class AllocationService {
    private Map<String, Integer> inventory = new HashMap<>();
    private Set<String> allocatedRoomIds = new HashSet<>();
    private Map<String, Integer> roomTypeCounters = new HashMap<>();

    public void setInventory(String type, int count) {
        inventory.put(type, count);
        roomTypeCounters.put(type, 1);
    }

    public void processQueue(Queue<BookingRequest> queue) {
        System.out.println("Room Allocation Processing");

        while (!queue.isEmpty()) {
            BookingRequest request = queue.poll();
            String type = request.getRoomType();

            if (inventory.containsKey(type) && inventory.get(type) > 0) {
                // Generate Unique Room ID (e.g., Single-1)
                int currentNumber = roomTypeCounters.get(type);
                String roomId = type + "-" + currentNumber;

                // Uniqueness Enforcement using Set
                if (!allocatedRoomIds.contains(roomId)) {
                    allocatedRoomIds.add(roomId);

                    // Inventory Synchronization (Decrement)
                    inventory.put(type, inventory.get(type) - 1);
                    roomTypeCounters.put(type, currentNumber + 1);

                    System.out.println("Booking confirmed for Guest: " + request.getGuestName() +
                            ", Room ID: " + roomId);
                }
            }
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        // Setup Inventory
        AllocationService allocationService = new AllocationService();
        allocationService.setInventory("Single", 5);
        allocationService.setInventory("Double", 3);
        allocationService.setInventory("Suite", 2);

        // Setup Request Queue (FIFO)
        Queue<BookingRequest> queue = new LinkedList<>();
        queue.add(new BookingRequest("Om", "Single"));
        queue.add(new BookingRequest("Shubham", "Single"));
        queue.add(new BookingRequest("Shreya", "Suite"));

        // Process Allocation
        allocationService.processQueue(queue);
    }
}