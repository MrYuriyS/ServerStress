package ua.mr_alex;

import java.util.ArrayList;

public class Stress extends Thread {
    private static Parameters parameters = Parameters.getInstance();

    @Override
    public void run() {
        ArrayList<Thread> clients = createClients();

        while (!isInterrupted()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                interruptedClients(clients);
                System.out.println(">>> All threads is interrupted.");
                return;
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        }
    }

    private ArrayList<Thread> createClients() {
        ArrayList<Thread> threads = new ArrayList<>();
        Thread client;
        for (int i = 0; i < parameters.getNumberOfConnections(); i++) {
            client = new Client();
            client.start();
            threads.add(client);
        }
        return threads;
    }

    private void interruptedClients(ArrayList<Thread> clients) {
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).interrupt();
        }
    }

    public static void setParameters(Parameters parameters) {
        Stress.parameters = parameters;
    }

    public static Parameters getParameters() {
        return parameters;
    }
}
