package com.codecool.library.testmodel;

import java.util.Objects;

public class Writer {
    private String name;
    private String birthPlace;

    public Writer(String name, String birthPlace) {
        this.name = name;
        this.birthPlace = birthPlace;
    }

    public Writer() {}

    public String getName() {
        return name;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return Objects.equals(name, writer.name) &&
                Objects.equals(birthPlace, writer.birthPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthPlace);
    }
}
