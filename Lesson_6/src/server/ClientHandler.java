package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Main server;
    private int clientNumber;

    public ClientHandler(Main server, Socket socket, int currentClient) {

        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.clientNumber = currentClient;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            String s = in.readUTF();
                            if(s.equals("/exit")){
                                break;
                            }
                           server.broadCastMessage(s);
                        }
                    } catch (IOException e) {
                        System.out.println("Ошибка работы с отправкой сообщений!");

                    } finally {
                        try {
                            server.removeClient(clientNumber - 1);
                            System.out.println("Клиент № " + clientNumber + " отключен");
                            in.close();
                        } catch (IOException e) {
                            System.out.println("Невозможно отключить входящий поток!");
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            System.out.println("Невозможно отключить исходящий поток!");
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            System.out.println("Ошибка инициализации клиента!");
        }

    }

    public void sendMessage (String s){
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            System.out.println("Ошибка работы с отправкой сообщений!");
        }

    }
}
