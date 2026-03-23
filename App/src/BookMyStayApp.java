import java.util.*;

class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}

class BookingValidator {
    private final List<String> validTypes = Arrays.asList("Single", "Double", "Suite");

    public void validateRoomType(String roomType) throws BookingException {
        // Checking against specific list to trigger the "Invalid room type" error
        if (!validTypes.contains(roomType)) {
            throw new BookingException("Booking failed: Invalid room type selected.");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingValidator validator = new BookingValidator();

        System.out.println("Booking Validation");

        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter room type (Single/Double/Suite): ");
        String roomType = scanner.nextLine();

        try {
            validator.validateRoomType(roomType);
            System.out.println("Booking confirmed for " + guestName);
        } catch (BookingException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}