package de.shadowdara.daras_library.io;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class BrowserOpener {

    public static void openFileInBrowser(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist: " + filePath);
                return;
            }

            // Desktop ist optional, checken ob verfügbar
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                // Datei als URI konvertieren und im Browser öffnen
                desktop.browse(file.toURI());
                System.out.println("Opened file in Browser.");
            } else {
                System.out.println("Usage is not supported :(");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openURLInBrowser(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(url));
                System.out.println("URL im Browser geöffnet.");
            } else {
                System.out.println("Desktop-Funktionalität nicht unterstützt.");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
