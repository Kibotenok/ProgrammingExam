package data.comparator.comparators;

import data.struct.Person;

import java.util.Comparator;

/**
 * Класс для сортировки объектов класса Person по имени
 * @author Антропов Никита
 * @version 1.0
 */
public class NameComparator implements Comparator<Person> {

    /**
     * @see Comparator#compare(Object, Object)
     */
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
