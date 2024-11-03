package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCatRepo;

    // Adding code to show events by clicking on a category
    // If category id is not present, display message "Invalid Category ID"
    // If category d is present but there are no events, table will stay empty
    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer catID, Model model) {

        if(catID == null){      // if no catID provided then find & display other records from DB
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        }
        else{
            Optional<EventCategory> result = eventCatRepo.findById(catID);              // Using Optional class to store results
            if(result.isEmpty()){                                                       // if result doesn't turn up any matches, that means ID doesn't exist
                model.addAttribute("title", "Invalid Category ID: " + catID); // displaying suitable message
            }
            else{
                EventCategory validCat = result.get();                                 // if ID exists then store in EventCategory object & display events by name
                model.addAttribute("title", "Events in Category: " + validCat.getName());
                model.addAttribute("events", validCat.getEvents());
            }
        }


        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCatRepo.findAll());

//        model.addAttribute("types", EventType.values());

        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }

        eventRepository.save(newEvent);
        return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }

        return "redirect:/events";
    }



}
