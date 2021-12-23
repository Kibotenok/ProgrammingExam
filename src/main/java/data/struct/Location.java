package data.struct;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Класс хранения данных о местоположении на базе паттерна Строитель
 * @author Антропов Никита
 * @version 1.0
 */
final class Location {
    /** Поле name - имя человека */
    private final String name;
    /** Поле y - положение по оси Y */
    private final Double y;
    /** Поле x - положение по оси X */
    private final long x;

    /**
     * Конструктор класса
     * @param builder - объект строителя
     */
    private Location(Builder builder) {
        this.name = builder.name;
        this.y = builder.y;
        this.x = builder.x;
    }

    /**
     * Вложенный класс строителя
     * @author Антропов Никита
     * @version 1.0
     */
    public static class Builder {
        /** Поле name - имя человека */
        private final String name;
        /** Поле y - положение по оси Y */
        private final Double y;
        /** Поле x - положение по оси X */
        private long x;

        /**
         * Конструктор класса
         * @param name - имя человека
         * @param y - положение по оси Y
         */
        public Builder(String name, Double y) {
            if (name == null || "".equals(name))
                throw new NullPointerException("Поле Имя не может быть пустым\n");
            if (y == null)
                throw new NullPointerException("Поле Y не может быть пустым\n");
            this.name = name;
            this.y = y;
            this.x = 0;
        }

        /**
         * Задание значения X
         * @see Builder#x
         * @param x - положение по оси X
         * @return ссылка на объект строителя
         */
        public Builder X(long x)
        {
            this.x = x;
            return this;
        }

        /**
         * Создание объекта класса Location
         * @see Location#Location(Builder)
         * @return ссылка на объект Location
         */
        public Location build() { return new Location(this);}
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Положение[имя=" + this.name + ", X=" + this.x + ", Y=" +
                new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.US)).format(this.y) + "]";
    }

    /**
     * Получение значения поля x
     * @see Location#x
     * @return значение поля x
     */
    public long getX() { return this.x; }

    /**
     * Получение значения поля y
     * @see Location#y
     * @return значение поля y
     */
    public Double getY() { return this.y; }

    /**
     * Получение значения поля name
     * @see Location#name
     * @return значение поля name
     */
    public String getName() { return this.name; }
}
