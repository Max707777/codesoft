import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Currency Converter ---");
        
        System.out.print("Enter Base Currency (e.g., USD, EUR, INR): ");
        String baseCurrency = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter Target Currency (e.g., USD, EUR, INR): ");
        String targetCurrency = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter Amount to Convert: ");
        double amount = 0;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered. Exiting.");
            scanner.close();
            return;
        }

        System.out.println("Fetching exchange rates...");

        double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);

        if (exchangeRate != -1) {
            double convertedAmount = amount * exchangeRate;
            System.out.printf("Exchange Rate: 1 %s = %.4f %s\n", baseCurrency, exchangeRate, targetCurrency);
            System.out.printf("Converted Amount: %.2f %s\n", convertedAmount, targetCurrency);
        } else {
            System.out.println("Failed to retrieve exchange rate. Please check your currency codes or network connection.");
        }

        scanner.close();
    }

    private static double fetchExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            // Using a public, no-key API for exchange rates
            String urlStr = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return -1;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Very basic manual JSON parsing to avoid external dependencies
            String json = response.toString();
            String searchString = "\"" + targetCurrency + "\":";
            int targetIndex = json.indexOf(searchString);

            if (targetIndex == -1) {
                return -1; // Target currency not found in response
            }

            // Extract the value part
            int startIndex = targetIndex + searchString.length();
            int endIndex = json.indexOf(",", startIndex);
            if (endIndex == -1) { // It might be the last element before '}'
                endIndex = json.indexOf("}", startIndex);
            }

            if (startIndex < endIndex) {
                String rateString = json.substring(startIndex, endIndex).trim();
                return Double.parseDouble(rateString);
            }

        } catch (Exception e) {
            // Silently return -1 on error (network, parsing, etc)
        }
        return -1;
    }
}
