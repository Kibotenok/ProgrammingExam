package data.struct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Класс хранения данных о человека на базе паттерна Строитель
 * @author Антропов Никита
 * @version 1.0
 */
public final class Person {
    /** Поле id - номер человека */
    private long id;
    /** Поле name - имя человека */
    private final String name;
    /** Поле coordinates - координаты человека */
    private final Coordinates coordinates;
    /** Поле creation_time - дата создания записи */
    private final LocalDate creation_date;
    /** Поле height - рост человека */
    private final Integer height;
    /** Поле passport_id - номер паспорта человека */
    private final String passport_id;
    /** Поле eye_color - цвет глаз */
    private final EEyeColor eye_color;
    /** Поле hair_color - цвет волос */
    private final EHairColor hair_color;
    /** Поле location - местоположение человека */
    private final Location location;

    /**
     * Конструктор класса
     * @param builder - объект строителя
     */
    private Person(Builder builder) {
        this.name = builder.name;
        this.coordinates = builder.coordinates;
        this.creation_date = builder.creation_date;
        this.height = builder.height;
        this.passport_id = builder.passport_id;
        this.eye_color = builder.eye_color;
        this.hair_color = builder.hair_color;
        this.location = builder.location;
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
        public Builder(String name, String passport_id, Integer height) {
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
        public Builder Coordinates(Float x, Float y) {
            this.coordinates = new Coordinates.Builder(x, y).build();
            return this;
        }

        /**
         * Задание значения поля eye_color
         * @see Builder#eye_color
         * @param eye_color - цвет глаз человека
         * @return ссылка на объект строителя
         */
        public Builder EyeColor(String eye_color) {
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
        public Builder HairColor(String hair_color) {
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
        public Builder Location(String name, Double y, long x) {
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
        String date_str = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.creation_date);
        String eye_str = this.eye_color == null ? "" : this.eye_color.toString();
        String hair_str = this.hair_color == null ? "" : this.hair_color.toString();
        String location_str = this.location == null ? "" : this.location.toString();

        return "Данные человека[id=" + this.id + ", имя=" + this.name + ", " +
                this.coordinates.toString() + ", дата_создания=" + date_str + ", рост=" +
                this.height.toString() + ", номер_паспорта=" + this.passport_id +
                ", цвет_глаз=" + eye_str + ", цвет_волос=" + hair_str + ", " +
                location_str + "]";
    }

    /**
     * Получение массива с доступными цветами для поля eye_color
     * @see EEyeColor
     * @return строка со значениями цветов
     */
    public static String getAvailableEyeColor() {
        return "[" + Arrays.stream(EEyeColor.values()).map(EEyeColor::toString)
                .collect(Collectors.joining(", ")) + "]";
    }

    /**
     * Получение массива с доступными цветами для поля hair_color
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
    public void Id(long id) { this.id = id; }

    /**
     * Получение значения поля id
     * @see Person#id
     * @return значение поля id
     */
    public long getId() { return this.id; }

    /**
     * Получение значения поля name
     * @see Person#name
     * @return значение поля name
     */
    public String getName() { return this.name; }

    /**
     * Получение значения поля coordinates
     * @see Person#coordinates
     * @return значение поля coordinates
     */
    public Coordinates getCoordinates() { return this.coordinates; }

    /**
     * Получение значения поля creation_date
     * @see Person#creation_date
     * @return значение поля creation_date
     */
    public LocalDate getCreationDate() { return this.creation_date; }

    /**
     * Получение значения поля height
     * @see Person#height
     * @return значение поля height
     */
    public Integer getHeight() { return this.height;}

    /**
     * Получение значения поля passport_id
     * @see Person#passport_id
     * @return значение поля passport_id
     */
    public String getPassportId() { return this.passport_id; }

    /**
     * Получение значения поля eye_color
     * @see Person#eye_color
     * @return значение поля eye_color
     */
    public EEyeColor getEyeColor() { return this.eye_color; }

    /**
     * Получение значения поля hair_color
     * @see Person#hair_color
     * @return значение поля hair_color
     */
    public EHairColor getHairColor() { return this.hair_color; }

    /**
     * Получение значения поля location
     * @see Person#location
     * @return значение поля location
     */
    public Location getLocation() { return this.location; }
}
