package HW_Exceptions.HW_Exceptions3;

import HW_Exceptions.HW_Exceptions3.DataProcessing.ParsingData;
import HW_Exceptions.HW_Exceptions3.DataProcessing.UserData;
import HW_Exceptions.HW_Exceptions3.View.InputData;
import HW_Exceptions.HW_Exceptions3.View.ViewData;
import HW_Exceptions.HW_Exceptions3.View.WorkWithFile;

import java.io.IOException;
import java.util.ArrayList;

public class Presenter {
    private final ViewData currentLog;
    private WorkWithFile fileData;
    private String path;
    private static String fileExtension = ".txt";

    public Presenter() {
        this.currentLog = new ViewData();
        //this.fileData = new WorkWithFile();
    }

    /** Запись данных пользователя в файл
     *
     */
    private void selectWriteData(){
        boolean dataRun = true;
        boolean parseSuccess = false;
        UserData userData = new UserData();
        String fio = null;
        while (dataRun) {
            currentLog.writeMessage("Введите данные пользователя в формате Фамилия Имя Отчество дата рождения номер телефона пол через пробел\n" +
                    "Фамилия Имя Отчество должны содержать более бвух букв, номер телефона должен состоять из 11 цифр\n" +
                    "Формат даты дд.мм.гггг, допустимые символы для указания пола - m и f. или введите \"exit\" для возврата в предыдущее меню");
            try {
                String userDataStr = InputData.getData();
                if (userDataStr.equals("exit")){
                    return;
                }
                String[] data = ParsingData.splitDataString(userDataStr);
                String[] fieldName = new String[data.length];
                dataRun = false;
                int indexField;
                for (indexField = 0;indexField < data.length; indexField++){
                    fieldName[indexField] = ParsingData.whatThis(data[indexField]);
                    if (ParsingData.checkData(data[indexField], fieldName[indexField])){
                        if (fieldName[indexField].equals("fio")){
                                if (fio == null && ParsingData.checkData(data[indexField],"fio")) {
                                    userData.setSurname(data[indexField]);
                                    fio = "f";
                                    parseSuccess = true;
                                } else if (fio.equals("f")&& ParsingData.checkData(data[indexField],"fio")) {
                                    userData.setName(data[indexField]);
                                    fio = "fi";
                                    parseSuccess = true;
                                }else if (fio.equals("fi")&& ParsingData.checkData(data[indexField],"fio")){
                                    userData.setPatronymic(data[indexField]);
                                    fio = "fio";
                                    parseSuccess = true;
                                }else if(fio.equals("fio")){
                                    currentLog.writeMessage("Введены некоректные данные");
                                    parseSuccess = false;
                                }
                        }else if (fieldName[indexField].equals("date")){
                            userData.setBirthday(ParsingData.parseDate(data[indexField]));
                        } else if (fieldName[indexField].equals("number")) {
                            userData.setNumber(Long.parseLong(data[indexField]));
                        } else if (fieldName[indexField].equals("sex")) {
                            userData.setSex(data[indexField]);
                        } else if(fieldName[indexField].equals("unknow")) {
                            currentLog.writeMessage("Данные " + data[indexField] + "не распознаны");
                            parseSuccess = false;
                        }
                    }else {
                        currentLog.writeMessage("Данные " + data[indexField] + "не распознаны");
                        parseSuccess = false;
                    }
                }
            }catch (IllegalArgumentException e){
                currentLog.writeLog(e.getMessage());
                parseSuccess = false;
            }
        }
        if (parseSuccess){
            try {
                WorkWithFile.writeMessage(path,userData.getSurname() + fileExtension,userData.toString());
                currentLog.writeMessage("Данные пользователя " +userData.getSurname()+ " сохранены");
            } catch (IOException e) {
                currentLog.writeMessage("Некоректная работа с файлом - " + e.getMessage());
            }
        }
    }

    /** Чтение данных из файла с данными пользователя
     *
     */
    private void selectReadData(){
        currentLog.writeMessage("Выберете № фамилии пользователя:");
        ArrayList<String> listFile = WorkWithFile.getDataFileList(path);
        int indexFile = 1;
        for (String fileName:
             listFile) {
            currentLog.writeMessage(indexFile +". "+ fileName);
            indexFile++;
        }
        currentLog.writeMessage(String.valueOf(indexFile) + ". Возврат в предыдущее меню");
        boolean runSelectFile = true;
        while (runSelectFile) {
            try {
                indexFile = Integer.parseInt(InputData.getData());
                if (indexFile > listFile.size()+1 || indexFile < 1) {
                    currentLog.writeMessage("Введен неправильный номер файла");
                }else runSelectFile = false;
            } catch (IllegalArgumentException e) {
                currentLog.writeMessage("Введены некоректные данные. Введите номер требуемого пользователя");
            }
        }
        if (indexFile == listFile.size() + 1) {
            return;
        }
        String nameFile = listFile.get(indexFile-1);
        try {
            String dataUser = WorkWithFile.getDataFile(path,nameFile+fileExtension);
            currentLog.writeMessage(dataUser);
        } catch (IOException e) {
            currentLog.writeMessage("Некоректная работа с файлом - " + e.getMessage());
        }
    }

    /** Меню выбора операций с файлами
     *
     */
    private void dataOperation() {
        boolean run = true;
        String choiceUser;
        while (run) {
            currentLog.writeMessage("Выберите действие\n1. Запись нового пользователя\n2. Чтение данных пользователя\n3. Возврат в предыдущее меню");
            choiceUser = InputData.getData();
            switch (choiceUser) {
                case "1":
                    selectWriteData();
                    break;
                case "2":
                    selectReadData();
                    break;
                case "3":
                    run = false;
                    break;
                default:
                    currentLog.writeMessage("Неверный ввод");
            }
        }
    }

    /**
     * Процедура завершения программы
     *
     * @param message Сообщение для записи в лог при завершении программы
     * @return возращает false для завершения
     */
    private boolean onStop(String message) {
        currentLog.writeMessage(message);
        //currentLog.setLog(new LogFile(pathLog));
        //currentLog.writeLog(message);
        return false;
    }

    /** Процедура запуска программы
     *
     * @return
     */
    public boolean onStart() {
       path = System.getProperty("user.dir") + "\\untitled\\src\\HW_Exceptions\\HW_Exceptions3\\Source\\";
        //path = path + "\\untitled\\src\\HW_Exceptions\\HW_Exceptions3\\Source\\";
        onRun();
        return true;
    }

    /** Первоначальное меню с выбором действий пользователя
     *
     */
    private void onRun() {

        currentLog.writeMessage("Программа сохранения данных пользователя");
        boolean run = true;
        String choiceUser;
        while (run) {
            currentLog.writeMessage("Выберите действие\n1. Операции с данными\n2. Выход");
            choiceUser = InputData.getData();
            switch (choiceUser) {
                case "1":
                    dataOperation();
                    //run = operation();
                    break;
                case "2":
                    run = onStop("Завершение работы");
                    break;
                default:
                    currentLog.writeMessage("Неверный ввод");
            }
        }
    }
}
