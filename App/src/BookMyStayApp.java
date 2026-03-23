import java.util.*;

class BookingRecord {
    private String guestName;
    private String roomType;

    public BookingRecord(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

class BookingHistory {
    private List<BookingRecord> history = new ArrayList<>();

    public void addRecord(String guestName, String roomType) {
        history.add(new BookingRecord(guestName, roomType));
    }

    public List<BookingRecord> getHistory() {
        return new ArrayList<>(history);
    }
}

class ReportService {
    public void generateHistoryReport(BookingHistory bookingHistory) {
        System.out.println("Booking History and Reporting\n");
        System.out.println("Booking History Report");

        List<BookingRecord> records = bookingHistory.getHistory();
        for (BookingRecord record : records) {
            System.out.println(record);
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();
        ReportService reportService = new ReportService();

        history.addRecord("Om", "Single");
        history.addRecord("Shubham", "Double");
        history.addRecord("Shreya", "Suite");

        reportService.generateHistoryReport(history);
    }
}