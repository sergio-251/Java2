package main;

import thread.ThreadNew;

public class ThreadMain {
    public static void main(String[] args) {
        oneThread(); // Заполнение массива один главным потоком исполнения
        someThread(2);// Заполнение массива заданным количеством потоков

    }

    public static void oneThread(){
        float[] arr = new float[ThreadNew.ITER];
        arr = ThreadNew.getArrayNumber(arr, 1); // Заполняем элементы массива единицами
        long a = System.currentTimeMillis();
        arr = ThreadNew.getArrayFormula(arr, 0, arr.length);
        ThreadNew.oneThreadTIme = System.currentTimeMillis() - a;
        System.out.println("Главный поток - время работы цикла заполнения " + ThreadNew.oneThreadTIme + " мс.");
        System.out.println();
    }

    public static void someThread(int numbersThread){
        new ThreadNew(2); // В качестве аргумента передается количество побочных потоков, работает на любом их количестве
    }
}
