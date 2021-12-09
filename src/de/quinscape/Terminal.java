package de.quinscape;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.UUID;

public class Terminal {

    private FileWriter file;

    public void createFileAppender() throws IOException {
        createNewFile();
        receiveUserEntry();
    }

    public void existingFileAppender(String filename) throws IOException {
        openExistingFileOrCreateNew(filename);
        receiveUserEntry();
    }

    private void receiveUserEntry() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();
        while (!nextLine.isEmpty()) {
            try {
                file.append(nextLine);
                file.append("\n");
            } catch (IOException e) {
                System.out.println(" Fehler beim schreiben der Datei " + e);
                break;
            }
            nextLine = scanner.nextLine();
        }
        file.close();
    }

    private void openExistingFileOrCreateNew(String filename) {
        if (Files.exists(Path.of(filename))) {
            try {
                file = new FileWriter(filename, true);
            } catch (IOException e) {
                System.out.println(" Fehler beim öffnen der Datei: " + e.getMessage());
            }
        } else {
            createNewFile();
        }
    }

    void createNewFile() {
        try {
            file = new FileWriter(randomFilename());
        } catch (IOException e) {
            System.out.println(" Fehler beim Erzeugen der Datei: " + e.getMessage());
        }
    }

    String randomFilename() {
        return UUID.randomUUID() + ".txt";
    }

}
