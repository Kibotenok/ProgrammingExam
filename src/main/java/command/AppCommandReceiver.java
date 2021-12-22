package command;

import iostream.console.ConsoleManager;

/**
 * Класс реализации получателя команд для работы с приложением на базе паттерна Команда и паттерна Одиночка
 * @author Антропов Никита
 * @version 1.0
 * */
public final class AppCommandReceiver {
    /** Поле receiver - единственный экземпляр данного класса */
    private static AppCommandReceiver receiver;
    /** Поле console - объект взаимодействия с консолью */
    private final ConsoleManager console;

    /**
     * Конструктор класса с инициализацией поля console
     * @see AppCommandReceiver#console
     */
    private AppCommandReceiver() {
        this.console = ConsoleManager.getInstance();
    }

    /**
     * Метод инициализации и получения единственного экземпляра данного класса
     * @see AppCommandReceiver#receiver
     * @return ссылка на объект класса
     */
    public static AppCommandReceiver getInstance() {
        if (AppCommandReceiver.receiver == null)
            AppCommandReceiver.receiver = new AppCommandReceiver();
        return AppCommandReceiver.receiver;
    }

    /**
     * Команда выполнения текстового
     * @param path - путь до выбранного скрипта в виде строки
     */
    public void executeScript(String path) {
        console.write(path);
    }

    /**
     * Команда выхода из приложения
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Команда вывода справки по командам
     * @param info - справка по командам в виде строки
     */
    public void help(String info) {
        console.write(info);
    }

    /**
     * Команда вывода истории команд
     * @param history - история команд
     */
    public void history(String history) {
        console.write(history);
    }
}
