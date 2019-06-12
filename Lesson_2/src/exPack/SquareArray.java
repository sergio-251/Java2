package exPack;

public class SquareArray {

    private static final int SIZE = 4;
    private String[][] array = new String[SIZE][SIZE];
    private static int line, row;

    public static int getLine() {
        return line;
    }

    public static int getRow() {
        return row;
    }

    public static int elementSum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        boolean k = true;
        for (String[] x: array) {
            if(x.length == SIZE) {
                k &= true;
            }
            else {
                k = false;
                break;
            }
        }
        if (array.length != SIZE || !k){
            throw new MyArraySizeException("Размера массива некорректен!");
        }
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    result += Integer.valueOf(array[i][j]);
                } catch (NumberFormatException e) {
                    line = i; // для возможности дальнейшего использования индексов
                    row = j;
                    throw new MyArrayDataException("Массив содержит неверный формат значений");
                }
            }
        }
        return result;
    }
}
