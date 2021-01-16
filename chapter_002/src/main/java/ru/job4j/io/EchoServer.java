package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean run = true;
            while (run) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    String message = "";
                    while (!str.isEmpty()) {
                        if (str.equals("GET /?msg=Exit HTTP/1.1")) {
                            run = false;
                        } else if (str.equals("GET /?msg=Hello HTTP/1.1")) {
                            message = "Hello";
                        } else if (message.isEmpty()) {
                            message = "What?";
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(message.getBytes());
                }
            }
        } catch (Exception e) {
            LOG.error("Exception IO", e);
        }
    }
}