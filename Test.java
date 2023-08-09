import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Test {
    public static void main(String[] args) {
        String phoneNumber = "54321";
        for (int i = 2000; i <= 3000; i++) {
            makeRequest(i + "", phoneNumber);
        }
    }

    static void makeRequest(String email, String phoneNumber) {
        String payload = "{\"email\":\"%s\", \"phoneNumber\":\"%s\"}";
        try {
            URL url = new URL("http://localhost:8080/identify");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = String.format(payload, email, phoneNumber).getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read and process the response
            } else {
                // Handle error cases
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
