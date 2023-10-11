package ru.aston.nikolaev.hometask1;

import java.util.*;

/**
 * Класс описывает работу простейшего ArrayList собственной реализации,
 * на основе динамически расширяемого массива.
 *
 * @author Dmitrii Nikolaev
 * @version 1.0
 */

public class MyArrayList<T> implements SimpleList<T> {

    /**
     * Хранение элементов осуществляется в массиве типа T.
     */
    private T[] container;

    /**
     * Количество элементов в масииве, увеличивается при добавлении
     * и уменьшается при удалении.
     */
    private int size;

    /**
     * Счетчик модификаций с массифом. Только увеличивается.
     */
    private int modCount;


    /**
     * Конструктор принимает на вход размер массива при первоночальном создании
     *
     * @param capacity размер внутреннего массива.
     */
    public MyArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Метод проверяет массив на заполненость, если места нет то массив увеличивается в 2 раза.
     */
    private void capacityIncrease() {
        if (container.length <= size) {
            container = Arrays.copyOf(container,
                    container.length * 2);
        }
    }

    /**
     * Метод добавляет элемент в массив, при этом счетчики size и modCount увеличиваются на 1.
     *
     * @param value доюавляемый элемент типа T.
     */
    @Override
    public void add(T value) {
        capacityIncrease();
        container[size++] = value;
        modCount++;
    }

    /**
     * Заменяет элемент на новый по указанному индексу.
     * @param index    индекс элемента который необходимо заменить.
     * @param newValue новый элемент.
     * @return возвращает заменяемый элемент.
     */

    @Override
    public T set(int index, T newValue) {
        T rsl = get(index);
        container[index] = newValue;
        modCount++;
        return rsl;
    }

    /**
     * Метод удаляет элемент по индексу и смещает правую часть массива на 1 в лево
     * и присваивает последнему элементу null.
     *
     * @param index индекс удаляемого элемента.
     * @return возвращает удаляемый элемент.
     */
    @Override
    public T remove(int index) {
        T rsl = get(index);
        System.arraycopy(container, index + 1,
                container, index, container.length - index - 1);
        container[size - 1] = null;
        size--;
        modCount++;
        return rsl;
    }

    /**
     * Возвращает элемент по индексу, после проверки индекса.
     *
     * @param index индекс элемента который мы хотим порлучить.
     * @return возврвщвет элемент по индексу.
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    /**
     * @return возвращает количество элементов в массиве.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод позволяет итерироваться по списку, если в нем ещё есть элементы.
     * @return возвращает элемент на котором остановился курсор.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            private final int expectedMod = modCount;

            @Override
            public boolean hasNext() {
                if (expectedMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size && container[cursor] != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }

    public void quickSort(MyArrayList<T> list, Comparator<T> comparator, int left, int right) {
        if (left >= right || list.size == 0) return;
        int middle = left + (right - left) / 2;
        T pivot = container[middle];

        int i = left;
        int j = right;

        while (i <= j) {
            while (comparator.compare(container[i], pivot) < 0) {
                i++;
            }

            while (comparator.compare(container[j], pivot) > 0) {
                j--;
            }

            if (i <= j) {
                T temp = container[i];
                container[i] = container[j];
                container[j] = temp;
                i++;
                j--;
            }
        }

        if (left < j) {
            quickSort(list, comparator, left, j);
        }

        if (right > i) {
            quickSort(list, comparator, i, right);
        }

    }

    @Override
    public String toString() {
        return new StringJoiner(MyArrayList.class.getSimpleName())
                .add(Arrays.toString(container))
                .toString();
    }
}
