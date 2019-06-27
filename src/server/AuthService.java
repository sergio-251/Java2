package server;

import java.sql.*;

public class AuthService {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        try {
            // обращение к драйверу
            Class.forName("org.sqlite.JDBC");
            // установка подключения
            connection = DriverManager.getConnection("jdbc:sqlite:clientsDB.db");
            // создание Statement для возможности оправки запросов
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        // формирование запроса
        String sql = String.format("SELECT nickname FROM main where login = '%s' and password = '%s'", login, pass);


        try {
            // оправка запроса и получение ответа
            ResultSet rs = stmt.executeQuery(sql);
            // если есть строка возвращаем результат если нет то вернеться null
            if(rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isLoginAndNick(String login, String nick) {
        // формирование запроса
        String sql1 = String.format("SELECT login FROM main WHERE login = '%s'", login);
        String sql2 = String.format("SELECT nickname FROM main WHERE nickname = '%s'", nick);
        System.out.println(sql1);
        System.out.println(sql2);
        try {
            // оправка запроса и получение ответа
            ResultSet isLogin = stmt.executeQuery(sql1);
            ResultSet isNick = stmt.executeQuery(sql2);
            // если есть строка возвращаем результат если нет то вернеться null
            if(!isLogin.next() || !isNick.next()) {
                return false;
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String register(String login, String pass, String nick) {
        // формирование запроса
        String sql = String.format("INSERT INTO main (login, password, nickname) VALUES ('%s', '%s', '%s')", login, pass, nick);

        try {
            // оправка запроса на добавление нового пользователя
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void disconnect() {
        try {
            // закрываем соединение
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
