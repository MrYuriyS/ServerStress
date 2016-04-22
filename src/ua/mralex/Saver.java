package ua.mralex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {

    public static void write(String path, String text) throws IOException {
        try (FileWriter fileWriter = new FileWriter(path);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(text);
        }
    }

    public static String read(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
        }

        return sb.toString();
    }
}
