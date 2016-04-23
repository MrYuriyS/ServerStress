package ua.mralex;

public class Main {
    private static final String PATH = "system.dat";

    public static void main(String[] args) {
        try {
            Parameters parameters = Parameters.getInstance();
            parameters.read(PATH);

            Stress dos = new Stress();
            dos.start();

            Thread.sleep(parameters.getTimer());
            dos.interrupt();

            parameters.save(PATH);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
