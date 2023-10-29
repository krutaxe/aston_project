package ru.aston.nikolaev.manyToMany;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "auto")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String color;
    private int number;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "driver_auto",
            joinColumns = @JoinColumn(name = "auto_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private List<Driver> drivers;

    public Auto(String type, String color, int number) {
        this.type = type;
        this.color = color;
        this.number = number;
    }

    public Auto() {
    }

    public void addDriverToAuto(Driver driver) {
        if (drivers == null) {
            drivers = new ArrayList<>();
        }
        drivers.add(driver);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Auto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("type='" + type + "'")
                .add("color='" + color + "'")
                .add("number=" + number)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return id == auto.id && number == auto.number && Objects.equals(type, auto.type) && Objects.equals(color, auto.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, color, number);
    }
}
