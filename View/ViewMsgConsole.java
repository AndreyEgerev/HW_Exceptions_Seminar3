package HW_Exceptions.HW_Exceptions3.View;

/** Класс для отображения сообщений в консоль
 *
 */
public class ViewMsgConsole implements ViewMsg {

    /**
     * Вывод сообщения
     *
     * @param message сообщение для вывода
     */
    @Override
    public void writeMessage(String message) {
        System.out.println(message);
    }
}
