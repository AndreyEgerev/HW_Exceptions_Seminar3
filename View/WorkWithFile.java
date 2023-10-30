package HW_Exceptions.HW_Exceptions3.View;

import java.io.*;
import java.util.ArrayList;

/** Класс для работы с файлом
 *
 */
public class WorkWithFile {
    private String pathFile;


    /** Конструктор класса для записи в файл
     *
     * @param pathFile путь к файлу для логирования
     */

    public WorkWithFile(String pathFile) {
        this.pathFile = pathFile;
    }
    /**
     * Запись сообщения в файл
     *
     * @param pathSource путь к папке с датафайлами
     * @param nameFile имя файла для записи сообщения
     * @param message сообщение для вывода
     */
    public static void writeMessage(String pathSource, String nameFile, String message) throws IOException{

        pathSource = pathSource + nameFile;// + ".txt";
        //System.out.println(pathSource);
        //setPathSource(pathFile + nameFile);
        File dataFile = new File(pathSource);
        if (!dataFile.exists()){
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new IOException("Невозможно создать файл " + nameFile);
            }
        }
        try (FileWriter dataFileWrite = new FileWriter(pathSource,true);){
             dataFileWrite.write(new StringBuilder().append(message).append('\n').toString());
        } catch (IOException e) {
            throw new IOException("Невозможно записать данные в файл " + nameFile);
        }
    }
    /** Получение списка файлов в директории
     *
     * @param pathSource путь к директории, из которой необходимо получить список файлов
     * @return список имен файлов
     */
    public static ArrayList<String> getDataFileList(String pathSource){
        ArrayList<String> listFile = new ArrayList<>();
        File folder = new File(pathSource);
        if (folder.listFiles().length  != 0 ) {
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    //listFile.add(file.getName().substring(0,file.getName().indexOf(".txt")));
                    listFile.add(new StringBuilder().append(file.getName()).substring(0, file.getName().indexOf(".txt")));
                }
            }
            //System.out.println(listFile);
            return listFile;
        } else {
            listFile.add("Директория пуста");
            return listFile;
        }
    }
    /** Получение информации из файла
     *
     * @param pathSource путь к директории
     * @param nameFile имя файла
     * @return возвращает строку с информацией из файла
     * @throws IOException
     */
    public static String getDataFile(String pathSource, String nameFile) throws IOException {
        StringBuilder dataFile = new StringBuilder();
        try(FileReader fileDataRead = new FileReader(pathSource+nameFile);
            BufferedReader reader = new BufferedReader(fileDataRead);)
        {
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                dataFile.append(line);
                dataFile.append("\n");
            }
            return dataFile.toString();
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Файл с именем " + nameFile + " не найден");
        }
        catch (IOException e) {
            throw new IOException("Нет доступа к файлу " + nameFile);
        }
    }
}
