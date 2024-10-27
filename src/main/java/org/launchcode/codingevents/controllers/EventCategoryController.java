package org.launchcode.codingevents.controllers;


import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCatRepo;


    // displayAllCategories
    @GetMapping
    public String displayAllCategories(Model m){
        m.addAttribute("title", "All Categories");
        m.addAttribute("categories", eventCatRepo.findAll());
        return "eventCategories/index";
    }

    // renderCreateEventCategoryForm
    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model m){
        m.addAttribute("title", "Create Category");
        m.addAttribute("eventCat", new EventCategory());
        return "eventCategories/create";
    }

    // processCreateEventCategoryForm
    @PostMapping("create")
    public String processCreateEventCategoryForm(@Valid @ModelAttribute EventCategory newEventCat, Errors e, Model m){
        if(e.hasErrors()){
            m.addAttribute("title", "Create Category");
            m.addAttribute("eventCat", new EventCategory());
            return "eventCategories/create";
        }

        eventCatRepo.save(newEventCat);
        return "redirect:/eventCategories";
    }
}
