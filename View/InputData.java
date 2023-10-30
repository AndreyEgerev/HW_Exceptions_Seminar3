package HW_Exceptions.HW_Exceptions3.View;

import java.util.Scanner;

public class InputData {
    /** Получение данных от пользователя
     *
     * @return строка данных от пользователя
     */
    public static String getData(){
        Scanner dataStr = new Scanner(System.in);
        String data = dataStr.nextLine();
        //dataStr.close();
        return data;
    }

}
