package com.metrix.webportal.controllers;

import java.util.Optional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import com.metrix.webportal.models.Events;
import com.metrix.webportal.repos.EventsRepo;
import com.metrix.webportal.repos.VenuesRepo;
import com.metrix.webportal.validation.MetrixException;

//1. Annotation for Web MVC Controller
//2. Annotation for handling request from "/events","/",""
public class EventsController {
    private static final String VIEW_PREFIX = "events/";

    //3. Annotation for Dependency injection
    private EventsRepo repo;

    //3. Annotation for Dependency injection
    private VenuesRepo venuesRepo;

    //4a. Annotation for handling HTTP GET request from "","/","/index"
    public String index(ModelMap m){
        m.addAttribute("allEvents", /*5. get list of events from DB*/);
        return VIEW_PREFIX + "index";
    }

    //4b. Annotation for handling HTTP GET request from "/create"
    public String create(ModelMap m){
        m.addAttribute("newEvent", /*6. create an empty Event */);
        m.addAttribute("allVenues", /*7. get list of venue from DB*/);
        return VIEW_PREFIX + "create";
    }

    //8a. Annotation for handling HTTP POST request from "/create"
    public String create(ModelMap m, /*9. Annotation for hibernate validation against newEvent object*/ /*10a. Annotation for mapping HTML from body with name "newEvent"*/ Events newEvent, BindingResult result){
        /*11. if binding result has error, assign suitable view objects (there are 2) and return the view name VIEW_PREFIX + "create". You can refer to line 35-37 */
        /*12. set the isStartSell to false, and then save newEvent object to DB */
        return "redirect:/" + VIEW_PREFIX + "index";
    }

    //4c. Annotation for handling HTTP GET request from "/update/{id}"
    public String update(ModelMap m, /*13. Annotation for mapping the variable "id" in the path*/ Integer id) throws MetrixException{
        /*14. Find the Events from DB, if not found, throw the following exception
              throw new MetrixException(-1, String.format("Event with id {%d} not found!", id), "/" + VIEW_PREFIX + "index"); */

        m.addAttribute("updEvent", /*15. pass the Events object from DB */);
        m.addAttribute("allVenues", /*16. get the list of venues from DB */);
        return VIEW_PREFIX + "update";
    }

    //8b. Annotation for handling HTTP POST request from /update/{id}
    public String update(ModelMap m, /*13. Annotation for mapping the variable "id" in the path*/Integer id, /*9. Annotation for hibernate validation against updEvent object*/ /*10b. Annotation for mapping HTML from body with name "updEvent"*/ Events updEvent, BindingResult result) throws MetrixException{
        /*14. Find the Events from DB, if not found, throw the following exception
              throw new MetrixException(-1, String.format("Event with id {%d} not found!", id), "/" + VIEW_PREFIX + "index"); */

        /* 15. if there is change of venue, while this event linked to any tickets, throw the following exception
               throw new MetrixException(-1, "Event already has ticket sold, change of venue is not allowed", "/" + VIEW_PREFIX + "update/" + id); */

        /*16. if binding result has error, assign suitable view objects (there are 2) and return the view name VIEW_PREFIX + "update". You can refer to line 52-54 */
        /*17. save the updEvent to DB */
        return "redirect:/" + VIEW_PREFIX + "index";
    }

    //4c. Annotation for handling HTTP GET request from "/delete/{id}"
    public String delete(/*13. Annotation for mapping the variable "id" in the path*/Integer id) throws MetrixException{
    /*14. Find the Events from DB, if not found, throw the following exception
            throw new MetrixException(-1, String.format("Event with id {%d} not found!", id), "/" + VIEW_PREFIX + "index"); */

        /*18. if this event already have ticket sold, throw the following exception
       throw new MetrixException(-1, "Event already has ticket sold, delete is not allowed! Please delete corresponding ticket(s) first", "/" + VIEW_PREFIX + "index"); */
        /*19. delete the record from DB by id */
        return "redirect:/" + VIEW_PREFIX + "index";
    }
}