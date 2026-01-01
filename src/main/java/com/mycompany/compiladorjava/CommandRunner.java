package com.mycompany.compiladorjava;

import javax.swing.JTextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Ejecuta procesos y envía stdout/stderr a un JTextArea.
 */
public class CommandRunner {

    public static int run(List<String> command, JTextArea outputArea) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true); // mezcla stdout y stderr
        Process p = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                appendLine(outputArea, line);
            }
        }
        int exit = p.waitFor();
        
        appendLine(outputArea, "Exit code: " + exit);
        return exit;
    }

    public static int runWithEnv(List<String> command, JTextArea outputArea, String key, String value)
            throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        if (key != null && value != null) {
            pb.environment().put(key, value);
        }
        pb.redirectErrorStream(true);
        Process p = pb.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                appendLine(outputArea, line);
            }
        }
        int exit = p.waitFor();
        appendLine(outputArea, "Exit code: " + exit);
        return exit;
    }

    private static void appendLine(JTextArea area, String line) {
        if (area != null) {
            area.append(line + System.lineSeparator());
            area.setCaretPosition(area.getDocument().getLength());
        }
    }
}