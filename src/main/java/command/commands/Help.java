package command.commands;

import command.AppCommandReceiver;
import command.CommandInvoker;
import command.ICommand;

import java.util.Comparator;
import java.util.Set;

/**
 * Класс реализации команды вывод справки по командам
 * @author Антропов Никита
 * @version 1.0
 */
public class Help implements ICommand{
    /** Поле command - единственный экземпляр данного класса */
    private static Help command;
    /** Поле receiver - ссылка на объект получателя для выполнения команд*/
    private final AppCommandReceiver receiver;
    /** Поле arg_count - количество аргументов команды*/
    private final int arg_count;

    /**
     * Конструктор класса
     */
    private Help() {
        this.receiver = AppCommandReceiver.getInstance();
        String command_regex = "^help[\s]*";
        this.arg_count = 0;

        CommandInvoker.getInstance().register(command_regex, this);
    }

    /**
     * Метод инициализации и получения единственного экземпляра данного класса
     * @see Help#command
     * @return ссылку на объект класса
     */
    public static Help getInstance() {
        if (Help.command == null)
            Help.command = new Help();
        return Help.command;
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
        Set<String> keys = CommandInvoker.getInstance().getCommandMap().keySet();
        String info = keys.stream().sorted(Comparator.naturalOrder()).reduce("", (str, e) -> str +
                CommandInvoker.getInstance().getCommandMap().get(e).getInfo() + "\n");
        this.receiver.executeScript(info);
    }

    /**
     * @see ICommand#getInfo()
     */
    @Override
    public String getInfo() {
        return "help - вывод справки по доступным командам";
    }
}
