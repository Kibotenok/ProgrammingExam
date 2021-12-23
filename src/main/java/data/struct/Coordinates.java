package data.struct;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Класс хранения данных о местоположении без имени на базе паттерна Строитель
 * @author Антропов Никита
 * @version 1.0
 */
final class Coordinates {
    /** Поле y - положение по оси X */
    private final Float x;
    /** Поле y - положение по оси Y */
    private final Float y;

    /**
     * Конструктор класса
     * @param builder - объект строителя
     */
    private Coordinates(Builder builder) {
        this.x = builder.x;
        this.y = builder.y;
    }

    /**
     * Вложенный класс строителя
     * @author Антропов Никита
     * @version 1.0
     */
    public static class Builder {
        /** Поле y - положение по оси X */
        private final Float x;
        /** Поле y - положение по оси Y */
        private final Float y;

        /**
         * Конструктор класса
         * @param x - положение по оси X
         * @param y - положение по оси Y
         */
        public Builder(Float x, Float y) {
            if (x == null)
                throw new NullPointerException("Поле X не может быть пустым\n");
            if (x <= -315.0)
                throw new IllegalArgumentException("Значение поля X должно быть больше -315\n");
            if (y == null)
                throw new NullPointerException("Поле Y не может быть пустым\n");
            this.x = x;
            this.y = y;
        }

        /**
         * Создание объекта класса Coordinates
         * @see Coordinates#Coordinates(Builder)
         * @return ссылка на объект Coordinates
         */
        public Coordinates build() {
            return new Coordinates(this);
        }
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Координаты[X=" + new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.US)).format(this.x)
                + ", Y=" + new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.US)).format(this.y) + "]";
    }

    /**
     * Получение значения поля x
     * @see Coordinates#x
     * @return значение поля x
     */
    public Float getX() { return this.x; }

    /**
     * Получение значения поля y
     * @see Coordinates#y
     * @return значение поля y
     */
    public Float getY() { return this.y; }
}
