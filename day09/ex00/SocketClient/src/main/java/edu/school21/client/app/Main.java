package edu.school21.client.app;

import java.io.*;
import java.net.Socket;
import com.beust.jcommander.*;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = {"--port"})
    int port;

    private static Socket clientSocket = null;
    private static BufferedReader reader = null;
    private static BufferedReader in = null;
    private static BufferedWriter out = null;

    public static void main(String[] args) {
        Main program = new Main();

        JCommander.newBuilder()
                .addObject(program)
                .build()
                .parse(args);

        program.run();
    }

    public void run() {
        try {
            clientSocket = new Socket("localhost", port);

            reader = new BufferedReader(new InputStreamReader(System.in));

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            getMessage();

            String command = reader.readLine();

            sendMessage(command);

            getMessage();

            String userName = reader.readLine();

            sendMessage(userName);

            getMessage();

            String password = reader.readLine();

            sendMessage(password);

            getMessage();

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getMessage() {
        try {
            String message = in.readLine();

            if (message.contains("Error")) {
                System.err.println(message);
                System.exit(1);
            }

            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}