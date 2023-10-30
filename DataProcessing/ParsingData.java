package HW_Exceptions.HW_Exceptions3.DataProcessing;

import java.time.LocalDate;

public class ParsingData {
    private static String fio = "fio";
    private static String number = "number";
    private static String date = "date";
    private static String sex = "sex";

    /** Разделение строки с данными пользователя
     *
     * @param inputData исходная строка
     * @return массив с разбитой на часть строкой
     * @throws IllegalArgumentException при некоректном разбивке исходной строки
     */
    public static String[] splitDataString(String inputData) throws IllegalArgumentException {
        String[] data = inputData.split(" ");
        if (data.length == 6) return data;
        else {
            throw new IllegalArgumentException("Введены некоректные данные");
        }
    }

    /** Преобразование даты из строки в формат LocalDate
     *
     * @param inputDate дата в формате String
     * @return дата в формате LocalDate
     */
    public static LocalDate parseDate(String inputDate){
        String[] date = inputDate.trim().split("\\.");
        return LocalDate.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
    }

    /** Определение содержания поля
     *
     * @param inputData исходная строка с данными
     * @return наименование поля данных
     */
    public static String whatThis (String inputData){
        if (inputData.length() > 2) {
            if (inputData.matches("^[a-zA-Zа-яА-ЯёЁ]+$")) {
                return fio;
            } else if (inputData.contains(".") && inputData.length() == 10) {
                return date;
            } else if (inputData.length() == 11 && inputData.matches("\\d+")){
                return number;
            }
        } else if (inputData.length() == 1) {
            return sex;
        }
        return "unknow";
    }

    /** Проверка данных на соответствие формату
     *
     * @param inputData проверяемая строка
     * @param field название поля с которым соотносятся данные
     * @return результат проверки (да / нет)
     * @throws IllegalArgumentException исключение для информирование пользователя о некоретных данных
     */
    public static boolean checkData (String inputData, String field) throws IllegalArgumentException {
        return switch (field) {
            case "fio" -> checkFIO(inputData);
            case "date" -> checkDate(inputData);
            case "number" -> checkNumber(inputData);
            case "sex" -> checkSex(inputData);
            default -> false;
        };
    }

    /** Проверка на соответсвие текстовых данных
     * <фамилия><имя><отчество>
     * @param inputData проверяемая строка
     * @return результат проверки (да / нет)
     * @throws IllegalArgumentException исключение для информирование пользователя о некоретных данных
     */
    private static boolean checkFIO (String inputData) throws IllegalArgumentException{
        if (inputData.length() > 2 ) {
            if (inputData.matches("^[a-zA-Zа-яА-ЯёЁ]+$")) {
                return true;
            }else {
                throw new IllegalArgumentException("Строка с ФИО " + inputData + " должна содержать только буквы");
            }
        }else {
            throw new IllegalArgumentException("Строка с ФИО " + inputData + " должна быть длиннее");
        }
    }
    /** Проверка на соответсвие формата даты
     * dd.mm.YYYY
     * @param inputData проверяемая строка
     * @return результат проверки (да / нет)
     * @throws IllegalArgumentException исключение для информирование пользователя о некоретных данных
     */
    private static boolean checkDate (String inputData) throws IllegalArgumentException{
        if (inputData.matches("\\d{2}.\\d{2}.\\d{4}")) {
            return true;
        } else {
            throw new IllegalArgumentException("Дата рождения " + inputData + " введена в неправильном формате");
        }
    }
    /** Проверка на соответсвие формата номера телефона
     * xxxxxxxxxxx - 11 цифр
     * @param inputData проверяемая строка
     * @return результат проверки (да / нет)
     * @throws IllegalArgumentException исключение для информирование пользователя о некоретных данных
     */
    private static boolean checkNumber (String inputData) throws IllegalArgumentException{
        if (inputData.length() == 11) {
            if (inputData.matches("\\d+")) {
                return true;
            } else {
                throw new IllegalArgumentException("Номер телефона " + inputData + " должен содержатьтолько цифры");
            }
        }else {
            throw new IllegalArgumentException("Номер телефона " + inputData + " содержит неверное количество цифр");
        }
    }
    /** Проверка на соответсвие формата указания пола
     * m / f - допустимые символы
     * @param inputData проверяемая строка
     * @return результат проверки (да / нет)
     * @throws IllegalArgumentException исключение для информирование пользователя о некоретных данных
     */
    private static boolean checkSex (String inputData) throws IllegalArgumentException {
        if (inputData.length() == 1) {
            if (inputData.matches("[fm]")) {
                return true;
            }else
                throw new IllegalArgumentException("Неправильно введен пол. Некоректные данные");
        } else {
            throw new IllegalArgumentException("Неправильно введен пол. Слишком много данных");
        }
    }

}
