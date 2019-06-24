package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class Main {

    private Vector<ClientHandler> clients;

    public Main() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        int currentClient = 0;
        try {
            server = new ServerSocket(8188);
            System.out.println("Сервер запущен!");

            while (true){
                socket = server.accept();
                System.out.println("Клиент № " + (currentClient + 1) + " подключен!");
                currentClient++;
                ClientHandler activityClient = new ClientHandler(this, socket, currentClient);
                clients.add(activityClient);
            }

        } catch (IOException e) {
            System.out.println("Ошибка запуска сервера! Проверьте подключение...");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Невозможно разорвать соединение с сервером");
            }
            try {
                server.close();
            } catch (IOException e) {
                System.out.println("Невозможно остановить сервер!");
            }
        }



    }

    public void broadCastMessage (String s){
        for (ClientHandler o: clients) {
            o.sendMessage(s);
         }
    }

    public void removeClient (int numClient){
        clients.contains(numClient);
    }


}
