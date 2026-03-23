import java.util.*;

class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}

class CancellationService {
    private Map<String, Integer> inventory;
    private Stack<String> allocatedRooms;

    public CancellationService(Map<String, Integer> inventory, Stack<String> allocatedRooms) {
        this.inventory = inventory;
        this.allocatedRooms = allocatedRooms;
    }

    public void cancelLastBooking(String roomType) throws BookingException {
        System.out.println("Booking Cancellation & Inventory Rollback");

        if (allocatedRooms.isEmpty()) {
            throw new BookingException("Cancellation failed: No active reservations found.");
        }

        String releasedRoomId = allocatedRooms.pop();

        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, inventory.get(roomType) + 1);

            System.out.println("Cancellation successful.");
            System.out.println("Released Room ID: " + releasedRoomId);
            System.out.println("Inventory rolled back for Type: " + roomType);
            System.out.println("Current Inventory for " + roomType + ": " + inventory.get(roomType));
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 4);

        Stack<String> allocatedRooms = new Stack<>();
        allocatedRooms.push("Single-1");

        CancellationService cancellationService = new CancellationService(inventory, allocatedRooms);

        try {
            cancellationService.cancelLastBooking("Single");
        } catch (BookingException e) {
            System.out.println(e.getMessage());
        }
    }
}