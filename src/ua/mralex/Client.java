package ua.mralex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Client extends Thread {

    private static String address;
    private static boolean allTime;

    public Client(String address, boolean allTime) {
        Client.address = address;
        Client.allTime = allTime;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                getPage();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.err.println(ex.getClass().getSimpleName() + ": " + ex.getMessage());
                return;
            } catch (IOException ex) {
                ex.printStackTrace();
                if (!allTime) {
                    interrupt();
                }
            }
        }
    }

    public String getPage() throws IOException {
        StringBuilder page = new StringBuilder();

        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try (InputStreamReader is = new InputStreamReader(connection.getInputStream());
             BufferedReader br = new BufferedReader(is)) {
            char[] buffer = new char[1024];
            int size;
            while ((size = br.read(buffer)) >= 0 && !isInterrupted()) {
                page.append(new String(buffer, 0, size));

                if (isInterrupted()) {
                    break;
                }
            }
        } finally {
            connection.disconnect();
        }

        return page.toString();
    }

    /*public String getPage() throws IOException {
        StringBuilder page = new StringBuilder();

        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try (InputStream br = connection.getInputStream()) {
            byte[] buffer = new byte[1024];
            int size;
            while ((size = br.read(buffer)) >= 0 && !isInterrupted()) {
                page.append(new String(buffer, 0, size));

                if (isInterrupted()) {
                    break;
                }
            }
        } finally {
            connection.disconnect();
        }

        return page.toString();
    }*/

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Client.address = address;
    }

    public static boolean isAllTime() {
        return allTime;
    }

    public static void setAllTime(boolean allTime) {
        Client.allTime = allTime;
    }
}
