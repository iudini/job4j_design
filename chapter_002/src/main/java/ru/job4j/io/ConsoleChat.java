package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    List<String> answers = new ArrayList<>();

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
                write(line);
                if (!stop) {
                    String answer = getAnswer();
                    System.out.println(answer);
                    write(answer);
                }
                line = reader.readLine();
            }
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

    private void write(String line) {
        try (PrintWriter bw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path, true)))) {
            bw.println(line);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("text.txt", "answers.txt");
        cc.run();
    }
}
