package com.metrix.webportal.validation;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.metrix.webportal.domains.SellForm;
import com.metrix.webportal.models.Events;
import com.metrix.webportal.repos.EventsRepo;

//1. Annotation to indicate this class is a Spring bean
@Component
public class TicketValidator implements Validator {

    @Autowired
    private EventsRepo evtRepo;

    @Override
    public boolean supports(Class<?> clazz) {
        return SellForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //1. Cast the object
        SellForm ticket = (SellForm) target; 

        Events event = null;
        Optional<Events> oEvent;
        //2. Find the events object from DB by the event id of the SellForm object
        oEvent = evtRepo.findById(ticket.getEventId());
        if(!oEvent.isPresent()){
            errors.rejectValue("eventId","","Event not found!");
                /* 3. Check whether the optional event is NOT present */
            //4. If not, assign an error messasge "Event not found!" to field eventId
        } else {
            /* 5. Check whether the event is start sell
                  If not, assign an error messasge "Event not start sell yet!" to field eventId
            */
            if(!event.isStartSell()){
                errors.rejectValue("eventId","","Event not start sell yet!");
            }
            /* 6. Check whether the Venue of the event is full. checking is
               Number of Seat < (ticket already sold for this event + number of ticket in SellForm object)
               If yes, assign an error messasge String.format("Not enough seat! {%d} seat left!", (numOfSeat - ticketSold)) to field numberOfTicket
            */
            int numberOfSeat = event.getVenue().getNumberOfSeat();
            int ticketSold = event.getTickets().size();
            if(numberOfSeat < ticketSold + ticket.getNumberOfTicket())
                errors.rejectValue("numberOfTicket", "","Not enough seat! %d seat left!");
        }
        }
    }
    
