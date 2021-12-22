package command.commands;

import command.AppCommandReceiver;
import command.CommandInvoker;
import command.ICommand;

/**
 * Класс реализации команды выполнения заданного скрипта
 * @author Антропов Никита
 * @version 1.0
 */
public class ExecuteScript implements ICommand {
    /** Поле command - единственный экземпляр данного класса */
    private static ExecuteScript command;
    /** Поле receiver - ссылка на объект получателя для выполнения команд*/
    private final AppCommandReceiver receiver;
    /** Поле arg_count - количество аргументов команды*/
    private final int arg_count;

    /**
     * Конструктор класса
     */
    private ExecuteScript() {
        this.receiver = AppCommandReceiver.getInstance();
        String command_regex = "^execute_script[\s]*";
        this.arg_count = 1;

        CommandInvoker.getInstance().register(command_regex, this);
    }

    /**
     * Метод инициализации и получения единственного экземпляра данного класса
     * @see ExecuteScript#command
     * @return ссылку на объект класса
     */
    public static ExecuteScript getInstance() {
        if (ExecuteScript.command == null)
            ExecuteScript.command = new ExecuteScript();
        return ExecuteScript.command;
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
        if (arg != null)
            this.receiver.executeScript(arg);
    }

    /**
     * @see ICommand#getInfo()
     */
    @Override
    public String getInfo() {
        return "execute_script file_name - выполнение скрипта из указанного файла";
    }
}
