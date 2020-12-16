package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    List<String> answers = new ArrayList<>();
    List<String> dialog = new LinkedList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean stop = false;
        makeAnswers();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            while (!line.equals(OUT)) {
                if (line.equals(STOP)) {
                    stop = true;
                } else if (line.equals(CONTINUE)) {
                    stop = false;
                }
                dialog.add(line);
                if (!stop) {
                    String answer = getAnswer();
                    System.out.println(answer);
                    dialog.add(answer);
                }
                line = reader.readLine();
            }
            write();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAnswer() {
        Random random = new Random();
        return answers.get(random.nextInt(answers.size()));
    }

    private void makeAnswers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            while (reader.ready()) {
                answers.add(reader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void write() {
        try (PrintWriter bw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path, true)))) {
            for (var line : dialog) {
                bw.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("text.txt", "answers.txt");
        cc.run();
    }
}
