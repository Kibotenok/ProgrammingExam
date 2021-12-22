package iostream.console;

import iostream.IMangerIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Класс взаимодействия с консолью на базе паттерна Одиночка
 * @see IMangerIO
 * @author Антропов Никита
 * @version 1.0
 */
public final class ConsoleManager implements IMangerIO {

    /** Поле console - единственный экземпляр данного класса */
    private static ConsoleManager console;
    /** Поле buffer - буфер чтения из потока ввода */
    private final BufferedReader buffer;

    /**
     * Конструктор класса с инициализацией поля buffer
     */
    private ConsoleManager() {
        this.buffer = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Метод инициализации и получения единственного экземпляра данного класса
     * @see ConsoleManager#console
     * @return ссылку на объект класса
     */
    public static ConsoleManager getInstance() {
        if (ConsoleManager.console == null)
            ConsoleManager.console = new ConsoleManager();
        return ConsoleManager.console;
    }

    /**
     * @see IMangerIO#write(String[])
     */
    @Override
    public void write(String[] str) {
        if (str != null) {
            for (String s : str) {
                System.out.print(s);
            }
        }
    }

    /**
     * Вывод информации в поток вывода
     * @param str - информация для вывода в виде строки
     */
    public void write(String str) {
        if (str != null)
            System.out.print(str);
    }

    /**
     * @see IMangerIO#read(String[])
     */
    @Override
    public String read(String[] str) throws IOException {
        String info;
        if (str != null) {
            // Вывод информации на экран перед вводом
            if (str.length == 1 || str.length == 3)
                this.write(str[0]);
            info = buffer.readLine();
            // Проверка введенной информации через регулярное выражение
            if (str.length == 2 || str.length == 3)
                if (!Pattern.compile(str[str.length - 2]).matcher(info).matches())
                    throw new IOException(str[str.length - 1]);
        }
        else
            info = buffer.readLine();

        return info;
    }

    /**
     * Чтение информации из потока ввода
     * @param str - информация для вывода в виде строки
     * @return полученная информация в виде строки
     */
    public String read(String str) throws IOException {
        if (str != null)
            this.write(str);
        return buffer.readLine();
    }

    /**
     * Чтение информации из потока ввода
     * @return полученная информация в виде строки
     */
    public String read() throws IOException {
        return buffer.readLine();
    }
}
