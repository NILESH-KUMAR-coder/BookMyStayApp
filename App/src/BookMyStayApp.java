import java.io.*;
import java.util.*;

public class BookMyStayApp {
    private static final String FILE_NAME = "inventory.txt";
    private static Map<String, Integer> inventory = new LinkedHashMap<>();

    public static void main(String[] args) {
        System.out.println("System Recovery");
        loadInventory();

        displayInventory();

        saveInventory();
    }

    private static void loadInventory() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.\n");
            // Default Initial State
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                inventory.put(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            System.out.println("Error loading recovery data. Starting fresh.");
        }
    }

    private static void saveInventory() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory state.");
        }
    }

    private static void displayInventory() {
        System.out.println("Current Inventory:");
        inventory.forEach((type, count) -> System.out.println(type + ": " + count));
    }
}