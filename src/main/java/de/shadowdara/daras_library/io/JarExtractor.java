package de.shadowdara.daras_library.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.*;

import static de.shadowdara.daras_library.io.DirKt.getCallerJarDirectory;

public class JarExtractor {
    /*/**
     * Extrahiert alle Dateien aus dem eigenen JAR, die im angegebenen Verzeichnis liegen
     * und mit der angegebenen Endung enden (z.B. "assets/" und ".json").
     *
     * @param resourceFolder Der Pfad im JAR, z.B. "assets/"
     * @param fileSuffix     Die Dateiendung, z.B. ".json"
     * @param outputDir      Zielverzeichnis im Dateisystem, z.B. "output/"
     * @throws IOException, falls ein Fehler auftritt
     */
    /*
    public static void extractFilesFromJar(String resourceFolder, String fileSuffix, String outputDir) throws IOException {
        // Laufenden JAR-Pfad ermitteln
        String jarPath = JarExtractor.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();

        File outDir = new File(outputDir);
        if (!outDir.exists()) {
            if (!outDir.mkdirs()) {
                throw new IOException("Output Folder could not be created: " + outputDir);
            }
        }

        try (JarFile jarFile = new JarFile(jarPath)) {
            jarFile.stream()
                    .filter(e -> e.getName().startsWith(resourceFolder) && e.getName().endsWith(fileSuffix))
                    .forEach(entry -> {
                        System.out.println("Extract: " + entry.getName());

                        File outFile = new File(outputDir, new File(entry.getName()).getName());

                        try (InputStream in = jarFile.getInputStream(entry);
                             OutputStream out = new FileOutputStream(outFile)) {

                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = in.read(buffer)) != -1) {
                                out.write(buffer, 0, len);
                            }

                        } catch (IOException e) {
                            throw new UncheckedIOException(e); // Damit im Stream catch block rauskommt
                        }
                    });
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }
    */

    public static void extractFile(String inputPath, String outputPath) {
        Path source = Paths.get(inputPath);
        Path target = Paths.get(getCallerJarDirectory() + outputPath);

        try {
            // Datei kopieren (überschreibt, falls vorhanden)
            Files.copy(source, target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied succesfully");
        } catch (IOException e) {
            System.err.println("Error while copying File: " + e.getMessage());
        }
    }
}
