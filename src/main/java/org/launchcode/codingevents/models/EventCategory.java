package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
// @Table(name="eventcategory")         // give Database table name
public class EventCategory {
    @Id
    @GeneratedValue
    private int id;

    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    public EventCategory() {
    }

    public EventCategory(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public @Size(min = 3, message = "Name must be at least 3 characters long") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, message = "Name must be at least 3 characters long") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventCategory that = (EventCategory) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
