package data.comparator.comparators;

import data.struct.Person;

import java.util.Comparator;

/**
 * Класс для сортировки объектов класса Person по росту
 * @author Антропов Никита
 * @version 1.0
 */
public class IdComparator implements Comparator<Person> {

    /**
     * @see Comparator#compare(Object, Object)
     */
    @Override
    public int compare(Person o1, Person o2) {
        return Long.compare(o1.getId(), o2.getId());
    }
}
