package ua.mr_alex;

import java.io.IOException;

public class Parameters {
    private static Parameters parameters;
    private static String link;
    private static int connectNumber;
    private static long timer;
    private static boolean reconnect;

    private Parameters() {

    }

    public static Parameters getInstance() {
        if (parameters == null) {
            parameters = new Parameters();
        }
        return parameters;
    }

    public static void read(String path) {
        try {
            String text = Saver.read(path);
            text = text.replace(" ", "");

            String[] pairs = text.split(";");
            for (String pair : pairs) {
                String[] p = pair.split("==");

                switch (p[0]) {
                    case "Link":
                        link = p[1];
                        break;
                    case "NumberOfConnections":
                        connectNumber = Integer.parseInt(p[1]);
                        break;
                    case "Timer":
                        timer = Long.parseLong(p[1]) * 1000;
                        break;
                    case "Reconnect":
                        reconnect = Boolean.parseBoolean(p[1]);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            link = null;
            connectNumber = 0;
            timer = 0;
            reconnect = false;
        }
    }

    public static void save(String path) throws IOException {
        Saver.write(path, info());
    }

    public static String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("Link = ").append(link)
                .append("; \r\nNumber Of Connections = ").append(connectNumber)
                .append("; \r\nTimer = ").append(timer / 1000)
                .append("; \r\nReconnect = ").append(reconnect);
        return sb.toString();
    }

    public static void setLink(String link) {
        Parameters.link = link;
    }

    public static String getLink() {
        return link;
    }

    public static void setNumberOfConnections(int connectNumber) {
        Parameters.connectNumber = connectNumber;
    }

    public static int getNumberOfConnections() {
        return connectNumber;
    }

    public static void setTimer(long timer) {
        Parameters.timer = timer;
    }

    public static long getTimer() {
        return timer;
    }

    public static void setReconnect(boolean reconnect) {
        Parameters.reconnect = reconnect;
    }

    public static boolean isReconnect() {
        return reconnect;
    }
}
