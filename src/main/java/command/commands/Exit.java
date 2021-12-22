package command.commands;

import command.AppCommandReceiver;
import command.CommandInvoker;
import command.ICommand;

/**
 * Класс реализации команды закрытия приложения
 * @author Антропов Никита
 * @version 1.0
 */
public class Exit implements ICommand {
    /** Поле command - единственный экземпляр данного класса */
    private static Exit command;
    /** Поле receiver - ссылка на объект получателя для выполнения команд*/
    private final AppCommandReceiver receiver;
    /** Поле arg_count - количество аргументов команды*/
    private final int arg_count;

    /**
     * Конструктор класса
     */
    private Exit() {
        this.receiver = AppCommandReceiver.getInstance();
        String command_regex = "^exit[\s]*";
        this.arg_count = 0;

        CommandInvoker.getInstance().register(command_regex, this);
    }

    /**
     * Метод инициализации и получения единственного экземпляра данного класса
     * @see Exit#command
     * @return ссылку на объект класса
     */
    public static Exit getInstance() {
        if (Exit.command == null)
            Exit.command = new Exit();
        return Exit.command;
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
        this.receiver.exit();
    }

    /**
     * @see ICommand#getInfo()
     */
    @Override
    public String getInfo() {
        return "exit - завершение команды";
    }
}
