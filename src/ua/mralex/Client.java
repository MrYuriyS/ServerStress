package ua.mralex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Client extends Thread {
    private static Parameters parameters = Parameters.getInstance();

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
                if (!parameters.isReconnect()) {
                    interrupt();
                }
            }
        }
    }

    public String getPage() throws IOException {
        StringBuilder page = new StringBuilder();

        URL url = new URL(parameters.getLink());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try (InputStreamReader is = new InputStreamReader(connection.getInputStream());
             BufferedReader br = new BufferedReader(is)) {
            char[] buffer = new char[1024];
            int size;
            while ((size = br.read(buffer)) >= 0 && !isInterrupted()) {
                page.append(new String(buffer, 0, size));
            }
        } finally {
            connection.disconnect();
        }

        return page.toString();
    }

    public static void setParameters(Parameters parameters) {
        Client.parameters = parameters;
    }

    public static Parameters getParameters() {
        return parameters;
    }
}
