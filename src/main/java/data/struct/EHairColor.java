package data.struct;

import java.util.Arrays;

/**
 * Перечисление с возможными цветами волос
 * @author Антропов Никита
 * @version 1.0
 */
enum EHairColor {
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
    public static EHairColor getByName(String name) {
        return Arrays.stream(EHairColor.values()).filter(e -> name.equals(e.name)).findAny()
                .orElseThrow(() -> new NullPointerException("Поле Цвет Волос не содержит значения " + name + "\n"));
    }
}
