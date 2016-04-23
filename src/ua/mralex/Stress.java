package ua.mralex;

import java.util.ArrayList;

public class Stress extends Thread {
    private static Parameters parameters = Parameters.getInstance();

    @Override
    public void run() {
        ArrayList<Thread> threads = new ArrayList<>();
        Thread client;
        for (int i = 0; i < parameters.getNumberOfConnections(); i++) {
            client = new Client();
            client.start();
            threads.add(client);
        }

        while (!isInterrupted()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                threads.stream().forEach(Thread::interrupt);

                System.out.println("All threads is interrupted.");
                return;
            }
        }
    }

    public static void setParameters(Parameters parameters) {
        Stress.parameters = parameters;
    }

    public static Parameters getParameters() {
        return parameters;
    }
}
