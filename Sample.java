import java.util.ArrayList;

public class Sample {
    public static void main(String[] args) {
        ArrayList<String> availableProducts = new ArrayList<>();
        availableProducts.add("id:CLO000000#name:benchshirt#qty:100#price:500#supplierId:2112512");
        
        for (String product : availableProducts) {
            // Split the string into parts using "#" as the delimiter
            String[] parts = product.split("#");
            for (String part : parts) {
                if (part.startsWith("qty:")) {
                    // Extract the quantity value
                    String qty = part.substring(4); // Skip the "qty:" prefix
                    System.out.println("Quantity: " + qty);
                }
            }
        }
    }
}