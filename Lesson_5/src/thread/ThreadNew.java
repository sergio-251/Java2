package thread;

import sun.plugin2.util.SystemUtil;

public class ThreadNew{
    final public static int ITER = 1_000_000;
    private static int numbersThread;
    private static Threads[] threads;
    private static float[] arrResult = new float[ITER];
    private static float[][] arrSeparation;
    static long timeStart;
    static long timeEnd;
    private static long separationTime;
    private static long pasteTime;
    public static long oneThreadTIme;


    public static float[][] getArrSeparation() {
        return arrSeparation;
    }

    // В конструкторе определяем поля и размер массива для многопотоковго заполнения элементов
    public ThreadNew(int _numbersThread){
        numbersThread = _numbersThread; //Записываем количество побочных потоков
        if(ITER % numbersThread == 0) arrSeparation = new float[numbersThread][ITER / numbersThread];
        else arrSeparation = new float[numbersThread + 1][ITER / numbersThread];
        threads = new Threads[arrSeparation.length];
        proceed(); // запускаем основной процесс
    }

    // Разделяем массив на num частей
    private static void arraySeparation(){
        long a = System.currentTimeMillis();
        int currentPos = 0;
        int currentLength = ITER / arrSeparation.length;
        //System.out.println(arrSeparation.length);
        for (int i = 0; i < arrSeparation.length; i++) {
            if ((i == arrSeparation.length - 1) && (ITER - currentPos) != currentLength) { //Если на последнем шаге итерации остаеются в остатке элементы, то их помещаем в доп массив
                System.arraycopy(arrResult, currentPos, arrSeparation[i], 0, currentLength);
                currentPos += currentLength;
                currentLength = (ITER - currentPos);
            }
            else {
                System.arraycopy(arrResult, currentPos, arrSeparation[i], 0, currentLength);
                currentPos += currentLength;
            }
        }
        separationTime = (System.currentTimeMillis() - a);
     }

    //Обратная склейка массивов
    private static void arrayPaste() {
        int currentPos = 0;
        long a = System.currentTimeMillis();
        for (int i = 0; i < arrSeparation.length; i++) {
            System.arraycopy(getArrSeparation()[i], 0, arrResult, currentPos, ((i == arrSeparation.length - 1 && numbersThread != arrSeparation.length) ?
            ITER % numbersThread : getArrSeparation()[i].length));
            currentPos += getArrSeparation()[i].length;
        }
        pasteTime = System.currentTimeMillis() - a;
    }

    public static float[] getArrayNumber(float[] k, int num){
        float[] resultArray = new float[k.length];
        for (int i = 0; i < k.length; i++) {
            resultArray[i] = num;
        }
        return resultArray;
    }

    public static float[] getArrayFormula(float[] k, int start, int end){
        float[] resultArray = new float[k.length];
        int n = 0;
        for (int i = start; i < end; i++) {
            resultArray[n] = (float)(k[n] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            n++;
        }
        return resultArray;
    }

    private static void proceed() {
        arrResult = getArrayNumber(arrResult, 1); // Заполняем единицами массив
        arraySeparation(); // Разделяем массив
        // Организуем многопоточный режим с передачей управления
        for (int i = 0; i < arrSeparation.length; i++) {
            threads[i] = new Threads(i);
            try {
                threads[i].t.join(); // ожидаем выполнения всех потоков
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Время разделения массива на " + arrSeparation.length + " ч. составило " +
                separationTime + " мс.");
        System.out.println("Время многопоточного заполнения массивов: " + (timeEnd - timeStart) + " мс.");
        arrayPaste();
        System.out.println("Время обратной склейки массивов " + arrSeparation.length + " составило " +
                pasteTime + " мс.");
        System.out.println("При этом общее время составило " + (separationTime + pasteTime + timeEnd - timeStart));
        System.out.println();
        System.out.println("Многопоточный метод оказался " + (oneThreadTIme - (separationTime + pasteTime + timeEnd - timeStart) > 0 ?
                "быстрее" : "медленее") + " на " + Math.abs(oneThreadTIme - (separationTime + pasteTime + timeEnd - timeStart)) + " мс.");
    }

}
