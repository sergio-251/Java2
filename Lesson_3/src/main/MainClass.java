package main;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;

import static main.CollectionClass.*;

public class MainClass {
    public static void main(String[] args){

        // Первое задание
        String[] textArray = {
                "Java", "Kotlin", "Collection", "GeekBrains", "Java", "Collection",
                "JDK", "Oracle", "Java Core", "Java Syntax", "Kotlin", "Java", "!"
        };

        ArrayList<String> stringList = getArrayList(textArray); // перобразуем массив в коллекцию ArrayList
        System.out.println("Уникальные слова = количество повторов в массиве:");
        System.out.println(uniqString(stringList)); // формируем HashMap(уникальное слово, кол-во встр. раз) и
        //одновременно выводим в консоль

        // Второе задание
        //Наполнение справочника
        TelephoneDirectory telephone = new TelephoneDirectory();
        telephone.add("Petr", "+7(908)345-67-89");
        telephone.add("Vasya", "+7(918)987-65-43");
        telephone.add("Vasya", "+7(908)343-33-33");
        telephone.add("Vasya", "+7(908)343-33-33");// при добавлении уже существующего телефона пользователя
        // выводится инфо сообщение, что такой телефон уже есть в справочнике
        telephone.add("Anna", "+7(999)000-13-13");
        telephone.add("Alena", "+7(908)345-67-89"); // при добавлении нового пользователя с существующим в базе
        // телефоном, такой пользователь не добавиться и будет выведено инфо сообщение

        //Вывод списка телефонов определенных пользователей
        System.out.println();
        System.out.println("Вывод списка телефонов определенных пользователей:");


        telephone.get("Vasya");
        telephone.get("Masha"); // Если пользователя нет в справочнике, будет информационное сообщение

        // с помощью метода getData() можно посмотреть все записи справочника в алфавитном порядке
        System.out.println(telephone.getData());


    }
}
