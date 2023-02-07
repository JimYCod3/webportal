package com.metrix.webportal.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import com.metrix.webportal.domains.SellForm;
import com.metrix.webportal.models.Events;
import com.metrix.webportal.models.Tickets;
import com.metrix.webportal.repos.EventsRepo;
import com.metrix.webportal.repos.TicketsRepo;
import com.metrix.webportal.validation.MetrixException;
import com.metrix.webportal.validation.TicketValidator;

//1. Annotation for Web MVC Controller
//2. Annotation for handling request from "/tickets"
public class TicketsController {
    private static final String VIEW_PREFIX = "tickets/";

    private static final String HARD_CODE_OPERATOR = "Dummy Operator";

    //3. Annotation for Dependency injection
    private TicketsRepo repo;

    //3. Annotation for Dependency injection
    private EventsRepo eventsRepo;

    //3. Annotation for Dependency injection
    private TicketValidator validator;

    //4a. Annotation for handling HTTP GET request from "/list/{eventId}"
    public String list(ModelMap m, /*5. Annotation for mapping the variable "eventId" in the path*/ Integer eventId) throws MetrixException{
        /*6. Find the Events from DB, if not found, throw the following exception
              throw new MetrixException(-1, "Event not found!", "/"); */        
        
        m.addAttribute("allTickets", /*7a. Get a list of tickets from events object from DB*/);
        m.addAttribute("ticketDetail", new Tickets());
        return VIEW_PREFIX + "list"; 
    }

    //4b. Annotation for handling HTTP GET request from "/create"
    public String create(ModelMap m){
        m.addAttribute("newSellForm", new SellForm());
        m.addAttribute("allEvents", /*7b. Get a list of events object from DB*/);
        return VIEW_PREFIX + "create";
    }

    //8a. Annotation for handling HTTP POST request from "/create"
    //9. Annotation for Spring managing DB transaction through out the whole method
    public String create(ModelMap m, /*9. Annotation for hibernate validation against newSellForm object*/ /*10a. Annotation for mapping HTML from body with name "newSellForm"*/ SellForm newSellForm, BindingResult result){
        //11. valid the newSellForm from validator and put the validation result in "result" object
        /*12. if binding result has error, assign suitable view objects (there are 2) and return the view name VIEW_PREFIX + "create". You can refer to line 54-56 */

        List<Tickets> ticketSold = new ArrayList<>();
        for(int i=0; i<newSellForm.getNumberOfTicket();i++){
            Tickets newTicket = new Tickets();
            newTicket.setClaimed(false);
            newTicket.setOperator(HARD_CODE_OPERATOR);
            newTicket.setSellDtm(new Date());
            newTicket.setEvent(eventsRepo.findById(newSellForm.getEventId()).get());
            //generate qr code but do while loop, you can assume it will never endless. Safe guard by only have max 100 seat
            boolean isDuplicated = true;
            do{
                String qrCode = UUID.randomUUID().toString();
                if(!repo.getTicketByQrCode(qrCode).isPresent()){
                    isDuplicated = false;
                }
                newTicket.setQrcode(qrCode);
            }while(isDuplicated);
            //13. Save the newTicket object to DB
            ticketSold.add(newTicket);
        }

        m.addAttribute("ticketSold", ticketSold);
        return VIEW_PREFIX + "ticketSold";
    }

    //4c. Annotation for handling HTTP GET request from "","/","/query"
    public String query(ModelMap m){
        m.addAttribute("ticketDetail", new Tickets());
        return VIEW_PREFIX + "query";
    }

    //8b. Annotation for handling HTTP POST request from "/detail"
    public String detail(ModelMap m, /*10b. Annotation for mapping HTML from body with name "ticketDetail"*/ Tickets queryTicket) throws MetrixException{
        if(queryTicket.getQrcode().trim().isEmpty()){
            throw new MetrixException(-1, "Ticket QR code must be provided!", "/" + VIEW_PREFIX + "query");
        }

        /*14. Find the Tickets from DB by QR Code. You should create a custom function in TicketRepo interface, with custom JPQL to filter against the qrcode field
             If not found, throw the following exception
             throw new MetrixException(-2, "Ticket not found!", "/" + VIEW_PREFIX + "query");*/        
        m.addAttribute("ticketDetail", /*15. Get the Tickets object from the return of Q.14 */);
        return VIEW_PREFIX + "detail"; 
    }

    //8c. Annotation for handling HTTP POST request from "/claim"
    public String claim(ModelMap m, /*10b. Annotation for mapping HTML from body with name "ticketDetail"*/ Tickets claimTicket) throws MetrixException{
        if(claimTicket.getQrcode().trim().isEmpty()){
            throw new MetrixException(-1, "Ticket QR code must be provided!", "/" + VIEW_PREFIX + "query");
        }

        /*14. Find the Tickets from DB by QR Code. You should create a custom function in TicketRepo interface, with custom JPQL to filter against the qrcode field
            If not found, throw the following exception
            throw new MetrixException(-2, String.format("Ticket with QR Code {%s} not found!", claimTicket.getQrcode()), "/" + VIEW_PREFIX + "query");*/    

        /*15. If the isClaimed is true for the Tickets object returned from Q.14, throw the following exception
              throw new MetrixException(-2, String.format("Ticket with QR Code {%s} already claimed!", claimTicket.getQrcode()), "/" + VIEW_PREFIX + "query");*/    

        //Set the isClaimed to true and then save to DB
        ticket.setClaimed(true);
        ticket.setClaimDtm(new Date());
        //16. Save the ticket object to DB

        m.addAttribute("ticketDetail", ticket);
        return VIEW_PREFIX + "detail";     
    }

    //4d. Annotation for handling HTTP GET request from /delete/{qrcode}
    public String delete(/*5. Annotation for mapping the variable "qrcode" in the path*/ String qrcode) throws MetrixException{
    /*14. Find the Tickets from DB by QR Code. You should create a custom function in TicketRepo interface, with custom JPQL to filter against the qrcode field
            If not found, throw the following exception
            throw new MetrixException(-2, String.format("Ticket with QR Code {%s} not found!", qrcode), "/" + VIEW_PREFIX + "query");*/    

        /*15. If the isClaimed is true for the Tickets object returned from Q.14, throw the following exception
              throw new MetrixException(-2, String.format("Ticket with QR Code {%s} already claimed!", qrcode), "/" + VIEW_PREFIX + "query");*/   
        //17. delete the record from DB by passing the ticket object
        return "redirect:/" + VIEW_PREFIX + "list/" + ticket.getEvent().getId();
    }  
}
