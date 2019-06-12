import exPack.MyArrayDataException;
import exPack.MyArraySizeException;
import exPack.SquareArray;
import java.time.DayOfWeek;

public class MainClass {

    public static void main(final String[] args){

        // Задание по enum
        int workingHours = getWorkingHours(DayOfWeek.MONDAY);

        System.out.println((workingHours > 0) ? "До конца рабочей недели осталось " + workingHours +
                " часов!" : "Сегодня выходной!");

        // Задание по исключениям
        String[][] inArray = {
                {"4", "2", "-2", "0",},
                {"3", "5", "-3", "6"},
                {"10", "3", "0", "4"},
                {"3", "2", "3", "7"}
        };

        try{
            System.out.println("Сумма элементов массива равна " + SquareArray.elementSum(inArray));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage()+" в ячейке " +
                    "[" + SquareArray.getLine() + "][" + SquareArray.getRow() +"]");
        }
    }

    public static int getWorkingHours(DayOfWeek day){
        int result = 40 - day.ordinal()* 8;
        return result;
    }

}


