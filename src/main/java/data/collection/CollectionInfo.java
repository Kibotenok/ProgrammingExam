package data.collection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Класс, содержащий информацию о коллекции данных
 * @author Антропов Никита
 * @version 1.0
 */
class CollectionInfo {
    /** Поле INIT_TIME - время и дата инициализации коллекции */
    private final String INIT_TIME;
    /** Поле DATA_TYPE - тип данных коллекции */
    private final String DATA_TYPE;
    /** Поле last_update_time - время и дата последнего обновления коллекции */
    private String last_update_time;
    /** Поле last_save_time - время и дата последнего сохранения коллекции */
    private String last_save_time;
    /** Поле element_count - количество элементов коллекции */
    private long element_count;

    /**
     * Констурктор класса
     * @param data_type - тип данных коллекции
     * @param element_count - количество элементов
     */
    public CollectionInfo(String data_type, long element_count) {
        this.INIT_TIME = this.timeFormat(LocalDateTime.now());
        this.DATA_TYPE = data_type;
        this.last_update_time = "-";
        this.last_save_time = "-";
        this.setElementCount(element_count);
    }

    /**
     * @see CollectionInfo#CollectionInfo(String, long)
     * @param data_type - тип данных коллекции
     */
    public CollectionInfo(String data_type) {
        this(data_type, 0);
    }

    /**
     * Изменение времени и даты последнего обновления коллекции
     * @param time - время и дата последнего обновления
     */
    public void setUpdateTime(LocalDateTime time) {
        if (time != null)
            this.last_update_time = this.timeFormat(time);
    }

    /**
     * Изменение времени и даты последнего сохранения коллекции
     * @param time - время и дата последнего сохранения
     */
    public void setSaveTime(LocalDateTime time) {
        if (time != null)
            this.last_save_time = this.timeFormat(time);
    }

    /**
     * Изменение количества элементов коллекции
     * @param element_count - новое количество элементов
     */
    public void setElementCount(long element_count) throws IndexOutOfBoundsException {
        if (element_count < 0)
            throw new IndexOutOfBoundsException("Количество элементов коллекции не может быть отрицательным\n");
        this.element_count = element_count;
    }

    /**
     * Получение информации по коллекции в виде строки
     * @return информация по коллекции в виде строки
     */
    public String toString() {
        return String.format("""
                        Тип данных: %s
                        Время инициализации: %s
                        Последнее обновление: %s
                        Последнее сохранение: %s
                        Количество элементов: %d
                        """,
                this.DATA_TYPE, this.INIT_TIME, this.last_update_time, this.last_save_time, this.element_count);
    }

    /**
     * Преобразование времени и даты в строковый формат по заданному паттерну
     * @param time - время и дата
     * @return время и дата в виде строки
     */
    private String timeFormat(LocalDateTime time) {
        return DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yyyy", new Locale("ru")).format(time);
    }
}
