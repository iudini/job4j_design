package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean run = true;
            while (run) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.equals("GET /?msg=Exit HTTP/1.1")) {
                            run = false;
                        } else if (str.equals("GET /?msg=Hello HTTP/1.1")) {
                            System.out.println("Hello");
                            break;
                        } else {
                            System.out.println("What?");
                            break;
                        }
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.".getBytes());
                }
            }
        }
    }
}