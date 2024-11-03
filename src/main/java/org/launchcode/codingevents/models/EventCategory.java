package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Entity                 // A class that you want to store in the database as a table
public class EventCategory extends AbstractEntity {
    // AbstractEntity is the base class for persistent class Event.
    // AbstractEntity contains the logic related to IDs which are our PKs, so need to extend AbstractEntity

    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    // Persistence Annotation
    @OneToMany(mappedBy = "eventCat") // foreign key reference to EventCategory object in Event class
    private final List<Event> events= new ArrayList<>();


    public EventCategory(@Size(min = 3, message = "Name must be at least 3 characters long") String name) {
        this.name = name;
    }

    public EventCategory() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public List<Event> getEvents() {
        return events;
    }
}

