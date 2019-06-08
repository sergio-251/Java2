package exPack;

public class MyArraySizeException extends Exception {

    private String message;

    public MyArraySizeException(String message) {
        super(message);
    }
}
