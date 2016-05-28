package ua.mr_alex;

public class Main {
    private static final String PATH = "system.dat";

    public static void main(String[] args) {
        try {
            Parameters parameters = Parameters.getInstance();
            parameters.read(PATH);

            if (Server.getStatus(parameters.getLink()) != 0) {
                System.out.println("Stress: START");
                Stress stress = new Stress();
                stress.start();

                Thread.sleep(parameters.getTimer());
                System.out.println("Stress: INTERRUPT");
                stress.interrupt();

                System.out.println("Stress: saving parameters");
                parameters.save(PATH);
            } else {
                System.err.println("WARNING: Server not responding: " + parameters.getLink());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Stress: EXIT");
            System.exit(0);
        }
    }
}
