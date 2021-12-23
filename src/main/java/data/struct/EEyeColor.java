package data.struct;

import java.util.Arrays;

/**
 * Перечисление с возможными цветами глаз
 * @author Антропов Никита
 * @version 1.0
 */
enum EEyeColor {
    GREEN("Зеленые"),
    BLACK("Черные"),
    BLUE("Голубые"),
    BROWN("Каштановые");
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
    public static EEyeColor getByName(String name) {
        return Arrays.stream(EEyeColor.values()).filter(e -> name.equals(e.name)).findAny()
                .orElseThrow(() -> new NullPointerException("Поле Цвет Глаз не содержит значения " + name + "\n"));
    }
}
