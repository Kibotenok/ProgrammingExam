package iostream;

import java.io.IOException;

/**
 * Интерфейс ввода/вывода информации с использованием паттерна Шаблон
 * @author Антропов Никита
 * @version 1.0
 * */
public interface IMangerIO {

    /**
     * Вывод информации в поток вывода
     * @param str - информация для вывода в виде массива строк
     * */
    void write(String[] str);

    /**
     * Чтение информации из потока ввода
     * @param str - информация для вывода и проверки в виде массива строк [{output}, {regex}, {errormes}]
     * @return полученная информация в виде строки
     */
    String read(String[] str) throws IOException;
}
