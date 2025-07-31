import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base currency (e.g., USD): ");
        String base = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String target = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        double rate = getExchangeRate(base, target);

        if (rate != 0) {
            double convertedAmount = amount * rate;
            System.out.printf("\n%.2f %s = %.2f %s\n", amount, base, convertedAmount, target);
        } else {
            System.out.println("Failed to get exchange rate.");
        }

        scanner.close();
    }

    public static double getExchangeRate(String base, String target) {
        try {
            String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + base;

            // Establish connection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("HTTP Error Code: " + responseCode);
                return 0;
            }

            // Proper input stream (not deprecated)
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder inline = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                inline.append(line);
            }

            reader.close();

            // Manual JSON-like parsing
            String json = inline.toString();
            String search = "\"" + target + "\":";
            int startIndex = json.indexOf(search);
            if (startIndex == -1) {
                System.out.println("Currency not found in response.");
                return 0;
            }

            startIndex += search.length();
            int endIndex = json.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = json.indexOf("}", startIndex);
            }

            String rateString = json.substring(startIndex, endIndex).trim();
            return Double.parseDouble(rateString);

        } catch (Exception e) {
            System.out.println("Error while fetching exchange rate: " + e.getMessage());
            return 0;
        }
    }
}
