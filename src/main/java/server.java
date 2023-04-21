import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private static int port = 8080;

    public static void main(String[] args) {
        String city = null;
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    city = getCity(city, out, in);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            throw new RuntimeException(e);
        }
    }

    private static String getCity(String city, PrintWriter out, BufferedReader in) throws IOException {
        if (city == null) {
            out.println("???");
            city = in.readLine();
            out.println("OK");
        } else {
            out.println(city);
            String newCity = in.readLine();
            if (city.charAt(city.length() - 1) == newCity.charAt(0)) {
                city = newCity;
                out.println("OK");
            } else {
                out.println(" NOT OK");
            }
        }
        return city;
    }
}
