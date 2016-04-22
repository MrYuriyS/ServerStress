package ua.mralex;

import java.io.IOException;

public class Parametrs {

    private String path;
    private String http;
    private int connectNumber;
    private long timework;
    private boolean allTime;

    public Parametrs() {

    }

    public Parametrs(String path) {
        try {
            this.path = path;
            read();
        } catch (IOException ex) {
            System.out.println("ERROR - " + ex.getMessage());
            http = null;
            connectNumber = 0;
            timework = 0;
            allTime = false;
        }
    }

    public void read() throws IOException {
        String text = Saver.read(path);
        text = text.replace(" ", "");

        String[] pairs = text.split(";");
        for (String pair : pairs) {
            String[] p = pair.split("=");

            switch (p[0]) {
                case "http":
                    http = p[1];
                    break;
                case "connectNumber":
                    connectNumber = Integer.parseInt(p[1]);
                    break;
                case "timework":
                    timework = Long.parseLong(p[1]) * 1000;
                    break;
                case "allTime":
                    allTime = Boolean.parseBoolean(p[1]);
                    break;
                default:
                    break;
            }
        }
    }

    public void save() throws IOException {
        Saver.write(path, toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("http = ").append(http)
                .append("; \r\nconnectNumber = ").append(connectNumber)
                .append("; \r\ntimework = ").append(timework / 1000)
                .append("; \r\nallTime = ").append(allTime);
        return sb.toString();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public String getHttp() {
        return http;
    }

    public void setConnectNumber(int connectNumber) {
        this.connectNumber = connectNumber;
    }

    public int getConnectNumber() {
        return connectNumber;
    }

    public void setTimework(long timework) {
        this.timework = timework;
    }

    public long getTimework() {
        return timework;
    }

    public void setAllTime(boolean allTime) {
        this.allTime = allTime;
    }

    public boolean getAllTime() {
        return allTime;
    }
}
