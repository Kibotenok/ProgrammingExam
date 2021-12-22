package command;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Класс реализации отправителя команд на базе паттерна Команда и паттерна Одиночка
 * @author Антропов Никита
 * @version 1.0
 */
public final class CommandInvoker {
    /** Поле invoker - единственный экземпляр данного класса */
    private static CommandInvoker invoker;
    /** Поле command_map - словарь с названиями команд и ссылками на их объекты */
    private final HashMap<String, ICommand> command_map;

    /**
     * Конструктор класса с инициализацией поля command_map
     * @see CommandInvoker#command_map
     */
    private CommandInvoker() {
        this.command_map = new HashMap<>();
    }

    /**
     * Метод инициализации и получения единственного экземпляра данного класса
     * @see CommandInvoker#invoker
     * @return ссылка на объект класса
     */
    public static CommandInvoker getInstance() {
        if (CommandInvoker.invoker == null)
            CommandInvoker.invoker = new CommandInvoker();

        return CommandInvoker.invoker;
    }

    /**
     * Регистрация команды в словаре отправителя
     * @param command_regex - название команды в виде регулярного выражения
     * @param command - ссылка на объект команды
     */
    public void register(String command_regex, ICommand command) {
        this.command_map.put(command_regex, command);
    }

    /**
     * Вызов команды по ее названию, введенному пользователем
     * @param command_name - название команды и ее аргументы
     */
    public void executeCommand(String[] command_name) throws IllegalArgumentException {
        if (command_name == null || command_name.length == 0)
            throw new IllegalArgumentException("Вы не ввели команду\n");
        // Поиск команды в словаре
        String find_command = this.command_map.keySet().stream().filter(e -> Pattern.compile(e).
                matcher(command_name[0]).matches()).findAny().orElse(null);
        if (find_command == null)
            throw new IllegalArgumentException("Команды " + command_name[0] + " не существует. " +
                    "Для справки воспользуйтесь командой help\n");
        // Получение ссылки на объект команды и проверка кол-ва аргументов
        ICommand command = this.command_map.get(find_command);
        if (!command.checkArg(command_name.length - 1))
            throw new IllegalArgumentException("Неверное количество аргументов команды " + command_name[0] +
                    ". Для справки воспользуйтесь командой help\n");

        CommandHistory.push(command_name[0]);
        command.execute(command_name[1]);
    }

    /**
     * Вызов команды по ее названию, введенному пользователем
     * @param command_name - название команды
     */
    public void executeCommand(String command_name) throws IllegalArgumentException {
        if (command_name.length() == 0)
            throw new IllegalArgumentException("Вы не ввели команду\n");
        // Поиск команды в словаре
        String find_command = this.command_map.keySet().stream().filter(e -> Pattern.compile(e).
                matcher(command_name).matches()).findAny().orElse(null);
        if (find_command == null)
            throw new IllegalArgumentException("Команды " + command_name + " не существует. " +
                    "Для справки воспользуйтесь командой help\n");
        // Получение ссылки на объект команды и проверка кол-ва аргументов
        ICommand command = this.command_map.get(find_command);
        if (!command.checkArg(0))
            throw new IllegalArgumentException("Неверное количество аргументов команды " + command_name +
                    ". Повторите ввод\n");

        CommandHistory.push(command_name);
        command.execute("");
    }

    /**
     * Получение словаря с командами
     * @return словарь с командами
     */
    public HashMap<String, ICommand> getCommandMap() { return this.command_map; }
}
