/*
 2. Написать программу для стресс-тестирования веб-серверов.
 */
package ua.mralex;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            String path = "system.dat";
            Parametrs param = new Parametrs(path);

            Stress dos = new Stress(param);
            dos.start();

            Thread.sleep(param.getTimework());
            dos.interrupt();

            param.save();
        } catch (InterruptedException | IOException ex) {
            System.out.println("ERROR - " + ex.getMessage());
        } finally {
            System.exit(0);
        }
    }
}
