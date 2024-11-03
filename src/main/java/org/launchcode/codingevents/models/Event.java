package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Created by Chris Bay
 */
@Entity
public class Event extends AbstractEntity  {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

//    private EventType type;            // Replaced eventType Enum (constant) with EventCategory for dynamic addition & deletion of categories
    @ManyToOne                           // Persistence Annotation
    @NotNull(message = "Category is required")
    private EventCategory eventCat;

    public Event(String name, String description, String contactEmail, EventCategory eCat) {
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.eventCat = eCat;
    }

    public Event() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public EventCategory getEventCat() {
        return eventCat;
    }

    public void setEventCat(EventCategory eventCat) {
        this.eventCat = eventCat;
    }

    @Override
    public String toString() {
        return name;
    }

}
