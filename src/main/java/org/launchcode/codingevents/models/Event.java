package org.launchcode.codingevents.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Created by Chris Bay
 */
@Entity                 // A class that you want to store in the database as a table
public class Event extends AbstractEntity  {
    // AbstractEntity is the base class for persistent class Event.
    // AbstractEntity contains the logic related to IDs which are our PKs, so need to extend AbstractEntity

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    // Using a JPA Annotation to set up this field eg One to Many
    // cascade is a common ORM concept that allows you to specify the relationship between 2 objects
    // ... how ORM operations are applied to sub-objects, and in this case, we want to specify
    // that whenever an event is saved, also specify an associated EventDetails object.
    // This is done cascade in OnToOne annotation
    // So every operation on an event object goes down into EventDetails sub-object
    // So if an event object is saved/deleted matching sub-object sh also be saved/deleted.
    // There are more values for cascade - can check it out!
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull        // Validations in EventDetails class through EventDetails object would not be checked without @NotNull annotation here
    private EventDetails eventDetails;
    // Can add an EventDetails object to the Constructor & update fields if application requires it.
    // Could have also left description & email fields in this class & used it to create EventDetails in our Constructor
    // Here we are using model binding as a setter to populate the description & email fields.


    // private EventType type;            // Replaced eventType Enum (constant) with EventCategory for dynamic addition & deletion of categories
    @ManyToOne                           // Persistence Annotation
    @NotNull(message = "Category is required")
    private EventCategory eventCat;

    public Event(String name, EventCategory eCat) {
        this.name = name;
        this.eventCat = eCat;
    }

    public Event() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventCategory getEventCat() {
        return eventCat;
    }

    public void setEventCat(EventCategory eventCat) {
        this.eventCat = eventCat;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    @Override
    public String toString() {
        return name;
    }

}
