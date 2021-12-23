package data.struct;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Перечисление с возможными цветами глаз
 * @author Антропов Никита
 * @version 1.0
 */
public enum EEyeColor implements Comparable<EEyeColor> {
    GREEN("Зеленые"),
    BLACK("Черные"),
    BLUE("Голубые"),
    BROWN("Карие");
    /** Название цвета */
    private final String name;

    /**
     * Констурктор перечисления
     * @param name - название цвета
     */
    EEyeColor(String name) { this.name = name;}

    /**
     * @see Enum#toString()
     */
    @Override
    public String toString() { return  this.name; }

    /**
     * Поиск значения по названию цвета
     * @param name - название цвета
     * @return значение EEyeColor
     */
    public static EEyeColor getByName(String name) throws NoSuchElementException {
        return Arrays.stream(EEyeColor.values()).filter(e -> name.equals(e.name)).findAny()
                .orElseThrow(() -> new NoSuchElementException("Поле Цвет Глаз не содержит значения " + name + "\n"));
    }
}
