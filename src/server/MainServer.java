package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class MainServer {

    private Vector<ClientHandler> clients;
    private int currentNumberClient = 0;

    public MainServer() throws SQLException {

        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();


        try {
            AuthService.connect();

            server = new ServerSocket(8180);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                new ClientHandler(socket, this);
                this.currentNumberClient++;
                System.out.println("Клиент № " + this.currentNumberClient + " подключился");

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    // подписываем клиента на рассылку
    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    // отписываем клиента от рассылки сообщений
    public void unsubscribe(ClientHandler client){
        clients.remove(client);
    }


    // общая рассылка
    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }

    // личные сообщения
    public void personalMsg(String msg, ClientHandler fromClient, String toClient){
        fromClient.sendMsg(fromClient.getNick() + " : " + msg);
        for (ClientHandler o: clients) {
            if(o.getNick().equals(toClient)){
               if(!fromClient.getNick().equals(o.getNick()))
                o.sendMsg(fromClient.getNick() + " : " + msg);
            }
        }
    }

    public boolean isAuthClient(String nick){
        for (ClientHandler o: clients){

            if(o.getNick().equals(nick)){
                return true;
            }
        }
        return false;
    }

}
