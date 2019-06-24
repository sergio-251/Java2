package client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    MenuBar menuBar;

    @FXML
    TextArea ta;

    @FXML
    Menu menu;

    @FXML
    TextField tf;

    @FXML
    Button btn;

    @FXML
    MenuItem style1, style2;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    final String IP_ADDRESS = "localhost";
    final int PORT = 8188;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket(IP_ADDRESS, PORT);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String s = in.readUTF();
                            ta.appendText(s + "\n");
                        }
                    } catch (IOException e) {
                        System.out.println("Вы вышли из чата!..");
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            System.out.println("Невозможно отключиться от сервера!");
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            System.out.println("Ошибка работы с клиентом!");
        }
    }

    public void pressedSend(){
        try {
            out.writeUTF(tf.getText());
            tf.clear();
            tf.requestFocus();
        } catch (IOException e) {
            System.out.println("Ошибка работы с отправкой сообщения! Вероятно вы отключились от сервера");
        }

    }


}
