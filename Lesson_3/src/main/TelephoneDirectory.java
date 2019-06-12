package main;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TelephoneDirectory extends TreeMap {

    private TreeMap<String, HashSet<String>> dataNames;


    public TelephoneDirectory() {
        this.dataNames = new TreeMap<String, HashSet<String>>();

    }

    // Гетер текущего телефонного справочника
    public TreeMap<String, HashSet<String>> getData() {
        return this.dataNames;
    }


    // Создание массива телефонов для нового пользователя
    public HashSet<String> newHashMap(String telephone) {
        HashSet<String> dataTelephone = new HashSet<String>();
        dataTelephone.add(telephone);
        return dataTelephone;
    }

    // Добавление телефонного номера к пользователю
    public void upgradeHashMap(String name, String telephone) {
        getData().get(name).add(telephone);
        System.out.println("Новый телефон добавлен к пользователю успешно!");
    }

    // Добавление новой  записи в телефонный справочник
    public void setTreeSet(String name, HashSet<String> telephone){
        getData().put(name, telephone);
        System.out.println("Новый пользователь добавлен успешно!");
    }

    public String isTelephone(String telephone){
        for (Map.Entry<String, HashSet<String>> inMap: getData().entrySet())
             {
                 //System.out.println(inMap.getKey());

                 if(inMap.getValue().contains(telephone)) {
                return inMap.getKey();
            }
        }
        return null;
    }


    // Основной метод добавления телефона пользователя в справочник
    public void add(String name, String telephone) {
        if (getData().isEmpty() || (!getData().containsKey(name) && (isTelephone(telephone) == null))) {
            setTreeSet(name, newHashMap(telephone));
            return;
        }
        if (isTelephone(telephone) != null){
            System.out.println("Такой телефон уже принадлежит пользователю " + isTelephone(telephone));
            return;
        }

        if(getData().get(name).contains(telephone)){
            System.out.println("Телефон " + telephone + " уже есть в телефонном справочнике у пользователя " + name);
            return;
        } else {
            upgradeHashMap(name, telephone);
        }
    }

    public void get(String name){
        try {
            String[] result = getData().get(name).toArray(new String[getData().get(name).size()]);
            System.out.println("Телефоны пользователя " + name + ":");
            for (String o : result) {
                System.out.println(o);
            }
        } catch (NullPointerException e){
            System.out.println("Такого пользователя не существует");
        }
    }

}
