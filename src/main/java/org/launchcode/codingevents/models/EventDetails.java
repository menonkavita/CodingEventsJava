package org.launchcode.codingevents.models;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity            // Wanting to store this class as a table in DB, so using @Entity annotation
public class EventDetails extends AbstractEntity{
    // AbstractEntity is the base class for persistent class Event.
    // AbstractEntity contains the logic related to IDs which are our PKs, so need to extend AbstractEntity

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    public EventDetails(String description, String contactEmail) {
        this.description = description;
        this.contactEmail = contactEmail;
    }

    // Create a no-arg Constructor as with all Entities
    public EventDetails(){
    }

    public @Size(max = 500, message = "Description too long!") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 500, message = "Description too long!") String description) {
        this.description = description;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Invalid email. Try again.") String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email. Try again.") String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
