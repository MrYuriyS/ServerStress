package ua.mralex;

import java.util.ArrayList;

public class Stress extends Thread {

    private String address;
    private int number;
    private boolean allTime;

    public Stress(String address, int number, boolean allTime) {
        this.address = address;
        this.number = number;
        this.allTime = allTime;
    }

    public Stress(Parametrs param) {
        this.address = param.getHttp();
        this.number = param.getConnectNumber();
        this.allTime = param.getAllTime();
    }

    @Override
    public void run() {
        ArrayList<Thread> threads = new ArrayList<>();
        Thread client;
        for (int i = 0; i < number; i++) {
            client = new Client(address, allTime);
            client.start();
            threads.add(client);
        }

        while (!isInterrupted()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                threads.stream().forEach((thread) -> thread.interrupt());

                System.out.println("All threads is interrupted.");
                return;
            }
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isAllTime() {
        return allTime;
    }

    public void setAllTime(boolean allTime) {
        this.allTime = allTime;
    }
}
