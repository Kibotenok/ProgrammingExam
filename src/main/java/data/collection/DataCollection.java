package data.collection;

import data.comparator.comparators.EyeColorComparator;
import data.comparator.comparators.IdComparator;
import data.struct.Person;
import iostream.console.ConsoleManager;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Класс взаимодействия с коллекцией данных TreeSet
 * @author Антропов Никита
 * @version 1.0
 */
public class DataCollection implements Iterable<Person> {
    /** Поле INFO - информация о коллекции */
    private final CollectionInfo INFO;
    /** Поле collection - коллекция данных */
    private TreeSet<Person> collection;

    /**
     * Конструктор класса
     */
    public DataCollection() {
        this.INFO = new CollectionInfo(Person.class.getSimpleName());
        this.collection = new TreeSet<>();
    }

    /**
     * Получение итератора коллекции
     * @return ссылка на итератор коллекции
     */
    @Override
    public Iterator<Person> iterator() {
        return this.collection.iterator();
    }

    /**
     * Изменение последнего времени и даты сохранения коллекции
     * @param time - время и дата сохранения коллекции
     */
    public void setSaveTime(LocalDateTime time) { this.INFO.setSaveTime(time); }

    /**
     * Получение информации о коллекции
     * @return информация о коллекции в виде строки
     */
    public String getInfo() { return this.INFO.toString(); }

    /**
     * Добавление элемента в коллекцию
     * @param person - новый элемент
     */
    public void addElement(Person person) throws IndexOutOfBoundsException {
        // Генерация id
        person.Id(this.collection.size() + 1);
        this.collection.add(person);
        // Изменение информации о коллекции
        this.INFO.setUpdateTime(LocalDateTime.now());
        this.INFO.setElementCount(this.collection.size());
    }

    /**
     * Добавление элемента в коллекцию, если его значение превышает значение максимального элемента этой коллекции
     */
    public void addMaxElement(Person person, Comparator<Person> comparator) {
        Person max_element = this.collection.stream().max(comparator).orElse(null);
        if (comparator.compare(max_element, person) < 0)
            this.addElement(person);
    }

    /**
     * Изменение элемента коллекции по id
     * @param person - новый элемент коллекции
     * @param id - id элемента коллекции
     */
    public void updateElement(Person person, long id) throws IndexOutOfBoundsException, NoSuchElementException {
        if (this.checkId(id)) {
            person.Id(id);
            this.collection = this.collection.stream().map(e -> e = e.getId() == id ? person : e)
                    .collect(Collectors.toCollection(TreeSet<Person>::new));
            // Изменение информации о коллекции
            this.INFO.setUpdateTime(LocalDateTime.now());
        }
    }

    /**
     * Удаление элемента коллекции по id
     * @param id - id элемента коллекции
     */
    public void removeElement(long id) throws IndexOutOfBoundsException, NoSuchElementException {
        if (this.checkId(id)) {
            this.collection = this.collection.stream().filter(e -> e.getId() != id)
                    .collect(Collectors.toCollection(TreeSet<Person>::new));
            // Изменение информации о коллекции
            this.INFO.setUpdateTime(LocalDateTime.now());
            this.INFO.setElementCount(this.collection.size());
        }
    }

    /**
     * Удаление всех элементов коллекции, которые меньше заданного
     * @param person - заданный элемент
     * @param predicate - правило фильтра элементов коллекции
     */
    public void removeLowerElement(Person person, Predicate<Person> predicate) throws IndexOutOfBoundsException {
        this.collection = this.collection.stream().filter(predicate).sorted()
                .collect(Collectors.toCollection(TreeSet<Person>::new));
        // Изменение информации о коллекции
        this.INFO.setUpdateTime(LocalDateTime.now());
        this.INFO.setElementCount(this.collection.size());
    }

    /**
     * Удаление всех элементов коллекции
     */
    public void clearElement() throws IndexOutOfBoundsException {
        this.collection.clear();
        // Изменение информации о коллекции
        this.INFO.setUpdateTime(LocalDateTime.now());
        this.INFO.setElementCount(this.collection.size());
    }

    /**
     * Получение суммы значений поля Height всех элементов
     * @return сумма значений поля Height
     */
    public Integer sumOfHeight() {
        return this.collection.stream().mapToInt(Person::getHeight).sum();
    }

    /**
     * Получение итератора на коллекцию с фильтром по заданному цвету волос
     * @param hair_color - заданный цвет волос
     * @return итератор на коллекцию с элементами, у которых значение поля HAIR_COLOR равно заданному
     */
    public Iterator<Person> filterByHairColor(String hair_color) throws NoSuchElementException{
        if (!Person.getAvailableHairColor().contains(hair_color))
            throw new NoSuchElementException("Поле Цвет Волос не содержит значения " + hair_color + "\n");
        return this.collection.stream().filter(e -> hair_color.equals(e.getHairColorName())).iterator();
    }

    /**
     * Поиск элемента с заданным id
     * @param id - id элемента
     * @return ссылка на найденный элемент
     */
    public Person findById(long id) throws IndexOutOfBoundsException, NoSuchElementException{
        if (id <= 0)
            throw new IndexOutOfBoundsException("Поле id должно быть больше 0\n");
        return this.collection.stream().filter(e -> e.getId() == id).findAny()
                .orElseThrow(() -> new NoSuchElementException("Элемента с заданным id не существует\n"));
    }

    /**
     * Получение итератора на коллекцию, отсортированную по полю EYE_COLOR
     * @return итератор на коллекцию, отсортированную по полю EYE_COLOR
     */
    public Iterator<Person> getFieldDescendingByEyeColor() {
        ConsoleManager console = ConsoleManager.getInstance();
        return this.collection.stream().sorted(new EyeColorComparator().reversed()
                .thenComparing(new IdComparator())).iterator();
    }

    /**
     * Проверка наличия элемента с заданным id в коллекции
     * @param id - id элемента
     * @return true, если элемент найден
     */
    private boolean checkId(long id) throws IndexOutOfBoundsException, NoSuchElementException {
        if (id <= 0)
            throw new IndexOutOfBoundsException("Поле id должно быть больше 0\n");
        if (this.collection.stream().noneMatch(e -> e.getId() == id))
            throw new NoSuchElementException("Элемента с заданным id не существует\n");

        return true;
    }
}
