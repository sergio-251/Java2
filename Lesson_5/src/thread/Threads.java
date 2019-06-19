package thread;

public class Threads implements Runnable {
    private int numberThread;
    public static Thread t;


    public Threads(int _numberThread){
        this.numberThread = _numberThread;
        this.t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        getArrayFormulaSeparated();
    }

        // Потоки параллельно заполняют разделенные массивы
    public void getArrayFormulaSeparated(){
        setTimeStart();
        ThreadNew.getArrSeparation()[numberThread] = ThreadNew.getArrayFormula(ThreadNew.getArrSeparation()[numberThread], 0, ThreadNew.getArrSeparation()[numberThread].length);
        ThreadNew.timeEnd = System.currentTimeMillis();
    }

    synchronized private void setTimeStart(){
        if(ThreadNew.timeStart == 0 ) {
            ThreadNew.timeStart = System.currentTimeMillis();
        }
    }


}
