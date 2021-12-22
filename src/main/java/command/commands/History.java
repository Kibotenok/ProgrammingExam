package command.commands;

import command.AppCommandReceiver;
import command.CommandHistory;
import command.CommandInvoker;
import command.ICommand;

import java.util.Comparator;
import java.util.Set;

/**
 * Класс реализации команды вывода истории команд
 * @author Антропов Никита
 * @version 1.0
 */
public class History implements ICommand{
    /** Поле command - единственный экземпляр данного класса */
    private static History command;
    /** Поле receiver - ссылка на объект получателя для выполнения команд*/
    private final AppCommandReceiver receiver;
    /** Поле arg_count - количество аргументов команды*/
    private final int arg_count;

    /**
     * Конструктор класса
     */
    private History() {
        this.receiver = AppCommandReceiver.getInstance();
        String command_regex = "^history[\s]*";
        this.arg_count = 0;

        CommandInvoker.getInstance().register(command_regex, this);
    }

    /**
     * Метод инициализации и получения единственного экземпляра данного класса
     * @see History#command
     * @return ссылку на объект класса
     */
    public static History getInstance() {
        if (History.command == null)
            History.command = new History();
        return History.command;
    }

    /**
     * @see ICommand#checkArg(int)
     */
    @Override
    public boolean checkArg(int arg_count) {
        return this.arg_count == arg_count;
    }

    /**
     * @see ICommand#execute(String)
     */
    @Override
    public void execute(String arg) {
        this.receiver.executeScript(CommandHistory.getHistory());
    }

    /**
     * @see ICommand#getInfo()
     */
    @Override
    public String getInfo() {
        return "history - вывод последних 7 команд";
    }
}
