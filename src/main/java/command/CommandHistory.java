package command;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс для работы с историей введенных команд
 * @author Антропов Никита
 * @version 1.0
 */
public final class CommandHistory {
    /** Поле history - буфер названий введенных команд */
    private final static Queue<String> history = new LinkedList<>();
    /** Поле history_size - размер буфера */
    private final static int history_size = 7;

    /**
     * Сохранение новой команды в истории
     * @param command - название команды в виде строки
     */
    public static void push(String command) {
        CommandHistory.history.add(command);
        // Удаление старых команд, если размер истории превышает заданный
        if (CommandHistory.history.size() > CommandHistory.history_size)
            CommandHistory.history.remove();
    }

    /**
     * Получение истории
     * @return история в виде строки
     */
    public static String getHistory() {
        return CommandHistory.history.stream().reduce("",(str, e) -> str + e + "\n");
    }
}
