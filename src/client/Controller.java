package client;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Controller {
    Socket socket;
    DataInputStream in;
    DataOutputStream out;


    @FXML
    MenuBar menuBar;

    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button btn1;

    @FXML
    HBox bottomPanel;

    @FXML
    HBox upperPanel, upperPanel2;

    @FXML
    VBox regForm, authForm;

    @FXML
    TextField loginFiled, loginFiled2, nickField;

    @FXML
    PasswordField passwordField, passwordField2;

    @FXML
    CheckMenuItem style1, style2, auth, reg;

    @FXML
    Menu menu, menu2;

    private boolean isAuthorized;

    final String IP_ADRESS = "localhost";
    final int PORT = 8180;

    // метод для показа нижней панели или верхней
    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;

        if (!isAuthorized) {
            authForm.setVisible(true);
            authForm.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
            regForm.setVisible(true);
            regForm.setManaged(true);
            menu.setDisable(true);
            menu2.setDisable(false);
        } else {
            authForm.setVisible(false);
            authForm.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            regForm.setVisible(false);
            regForm.setManaged(false);
            menu.setDisable(false);
            menu2.setDisable(true);
        }
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // блок для авторизации

                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/authok") || str.startsWith("/regok")) {
                                setAuthorized(true);
                                textArea.appendText(str.split(" ", 2)[1] + "\n");
                                break;
                            } else {
                                textArea.appendText(str + "\n");
                            }

                        }


                        // блок для разбора сообщений
                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverClosed")) {
                                break;
                            }

                            if (str.startsWith("/w")) {
                                String[] token = str.split(" ");
                                str = token[2];
                            }

                            textArea.appendText(str + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // метод для авторизации
    public void tryToAuth() {
        if (loginFiled.getText().equals("") || passwordField.getText().equals("")) {
            textArea.appendText("Введены не все данные для входа!\n");
            return;
        }
        else if (socket == null || socket.isClosed()) {
            // сначала подключаемся к серверу
            connect();
        }
            try {


                // отправка сообщений на сервер для авторизации
                out.writeUTF("/auth " + loginFiled.getText() + " " + passwordField.getText());
                loginFiled.clear();
                passwordField.clear();
            }
         catch(IOException e){
            e.printStackTrace();
            }
        }



    // метод для отправки сообщений
    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToReg() {
        if (loginFiled2.getText().equals("") || passwordField2.getText().equals("") || nickField.getText().equals("")) {
            textArea.appendText("Введены не все данные для регистрации!\n");
            return;
        }
        if (socket == null || socket.isClosed()) {
            // сначала подключаемся к серверу
            connect();
        }
        try {
            // отправка сообщений на сервер для регистрации
            out.writeUTF("/reg " + loginFiled2.getText() + " " + passwordField2.getText() + " " + nickField.getText());
            loginFiled.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRegForm() {
        reg.setSelected(true);
        auth.setSelected(false);
        upperPanel.setDisable(true);
        regForm.setDisable(false);
    }

    public void setAuthForm() {
        reg.setSelected(false);
        auth.setSelected(true);
        upperPanel.setDisable(false);
        regForm.setDisable(true);
    }

    public void setStyle1() {
        style1.setSelected(true);
        style2.setSelected(false);
    }

    public void setStyle2() {
        style1.setSelected(false);
        style2.setSelected(true);
    }
}
