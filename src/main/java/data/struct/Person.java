package data.struct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Класс хранения данных о человека на базе паттерна Строитель
 * @author Антропов Никита
 * @version 1.0
 */
public final class Person implements Comparable<Person> {
    /** Поле id - номер человека */
    private long id;
    /** Поле name - имя человека */
    private final String NAME;
    /** Поле coordinates - координаты человека */
    private final Coordinates COORDINATES;
    /** Поле creation_time - дата создания записи */
    private final LocalDate CREATION_DATE;
    /** Поле height - рост человека */
    private final Integer HEIGHT;
    /** Поле passport_id - номер паспорта человека */
    private final String PASSPORT_ID;
    /** Поле eye_color - цвет глаз */
    private final EEyeColor EYE_COLOR;
    /** Поле hair_color - цвет волос */
    private final EHairColor HAIR_COLOR;
    /** Поле location - местоположение человека */
    private final Location LOCATION;

    /**
     * Конструктор класса
     * @param builder - объект строителя
     */
    private Person(Builder builder) {
        this.NAME = builder.name;
        this.COORDINATES = builder.coordinates;
        this.CREATION_DATE = builder.creation_date;
        this.HEIGHT = builder.height;
        this.PASSPORT_ID = builder.passport_id;
        this.EYE_COLOR = builder.eye_color;
        this.HAIR_COLOR = builder.hair_color;
        this.LOCATION = builder.location;
    }

    /**
     * Метод сравнения двух объектов Person
     * @param p - объект Person
     * @return 0,1 или -1 в зависимости от результата сравнения
     */
    @Override
    public int compareTo(Person p) {
        return Long.compare(this.id, p.getId());
    }

    /**
     * Вложенный класс строителя
     * @author Антропов Никита
     * @version 1.0
     */
    public static class Builder {

        /** Поле name - имя человека */
        private final String name;
        /** Поле coordinates - координаты человека */
        private Coordinates coordinates;
        /** Поле creation_time - дата создания записи */
        private final LocalDate creation_date;
        /** Поле height - рост человека */
        private final Integer height;
        /** Поле passport_id - номер паспорта человека */
        private final String passport_id;
        /** Поле eye_color - цвет глаз */
        private EEyeColor eye_color;
        /** Поле hair_color - цвет волос */
        private EHairColor hair_color;
        /** Поле location - местоположение человека */
        private Location location;

        /**
         * Конструктор класса
         * @param name - имя человека
         * @param passport_id - номер паспорта человека
         * @param height - рост человека
         */
        public Builder(String name, String passport_id, Integer height)
                throws NullPointerException, IllegalArgumentException {
            if (name == null || "".equals(name))
                throw new NullPointerException("Поле Имя не может быть пустым\n");
            if (passport_id == null)
                throw new NullPointerException("Поле Номер Паспорта не может быть пустым\n");
            if (passport_id.length() > 43)
                throw new IllegalArgumentException("Значение поля Номер Паспорта должно занимать меньше 43 символов\n");
            if (height == null)
                throw new NullPointerException("Поле Рост не может быть пустым\n");
            if (height <= 0)
                throw new IllegalArgumentException("Значение поля Рост должно быть больше 0\n");
            this.name = name;
            this.passport_id = passport_id;
            this.height = height;
            this.creation_date = LocalDate.now();
        }

        /**
         * Задание значения поля coordinates
         * @see Builder#coordinates
         * @param x - положение по оси X
         * @param y - положение по оси Y
         * @return ссылка на объект строителя
         */
        public Builder Coordinates(Float x, Float y) throws NullPointerException, IllegalArgumentException {
            this.coordinates = new Coordinates.Builder(x, y).build();
            return this;
        }

        /**
         * Задание значения поля eye_color
         * @see Builder#eye_color
         * @param eye_color - цвет глаз человека
         * @return ссылка на объект строителя
         */
        public Builder EyeColor(String eye_color) throws NoSuchElementException {
            if ("".equals(eye_color) || eye_color == null)
                return this;
            else
                this.eye_color = EEyeColor.getByName(eye_color);
            return this;
        }

        /**
         * Задание значения поля hair_color
         * @see Builder#hair_color
         * @param hair_color - цвет глаз человека
         * @return ссылка на объект строителя
         */
        public Builder HairColor(String hair_color) throws NoSuchElementException {
            if ("".equals(hair_color) || hair_color == null)
                return this;
            else
                this.hair_color = EHairColor.getByName(hair_color);
            return this;
        }

        /**
         * Задание поля location
         * @see Builder#location
         * @param name - имя человека
         * @param y - положение по оси Y
         * @param x - положение по оси X
         * @return ссылка на объект строителя
         */
        public Builder Location(String name, Double y, long x) throws NullPointerException {
            this.location = new Location.Builder(name, y).X(x).build();
            return this;
        }

        /**
         * Создание объекта класса Person
         * @see Person#Person(Builder)
         * @return ссылка на объект Person
         */
        public Person build() { return new Person(this); }
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        String date_str = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.CREATION_DATE);
        String eye_str = this.EYE_COLOR == null ? "" : this.EYE_COLOR.toString();
        String hair_str = this.HAIR_COLOR == null ? "" : this.HAIR_COLOR.toString();
        String location_str = this.LOCATION == null ? "" : this.LOCATION.toString();

        return "Данные человека[id=" + this.id + ", имя=" + this.NAME + ", " +
                this.COORDINATES.toString() + ", дата_создания=" + date_str + ", рост=" +
                this.HEIGHT.toString() + ", номер_паспорта=" + this.PASSPORT_ID +
                ", цвет_глаз=" + eye_str + ", цвет_волос=" + hair_str + ", " +
                location_str + "]";
    }

    /**
     * Получение доступных цветов для поля EYE_COLOR
     * @see EEyeColor
     * @return строка со значениями цветов
     */
    public static String getAvailableEyeColor() {
        return "[" + Arrays.stream(EEyeColor.values()).map(EEyeColor::toString)
                .collect(Collectors.joining(", ")) + "]";
    }

    /**
     * Получение доступных цветов для поля HAIR_COLOR
     * @see EHairColor
     * @return строка со значениями цветов
     */
    public static String getAvailableHairColor() {
        return "[" + Arrays.stream(EHairColor.values()).map(EHairColor::toString)
                .collect(Collectors.joining(", ")) + "]";
    }

    /**
     * Задание поля id
     * @see Person#id
     * @param id - номер id
     */
    public void Id(long id) throws IllegalArgumentException {
        if (id <= 0)
            throw new IllegalArgumentException("Поле id должно быть больше 0\n");
        this.id = id;
    }

    /**
     * Получение значения поля id
     * @see Person#id
     * @return значение поля id
     */
    public long getId() { return this.id; }

    /**
     * Получение значения поля name
     * @see Person#NAME
     * @return значение поля name
     */
    public String getName() { return this.NAME; }

    /**
     * Получение значения поля coordinates
     * @see Person#COORDINATES
     * @return значение поля coordinates
     */
    public Coordinates getCoordinates() { return this.COORDINATES; }

    /**
     * Получение значения поля creation_date
     * @see Person#CREATION_DATE
     * @return значение поля creation_date
     */
    public LocalDate getCreationDate() { return this.CREATION_DATE; }

    /**
     * Получение значения поля height
     * @see Person#HEIGHT
     * @return значение поля height
     */
    public Integer getHeight() { return this.HEIGHT;}

    /**
     * Получение значения поля passport_id
     * @see Person#PASSPORT_ID
     * @return значение поля passport_id
     */
    public String getPassportId() { return this.PASSPORT_ID; }

    /**
     * Получение значения поля eye_color
     * @see Person#EYE_COLOR
     * @return значение поля eye_color
     */
    public EEyeColor getEyeColor() { return this.EYE_COLOR; }

    /**
     * Получение значения поля eye_color в виде строки
     * @see Person#EYE_COLOR
     * @return значение поля eye_color в виде строки
     */
    public String getEyeColorName() { return this.EYE_COLOR.toString(); }

    /**
     * Получение значения поля hair_color
     * @see Person#HAIR_COLOR
     * @return значение поля hair_color
     */
    public EHairColor getHairColor() { return this.HAIR_COLOR; }

    /**
     * Получение значения поля hair_color в виде строки
     * @see Person#HAIR_COLOR
     * @return значение поля hair_color в виде строки
     */
    public String getHairColorName() { return this.HAIR_COLOR.toString(); }

    /**
     * Получение значения поля location
     * @see Person#LOCATION
     * @return значение поля location
     */
    public Location getLocation() { return this.LOCATION; }

    /**
     * Получение дистанции поля Coordinates
     * @see Coordinates#getDistance()
     * @return дистанция от начала координат
     */
    public Double getCoordDistance() { return this.COORDINATES.getDistance(); }

    /**
     * Получение дистанции поля Location
     * @see Location#getDistance()
     * @return дистанция от начала координат
     */
    public Double getLocDistance() { return this.LOCATION.getDistance(); }
}
