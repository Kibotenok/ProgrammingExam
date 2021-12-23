package data.struct;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Перечисление с возможными цветами волос
 * @author Антропов Никита
 * @version 1.0
 */
public enum EHairColor {
    RED("Красные"),
    BLACK("Черные"),
    BROWN("Каштановые");
    /** Название цвета */
    private final String name;

    /**
     * Констурктор перечисления
     * @param name - название цвета
     */
    EHairColor(String name) { this.name = name;}

    /**
     * @see Enum#toString()
     */
    @Override
    public String toString() { return  this.name; }

    /**
     * Поиск значения по названию цвета
     * @param name - название цвета
     * @return значение EHairColor
     */
    public static EHairColor getByName(String name) throws  NoSuchElementException {
        return Arrays.stream(EHairColor.values()).filter(e -> name.equals(e.name)).findAny()
                .orElseThrow(() -> new NoSuchElementException("Поле Цвет Волос не содержит значения " + name + "\n"));
    }
}
