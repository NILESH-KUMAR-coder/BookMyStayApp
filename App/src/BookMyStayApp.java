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

class BookingQueue {
    private Queue<BookingRequest> requestQueue = new LinkedList<>();

    public void addRequest(BookingRequest request) {
        requestQueue.add(request);
    }

    public void processRequests() {
        System.out.println("Booking Request Queue");
        while (!requestQueue.isEmpty()) {
            BookingRequest request = requestQueue.poll();
            System.out.println("Processing booking for Guest: " + request.getGuestName() +
                    ", Room Type: " + request.getRoomType());
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        BookingQueue queue = new BookingQueue();

        queue.addRequest(new BookingRequest("Om", "Single"));
        queue.addRequest(new BookingRequest("Shubham", "Double"));
        queue.addRequest(new BookingRequest("Shreya", "Suite"));

        queue.processRequests();
    }
}