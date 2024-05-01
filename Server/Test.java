
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class Test{
    public static void main(String[] args) throws IOException {
        String supabaseUrl = "https://rafhblqrgvjzlxhigvlt.supabase.co/storage/v1/object/post-images/test.jpg";
        String filePath = "./cato.jpg";
        String apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJhZmhibHFyZ3Zqemx4aGlndmx0Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcwOTYxMzI5NiwiZXhwIjoyMDI1MTg5Mjk2fQ.hdnYG3UDwEolp6-mspMJHC3TevtNy09lnTE9dUmhjJw";

        File file = new File(filePath);
        byte[] fileBytes = readFileToBytes(file);
        System.out.println(Arrays.toString(fileBytes));

        String mimeType = guessMimeType(fileBytes);
        System.out.println("MIME Type: " + mimeType);

        HttpURLConnection connection = (HttpURLConnection) new URL(supabaseUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setRequestProperty("Content-Type", mimeType);

        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(fileBytes);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("File uploaded successfully.");
        } else {
            System.err.println("Failed to upload file. Response code: " + responseCode);
        }
    }

    private static byte[] readFileToBytes(File file) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             FileInputStream inputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        }
    }
    private static String guessMimeType(byte[] data) throws IOException {
        return URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(data));
    }
}
