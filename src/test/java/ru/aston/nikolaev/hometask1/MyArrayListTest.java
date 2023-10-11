package ru.aston.nikolaev.hometask1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.assertj.core.api.Assertions.assertThat;


class MyArrayListTest {

    public MyArrayList<Integer> myArrayList = new MyArrayList<>(10);
    @BeforeEach
    public void addList() {
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
    }


    @Test
    void add() {
        myArrayList.add(4);
        myArrayList.add(4);
        assertThat(5).isEqualTo(myArrayList.size());
    }

    @Test
    void set() {
        int expected = 3;
        int rsl = myArrayList.set(2, 100);
        assertThat(expected).isEqualTo(rsl);
        assertThat(100).isEqualTo(myArrayList.get(2));
    }

    @Test
    void remove() {
        int rsl = myArrayList.remove(1);
        int expected = 2;
        assertThat(expected).isEqualTo(rsl);
        assertThat(expected).isEqualTo(myArrayList.size());
    }

    @Test
    void get() {
        assertThat(1).isEqualTo(myArrayList.get(0));
        assertThat(2).isEqualTo(myArrayList.get(1));
        assertThat(3).isEqualTo(myArrayList.get(2));
    }

    @Test
    void size() {
        int expected = 3;
        int actual = myArrayList.size();;
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void iterator() {
        Iterator<Integer> iterator = myArrayList.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(1).isEqualTo(iterator.next());
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void quickSort() {
        MyArrayList<Person> personList = new MyArrayList<>(5);
        personList.add(new Person("Tom", 23));
        personList.add(new Person("Don", 22));
        personList.add(new Person("Alex", 35));
        personList.add(new Person("Misha", 50));
        personList.add(new Person("Elene", 18));

        personList.quickSort(personList, new AgePersonComparator(), 0, personList.size() - 1);

        assertThat(personList.get(0).getAge()).isEqualTo(18);
        assertThat(personList.get(4).getAge()).isEqualTo(50);
    }
}