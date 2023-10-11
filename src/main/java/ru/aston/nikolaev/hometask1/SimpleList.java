package ru.aston.nikolaev.hometask1;

/**
 * Данный интерфейс описывает работу простейшего List для собственной реализации,
 * с основными базовыми методами.
 * @author Dmitrii Nikolaev
 * @version 1.0
 */
public interface SimpleList<T> extends Iterable<T> {
    /**
     * Добавляет элемент в конец списка.
     * @param value добавляемый элемент.
     */
    void add(T value);

    /**
     * Заменяет элемент на новый по указанному индексу.
     * @param index    индекс элемента который необходимо заменить.
     * @param newValue новый элемент.
     * @return возвращает заменяемый элемент.
     */
    T set(int index, T newValue);

    /**
     * Метод удаляет и возвращает удаляемый элемент по индексу.
     * @param index индекс удаляемого элемента.
     * @return возвращает удаляемый элемент.
     */
    T remove(int index);

    /**
     * Возвращает элемент по индексу.
     * @param index индекс элемента который мы хотим получить.
     * @return возвращает элемент по индексу.
     */
    T get(int index);

    /**
     * @return возвращает размер списка.
     */
    int size();

}
